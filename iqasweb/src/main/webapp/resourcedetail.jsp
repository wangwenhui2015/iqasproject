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
  </head>
  <body>
	<div class="container-fluid" style="text-align:left; width: 700px;border:solid red 1px;">
	 	<div style="text-align: left;"> 
	 		<h4>park</h4>
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
	 		<span style="width: 100%;">图片 </span>
	 		<div class="row">
			  <div class="col-sm-6 col-md-3">
			    <div class="thumbnail">
			      <img src="images/dog.jpg" alt="...">
			      <div class="caption" style="text-align: center;">
			         <a href="#" class="btn btn-warning" role="button">删除</a></p>
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-6 col-md-3">
			    <div class="thumbnail">
			      <img src="images/dog.jpg" alt="...">
			      <div class="caption" style="text-align: center;">
			         <a href="#" class="btn btn-warning" role="button">删除</a></p>
			      </div>
			    </div>
			  </div>
			  <div class="col-sm-6 col-md-3">
			    <div class="thumbnail">
			      <img src="images/dog.jpg" alt="...">
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
	 	<div> 绘本
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
	 	<div> 视频
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

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="js/jquery-2.1.3.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>