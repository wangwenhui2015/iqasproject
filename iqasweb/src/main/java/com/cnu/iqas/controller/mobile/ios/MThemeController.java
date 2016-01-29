package com.cnu.iqas.controller.mobile.ios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.ResourceConstant;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.service.iword.WordAttributeResourceService;
import com.cnu.iqas.service.iword.WordResourceService;
import com.cnu.iqas.utils.JsonTool;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.noumenon.OntologyManage.OntologyManage;
import com.noumenon.entity.PropertyEntity;

import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2016年1月26日 下午10:16:41
* 类说明：苹果端根据服务主题
* 一个单词的显示内容：词义，句子，故事（情景），绘本，图片
*/
@Controller
@RequestMapping(value="/mobile/ios/theme/")
public class MThemeController {

	/**
	 * 单词资源服务接口
	 */
	private WordResourceService wordResourceService;
	/**
	 * 单词属性资源服务接口
	 */
	private WordAttributeResourceService wordAttributeResourceService;
	//本体操作服务接口
	private OntologyManage ontologyManage;
	/**
	 * 发现主题下的所有单词:
	 * @param theme：主题，如“20.自然-(69)山川与河流-山川”
	 * @return
	 */
	@RequestMapping(value="findBrothers")
	public ModelAndView findBrothers(String theme){
		MyStatus status =new MyStatus();
		//存放所有实体的json对象
		List<JSONObject>  listJson = new ArrayList<>();
		
		try {
			 if( theme ==null || !theme.trim().equals("")){
				List<ResultSet> resultsAllBrother = ontologyManage.QueryBrotherIndividual(theme);
				for (int i = 0; i < resultsAllBrother.size(); i++) {
					if (resultsAllBrother.get(i).hasNext()) {
						//获取一条实体记录
						QuerySolution solutionEachBrother = resultsAllBrother.get(i).next();
						//封装成实体
						PropertyEntity pe =PropertyEntity.generatePropertyEntity(solutionEachBrother);
						//生成json对象
						JSONObject peJson = JSONObject.fromObject(pe);
						listJson.add(peJson);
					} else {
						status.setStatus(StatusConstant.NOUMENON_NO_THEME);
						status.setMessage("知识本体库中没有此主题");
					}
				}
			 }else{
				   status.setStatus(StatusConstant.PARAM_ERROR);
					status.setMessage("请输入查看的主题！");
			   }
		}catch(StringIndexOutOfBoundsException soe){
			soe.printStackTrace();
			status.setStatus(StatusConstant.PARAM_ERROR);
			status.setMessage("参数有误");
		}catch (Exception e) {
			e.printStackTrace();
			status.setStatus(StatusConstant.UNKONWN_EXECPTION);
			status.setMessage("未知异常");
		}
		return JsonTool.generateModelAndView(listJson, status);
	}
	/**
	 * 根据单词查看器所有属性值
	 * @param word
	 * @return
	 */
	@RequestMapping(value="findWordProperty")
	public ModelAndView findWordProperty(String word){
		MyStatus status =new MyStatus();
		//存放所有实体的json对象
		List<JSONObject>  listJson = new ArrayList<>();
		try {
			   if( word !=null && !word.trim().equals("")){
				   ResultSet wordResults = ontologyManage.QueryIndividual(word);
					if (wordResults.hasNext()) {
						//获取一条实体记录
						QuerySolution solutionEach = wordResults.next();
						//封装成实体
						PropertyEntity pe =PropertyEntity.generatePropertyEntity(solutionEach);
						//生成json对象
						JSONObject peJson = JSONObject.fromObject(pe);
						listJson.add(peJson);
					} 
			   }else{
				   status.setStatus(StatusConstant.PARAM_ERROR);
					status.setMessage("请输入查看的单词！");
			   }
			
		}catch (Exception e) {
			e.printStackTrace();
			status.setStatus(StatusConstant.UNKONWN_EXECPTION);
			status.setMessage("未知异常");
		}

		return JsonTool.generateModelAndView(listJson, status);
	}
	/**
	 * 获取单词的图片
	 * @param wordid  单词id
	 * @return
	 */
	@RequestMapping(value="picturesOfword")
	public ModelAndView getPictures(String wordid){
		MyStatus status =new MyStatus();
		//存放所有实体的json对象
		List<JSONObject>  listJson=null;
		try {
			if( wordid!=null && !wordid.equals("")){
				listJson=getResouces(wordid,ResourceConstant.TYPE_IMAGE);
			}else{
				status.setMessage("输入查询的单词!");
				status.setStatus(StatusConstant.NOUMENON_NO_WORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatus(StatusConstant.UNKONWN_EXECPTION);
			status.setMessage("未知异常！");
		}
		return JsonTool.generateModelAndView(listJson, status);
	}
	
	/**
	 * 获取单词的绘本
	 * @param wordid
	 * @return
	 */
	@RequestMapping(value="booksOfword")
	public ModelAndView getBooks(String wordid){
		MyStatus status =new MyStatus();
		//存放所有实体的json对象
		List<JSONObject>  listJson=null;
		try {
			if( wordid!=null && !wordid.equals("")){
				listJson=getResouces(wordid,ResourceConstant.TYPE_PICTUREBOOK);
			}else{
				status.setMessage("输入查询的单词!");
				status.setStatus(StatusConstant.NOUMENON_NO_WORD);
			}
		} catch (Exception e) {
			e.printStackTrace();
			status.setStatus(StatusConstant.UNKONWN_EXECPTION);
			status.setMessage("未知异常！");
		}
		
		return JsonTool.generateModelAndView(listJson, status);
	}
    
	
	
	
	
	
	/**
	 * 获取单词资源和单词属性资源特定类型资源
	 * @param wordid
	 * @param type
	 * @return
	 */
	public List<JSONObject> getResouces(String wordid,int type){
		//存放所有实体的json对象
		List<JSONObject>  listJson = new ArrayList<>();
			//获取单词资源的图片
			List<WordResource> wrs = wordResourceService.findByWordId(wordid, ResourceConstant.TYPE_IMAGE);
		    //将单词本身资源图片资源保存到List集合中
			if(wrs!=null)
			for( WordResource wr : wrs ){
				JSONObject wrJson = new JSONObject();
				wrJson.put("resoucePath", wr.getSavepath());
				listJson.add(wrJson);
			}
			//获取单词属性资源的图片
			List<WordAttributeResource> wars= wordAttributeResourceService.findByWordId(wordid, ResourceConstant.TYPE_IMAGE);
			 //将单词属性资源图片资源保存到List集合中
			for( WordAttributeResource war : wars ){
				JSONObject warJson = new JSONObject();
				warJson.put("resoucePath", war.getSavepath());
				listJson.add(warJson);
			}
		
		return listJson;
	}
	
	public WordResourceService getWordResourceService() {
		return wordResourceService;
	}
	@Resource
	public void setWordResourceService(WordResourceService wordResourceService) {
		this.wordResourceService = wordResourceService;
	}
	public WordAttributeResourceService getWordAttributeResourceService() {
		return wordAttributeResourceService;
	}
	@Resource
	public void setWordAttributeResourceService(WordAttributeResourceService wordAttributeResourceService) {
		this.wordAttributeResourceService = wordAttributeResourceService;
	}
	public OntologyManage getOntologyManage() {
		return ontologyManage;
	}
	@Resource
	public void setOntologyManage(OntologyManage ontologyManage) {
		this.ontologyManage = ontologyManage;
	}
	
}
