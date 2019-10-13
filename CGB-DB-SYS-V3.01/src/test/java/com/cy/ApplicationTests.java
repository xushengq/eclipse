package com.cy;

import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cy.pj.sys.dao.SysLogDao;
import com.cy.pj.sys.entity.SysLog;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	 private SysLogDao sysLogDao;
	@Test
	public void contextLoads() throws SQLException {
	 
		int rowCount = sysLogDao.getRowCount(null);
		System.out.println(rowCount);
		
	}
	@Test
      public void testFindPageObjects() {
    	  List<SysLog> log = sysLogDao.findPageObjects("ado", 0, 3);
    	  for(SysLog logl:log) {
    		  System.out.println(logl);
    	  }
    	  
      }
	@Test
	public void deleteById() {
		int deleteObjects = sysLogDao.deleteObjects(9,10);
		System.out.println(deleteObjects);
	}
      
}
