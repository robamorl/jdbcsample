<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>口座詳細画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1>口座詳細画面</h1>
	<dl>
		<dt>口座名</dt>
		<dd>
			<c:out value="${account.accountName}" />
		</dd>
		<dt>口座番号</dt>
		<dd>
			<c:out value="${account.accountNumberForDisplay}" />
		</dd>
		<dt>口座区分</dt>
		<dd>
			<c:out value="${account.accountKubunMei}" />
		</dd>
		<dt>登録日時</dt>
		<dd>
			<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
				value="${account.entryDate}" />
		</dd>
		<dt>登録者</dt>
		<dd>
			<c:out value="${account.entryUser}" />
		</dd>
		<dt>更新日時</dt>
		<dd>
			<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
				value="${account.entryDate}" />
		</dd>
		<dt>更新者</dt>
		<dd>
			<c:out value="${account.entryUser}" />
		</dd>
	</dl>
	<c:url value="/account/list" var="url" />
	<button onclick="location.href='${url}'">一覧</button>
	<c:url value="/account/list/${account.accountId}/delete" var="url" />
	<button
		onclick="if (confirm('口座番号「${account.accountNumberForDisplay}」を削除します。よろしいですか？')) {location.href='${url}'}">削除</button>

</BODY>
</HTML>