<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1>確認画面</h1>
	<form method="post">
		<input type="hidden" name="${editAccount.accountId}" /> <input
			type="hidden" name="${editAccount.userId}" />
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
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
		<button type="submit" name="_event_confirmed">登録</button>
		<button type="submit" name="_event_revise">再入力</button>
	</form>
</BODY>
</HTML>