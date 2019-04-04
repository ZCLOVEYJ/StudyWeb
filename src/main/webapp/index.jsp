<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<html>
<head>
    <base href="<%=basePath%>">
</head>

<body>
<h2>Hello World!</h2>


SpringMVC上传文件
<form name="form1" action="<%=basePath%>/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="SpringMVC上传文件"/>
</form>


SpringMVC富文本图片上传文件
<form name="form2" action="<%=basePath%>/manage/product/rich_text_img_upload.do" method="post"
      enctype="multipart/form-data">
    <input type="file" name="upload_file"/>
    <input type="submit" value="SpringMVC富文本图片上传文件"/>
</form>

</body>
</html>
