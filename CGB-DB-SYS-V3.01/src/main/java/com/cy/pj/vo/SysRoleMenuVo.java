package com.cy.pj.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
/**
 * 
 * 通过此对象封装基于角色ID查到的角色信息以及对应的菜单id
 * @author Administrator
 *
 */
@Data
public class SysRoleMenuVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6048591348050051105L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;

}
