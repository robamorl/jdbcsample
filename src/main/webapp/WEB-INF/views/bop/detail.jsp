<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>収支詳細画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1>収支詳細画面</h1>
	<dl>
		<dt>ユーザコード</dt>
		<dd>
			<c:out value="${user.userCode}" />
		</dd>
		<dt>パスワード</dt>
		<dd>
			<c:out value="${user.password}" />
		</dd>
		<dt>名前</dt>
		<dd>
			<c:out value="${user.userName}" />
		</dd>
		<dt>Eメールアドレス</dt>
		<dd>
			<c:out value="${user.mailAddress}" />
		</dd>
		<dt>登録日時</dt>
		<dd>
			<fmt:formatDate pattern="yyyy/MM/dd" value="${user.entryDate}" />
		</dd>
		<dt>登録者</dt>
		<dd>
			<c:out value="${user.entryUser}" />
		</dd>
	</dl>
	<c:url value="/user/list" var="url" />
	<button onclick="location.href='${url}'">一覧</button>
	<c:url value="/user/list/${user.userId}/delete" var="url" />
	<button onclick="if (confirm('${user.userName}を削除します。よろしいですか？')) {location.href='${url}'}">削除</button>
</BODY>
</HTML>