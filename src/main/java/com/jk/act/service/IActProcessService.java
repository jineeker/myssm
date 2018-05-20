/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jk.act.service;

import com.github.pagehelper.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

/**
 * 流程定义相关Controller
 * @author ThinkGem
 * @version 2013-11-03
 */
public interface IActProcessService {

	/**
	 * 流程定义列表
	 */
	public Map<String,Object> processListMap(Page<Object[]> page, String category) ;

	/**
	 * 运行中的流程定义列表
	 */
	public Map<String,Object> runningList(Page<Object[]> page, String procInsId, String procDefKey) ;

	/**
	 * 读取资源，通过部署ID
	 */
	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception ;

	/**
	 * 部署流程 - 保存
	 * @param file
	 * @return
	 */
	@Transactional(readOnly = false)
	public String deploy(String exportDir, String category, MultipartFile file) ;
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
	public String updateState(String state, String procDefId) ;

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
	public void deleteDeployment(String deploymentId) ;

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
