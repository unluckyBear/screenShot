<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
	<script type='text/javascript' src='js/jquery-1.5.1.js'></script>
	<script type='text/javascript' src='js/json.js'></script>
	<script type="text/javascript">
	$(function() {
		$("#find").click(function() {

			$.post('screenShot.action', function(data) {
				$("image").html("<img alt="" src=\"image/2.jpg\"/>");
			});
		});
	});
	</script>
  </head>
  
  <body>
  	<div id="image" style="width: 300px;height: 200px;border: solid;">
	  	
  	</div>
  	<input type="button" id="find" value="截图" />
	<p>hello!</p>
  </body>
</html>
