<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>収支詳細画面</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1 id=header>収支詳細画面</h1>
	<dl>
		<dt>口座名</dt>
		<dd>
			<c:out value="${bop.accountName}" />
			<input type="hidden" value="${bop.accountId}">
		<dt>口座番号</dt>
		<dd>
			<c:out value="${bop.accountNumberForDisplay}" />
		</dd>
		<dt>現在残高</dt>
		<dd>
			<fmt:formatNumber value="${bop.balance}" maxFractionDigits="0"
				type="CURRENCY" currencySymbol="\\" />
			<input type="hidden" value="${bop.balance}">
		</dd>
	</dl>
	<BR>
	<HR>
	<dl>
		<dt>収入/支出</dt>
		<dd>
			<c:out value="${bop.balanceOfPaymentsKubunMei}" />
			<input type="hidden" value="${bop.balanceOfPaymentsKubun}">
		</dd>
		<dt>費目</dt>
		<dd>
			<c:out value="${bop.expensesKubunMei}" />
			<input type="hidden" value="${bop.expensesKubun}">
		</dd>
		<dt>金額</dt>
		<dd>
			<fmt:formatNumber value="${bop.amount}" maxFractionDigits="0"
				type="CURRENCY" currencySymbol="\\" />
			<input type="hidden" value="${bop.amount}">
		</dd>
		<dt>終始発生日</dt>
		<dd>
			<fmt:formatDate value="${bop.transactionDate}" pattern="yyyy/MM/dd" />
			<input type="hidden" value="${bop.transactionDate}">
		</dd>
	</dl>
	<c:url value="/bop/list" var="url" />
	<button onclick="location.href='${url}'">一覧</button>
	<c:url value="/bop/list/${bop.balanceOfPaymentsId}/delete" var="url" />
	<button
		onclick="if (confirm('この収支を削除します。よろしいですか？')) {location.href='${url}'}">削除</button>
</BODY>
</HTML>