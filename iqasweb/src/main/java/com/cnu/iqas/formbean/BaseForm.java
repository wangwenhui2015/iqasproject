package com.cnu.iqas.formbean;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cnu.iqas.constant.ResourceConstant;
import com.cnu.iqas.utils.PropertyUtils;
import com.cnu.iqas.utils.WebUtils;

public class BaseForm {
	/*当前页*/
	private int page = 1;  
	/*是否查询*/
	private String query; 
	/*错误集合*/
	private Map<String,String> errors = new HashMap<String,String>();
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page < 1 ? 1 : page;
	}
	
	public Map<String, String> getErrors() {
		return errors;
	}
	public void setErrors(Map<String, String> errors) {
		this.errors = errors;
	}
	
	/**
	 * 检查字符串是否有效
	 * @param str
	 * @return true：有效，
	 */
	public static boolean validate(String str)
	{
		if( null !=str && !"".equals(str.trim()))
				return true;
		else {
			return false;
		}
	}

	/**
	 * 保存文件，并返回保存的相对路径
	 * @param servletContext    应用上下文
	 * @param file				保存的文件
	 * @param filetype			文件类型：图片、绘本、声音、视频
	 * @return					返回文件在工程中的先对路径
	 * @throws Exception
	 */
	public  String saveFile(ServletContext servletContext, CommonsMultipartFile file,int filetype) throws Exception{
			//文件原名称
		   String fileName = file.getOriginalFilename();
		   //文件后缀名
		   String fileExt = getExt(fileName);
		   //生成随机的文件名
		   String savefilename = UUID.randomUUID().toString()+ "."+fileExt;
		   //根据类型选择保存的相对路径
		   String relativepath=null;
		   switch(filetype){
			    case ResourceConstant.TYPE_IMAGE: //上传图片类型文件
			    	relativepath = PropertyUtils.get("word.imagesavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_VOICE://声音
			    	relativepath = PropertyUtils.get("word.voicesavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_VIDEO://视频
			    	relativepath = PropertyUtils.get("word.videosavedir");  //获取本地存储路径
			    	break;
			    case ResourceConstant.TYPE_PICTUREBOOK://绘本
			    	relativepath = PropertyUtils.get("word.picturebooksavedir");  //获取本地存储路径
			    	break;
			    default:
			    		break;
		   }
		   //获取保存的绝对路径
		   String absolutepath=null;
		   if( relativepath != null && !relativepath.equals("")){
			   absolutepath=servletContext.getRealPath(relativepath);
		   }else{
			   errors.put("error", "保存路径未设置！");
		   }
		   //生成保存路径文件
		   File filedir = new File(absolutepath);
		   //不存在生成
		   if(!filedir.exists()){
			   filedir.mkdirs();
		   }
		   File savefile = new File(filedir,savefilename); //新建保存的文件 
		   try {//将上传的文件写入  新建的文件中
			    file.getFileItem().write(savefile); 
		   } catch (Exception e) {
			    e.printStackTrace();
		   }
		  //相对路径   relativepath+"/"+filesavename
		return relativepath+"/"+savefilename;
	}
	/**
	 * 检验上传资源格式和大小是否合理
	 * @param file  上传的文件
	 * @return  格式和大小符合要求返回正确
	 */
	public  boolean validateResourceTypeAndSize(CommonsMultipartFile  file,int type){
		//校验文件不为空，字节大于0
		if(!file.isEmpty() && file.getSize()>0){
			//获取文件MIME类型，如image/pjpeg、text/plain
			String fileContentType =file.getContentType();
			//上传文件原名
			String fileName = file.getOriginalFilename();
			//获取文件字节大小,单位byte
			long filesize = file.getSize();
			
			//文件类型校验结果
			boolean typeresult = false;
			//文件大小校验结果
			boolean sizeresult = false;
			switch(type){
			case ResourceConstant.TYPE_IMAGE: //上传图片类型文件
				typeresult=validateImageFileType(fileName, fileContentType);
				sizeresult = validateFileSize(filesize,ResourceConstant.UPLOAD_SIZE_IMAGE);
				if(!sizeresult)
				    errors.put("error", "上传文件大小不能超过"+WebUtils.convertStorage(ResourceConstant.UPLOAD_SIZE_IMAGE));
				break;
			case ResourceConstant.TYPE_VOICE://声音
				//typeresult=validateImageFileType(fileName, fileContentType);
				sizeresult = validateFileSize(filesize,ResourceConstant.UPLOAD_SIZE_VOICE);
				if(!sizeresult)
				    errors.put("error", "上传文件大小不能超过"+WebUtils.convertStorage(ResourceConstant.UPLOAD_SIZE_VOICE));
				break;
			case ResourceConstant.TYPE_VIDEO://视频
				//typeresult=validateImageFileType(fileName, fileContentType);
				sizeresult = validateFileSize(filesize,ResourceConstant.UPLOAD_SIZE_VIDEO);
				if(!sizeresult)
				    errors.put("error", "上传文件大小不能超过"+WebUtils.convertStorage(ResourceConstant.UPLOAD_SIZE_VIDEO));
				break;
			case ResourceConstant.TYPE_PICTUREBOOK://绘本
				//typeresult=validateImageFileType(fileName, fileContentType);
				sizeresult = validateFileSize(filesize,ResourceConstant.UPLOAD_SIZE_PICTUREBOOK);
				if(!sizeresult)
				    errors.put("error", "上传文件大小不能超过"+WebUtils.convertStorage(ResourceConstant.UPLOAD_SIZE_PICTUREBOOK));
				break;
			}
			//文件类型和大小都通过则返回正确
			if( typeresult && sizeresult)
				return true;
			else{
				if( !typeresult){
					errors.put("error", "上传文件类型有误！");
				}
			}
		}else{
			errors.put("error", "请上传文件！");
		}
		return false;
	}
	/**
	 * 上传文件大小和限制大小的比较
	 * @param fileSize 文件大小
	 * @param limitSize 限制的大小
	 * @return  返回true 表示符合要求fileSize<=limitSize
	 */
	public  boolean validateFileSize(long fileSize,long limitSize){
		return fileSize <=limitSize;
	}
	/**
	 * 验证上传文件类型是否属于图片格式
	 * @param fileFileName  文件名
	 * @param fileContentType 文件类型
	 * @return true表示没错或者文件为null，false：有误
	 */
	public static boolean validateImageFileType(String fileFileName,String fileContentType){
		
			List<String> arrowType = Arrays.asList("image/bmp","image/png","image/gif","image/jpg","image/jpeg","image/pjpeg");
			List<String> arrowExtension = Arrays.asList("gif","jpg","bmp","png");
			String ext = getExt(fileFileName);
			return arrowType.contains(fileContentType.toLowerCase()) && arrowExtension.contains(ext);
		
	}
	/**
	 * 得到文件后缀名
	 * @param fileFileName
	 * @return
	 */
	public static String getExt(String fileFileName){
		return fileFileName.substring(fileFileName.lastIndexOf('.')+1).toLowerCase();
	}
	
	
}
