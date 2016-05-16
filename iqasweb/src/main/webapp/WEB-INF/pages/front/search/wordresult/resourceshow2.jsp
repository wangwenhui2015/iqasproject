<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>   
<!--Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>Clean Plans and Pricing Tables  Responsive Widget Template | Home :: w3layouts</title>
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="Clean Plans and Pricing Tables  Responsive, Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<!--web-fonts-->
<link href='//fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'><!-- jQuery -->
<link href='//fonts.googleapis.com/css?family=Ubuntu:400,300,300italic,400italic,500,500italic,700,700italic' rel='stylesheet' type='text/css'>
</head>
<body>
		<div class="header">
		  <h1></h1><br /><br /><br /><br />
</div>
		<div class="main">
		
			<div class="pricing-table">
				<div class="pricing">
					<div class="price-top">
						<h2>联想</h2>
					</div>
					<div class="price-bottom" >
						<!-- <input name="text1" type="text" value="This is my,,,," style="width:320px; height:230px;"> -->
						<div style="width:324px; height:230px;"><img src="${media.associationpic}"></div>
						<div style="width:324px; height:30px; margin-top:35px; "><audio src="${media.associationaud}" controls="controls"></audio></div>
						<div style="width:324px; height:40px; margin-top:20px; ">${media.associationtxt}</div>
						<!-- <a href="#" class="button">sign up</a> -->
						<br />
					</div>
				</div>
				<div class="pricing">
					<div class="price-top">
						<h2>同义词</h2>
					</div>
					<div class="price-bottom">
						<!-- <input name="text1" type="text" value="This is my,,,," style="width:320px; height:230px;"> -->
						<div style="width:324px; height:230px;"><img src="${media.Synonymspic}"></div>
						<div style="width:324px; height:30px; margin-top:35px; "><audio src="${media.Synonymsaud}" controls="controls"></audio></div>
						<div style="width:324px; height:40px; margin-top:20px;">${media.Synonymstxt}</div>
						<!-- <a href="#" class="button">sign up</a> -->
						<br />
					</div>
				</div>
				<div class="pricing">
					<div class="price-top top2">
						<h2>反义词</h2>
					</div>
					<div class="price-bottom">
						<!-- <input name="text1" type="text" value="This is my,,,," style="width:320px; height:230px;"> -->
						<div style="width:324px; height:230px;"><img src="${media.Antonympic}"></div>
						<div style="width:324px; height:30px; margin-top:35px;"><audio src="${media.Antonymaud}" controls="controls"></audio></div>
						<div style="width:324px; height:40px; margin-top:20px;">${media.Antonymtxt}</div>
						<!-- <a href="#" class="button">sign up</a> -->
						<br />
					</div>
				</div>
				<div class="pricing">
					<div class="price-top top3">
						<h2>拓展</h2>
					</div>
					<div class="price-bottom">
						<!-- <input name="text1" type="text" value="This is my,,,," style="width:320px; height:230px;"> -->
						<div style="width:324px; height:230px;"><img src="${media.Expandpic}"></div>
						<div style="width:324px; height:30px; margin-top:35px;"><audio src="${media.Expandaud}" controls="controls"></audio></div>
						<div style="width:324px; height:40px;margin-top:20px;">${media.Expandtxt}</div>
						<!-- <a href="#" class="button">sign up</a> -->
						<br />
					</div>
				</div>
		
				<div class="pricing" style="margin-left:30px;">
					<div class="price-top top4" >
						<h2>常用</h2>
					</div>
					<div class="price-bottom">
				<!-- <input name="text1" type="text" value="This is my,,,," style="width:320px; height:230px;"> -->
						<div style="width:324px; height:230px;"><img src="${media.CommonUsepic}"></div>
						<div style="width:324px; height:30px; margin-top:35px;"><audio src="${media.CommonUseaud}" controls="controls"></audio></div>
						<div style="width:324px; height:40px;margin-top:20px;">${media.CommonUsetxt}</div>
						<!-- <a href="#" class="button">sign up</a> -->
						<br />
					</div>
				</div>
				
				<div class="clear"></div>
			</div>
			
		</div>	
		<div class="footer">
			<p>&copy 2016 Capital Normal University . All rights reserved | Design by <a href="http://w3layouts.com">Open Learning.</a></p>
		</div>

</body>
</html>