/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jk.act.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.StringUtil;
import com.jk.act.service.IActProcessService;
import com.jk.base.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 流程定义相关Controller
 * @author ThinkGem
 * @version 2013-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/act/process")
public class ActProcessController extends BaseController {

	@Autowired
	private IActProcessService actProcessService;

	/**
	 * 部署流程-view
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deployView")
	public String deploy() {
		return "/admin/actProcessDeploy";
	}

	/**
	 * 部署流程-action
	 * @param file
	 * @return
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deploy")
	public @ResponseBody
	Map<String,Object> deploy(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir,
						 String category, MultipartFile file) {

		String fileName = file.getOriginalFilename();

		Map<String,Object> returnMap = new HashMap<String, Object>();
		if (StringUtils.isBlank(fileName)){
			returnMap.put("message", "请选择要部署的流程文件");
		}else{
			String message = actProcessService.deploy(exportDir, category, file);
			returnMap.put("message", message);
		}

		return returnMap;
	}

	/**
	 * 流程管理列表-view
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = {"processListView"})
	public String processListView() {
		return "/admin/actProcessList";
	}

	/**
	 * 流程定义列表
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = {"processList"})
	public  @ResponseBody
	Map<String,Object> processList(@RequestParam(value = "page", required = false) Integer page,
								   @RequestParam(value = "rows", required = false) Integer rows,
								   String category) {

		Page pageEntity = new Page(page,rows);

		System.out.println(pageEntity.getPageNum()+"-----"+pageEntity.getStartRow()+"----"+pageEntity.getPageSize());
		Map<String,Object> pageListMap = actProcessService.processListMap(pageEntity, category);

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", pageListMap.get("rows"));
		map.put("total",pageListMap.get("total"));

		return map;
	}

	/**
	 * 流程定义列表页面跳转
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = {"runninglistView"})
	public String runninglistView(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "/admin/actProcessRunningList";
	}

	/**
	 * 运行中的实例列表
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "running")
	public @ResponseBody
	Map<String,Object> runningList(String procInsId, String procDefKey, HttpServletRequest request, HttpServletResponse response, Model model) {
		String pageNum = request.getParameter("page");
		String pageSize = request.getParameter("rows");
		if(StringUtil.isEmpty(pageNum)){
			pageNum = "1";
			pageSize = "10";
		}
		Page<Object[]> page = new Page<Object[]>();
		page.setPageNum(Integer.parseInt(pageNum));
		page.setPageSize(Integer.parseInt(pageSize));

		Map<String,Object> pageListMap = actProcessService.runningList(page, procInsId, procDefKey);
		return pageListMap;
	}

	/**
	 * 读取资源，通过部署ID
	 * @param procDefId
	 * @param proInsId
	 * @param resType
	 * @param response
     * @throws Exception
     */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "resource/read")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = actProcessService.resourceRead(procDefId, proInsId, resType);
		byte[] b = new byte[1024];
		int len;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}

//
//	/**
//	 * 设置流程分类
//	 */
//	@RequiresPermissions("act:process:edit")
//	@RequestMapping(value = "updateCategory")
//	public String updateCategory(String procDefId, String category, RedirectAttributes redirectAttributes) {
//		actProcessService.updateCategory(procDefId, category);
//		return "redirect:" + adminPath + "/act/process";
//	}

	/**
	 * 挂起、激活流程实例
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "update",method = RequestMethod.POST)
	public  @ResponseBody
	Map<String,Object> updateState(String state, String procDefId, RedirectAttributes redirectAttributes) {
		String message = actProcessService.updateState(state, procDefId);
		redirectAttributes.addFlashAttribute("message", message);

		Map<String,Object> resultMap = new HashMap<String, Object>();
		resultMap.put("message",message);
		return resultMap;
	}

//	/**
//	 * 将部署的流程转换为模型
//	 * @param procDefId
//	 * @param redirectAttributes
//	 * @return
//	 * @throws UnsupportedEncodingException
//	 * @throws XMLStreamException
//	 */
//	@RequiresPermissions("act:process:edit")
//	@RequestMapping(value = "convert/toModel")
//	public String convertToModel(String procDefId, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, XMLStreamException {
//		org.activiti.engine.repository.Model modelData = actProcessService.convertToModel(procDefId);
//		redirectAttributes.addFlashAttribute("message", "转换模型成功，模型ID="+modelData.getId());
//		return "redirect:" + adminPath + "/act/model";
//	}
//
//	/**
//	 * 导出图片文件到硬盘
//	 */
//	@RequiresPermissions("act:process:edit")
//	@RequestMapping(value = "export/diagrams")
//	@ResponseBody
//	public List<String> exportDiagrams(@Value("#{APP_PROP['activiti.export.diagram.path']}") String exportDir) throws IOException {
//		List<String> files = actProcessService.exportDiagrams(exportDir);;
//		return files;
//	}
//
	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId 流程部署ID
	 */
//	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "delete")
	public @ResponseBody
	Map<String,Object> delete(String deploymentId) {
		Map<String,Object> resultMap = new HashMap<String, Object>();
		try{
			actProcessService.deleteDeployment(deploymentId);
			resultMap.put("message","操作成功");
		}catch (Exception e){
			e.printStackTrace();
			resultMap.put("message","操作失败");
		}
		return resultMap;
	}

//	/**
//	 * 删除流程实例
//	 * @param procInsId 流程实例ID
//	 * @param reason 删除原因
//	 */
//	@RequiresPermissions("act:process:edit")
//	@RequestMapping(value = "deleteProcIns")
//	public String deleteProcIns(String procInsId, String reason, RedirectAttributes redirectAttributes) {
//		if (StringUtils.isBlank(reason)){
//			addMessage(redirectAttributes, "请填写删除原因");
//		}else{
//			actProcessService.deleteProcIns(procInsId, reason);
//			addMessage(redirectAttributes, "删除流程实例成功，实例ID=" + procInsId);
//		}
//		return "redirect:" + adminPath + "/act/process/running/";
//	}
	
}
