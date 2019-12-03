package com.cy.pj.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cy.pj.common.exception.ServiceException;
import com.cy.pj.common.vo.CheckBox;
import com.cy.pj.common.vo.PageObject;

import com.cy.pj.sys.service.SysRoleService;
import com.cy.pj.vo.SysRoleMenuVo;
import com.cy.pj.sys.dao.SysRoleDao;
import com.cy.pj.sys.dao.SysRoleMenuDao;
import com.cy.pj.sys.dao.SysUserRoleDao;
import com.cy.pj.sys.entity.SysRole;

@Service
public class SysRoleServiceImpl implements SysRoleService {
  
	@Autowired
	private SysRoleDao  sysRoleDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Override
	public PageObject<SysRole> findPageObjects(String name, Integer pageCurrent) {
		if(pageCurrent==null||pageCurrent<1)
		 throw new IllegalArgumentException("当前页码不正确");
		int rowCount = sysRoleDao.getRowCount(name);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		int pageSize=3;
		int startIntex=(pageCurrent-1)*pageSize;
		List<SysRole> records=sysRoleDao.findPageObjects(name, startIntex, pageSize);
		  PageObject<SysRole> pageObject=new PageObject<>();

		  pageObject.setPageCurrent(pageCurrent);
		  pageObject.setPageSize(pageSize);
		  pageObject.setRowCount(rowCount);
		  pageObject.setRecords(records);
          pageObject.setPageCount((rowCount-1)/pageSize+1);
		  //5.返回封装结果。
		  return pageObject;

	}
	@Override
	public int deleteObject(Integer id) {
		if(id==null||id<0)
			throw new ServiceException("id的值不正确,id="+id);
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectsByRoleId(id);
		int rows=sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("数据可能已经不存在");
		return rows;

	}
	public int saveObject(SysRole entity,Integer[] menuIds) {
    	//1.合法性验证
    	if(entity==null)
    throw new ServiceException("保存数据不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
   	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色赋予权限");
    	//2.保存数据
    	int rows=sysRoleDao.insertObject(entity);
    	if(rows==0)
    		throw new ServiceException("保存数据失败");
    	sysRoleMenuDao.insertObjects(
    			entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
 }
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
    if(id==null||id<1)
    	throw new IllegalArgumentException("Id值无效");
       SysRoleMenuVo rm=sysRoleDao.findObjectById(id);
      if(rm==null)
	 throw new ServiceException("记录可能不存在");
	return rm;
	}
	@Override
	public int updateObject(SysRole entity, Integer[] menuIds) {
		//1.合法性验证
    	if(entity==null)
    throw new ServiceException("保存数据不能为空");
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
   	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色赋予权限");
    	//2.保存数据
    	int rows=sysRoleDao.updateObject(entity);
    	if(rows==0)
    		throw new ServiceException("保存数据失败");
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	sysRoleMenuDao.insertObjects(
    			entity.getId(),menuIds);
    	//3.返回结果
    	return rows;
	}
	@Override
    public List<CheckBox> findObjects() {
     	
		return sysRoleDao.findObjects();
    }

}
