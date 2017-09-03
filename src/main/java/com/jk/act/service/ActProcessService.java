/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jk.act.service;

import com.github.pagehelper.Page;
import com.jk.act.entity.ProcessDefinitionEntity;
import com.jk.act.entity.ProcessInstanceEntity;
import com.jk.base.BaseService;
import org.activiti.engine.ActivitiException;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * 流程定义相关Controller
 * @author ThinkGem
 * @version 2013-11-03
 */
@Service
@Transactional(readOnly = true)
public class ActProcessService extends BaseService {

	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;

	/**
	 * 流程定义列表
	 */
	public Map<String,Object> processListMap(Page<Object[]> page, String category) {

	    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
	    		.latestVersion().orderByProcessDefinitionKey().asc();
	    
	    if (StringUtils.isNotEmpty(category)){
	    	processDefinitionQuery.processDefinitionCategory(category);
		}
	    List<ProcessDefinitionEntity> pdList = new ArrayList<ProcessDefinitionEntity>();
	    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(page.getStartRow(), page.getPageSize());
	    for (ProcessDefinition processDefinition : processDefinitionList) {
			String deploymentId = processDefinition.getDeploymentId();
			Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();

			//封装到ProcessDefinitionEntity中
			ProcessDefinitionEntity	pd = new ProcessDefinitionEntity();
			pd.setId(processDefinition.getId());
			pd.setName(processDefinition.getName());
			pd.setKey(processDefinition.getKey());
			pd.setDeploymentId(processDefinition.getDeploymentId());
			pd.setVersion(processDefinition.getVersion());
			pd.setResourceName(processDefinition.getResourceName());
			pd.setDiagramResourceName(processDefinition.getDiagramResourceName());
			pd.setDeploymentTime(deployment.getDeploymentTime());
			pd.setSuspended(processDefinition.isSuspended());

			pdList.add(pd);
	    }

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows",pdList);
		map.put("total",processDefinitionQuery.count());
		return map;
	}

	/**
	 * 运行中的流程定义列表
	 */
	public Map<String,Object> runningList(Page<Object[]> page, String procInsId, String procDefKey) {

	    ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

	    if (StringUtils.isNotBlank(procInsId)){
		    processInstanceQuery.processInstanceId(procInsId);
	    }

	    if (StringUtils.isNotBlank(procDefKey)){
		    processInstanceQuery.processDefinitionKey(procDefKey);
	    }

		List<ProcessInstanceEntity> piList = new ArrayList<ProcessInstanceEntity>();
		List<ProcessInstance> processInstanceList = processInstanceQuery.listPage(page.getStartRow(), page.getPageSize());
		for(ProcessInstance processInstance:processInstanceList){
			ProcessInstanceEntity pi = new ProcessInstanceEntity();
			pi.setId(processInstance.getId());
			pi.setProcessInstanceId(processInstance.getProcessInstanceId());
			pi.setProcessDefinitionId(processInstance.getProcessDefinitionId());
			pi.setActivityId(processInstance.getActivityId());
			pi.setSuspended(processInstance.isSuspended());

//			ProcessDefinitionCache.setRepositoryService(this.repositoryService);
//			String taskName = ProcessDefinitionCache.getActivityName(processInstance.getProcessDefinitionId(), processInstance.getActivityId());
//			pi.setTaskName(taskName);
			piList.add(pi);
		}

		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows",piList);
		map.put("total",processInstanceQuery.count());
		return map;
	}

	/**
	 * 读取资源，通过部署ID
	 * @param processDefinitionId  流程定义ID
	 * @param processInstanceId 流程实例ID
	 * @param resourceType 资源类型(xml|image)
	 */
	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {

		if (StringUtils.isBlank(procDefId)){
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId).singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		return resourceAsStream;
	}

	/**
	 * 部署流程 - 保存
	 * @param file
	 * @return
	 */
	@Transactional(readOnly = false)
	public String deploy(String exportDir, String category, MultipartFile file) {

		String message = "";

		String fileName = file.getOriginalFilename();

		try {
			InputStream fileInputStream = file.getInputStream();
			Deployment deployment;
			String extension = FilenameUtils.getExtension(fileName);
			if (extension.equals("zip") || extension.equals("bar")) {
				ZipInputStream zip = new ZipInputStream(fileInputStream);
				deployment = repositoryService.createDeployment().addZipInputStream(zip).deploy();
			} else if (extension.equals("png")) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (fileName.indexOf("bpmn20.xml") != -1) {
				deployment = repositoryService.createDeployment().addInputStream(fileName, fileInputStream).deploy();
			} else if (extension.equals("bpmn")) { // bpmn扩展名特殊处理，转换为bpmn20.xml
				String baseName = FilenameUtils.getBaseName(fileName);
				deployment = repositoryService.createDeployment().addInputStream(baseName + ".bpmn20.xml", fileInputStream).deploy();
			} else {
				message = "不支持的文件类型：" + extension;
				return message;
			}

			List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();

			// 设置流程分类
			for (ProcessDefinition processDefinition : list) {
//					ActUtils.exportDiagramToFile(repositoryService, processDefinition, exportDir);
				repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
				message += "部署成功，流程ID=" + processDefinition.getId() + "<br/>";
			}

			if (list.size() == 0){
				message = "部署失败，没有流程。";
			}

		} catch (Exception e) {
			throw new ActivitiException("部署失败！", e);
		}
		return message;
	}
//
//	/**
//	 * 设置流程分类
//	 */
//	@Transactional(readOnly = false)
//	public void updateCategory(String procDefId, String category) {
//		repositoryService.setProcessDefinitionCategory(procDefId, category);
//	}
//
	/**
	 * 挂起、激活流程实例
	 */
	@Transactional(readOnly = false)
	public String updateState(String state, String procDefId) {
		if (state.equals("active")) {
			repositoryService.activateProcessDefinitionById(procDefId, true, null);
			return "已激活ID为[" + procDefId + "]的流程定义。";
		} else if (state.equals("suspend")) {
			repositoryService.suspendProcessDefinitionById(procDefId, true, null);
			return "已挂起ID为[" + procDefId + "]的流程定义。";
		}
		return "无操作";
	}

//	/**
//	 * 将部署的流程转换为模型
//	 * @param procDefId
//	 * @throws UnsupportedEncodingException
//	 * @throws XMLStreamException
//	 */
//	@Transactional(readOnly = false)
//	public org.activiti.engine.repository.Model convertToModel(String procDefId) throws UnsupportedEncodingException, XMLStreamException {
//
//		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(procDefId).singleResult();
//		InputStream bpmnStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
//		processDefinition.getResourceName());
//		XMLInputFactory xif = XMLInputFactory.newInstance();
//		InputStreamReader in = new InputStreamReader(bpmnStream, "UTF-8");
//		XMLStreamReader xtr = xif.createXMLStreamReader(in);
//		BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);
//
//		BpmnJsonConverter converter = new BpmnJsonConverter();
//		ObjectNode modelNode = converter.convertToJson(bpmnModel);
//		org.activiti.engine.repository.Model modelData = repositoryService.newModel();
//		modelData.setKey(processDefinition.getKey());
//		modelData.setName(processDefinition.getResourceName());
//		modelData.setCategory(processDefinition.getCategory());//.getDeploymentId());
//		modelData.setDeploymentId(processDefinition.getDeploymentId());
//		modelData.setVersion(Integer.parseInt(String.valueOf(repositoryService.createModelQuery().modelKey(modelData.getKey()).count()+1)));
//
//		ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
//		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
//		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, modelData.getVersion());
//		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
//		modelData.setMetaInfo(modelObjectNode.toString());
//
//		repositoryService.saveModel(modelData);
//
//		repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
//
//		return modelData;
//	}
//
//	/**
//	 * 导出图片文件到硬盘
//	 */
//	public List<String> exportDiagrams(String exportDir) throws IOException {
//		List<String> files = new ArrayList<String>();
//		List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().list();
//
//		for (ProcessDefinition processDefinition : list) {
//			String diagramResourceName = processDefinition.getDiagramResourceName();
//			String key = processDefinition.getKey();
//			int version = processDefinition.getVersion();
//			String diagramPath = "";
//
//			InputStream resourceAsStream = repositoryService.getResourceAsStream(
//					processDefinition.getDeploymentId(), diagramResourceName);
//			byte[] b = new byte[resourceAsStream.available()];
//
//			@SuppressWarnings("unused")
//			int len = -1;
//			resourceAsStream.read(b, 0, b.length);
//
//			// create file if not exist
//			String diagramDir = exportDir + "/" + key + "/" + version;
//			File diagramDirFile = new File(diagramDir);
//			if (!diagramDirFile.exists()) {
//				diagramDirFile.mkdirs();
//			}
//			diagramPath = diagramDir + "/" + diagramResourceName;
//			File file = new File(diagramPath);
//
//			// 文件存在退出
//			if (file.exists()) {
//				// 文件大小相同时直接返回否则重新创建文件(可能损坏)
//				logger.debug("diagram exist, ignore... : {}", diagramPath);
//
//				files.add(diagramPath);
//			} else {
//				file.createNewFile();
//				logger.debug("export diagram to : {}", diagramPath);
//
//				// wirte bytes to file
//				FileUtils.writeByteArrayToFile(file, b, true);
//
//				files.add(diagramPath);
//			}
//
//		}
//
//		return files;
//	}

	/**
	 * 删除部署的流程，级联删除流程实例
	 * @param deploymentId 流程部署ID
	 */
	@Transactional(readOnly = false)
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId, true);
	}

//	/**
//	 * 删除部署的流程实例
//	 * @param procInsId 流程实例ID
//	 * @param deleteReason 删除原因，可为空
//	 */
//	@Transactional(readOnly = false)
//	public void deleteProcIns(String procInsId, String deleteReason) {
//		runtimeService.deleteProcessInstance(procInsId, deleteReason);
//	}
	
}
