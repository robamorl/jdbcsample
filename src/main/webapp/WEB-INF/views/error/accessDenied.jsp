<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<meta charset="UTF-8">
</HEAD>
<BODY>
    <jsp:include page="../header/login_info.jsp"/>
	<h1>アクセス拒否</h1>
	アクセスが拒否されました。
	<BR><BR>
	<c:url value="/top" var="url" />
	<button onclick="location.href='${url}'">トップへ戻る</button>
</BODY>
</HTML>
