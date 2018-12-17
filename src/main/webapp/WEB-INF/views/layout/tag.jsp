<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <c:set var="contextPath"  value="${pageContext.request.contextPath}" scope="request"/>
    <c:set var="contextStatic"  value="${pageContext.request.contextPath}/static" scope="request"/>

</body>

</html>