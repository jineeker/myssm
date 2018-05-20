package com.jk.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;
import com.jk.model.AdminRoleEntity;
import com.jk.service.AdminRoleService;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(value = "${adminPath}/role/")
public class AdminRoleController {

	@Autowired
	private AdminRoleService adminRoleService;

	@RequestMapping("roleListView.do")
	public String roleListView(){
		return "/admin/roleList";
	}

	@RequestMapping("role.do")
	public @ResponseBody Map<String,Object> role(
			@RequestParam(value = "page", required = false) Integer page,
		 	@RequestParam(value = "rows", required = false) Integer rows){

		Map<String,Object> map = new HashMap<String, Object>();
		AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
		Page resultPage = adminRoleService.getList4Page(adminRoleEntity,page,rows);

		map.put("rows", resultPage.getResult());
		map.put("total",resultPage.getTotal());
		return map;
	}

	@RequestMapping("roleUpdateView.do")
	public String roleUpdateView(@RequestParam(value = "id", required = false) String id, Model model){
		AdminRoleEntity entity = new AdminRoleEntity();
		if(StringUtil.isNotEmpty(id)){
			entity.setId(id);
			entity = adminRoleService.getEntity(entity);
		}
		model.addAttribute("entity",entity);
		return "/admin/roleUpdate";
	}

	@RequestMapping("roleSaveOrUpdate.do")
	public @ResponseBody Map<String,Object> roleSaveOrUpdate(HttpServletRequest request){
		try{
			String id = request.getParameter("id");
			String roleName = request.getParameter("roleName");

			Map<String,Object> resultMap = new HashedMap();
			if(StringUtil.isEmpty(roleName)){
				resultMap.put("success","输入有误");
				return resultMap;
			}
			AdminRoleEntity entity = new AdminRoleEntity();
			entity.setRoleName(roleName);

			if(StringUtil.isNotEmpty(id)){
				//更新
				entity.setId(id);
				if(adminRoleService.updateEntity(entity)){
					resultMap.put("success","success");
					return resultMap;
				}
			}else{
				//添加
				entity.setId(UUID.randomUUID().toString().replaceAll("-",""));
				if(adminRoleService.saveEntity(entity)){
					resultMap.put("success","success");
					return resultMap;
				}
			}

			return resultMap;

		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("roleMenuView.do")
	public String roleMenuView(@RequestParam(value = "id", required = false) String id, Model model){
		AdminRoleEntity entity = new AdminRoleEntity();
		if(StringUtil.isNotEmpty(id)){
			entity.setId(id);
			entity = adminRoleService.getEntity(entity);
		}
		model.addAttribute("entity",entity);
		return "/admin/roleMenu";
	}

	@RequestMapping("roleMenuSaveOrUpdate.do")
	public @ResponseBody Map<String,Object> roleMenuSaveOrUpdate(
			@RequestParam(value = "menuIds", required = false) String menuIds,
		   	@RequestParam(value = "roleId", required = false) String roleId,Model model){

		Map<String,Object> resultMap = new HashedMap();

		adminRoleService.deleteRoleMenu(roleId);

		String [] menuIdsArry = menuIds.split(",");
		Map roleMenuMap = null;
		List<Map> rolemenuList =  new ArrayList<Map>();
		for(String menuId:menuIdsArry){
			roleMenuMap = new HashedMap();
			roleMenuMap.put("sysRoleId",roleId);
			roleMenuMap.put("menuId",menuId);

			rolemenuList.add(roleMenuMap);
		}
		if(adminRoleService.insertRoleMenu(rolemenuList)){
			resultMap.put("success","success");
		}
		return resultMap;
	}

	@RequestMapping("rolePermView.do")
	public String rolePermView(@RequestParam(value = "id", required = false) String id, Model model){
		AdminRoleEntity entity = new AdminRoleEntity();
		if(StringUtil.isNotEmpty(id)){
			entity.setId(id);
			entity = adminRoleService.getEntity(entity);
		}
		model.addAttribute("entity",entity);
		return "/admin/rolePerm";
	}

	@RequestMapping("rolePermSaveOrUpdate.do")
	public @ResponseBody Map<String,Object> rolePermSaveOrUpdate(
			@RequestParam(value = "perms", required = false) String perms,
			@RequestParam(value = "roleId", required = false) String roleId,Model model){

		Map<String,Object> resultMap = new HashedMap();

		if(adminRoleService.deleteRolePerm(roleId)){
			if(adminRoleService.insertRolePerm(roleId,perms)){
				resultMap.put("success","success");
			}
		}

		return resultMap;
	}
}
