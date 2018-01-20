<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登録完了</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1 id=header>登録完了</h1>
	<b style="color: #FF0000;"><c:out value="${message}" /></b>
	<dl>
		<dt>口座名</dt>
		<dd>
			<c:out value="${editBop.accountName}" />
			<input type="hidden" value="${editBop.accountId}">
		<dt>口座番号</dt>
		<dd>
			<c:out value="${editBop.accountNumberForDisplay}" />
		</dd>
		<dt>収入/支出</dt>
		<dd>
			<c:out value="${editBop.balanceOfPaymentsKubunMei}" />
			<input type="hidden" value="${editBop.balanceOfPaymentsKubun}">
		</dd>
		<dt>費目</dt>
		<dd>
			<c:out value="${editBop.expensesKubunMei}" />
			<input type="hidden" value="${editBop.expensesKubun}">
		</dd>
		<dt>口座残高</dt>
		<dd>
			<fmt:formatNumber value="${editBop.balance}" maxFractionDigits="0"
				type="CURRENCY" currencySymbol="\\" />
			<input type="hidden" value="${editBop.balance}">
		<dt>金額</dt>
		<dd>
			<fmt:formatNumber value="${editBop.amount}" maxFractionDigits="0"
				type="CURRENCY" currencySymbol="\\" />
			<input type="hidden" value="${editBop.amount}">
		</dd>
		<dt>終始発生日</dt>
		<dd>
			<fmt:formatDate value="${editBop.transactionDate}"
				pattern="yyyy/MM/dd" />
			<input type="hidden" value="${editBop.transactionDate}">
		</dd>
	</dl>
	<c:url var="url" value="/bop/list" />
	<button onclick="location.href='${url}'">戻る</button>
</BODY>
</HTML>