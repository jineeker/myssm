package com.jk.web;

import com.jk.model.AdminMenuEntity;
import com.jk.model.AdminUsersEntity;
import com.jk.service.AdminMenuService;
import com.jk.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 加载后台菜单
 * @Author hukai
 * @Email 614811431@qq.com
 * @Date 2017-10-27 16:23
 */
@Controller
@RequestMapping(value = "${adminPath}/index")
public class AdminMenuController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminMenuService adminMenuService;

	private static final Logger LOGGER = Logger.getLogger(AdminMenuController.class);

	@RequestMapping("loadingMenu.do")
	public @ResponseBody String loadingMenu(HttpServletRequest req, HttpServletResponse resp){

		AdminUsersEntity adminUsersEntity = new AdminUsersEntity();
		adminUsersEntity.setUsername(SecurityUtils.getSubject().getPrincipal().toString());
		adminUsersEntity = userService.getEntity(adminUsersEntity);

		List<AdminMenuEntity> list = adminMenuService.getMenuList(adminUsersEntity);
		StringBuilder result = new StringBuilder();
		result.append("{\"menus\":" + "[");

		List<AdminMenuEntity> listP = new ArrayList<AdminMenuEntity>();
		List<AdminMenuEntity> listOther = new ArrayList<AdminMenuEntity>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getMenuPid().equals("0")){
				listP.add(list.get(i));
			}else{
				listOther.add(list.get(i));
			}
		}
		for(int i=0;i<listP.size();i++){
			//根节点菜单
			result.append("{\"menuid\":\"" + listP.get(i).getMenuId()
					+ "\",\"icon\":\"" + listP.get(i).getMenuIcon()
					+ "\",\"menuname\":\"" + listP.get(i).getMenuName() + "\"");

			//构建第一级子菜单
			StringBuilder sb = new StringBuilder();
			createFirstSon4Tree(sb,listOther,listP.get(i));
			result.append(sb.toString());
			result.append("},");
		}
		String temp = result.toString().substring(0,result.length()-1);
		result.delete( 0, result.length() );
		result.append(temp);
		result.append("]}");

		return result.toString();
	}

	//构建一级子菜单
	public static void createFirstSon4Tree(StringBuilder sb, List<AdminMenuEntity> list, AdminMenuEntity menuEntityP){

		//子菜单列表
		List<AdminMenuEntity> listS = new ArrayList<AdminMenuEntity>();
		List<AdminMenuEntity> listOther = new ArrayList<AdminMenuEntity>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getMenuPid().equals(menuEntityP.getMenuId())){
				listS.add(list.get(i));
			}else{
				listOther.add(list.get(i));
			}
		}

		if(listS.size()>0){
			sb.append(",\"menus\":[");
			for(int j=0;j<listS.size();j++){

				sb.append("{\"menuid\":\"" + listS.get(j).getMenuId()
						+ "\",\"menuname\":\"" + listS.get(j).getMenuName()
						+ "\",\"icon\":\"" + listS.get(j).getMenuIcon()
						+ "\",\"url\":\"" + listS.get(j).getMenuUrl()+ "\"");
				createSecondSon4Tree(sb,listOther,listS.get(j));
				sb.append("},");
			}
			String temp = sb.toString().substring(0,sb.length()-1);
			sb.delete( 0, sb.length() );
			sb.append(temp);
			sb.append("]");
		}

	}

	//构建二级子菜单
	public static void createSecondSon4Tree(StringBuilder sb, List<AdminMenuEntity> list, AdminMenuEntity menuEntityP){

		//子菜单列表
		List<AdminMenuEntity> listS = new ArrayList<AdminMenuEntity>();
		List<AdminMenuEntity> listOther = new ArrayList<AdminMenuEntity>();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getMenuPid().equals(menuEntityP.getMenuId())){
				listS.add(list.get(i));
			}else{
				listOther.add(list.get(i));
			}
		}

		if(listS.size()>0){
			sb.append(",\"child\":[");
			for(int j=0;j<listS.size();j++){

				sb.append("{\"menuid\":\"" + listS.get(j).getMenuId()
						+ "\",\"menuname\":\"" + listS.get(j).getMenuName()
						+ "\",\"icon\":\"" + listS.get(j).getMenuIcon()
						+ "\",\"url\":\"" + listS.get(j).getMenuUrl()+ "\"");

				sb.append("},");
			}
			String temp = sb.toString().substring(0,sb.length()-1);
			sb.delete( 0, sb.length() );
			sb.append(temp);
			sb.append("]");
		}
	}


	/**
	 * 跳转到菜单列表页面
	 * @return
	 */
	@RequiresPermissions(value = {"aa:bb"})
	@RequestMapping(value = "menuListView.do")
	public String menuListView(){
		return "admin/menuList";
	}

	/**
	 * 菜单列表
	 * @return
	 */
	@RequestMapping(value = "getAllMenuList.do")
	public @ResponseBody Map<String,Object> menuList(){

		Map<String,Object> map = new HashedMap();
		AdminMenuEntity adminMenuEntity = new AdminMenuEntity();
		List<AdminMenuEntity> adminMenuEntityList= adminMenuService.getAllMenuList(new AdminUsersEntity());

		List<AdminMenuEntity> adminMenuEntityListPar = new ArrayList<AdminMenuEntity>();
		for(int i=0;i<adminMenuEntityList.size();i++){
			if("0".equals(adminMenuEntityList.get(i).getMenuPid())){
				adminMenuEntityListPar.add(adminMenuEntityList.get(i));
			}
		}
		for(int j=0;j<adminMenuEntityListPar.size();j++){
			List<AdminMenuEntity> adminMenuEntityListSon = new ArrayList<AdminMenuEntity>();
			for(int i=0;i<adminMenuEntityList.size();i++){
				if(adminMenuEntityList.get(i).getMenuPid()
						.equals(adminMenuEntityListPar.get(j).getMenuId())){
					adminMenuEntityListSon.add(adminMenuEntityList.get(i));
				}
			}
			adminMenuEntityListPar.get(j).setChildren(adminMenuEntityListSon);
		}

		map.put("rows", adminMenuEntityListPar);
		return map;
	}

	/**
	 * 菜单修改页面
	 * @return
	 */
	@RequiresPermissions(value = {"ccc"})
	@RequestMapping(value = "menuUpdateView.do")
	public String menuUpdateView(@RequestParam(value = "menuId", required = false) String menuId, Model model){
		AdminMenuEntity entity = new AdminMenuEntity();
		if(null!=menuId&&!"".equals(menuId)){
			entity.setMenuId(menuId);
			entity = adminMenuService.getEntity(entity);
		}

		List<AdminMenuEntity> list4Pentity = adminMenuService.getMenu4UpdateList();

		model.addAttribute("entity",entity);
		model.addAttribute("list4Pentity",list4Pentity);
		return "/admin/menuUpdate";
	}

	/**
	 * 菜单修改
	 * @return
	 */
	@RequestMapping(value = "menuSaveOrUpdate.do")
	public  @ResponseBody Map<String,Object> menuSaveOrUpdate(HttpServletRequest request){

		String menuId = request.getParameter("menuId");//菜单id
		String menuName = request.getParameter("menuName");//菜单名称
		String menuIcon = request.getParameter("menuIcon");//菜单图标
		String menuUrl = request.getParameter("menuUrl");//访问路径
		String menuPid = request.getParameter("menuPid");//父菜单id
		String menuSort = request.getParameter("menuSort");//排序标识

		AdminMenuEntity entity = new AdminMenuEntity();
		entity.setMenuId(menuId);
		entity.setMenuName(menuName);

		if(StringUtils.isNoneEmpty(menuIcon)){
			entity.setMenuIcon(menuIcon);
		}else{
			entity.setMenuIcon("icon-sys");
		}
		if(StringUtils.isNotEmpty(menuUrl)){
			entity.setMenuUrl(menuUrl);
		}
		entity.setMenuPid(menuPid);
		entity.setMenuSort(Integer.parseInt(menuSort));

		boolean isSuccess;
		if(StringUtils.isEmpty(menuId)){
			if(StringUtils.isEmpty(menuPid)){
				entity.setMenuPid("0");
			}
			entity.setMenuId(UUID.randomUUID().toString().replaceAll("-",""));
			isSuccess = adminMenuService.saveEntity(entity);
		}else{
			isSuccess = adminMenuService.updateEntity(entity);
		}


		Map<String,Object> resultMap = new HashedMap();
		resultMap.put("success",isSuccess?"success":"修改失败，请稍候再试");
		return resultMap;
	}
}
