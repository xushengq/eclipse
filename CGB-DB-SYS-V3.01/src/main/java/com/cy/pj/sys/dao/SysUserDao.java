package com.cy.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.cy.pj.sys.entity.SysUser;
import com.cy.pj.vo.SysUserDeptVo;

@Mapper
public interface SysUserDao {
	
	SysUser findUserByUserName(String username);

	int updateObject(SysUser entity);
	
	int insertObject(SysUser entity);
	
	
	SysUserDeptVo findObjectById(Integer id);
	
	
	
	@Select("select username from sys_users")
	String[] getName();
	
	
	int getRowCount(@Param("username")String username);
	
	List<SysUserDeptVo> findPageObjects(
		      @Param("username")String  username,
		      @Param("startIndex")Integer startIndex,
		      @Param("pageSize")Integer pageSize);
   /**
    * 用户禁用启用
    * 
    */
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);

}
