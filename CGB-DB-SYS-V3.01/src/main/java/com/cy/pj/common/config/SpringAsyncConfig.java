package com.cy.pj.common.config;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Setter
@Configuration
@ConfigurationProperties("async-thread-pool")
public class SpringAsyncConfig implements AsyncConfigurer{
private Integer corePoolSize;
private   Integer maxPoolSize;
private  Integer  keepAliveSeconds;
private Integer queueCapacity;

//构建线程工厂(其目的主要是为池线程对象起个名字)
private ThreadFactory threadFactory=new ThreadFactory() {
	//线程安全原子操作对象(底层CAS算法，基于CPU硬件实现)
	private AtomicLong at=new AtomicLong(1);
	public Thread newThread(Runnable r) {
		
		return new Thread(r, "db-project-thread-"+at.getAndIncrement());
	};
};

@Override
	public Executor getAsyncExecutor() {
			ThreadPoolTaskExecutor pExecutor=new ThreadPoolTaskExecutor();
			  pExecutor.setCorePoolSize(corePoolSize);
		        pExecutor.setMaxPoolSize(maxPoolSize);
		        pExecutor.setKeepAliveSeconds(keepAliveSeconds);
		        pExecutor.setQueueCapacity(queueCapacity);
		       
				pExecutor.setThreadFactory(threadFactory);
		        pExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
					@Override
					public void rejectedExecution(Runnable r1, ThreadPoolExecutor executor) {
						log.error("任务已满");
					}
});
		        pExecutor.initialize();
		return pExecutor;
	}
public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
	// TODO Auto-generated method stub
	return new AsyncUncaughtExceptionHandler() {
		@Override
		public void handleUncaughtException(Throwable ex, Method method, Object... params) {
			log.error("任务执行时出现了未知的异常",ex);
		}

		
	};
}
}
