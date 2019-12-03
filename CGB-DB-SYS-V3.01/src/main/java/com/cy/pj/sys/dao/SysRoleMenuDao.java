package com.cy.pj.sys.dao;

import java.util.List;

/**
 * 一行记录映射为内存中的一个map对象
 * 很多优秀的产品级应用中其实不推荐
 * 直接基于做映射存储,因为第一可读性相对较差,第二他的值得类型不可控,但是有时提高开发的速度.map
 * 可直接作为映射存储对象,尤其是一些外包项目
 *  //描述数据层接口对象
//表示此接口对象的实现类由系统底层自动创建,并且会在此接口的实现类中注入SqlSessionFactory对象
 */


import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {
  @Delete("delete from sys_role_menus where menu_id=#{menuId}")
		int deleteObjectsByMenuId(Integer menuId);
  
  @Delete("delete from sys_role_menus where role_Id=#{roleId}")
   int deleteObjectsByRoleId(Integer roleId);
  
  int insertObjects(@Param("roleId")Integer roleId,@Param("menuIds")Integer[] menuIds);
  
  List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);

}
