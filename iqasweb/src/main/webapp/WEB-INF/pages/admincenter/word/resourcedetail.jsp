<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <base href="<%=basePath%>">   
	<title>标题</title> 
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    
    
<script language="javascript">
function playmusic(){
var xx=document.getElementById("embed").src;
var url="http://localhost:8088/iqasweb/voices/xiaobai.wav";
//我叫小沈阳 dj
	if(xx=="http://localhost:8088/iqasweb/"){
	document.getElementById("embed").src=url;
	document.getElementById("musicbtn").value="停止播放";
	}else{
	document.getElementById("embed").src="";
	document.getElementById("musicbtn").value="播放音乐";
	}
}
</script>

  </head>
  <body>

	<div class="container-fluid" style="text-align:left; width: 700px;border:solid red 1px;">
	 	<div style="text-align: left;"> 
	 		<ol class="breadcrumb">
			  <li><h3>${word.content }</h3></li>
			  <li><a href="#">
			    ${word.version==1?"北师大版":"" }${word.version==2?"北京版":"" }
				${word.version==3?"外研社新标准版":"" }${word.version==4?"外研社一年级起版":"" }
				${word.version==5?"人教版":"" }${word.version==6?"朗文版":"" }
				</a></li>
			  <li ><a href="#">${word.book}册</a></li>
			   <li ><a href="#">${word.unit}单元</a></li>
			   <li><a href="javascript:void()">第${word.rank}个单词</a></li>
			  
			</ol>
	 	</div>
	 	<span>
		 	<span style="margin-right: 30px;">
		 		<small>英</small>
		 		<a href="" ><img alt="" src="images/admin/word/voice.png"> </a>
		 	</span>
		 	<span>
		 		<small>美</small>
		 		<a href=""> <img alt="" src="images/admin/word/voice.png"> </a>
		 	</span>
		 	 
	 	</span>
	 	<div> 
	 	<span class="btn btn-primary">图片</span>
	 		<div class="row">
	 		<c:forEach  items="${list }" var="resource">
		 		<div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="${resource.savepath }" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="<c:url value='admin/control/wordresource/delete.html?id=${resource.id}'/>" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				    </div>
				  </div>
	 		</c:forEach>
			  
			  <div class="col-sm-6 col-md-3">
			    <div class="thumbnail" style="border:0px;">
			      <img src="images/add.jpg" class="img-circle btn" alt="...">
			    </div>
			  </div>
			</div>
	 	</div>
	 	<div>  
	 	<span class="btn btn-primary">绘本</span>
		 	<div class="row">
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				    </div>
				  </div>
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				    </div>
				  </div>
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				    </div>
				  </div>
				 
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail" style="border:0px;">
				      <img src="images/add.jpg" class="img-circle btn" alt="...">
				    </div>
				  </div>
			</div>
	 	
	 	</div>
	 	<div> 
	 	<span class="btn btn-primary">视频</span>
	 		<div class="row">
			 	 <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				      </div>
				  </div>
			 	 <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				      </div>
				  </div>
			 	 <div class="col-sm-6 col-md-3">
				    <div class="thumbnail">
				      <img src="images/hbmonkey.jpeg" alt="...">
				      <div class="caption" style="text-align: center;">
				         <a href="#" class="btn btn-warning" role="button">删除</a></p>
				      </div>
				      </div>
				  </div>
				  <div class="col-sm-6 col-md-3">
				    <div class="thumbnail" style="border:0px;">
				      <img src="images/add.jpg" class="img-circle btn" alt="...">
				    </div>
				  </div>
			</div>
	 	</div>
	 	
	 	<div>
	 	<input type="button" value="返回" class="btn btn-info " onclick="javascript:history.go(-1);">
	   </div>
	 	
	</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-2.1.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>