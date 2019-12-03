package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.sys.entity.SysRole;
import com.cy.pj.vo.SysRoleMenuVo;

@Mapper
public interface SysRoleDao {

	
	int insertObject(SysRole sysRole);
	
	int getRowCount(@Param("name")String name);
	
	@Delete("delete from sys_roles where id=#{id}")
	int deleteObject(Integer id);

	List<SysRole> findPageObjects(
			@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
			
			
	SysRoleMenuVo findObjectById(Integer id);
	
	int updateObject(SysRole sysRole);
	@Select("select id,name  from sys_roles")
	List<CheckBox> findObjects();
}
