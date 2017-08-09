<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="scripts/jquery-1.9.1.min.js"></script>
	<script type="text/javascript">
	
		$(function(){
			//1.点击delete 时，弹出确定是要删除 xx 的信息吗？若确定，执行删除，若不确定，则取消
			$(".delete").click(function(){
				var lastName = $(this).next(":hidden").val();
				var flag = confirm("确定是要删除"+lastName+"的信息吗？");
				if(flag){
					//删除，使用ajax的方式
					var $tr = $(this).parent().parent();
					var url = this.href;
					var args = {"time":new Date()};
					$.post(url,args,function(data){
						//若data 的返回值为1，则提示删除成功，且把当前删除
						if(data == "1")
						{
							alert("删除成功!");
							$tr.remove();
						}else{
						//若data 的返回值不为1，仄起式删除失败。
							alert("删除失败！");
						}
					});
				};
				//取消超链接的行为
				return false;
			});
		});
	</script>
  </head>
  
  <body>
    	<h4>Employee List Page</h4>
    	<s:if test="#request.employees == null || #request.employees.size() ==0">
    		没有任何员工信息
    	</s:if>
    	<s:else>
	    	<table border="1" cellpadding="10" cellspacing="0">
	    	
	    		<tr>
	    			<td>ID</td>
	    			<td>LASTNAME</td>
	    			<td>EMAIL</td>
	    			<td>BIRTH</td>
	    			<td>CREATETIME</td>
	  				<td>DEPARTMENT</td>
	  				<td>DELETE</td>
	  				<td>EDIT</td>
	    		</tr>
	    		<s:iterator value="#request.employees">
	    			<tr>
	    				<td>${id }</td>
	    				<td>${lastName }</td>
	    				<td>${email }</td>
	    				<td>
							<s:date name="birth" format="yyyy-MM-dd"/>
						</td>
	    				<td>
							<s:date name="createTime" format="yyyy-MM-dd hh:mm:ss"/>
						</td>
	    				<td>${department.departmentName }</td>
	    				<td>
	    					<a href="emp-delete?id=${id}" class="delete">Delete</a>
	    					<input type="hidden" value="${lastName }"/>
	    				</td>
	    				<td>
	    					<a href="emp-input?id=${id}">Edit</a>
	    				</td>
	    			</tr>	    			
	    		</s:iterator>
	    	</table>
    	</s:else>
    	
  </body>
</html>
