<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1 id=header>登録完了</h1>
	<b style="color: #FF0000;"><c:out value="${message}" /></b>
	<input type="hidden" name="${editAccount.accountId}" />
	<input type="hidden" name="${editAccount.userId}" />
	<dl>
		<dt>口座名</dt>
		<dd>
			<c:out value="${editAccount.accountName}" />
		<dt>口座番号</dt>
		<dd>
			<c:out value="${editAccount.accountNumber}" />
		</dd>
		<dt>口座区分</dt>
		<dd>
			<c:out value="${editAccount.accountKubunMei}" />
			<input type="hidden" value="${editAccount.accountKubun}">
		</dd>
	</dl>
	<c:url var="url" value="/account/list" />
	<button onclick="location.href='${url}'">戻る</button>
</BODY>
</HTML>