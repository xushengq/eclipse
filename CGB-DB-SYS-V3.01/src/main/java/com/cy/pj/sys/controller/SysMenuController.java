package com.cy.pj.sys.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cy.pj.common.vo.JsonResult;

import com.cy.pj.sys.entity.SysMenu;
import com.cy.pj.sys.service.SysMenuService;

@RestController
@RequestMapping("/menu")
public class SysMenuController {
  @Autowired
	private SysMenuService sysMenuService;
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		
		List<Map<String, Object>> findObjects = sysMenuService.findObjects();
		System.out.println("啦啦");
		return new JsonResult(findObjects);
	}
	 
	 @RequestMapping("doDeleteObject")
	   public JsonResult doDeleteObject(Integer id){
		  sysMenuService.deleteObjects(id);
		  return new JsonResult("delete ok");
	  }
	 @RequestMapping("doFindZtreeMenuNodes")
	  public JsonResult findZtreeMenuNodes() {
		return new JsonResult(sysMenuService.findZtreeMenuNodes());
	 }
	 @RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysMenu sysMenu) {
		sysMenuService.insertObject(sysMenu);
		return new JsonResult("insert ok");
		
	}
	 @RequestMapping("doUpdateObject")
		public JsonResult doUpdateObject(SysMenu sysMenu) {
			sysMenuService.updateObject(sysMenu);
			return new JsonResult("Update ok");
			
		}
	
}
