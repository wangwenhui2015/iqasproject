package com.cnu.iqas.controller.mobile.ios;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cnu.iqas.bean.base.MyStatus;
import com.cnu.iqas.constant.StatusConstant;
import com.cnu.iqas.controller.web.ontology.FileController;
import com.cnu.iqas.formbean.BaseForm;
import com.cnu.iqas.utils.JsonTool;
import com.cnu.iqas.utils.PropertyUtils;
import com.cnu.iqas.utils.WebUtils;

/**
* @author 周亮 
* @version 创建时间：2016年3月2日 下午3:27:48
* 类说明
*/
@Controller
@RequestMapping(value="/mobile/ios/work/")
public class SWorkController implements ServletContextAware{
	private ServletContext servletContext;

	private final static Logger logger = LogManager.getLogger(SWorkController.class);
	
	
	@RequestMapping(value="uploadWork")
	public ModelAndView uploadWork(@RequestParam("file") CommonsMultipartFile  file){
		ModelAndView mv = new ModelAndView();
		//保存作品，并获取作品的保存路径
		String relativepath =null;
		MyStatus status = new MyStatus();
		if (!file.isEmpty()) {
			   String path = servletContext.getRealPath("/upload/ios/works");  //获取本地存储路径
			   logger.info("文件路径："+path);
			 //获取文件保存的相对路径
				String relativedir= PropertyUtils.getFileSaveDir(PropertyUtils.IOS_WORK_PIC);
				if( !WebUtils.isNull(relativedir)){
					try {
						relativepath=BaseForm.saveFile(servletContext, relativedir, file);
					}catch(Exception e){
						e.printStackTrace();
						status.setStatus(StatusConstant.WORK_UPLOAD_FAILURE);
						status.setMessage("上传文件失败");
					}
				}else{
					status.setStatus(StatusConstant.WORK_SAVEDIR_NOT_CONFIGURE);
					status.setMessage("服务器学生作品保存路径未配置");
				}
				
			}else{
				status.setStatus(StatusConstant.WORK_ISNULL);
				status.setMessage("上传作品为空");
			}
		return JsonTool.generateModelAndView(status);
	}
	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}
}
