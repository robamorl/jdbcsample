<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>指定されたデータは見つかりません</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
    <jsp:include page="../header/login_info.jsp"/>
	<h1>指定されたデータは見つかりません</h1>
	<h3>${exception.message}</h3>
	<BR>お手数ですが、最初からやり直して下さい。<BR>
	<c:url value="/" var="url" />
	<button onclick="location.href='${url}'">トップへ戻る</button>
</BODY>
</HTML>