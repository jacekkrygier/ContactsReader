
<%@ taglib uri ="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix ="spring" uri ="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Upload Page</title>
</head>
<body>

<spring:url value="/doUpload" var="doUploadURL" />
<form:form method="post" modelAttribute="formUpload" action="${doUploadURL}" enctype="multipart/form-data">
    <form:input path="files" type="file" multiple="multiple" />
    <form:errors path="files" /><br/>

    <button type="submit">Upload</button>
</form:form>
</body>
</html>
