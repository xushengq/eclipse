package com.cy.pj.sys.dao;

/**
 * 一行记录映射为内存中的一个map对象
 * 很多优秀的产品级应用中其实不推荐
 * 直接基于做映射存储,因为第一可读性相对较差,第二他的值得类型不可控,但是有时提高开发的速度.map
 * 可直接作为映射存储对象,尤其是一些外包项目
 *  //描述数据层接口对象
//表示此接口对象的实现类由系统底层自动创建,并且会在此接口的实现类中注入SqlSessionFactory对象
 */
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.Node;
import com.cy.pj.sys.entity.SysMenu;

@Mapper
public interface SysMenuDao {
   
	List<Map<String,Object>> findObjects();
	@Select("select count(*) from sys_menus where parentId=#{id}")
	int getChildCount(Integer id);
	@Delete("delete from sys_menus where id=#{id}")
	int deleteObject(Integer id);
	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	
	int insertObject(SysMenu sysMenu);
	int updateObject(SysMenu sysMenu);
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);

	
}
