package com.cnu.iqas.controller.mobile.ontology;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.ontology.ISentence;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.service.ontology.SentenceSim;
import com.cnu.iqas.utils.WebUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
* @author 周亮 
* @version 创建时间：2015年11月7日 下午10:46:28
* 类说明
*/
@Controller
@RequestMapping(value="/mobile/search")
public class MOntologyController {
	private SentenceSim sentenceSim ;
	
	/**
	 * 用户输入问句后首先进行判断，如果是单词跳转到查询单词的页面，如果是查询句子，跳转到查询句子的页面。
	 * @param text 用户搜索的句子
	 * @return json格式数据
	 * {
	 *  status:1,    1:成功，0失败
	 *  message:"ok",原因
	 *  result:{
	 *  count:1,
	 *     data:[
	 *     	{account:"zhangsan",password:"123",gender:"男",picturePath:"http://172.19.68.77:8080/zhushou/images/logo.jpg"}
	 * 	]
	 * 	}
	 */
	//Excuse me,Where are you from,ok?  park
	@SuppressWarnings("finally")
	@RequestMapping(value="/sentence")
	public ModelAndView searchSentence(String text){
		ModelAndView mv = new ModelAndView("share/json");
		int scode =StatusConstant.OK;//结果
		String message ="ok";//结果说明
		//总的json对象
		JSONObject jsonObejct = new JSONObject();
		//result对象
		JSONObject resultObject = new JSONObject();
		//对象
		JSONObject senObject ;
    	try {
    		
			if(text.trim().length()<15)
			{
				//根据单词进行查询
				Iword iword=sentenceSim.findWordProperty(text);
				/*if( iword !=null ){
					mv.addObject("word", iword);
				}*/
				senObject= JSONObject.fromObject(iword);
			}
			else
			{	
				//根据句子进行查询
			    System.out.println("根据句子进行查询");
			    ISentence sentence=sentenceSim.maxSimilar(text);
			    
				senObject= JSONObject.fromObject(sentence);
			}	
			JSONArray usersArray = new JSONArray();
			usersArray.add(senObject);
			
			resultObject.put("count", 1);
			resultObject.put("data", usersArray);
    	}catch(Exception e ){
			scode = StatusConstant.PARAM_ERROR;
			message = e.getMessage();
		}finally{
			//-------------------返回视图
			return WebUtils.beforeReturn(scode, message, jsonObejct, resultObject, mv);
		}
	}

	public SentenceSim getSentenceSim() {
		return sentenceSim;
	}
	@Resource
	public void setSentenceSim(SentenceSim sentenceSim) {
		this.sentenceSim = sentenceSim;
	}

}
