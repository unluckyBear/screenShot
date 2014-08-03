<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript">
		function shot(){
			var basePath = '<%=basePath%>';
			var fileName = document.myApplet.testWriteFile(basePath);
			var div = document.getElementById("image");
			div.innerHTML = "";
			var img = document.createElement("img");
			img.src = "<%=path%>/image/" + fileName + ".png";
			div.appendChild(img);
		}
	</script>
  </head>
  
  <body>
    <jsp:plugin 
            type="applet"
            archive="myApplet.jar"
            codebase="." 
            code="com.test.applet.myApplet.class" 
            name="myApplet" 
            width="0" 
            height="0">
	</jsp:plugin>
	<div id="image" style="border:2px;border-style:solid; width: 300px;height:300px">
	</div>
	<input type="button" value="粘贴" onclick="shot()"/>
  </body>
</html>
