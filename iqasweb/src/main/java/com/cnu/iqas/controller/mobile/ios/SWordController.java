package com.cnu.iqas.controller.mobile.ios;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordAttributeResource;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.ResourceConstant;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.enumtype.WordAttributeEnum;
import com.cnu.iqas.service.common.IFindWordInNoumenon;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.service.iword.WordAttributeResourceService;
import com.cnu.iqas.service.iword.WordResourceService;
import com.cnu.iqas.utils.JsonTool;
import com.cnu.iqas.utils.WebUtils;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.noumenon.OntologyManage.OntologyManage;
import com.noumenon.entity.PropertyEntity;

import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2016年3月2日 下午7:48:51
* 类说明
*/
@Controller
@RequestMapping(value="/mobile/ios/word/")
public class SWordController {

	//本体操作服务接口
	private OntologyManage ontologyManage;
	/**
	 * 查询单词本身资源服务
	 */
	private WordResourceService wordResourceService;
	/**
	 * 查询单词相关属性资源服务接口
	 */
	private WordAttributeResourceService wordAttributeResourceService;
/**
 *    
 * @param text  查询的单词
 * @return
{
  "result": {
    "word": {
      "wordId": "5/3/5/4",
      "word": "boat",
      "meaning": "ship(船)@1.There is a boat on the lake.@1.Look! There is a boat on the lake. 2.I like boats.@A:How are you going to the island?(你们怎么去岛上？) B:We're going by boat.(我们坐船去。)",
      "picture": "ifilesystem/noumenon/wordresource/images/1ea15867-e6c4-4b44-9f3c-0c54d46f424b.jpg",
      "dialogue": "ifilesystem/noumenon/wordresource/images/574bc948-cccb-4052-8681-3985739f289c.jpg@ifilesystem/noumenon/wordresource/images/c5dba7f9-5c50-4472-bda2-fe4ddf9bebb5.jpg@ifilesystem/noumenon/wordresource/images/bb81a8c4-6812-4c16-92bc-4f4857eaddca.jpg"
    }
  },
  "status": 1,
  "message": "ok"
}
 */
	@RequestMapping(value="findWord")
	public ModelAndView findWord(String text){
		//分隔符
		 String SPLIT_CHAR="@";
		//单词json
		JSONObject wordJson=new JSONObject();
		MyStatus status = new MyStatus();
		
		StringBuffer wordId= new StringBuffer();
		//存放多个版本的词义
		StringBuffer meanings= new StringBuffer();
		//按钮2:单词本身的图片（图片）
		StringBuffer pictures= new StringBuffer();
		//按钮3:与单词有关的句子
		StringBuffer sentences= new StringBuffer();
		//按钮4:与单词有关的课文段落（图片）
		StringBuffer dialogues= new StringBuffer();
		//按钮5:与单词有关的动画（视频）
		StringBuffer videos= new StringBuffer();
		//按钮6:与单词有关的绘本（图片）
		StringBuffer picturebooks= new StringBuffer();
		
		if( !WebUtils.isNull(text)){
			try {
				// 查该单词对应所有ID的结果集
				ResultSet resultsIdofOneWord= ontologyManage.QueryIndividualAllId(text);
				
				if (resultsIdofOneWord.hasNext()) {
					//将多个版本的相同单词封装成json
					while (resultsIdofOneWord.hasNext()) {
						QuerySolution solutionInstance = resultsIdofOneWord.next();
						//找出该单词的对应的所有ID
						ResultSet resultsAllPropertyOfThisId = ontologyManage.QueryIndividualDependOnId(solutionInstance.get("?propertyID").toString());
						QuerySolution solutionAllPropertyOfThisId = resultsAllPropertyOfThisId.next();
						//从本体中解析出一个包含单词的23个属性的实体
						PropertyEntity pe =PropertyEntity.generatePropertyEntity(solutionAllPropertyOfThisId);
						//将一个单词的数据封装成要求的json格式
						if( pe !=null){
							wordId.append(pe.getPropertyID()+SPLIT_CHAR);
							//1.词义
							//中文意思
							String mean = pe.getPropertySynonyms();
							//2.与单词有关的句子
							//课文原句
							String sentence = pe.getPropertyText();
							//情景段落
							String scene = pe.getPropertyScene();
							//延伸例句
							String extend = pe.getPropertyExtend();
							
							meanings.append(mean).append(SPLIT_CHAR).append(sentence)
							        .append(SPLIT_CHAR).append(scene).append(SPLIT_CHAR)
									.append(extend).append(SPLIT_CHAR);
							
							//3.单词本身的图片（图片）
							List<WordResource> wResouces= wordResourceService.findByWordId(pe.getPropertyID(), ResourceConstant.TYPE_IMAGE);
							
							for( WordResource wr : wResouces)
								pictures.append(wr.getSavepath()+SPLIT_CHAR);
							
							//4.单词有关的课文段落（图片）
							List<WordAttributeResource> wAtResouces =wordAttributeResourceService.find(pe.getPropertyID(), WordAttributeEnum.PROPERTYSCENE, ResourceConstant.TYPE_IMAGE);
							
							for( WordAttributeResource wr : wAtResouces)
								dialogues.append(wr.getSavepath()+SPLIT_CHAR);
							
							//5:与单词有关的动画（视频）
							   //5.1自身的相关视频
							List<WordResource> ownVideos= wordResourceService.findByWordId(pe.getPropertyID(), ResourceConstant.TYPE_VIDEO);
							   //5.2属性相关的视频
							List<WordAttributeResource> attrVideos =wordAttributeResourceService.findByWordId(pe.getPropertyID(), ResourceConstant.TYPE_VIDEO);
							
							for( WordResource wr : ownVideos)
								videos.append(wr.getSavepath()+SPLIT_CHAR);
							
							for( WordAttributeResource wr : attrVideos)
								videos.append(wr.getSavepath()+SPLIT_CHAR);
							
							//6:与单词有关的绘本（图片）
								//6.1自身的相关绘本
							List<WordResource> ownBooks= wordResourceService.findByWordId(pe.getPropertyID(), ResourceConstant.TYPE_PICTUREBOOK);
							   //6.2属性相关的绘本
							List<WordAttributeResource> attrBooks =wordAttributeResourceService.findByWordId(pe.getPropertyID(), ResourceConstant.TYPE_PICTUREBOOK);
							
							for( WordResource wr : ownBooks)
								picturebooks.append(wr.getSavepath()+SPLIT_CHAR);
							
							for( WordAttributeResource wr : attrBooks)
								picturebooks.append(wr.getSavepath()+SPLIT_CHAR);
						}
						
					}
				}
				//去掉最后的逗号
				String wordIdstr= wordId.length()>0?wordId.toString().substring(0, wordId.length()-1):null;
				String wordstr = text;
				String meaning= meanings.length()>0?meanings.toString().substring(0, meanings.length()-1):null;
				String picture= pictures.length()>0?pictures.toString().substring(0, pictures.length()-1):null;
				String sentence= sentences.length()>0?sentences.toString().substring(0, sentences.length()-1):null;
				String dialogue= dialogues.length()>0?dialogues.toString().substring(0, dialogues.length()-1):null;
				String video= videos.length()>0?videos.toString().substring(0, videos.length()-1):null;
				String picturebook=picturebooks.length()>0? picturebooks.toString().substring(0, picturebooks.length()-1):null;
				

				wordJson.put("wordId", wordIdstr);
				wordJson.put("word", wordstr);
				wordJson.put("meaning", meaning);
				wordJson.put("picture", picture);
				wordJson.put("sentence", sentence);
				wordJson.put("dialogue",dialogue);
				wordJson.put("video", video);
				wordJson.put("picturebook", picturebook);
			} catch (Exception e) {
				e.printStackTrace();
				status.setStatus(StatusConstant.UNKONWN_EXECPTION);
				status.setMessage("未知异常!");
			}
		}else{
			status.setStatus(StatusConstant.PARAM_ERROR);
			status.setMessage("参数有误!");
		}
		
		return JsonTool.generateModelAndView("word", wordJson, status);
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
