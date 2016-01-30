package com.cnu.iqas.controller.mobile.pass;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.bean.iword.Iword;
import com.cnu.iqas.bean.iword.WordResource;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.service.iword.IwordService;
import com.cnu.iqas.service.iword.WordResourceService;
import com.cnu.iqas.utils.JsonTool;
import com.cnu.iqas.utils.WebUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
* @author  王文辉
* @version 创建时间：2016年1月22日 下午10:46:28
* 类说明
*/
@Controller
@RequestMapping(value="/mobile/pass/")
public class MPassController {
	String pictruepath;
	String picwrongpath1;
	String picwrongpath2;
	private IwordService iwordService;
	private WordResourceService wordResourceService;
	private String content; 
   @SuppressWarnings("finally")
	@RequestMapping(value="listWord")
	public ModelAndView listWord()
	{
	    ModelAndView mv = new ModelAndView("share/json");
		MyStatus status = new MyStatus();
		//总的json对象
		JSONObject jsonObject = new JSONObject();
		JSONArray usersArray = new JSONArray();
		JSONObject picJson = new JSONObject();  
	  try{
		//单词内容存放在容器
		picJson.put("content","boat");
		picJson.put("type", 2);
		picJson.put("aspect", 1);
		picJson.put("difficulty", 1);
		usersArray.add(picJson);
		//usersArray.add(wordpicture.getSavepath());
		//usersArray.add(wordpicture1.getSavepath());
		//usersArray.add(wordpicture2.getSavepath());
	  }catch(Exception e ){
		  status.setMessage("未知异常");
		  status.setStatus(StatusConstant.UNKONWN_EXECPTION);
	 }finally{
		//-------------------返回视图
		JsonTool.putJsonObject(jsonObject, usersArray, status);
		mv.addObject("json", jsonObject.toString());
		return mv;
		
	}
}
	@SuppressWarnings("finally")
	@RequestMapping(value="list")
	//根据单词查询，展示要显示内容
	public ModelAndView listWordResource(String content){
	ModelAndView mv = new ModelAndView("share/json");
	MyStatus status = new MyStatus();
	//总的json对象
	JSONObject jsonObject = new JSONObject();
	JSONArray usersArray = new JSONArray();
	try {
		Iword iword=iwordService.find("o.content = ?",content);
		String wordId=iword.getId();
	do
	  {	//根据单词id来查询单词的对象
		WordResource wordpictrue=wordResourceService.find("o.wordId = ?", wordId);
		//随机查询两个图片对象
		WordResource wordpicwrong1=wordResourceService.findByContent();
		WordResource wordpicwrong2=wordResourceService.findByContent();
		//获取单词图片的保存路径
	    pictruepath=wordpictrue.getSavepath();
	    picwrongpath1=wordpicwrong1.getSavepath();
	    picwrongpath2=wordpicwrong2.getSavepath();
	  }while(pictruepath.equals(picwrongpath1)||picwrongpath1.equals(picwrongpath2)||pictruepath.equals(picwrongpath2));
		//usersArray.add("wordpicture", wordpicture.getSavepath());
	    boolean b=pictruepath.equals(picwrongpath1)||picwrongpath1.equals(picwrongpath2)||pictruepath.equals(picwrongpath2);
	    System.out.println("---------------------------"+b);
	    //根据单词资源的保存路径来查询单词
	    WordResource wordpicwrongpath1=wordResourceService.find("o.savepath = ?", picwrongpath1);
	    WordResource wordpicwrongpath2=wordResourceService.find("o.savepath = ?", picwrongpath2);
	    //根据单词资源表中的资源路径来查询单词content属性？？？？
	    Iword word1=iwordService.find("o.id=?", wordpicwrongpath1.getWordId());
	    String picwrongcontent1=word1.getContent();
	    Iword word2=iwordService.find("o.id=?", wordpicwrongpath2.getWordId());
	    String picwrongcontent2=word2.getContent();
	    System.out.println("原来的单词"+content);
	    System.out.println("第一个错误图片对应的单词"+picwrongcontent1);
	    System.out.println("第二个错误图片对应的单词"+picwrongcontent2);
		JSONObject picJson = new JSONObject();  
		//单词内容存放在容器
		picJson.put("content",content);
		picJson.put("wordpicture",pictruepath);
		picJson.put("picwrongcontent1", picwrongcontent1);
		picJson.put("wordpicture1",picwrongpath1);
		picJson.put("picwrongcontent2", picwrongcontent2);
		picJson.put("wordpicture2",picwrongpath2);
		usersArray.add(picJson);
		//usersArray.add(wordpicture.getSavepath());
		//usersArray.add(wordpicture1.getSavepath());
		//usersArray.add(wordpicture2.getSavepath());
	}catch(Exception e ){
		status.setMessage("未知异常");
		status.setStatus(StatusConstant.UNKONWN_EXECPTION);
	}finally{
		//-------------------返回视图
		JsonTool.putJsonObject(jsonObject, usersArray, status);
		mv.addObject("json", jsonObject.toString());
		return mv;
	}
}
   /*
	mv.addObject("wordUuid", wordUuid);
	mv.addObject("ResourceName",ResourceName);
	mv.addObject("ResourcePath", ResourcePath);
	mv.addObject("wordPicture1", wordPicture1);
	mv.addObject("wordPicture2", wordPicture2);
    return mv;
	}*/
	public IwordService getIwordService() {
		return iwordService;
	}
	@Resource
	public void setIwordService(IwordService iwordService) {
		this.iwordService = iwordService;
	}

	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public WordResourceService getWordResourceService() {
		return wordResourceService;
	}
	@Resource
	public void setWordResourceService(WordResourceService wordResourceService) {
		this.wordResourceService = wordResourceService;
	}
	
}