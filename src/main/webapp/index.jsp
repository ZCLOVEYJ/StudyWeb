<%@page language="java" contentType="text/html; charset=utf-8"
        pageEncoding="utf-8" %>
<html>
<body>
<h2>Hello World!</h2>
SpringMVC上传文件
<form name="form1" action="/manage/product/upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="上传文件">

</form>
SpringMVC上传富文本
<form name="form2" action="/manage/product/rich_text_img_upload.do" method="post" enctype="multipart/form-data">
    <input type="file" name="upload_file">
    <input type="submit" value="上传富文本">

</form>
</body>
</html>
