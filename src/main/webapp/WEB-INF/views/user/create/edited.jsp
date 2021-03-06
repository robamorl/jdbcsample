<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
    <jsp:include page="../../header/login_info.jsp"/>
	<h1 id=header>登録完了</h1>
	<b style="color:#FF0000;"><c:out value="${message}" /></b>
	<dl>
		<dt>ユーザコード</dt>
		<dd>
			<c:out value="${editUser.userCode}" />
		</dd>
		<dt>パスワード</dt>
		<dd>
			<c:out value="${editUser.password}" />
		</dd>
		<dt>名前</dt>
		<dd>
			<c:out value="${editUser.userName}" />
		</dd>
		<dt>Eメールアドレス</dt>
		<dd>
			<c:out value="${editUser.mailAddress}" />
		</dd>
	</dl>
	<c:url var="url" value="/user/list" />
	<button onclick="location.href='${url}'">戻る</button>
</BODY>
</HTML>