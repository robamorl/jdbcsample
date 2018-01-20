<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>収支一覧画面</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1 id=header>収支一覧画面</h1>
	<b style="color: #FF0000;"><c:out value="${message}" /></b>
	<BR>
	<c:url value="/bop/list/create" var="url" />
	<button onclick="location.href='${url}'">登録</button>
	&nbsp;&nbsp;
	<c:url value="/" var="url" />
	<button onclick="location.href='${url}'">戻る</button>
	<TABLE border="1" class="listtable" cellspacing="0">
		<TR>
			<TH class="listheader">口座名</TH>
			<TH class="listheader">口座番号</TH>
			<TH class="listheader">収支区分</TH>
			<TH class="listheader">費目</TH>
			<TH class="listheader">金額</TH>
			<TH class="listheader">処理日</TH>
			<TH class="listheader">処理者</TH>
			<TH class="listheader"></TH>
		</TR>
		<c:forEach items="${bops}" var="bop">
			<TR>
				<TD><c:out value="${bop.accountName}" /></TD>
				<TD><c:out value="${bop.accountNumberForDisplay}" /></TD>
				<TD><c:out value="${bop.balanceOfPaymentsKubunMei}" /></TD>
				<TD><c:out value="${bop.expensesKubunMei}" /></TD>
				<TD align="right"><fmt:formatNumber value="${bop.amount}"
						maxFractionDigits="0" type="CURRENCY" currencySymbol="\\" /></TD>
				<TD><fmt:formatDate value="${bop.transactionDate}"
						pattern="yyyy/MM/dd" /> <input type="hidden"
					value="${bop.transactionDate}" /></TD>
				<TD><c:out value="${bop.updateUser}" /></TD>
				<TD><c:url value="/bop/list/${bop.balanceOfPaymentsId}"
						var="url" />
					<button onclick="location.href='${url}'">詳細</button> <c:url
						value="/bop/list/${bop.balanceOfPaymentsId}/edit" var="url" />
					<button onclick="location.href='${url}'">編集</button>
			</TR>
		</c:forEach>
	</TABLE>
</BODY>
</HTML>
