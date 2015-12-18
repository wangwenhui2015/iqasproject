<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/pages/share/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>单词主题</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/admin-all.css">
<link rel="stylesheet" type="text/css" href="css/base.css">
<script type="text/javascript" src="js/admin/word/wordthemelist.js"></script> 

<style type="text/css">
.editClass{
cursor:pointer;
}
.xeditClass{
cursor:pointer;
display: none;
}
</style>
<script type="text/javascript">

</script>
</head>
<body>


<div class="panel panel-default">
  <%-- <div class="panel-heading">
    <h3 class="panel-title">当前位置 &gt;热点话题管理 &gt;<a href='control/group/list'><font color="blue">热点话题信息</font></a>&gt;<font style="font-family:'楷体';font-weight: bold; ">${pageView.records[0].groupChat.name}</font></h3>
  </div> --%>
  <div class="panel-body">
  
	<form  action="<c:url value='admin/control/wordtheme/list.html'/>" method="post">
	
	<table >
       <tbody>
         <tr>
           <td>查询条件：</td>
           <td>&nbsp;&nbsp;主题名称：</td>
           <td>
           <input type="text"   id="name" name="name" value="${formbean.name }" style="width: 120px"  >
           </td>
           <td>
           <input  type="button" onclick="topage('1')" value="查询 " name="sButton2">
           </td>
        </tr>
     </tbody>
   </table>
   <!-- 查询参数 -->
    <input type="hidden" name="page">
	<table class="table table-bordered table-striped"> <!-- table-bordered -->
		<thead>
			<tr>
				<td align="center" width="20%">主题ID</td>
				<td align="center" width="30%">主题名称</td>
				<td align="center" width="25%">主题包含单词数</td>
				<td align="center" width="15%">创建时间</td>
				<td align="center" width="10%">操作</td>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageView.records }" var="entity">
			<tr>
				<td align="center"><a href="<c:url value='admin/control/wordtheme/wordlist.html?id=${entity.id }&page=1'/>">${entity.id} </a></td> 
				<td align="center">
				 <span id="s${entity.id}">${entity.name}</span>
				 <input type="hidden" id="i${entity.id}"  value="${entity.name}"> 
				  <!--  glyphicon-ok  glyphicon-pencil-->
				 <span class="glyphicon glyphicon-pencil editClass" id="e${entity.id}" aria-hidden="true" onclick="editName('${entity.id}')" ></span>
				 &nbsp;
				 <span class="glyphicon glyphicon-remove xeditClass" id="de${entity.id}" aria-hidden="true" onclick="dropEditName('${entity.id}')" ></span>
				 
				 </td>
				<td align="center">
				</td>
				<td align="center">${entity.createTime} </td>
				<td align="center">
				 <input type="button" value="删除" class="btn btn-info btn-xs" onclick="javascript:_action('delete','${entity.id }')">
				 </td>
			</tr>
			
		 </c:forEach>
		</tbody>
	</table>
		<div align="center">
		 <a href="<c:url value='admin/control/wordtheme/addUI.html'/>"  class="btn btn-info" >添加主题</a>
		</div>
	</form>
  </div>
   <div class="panel-footer">
     <%@ include file="/WEB-INF/pages/share/fenye.jsp"%>
   </div>
</div>
</body>
</html>