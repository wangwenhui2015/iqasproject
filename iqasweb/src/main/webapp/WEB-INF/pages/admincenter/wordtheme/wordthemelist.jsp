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
<title>单词</title>
<jsp:include page="/WEB-INF/pages/share/bootstrap.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="css/admin-all.css">
<link rel="stylesheet" type="text/css" href="css/base.css">

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
$(function(){
	//在搜索框中按“回车键”开始查询
	 $("#name").keydown(function(event){
		 if(event.keyCode==13) //Enter
		 {
			 topage(1);
		 }
	 });
});
	//查询
	function topage(page)
	{
		var nameNode = $("#name").val();
		if( nameNode!="" || nameNode.trim()!="")
		{
			var form = document.forms[0];
			form.page.value= page;
			form.submit();
		}
	}
	var count =0;
//编辑主题名,id：被编辑主题的id
function editName(id) {
	
	//获取编辑小图标节点
	var editNode = $("#e"+id);
	//主题名称节点
	var spanNode =$("#s"+id);
	
	//判断当前处于编辑状态还是显示状态，判断依据：编辑框的type属性是否为"hidden",如果为hidden则是显示状态，否则为编辑状态。
	//如果是编辑状态则进行显示，如果是显示状态则调成编辑状态。
	
	//编辑框节点
	var inputNode = $("#i"+id);
	var val = inputNode.attr("type");
	if( val=="hidden")//当前为显示状态，进入编辑状态
	{
		//x号按钮
		var xNode = $("#de"+id);
		xNode.show();//x号按钮显示
		
		//去掉“编辑”图标，换成"对号"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-pencil");
		editNode.addClass("glyphicon-ok");
		
		//显示节点隐藏
		spanNode.hide();
		//编辑节点显示
		inputNode.attr("type","text");
		//给编辑框绑定键盘事件，当按"enter"键时去访问后台
		inputNode.bind("keydown",{id:id},keyDownHandler);
		//聚焦
		inputNode.focus();
		
	}else{//当前为编辑状态，进入显示状态
		//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-ok");
		editNode.addClass("glyphicon-pencil");
		//访问后台进行数据修改
		ajaxeditName(id);
		//解绑编辑节点绑定的键盘事件
		 $("#i"+id).unbind("keydown");
	}
}
//对键盘按下事件的处理函数
function keyDownHandler(event){
	 if(event.keyCode==13) //处理Enter事件
	 {
		//获取编辑小图标节点
		var editNode = $("#e"+event.data.id);
		 //调用ajax修改函数修改姓名
		 ajaxeditName(event.data.id);
		 //解除键盘绑定事件,因为每次修改都会绑定一个键盘事件，所以当有键盘事件发生时，就会执行多个绑定函数，尽管函数都一样。
		 $("#i"+event.data.id).unbind("keydown");
		 
		//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
		editNode.removeClass("glyphicon-ok");
		editNode.addClass("glyphicon-pencil");
		//x号按钮
		var xNode = $("#de"+id);
		xNode.hide();//x号按钮隐藏
	 }
}
//放弃编辑名称
function dropEditName(id){
	//获取编辑小图标节点
	var editNode = $("#e"+id);
	//x号按钮
	var xNode = $("#de"+id);
	//编辑框
	var inputNode = $("#i"+id);
	//主题名称的span标签
	var spanNode =$("#s"+id);
	//修改之前的名称
	var beforeName = spanNode.text().trim();
	
	//去掉“对号”图标，换成"编辑"小图标glyphicon-ok  glyphicon-pencil
	editNode.removeClass("glyphicon-ok");
	editNode.addClass("glyphicon-pencil");
	
	inputNode.attr("type","hidden");
	//将编辑内容设为原来内容
	inputNode.val(beforeName);
	spanNode.show();
	
	//x号按钮隐藏
	xNode.hide();
	//解绑编辑节点绑定的键盘事件
	$("#i"+id).unbind("keydown");
}
//调用ajax修改主题名
function ajaxeditName(id) {
	//主题名称的span标签
	var spanNode =$("#s"+id);
	//x号按钮
	var xNode = $("#de"+id);
	
	//修改之前的名称
	var beforeName = spanNode.text().trim();
	//编辑框
	var inputNode = $("#i"+id);
	//修改后的名称
	var afterName = inputNode.val().trim();
	//比较修改后的名称是否满足格式以及是否不和原名称一样，如果满足则修改，否则给出提示并不修改
	if( afterName!="" && afterName!=beforeName){
		//判断是否超过20个字符
		if( afterName.length<=20){
			//修改
			$.get("admin/control/wordtheme/ajaxeditName.html",{id:id,name:afterName},function(content){
				var status = content.status;
				var message = content.message;
				if( status == 1){//修改成功，将原名称修改为新名称显示
					spanNode.text(afterName);
				}else{//修改失败，将编辑框的名称改回原样，
					inputNode.val(beforeName);
					//给出提示
					alert(message);
				}
				//隐藏编辑框，显示内容
				inputNode.attr("type","hidden");
				spanNode.show();
				xNode.hide();//x号按钮隐藏
			},"json");
		}else{
			alert("主题名称不要超过20个字符!");
		}
	}else{//没改变，隐藏编辑框，显示内容
		inputNode.attr("type","hidden");
		//将编辑内容设为原来内容
		inputNode.val(beforeName);
		spanNode.show();
		xNode.hide();//x号按钮隐藏
	}
}
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
				<td align="center">${entity.id} </td> 
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
				 <input type="button" value="编辑" class="btn btn-info btn-xs" onclick="javascript:_action('delete','${entity.id }')">
				 </td>
			</tr>
			<!-- 给每个input编辑框绑定键盘事件 -->
			<!-- <script type="text/javascript">
				//将编辑框显示出来
				var inputNode = $("#i"+id);
				inputNode.attr("type","text");
				//给编辑框绑定键盘事件
				inputNode.keydown(function(event){
					alert(count);
					count++;
					 if(event.keyCode==13) //处理Enter事件
					 {
						 //调用ajax修改函数修改姓名
						 ajaxeditName(id);
					 }
				 });
			</script> -->
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