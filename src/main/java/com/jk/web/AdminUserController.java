package com.jk.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;
import com.jk.model.AdminRoleEntity;
import com.jk.model.AdminUserAndRoleEntity;
import com.jk.model.AdminUsersEntity;
import com.jk.service.AdminRoleService;
import com.jk.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "${adminPath}/user/")
public class AdminUserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminRoleService adminRoleService;

	/**
	 * 页面跳转至查询用户信息
	 * @return
     */
//	@RequiresPermissions("aa:bb")
	@RequestMapping("userListView.do")
	public String userView(){
		return "/admin/userList";
	}

	/**
	 * 查询用户信息
     * @return
     */
	@RequestMapping("user.do")
	public @ResponseBody Map<String,Object> user(
			@RequestParam(value = "page", required = false) Integer page,
		 	@RequestParam(value = "rows", required = false) Integer rows){

		Map<String,Object> map = new HashMap<String, Object>();
		AdminUsersEntity adminUsersEntity = new AdminUsersEntity();
		Page resultPage = userService.getList4Page(adminUsersEntity,page,rows);

		map.put("rows", resultPage.getResult());
		map.put("total",resultPage.getTotal());
		return map;
	}

	/**
	 * 修改或添加用户
	 * @return
	 */
	@RequestMapping("userUpdateView.do")
	public String userUpdateView(@RequestParam(value = "id", required = false) String id, Model model){
		AdminUsersEntity entity = new AdminUsersEntity();
		if(StringUtil.isNotEmpty(id)){
			entity.setId(id);
			entity = userService.getEntity(entity);
		}
		//查询角色列表
		AdminRoleEntity adminRoleEntity = new AdminRoleEntity();
		adminRoleEntity.setAvailable("1");
		Page rolePage = adminRoleService.getList4Page(adminRoleEntity,1,100);

		model.addAttribute("entity",entity);
		model.addAttribute("roleList",rolePage.getResult());
		return "/admin/userUpdate";
	}

	/**
	 * 页面跳转至修改用户
	 * @return
	 */
	@RequestMapping("userSaveOrUpdate.do")
	public @ResponseBody Map<String,Object> userSaveOrUpdate(HttpServletRequest request){
		try{
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String roleId = request.getParameter("roleId");
			String locked = request.getParameter("locked");

			Map<String,Object> resultMap = new HashedMap();

			AdminUsersEntity entity = new AdminUsersEntity();
			entity.setLocked(locked);

			if(!StringUtil.isEmpty(password)){
				//生成随机盐
				Integer salt = (int)(Math.random()*1000);
				String newPassWord=new SimpleHash("MD5",password,salt.toString(),2).toHex();
				entity.setPasswordSalt(salt.toString());
				entity.setPassword(newPassWord);
			}

			if(StringUtil.isNotEmpty(id)){
				//更新
				entity.setId(id);
				if(userService.updateEntity(entity)){
					resultMap.put("success","success");
				}
			}else{
				//添加
				if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
					resultMap.put("success","输入有误");
					return resultMap;
				}
				entity.setUsername(username);
				if(userService.saveEntity(entity)){
					resultMap.put("success","success");
				}
			}

			//更新用户角色,先删除再插入
			AdminUserAndRoleEntity adminUserAndRoleEntity = new AdminUserAndRoleEntity();
			adminUserAndRoleEntity.setId(UUID.randomUUID().toString().replaceAll("-",""));
			adminUserAndRoleEntity.setSysUserId(entity.getId());
			adminUserAndRoleEntity.setSysRoleId(roleId);
			userService.removeUserAndRole(adminUserAndRoleEntity);
			userService.updateUserAndRole(adminUserAndRoleEntity);

			return resultMap;

		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
