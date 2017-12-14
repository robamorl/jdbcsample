<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>口座一覧画面</title>
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1>口座一覧画面</h1>
	<b style="color: #FF0000;"><c:out value="${message}" /></b>
	<TABLE border="1">
		<TR>
			<TH>口座名</TH>
			<TH>口座番号</TH>
			<TH>口座区分</TH>
			<TH>登録日時</TH>
			<TH>更新日時</TH>
			<TH></TH>
		</TR>
		<c:forEach items="${accounts}" var="account">
			<TR>
				<TD><c:out value="${account.accountName}" />
				<TD><c:out value="${account.accountNumberForDisplay}" /></TD>
				<TD><c:out value="${account.accountKubunMei}" /></TD>
				<TD><fmt:formatDate value="${account.entryDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /> <input type="hidden"
					value="${account.entryDate}" /></TD>
				<TD><fmt:formatDate value="${account.updateDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /> <input type="hidden"
					value="${account.updateDate}" /></TD>
				<TD><c:url value="/account/list/${account.accountId}" var="url" />
					<button onclick="location.href='${url}'">詳細</button> <c:url
						value="/account/list/${account.accountId}/edit" var="url" />
					<button onclick="location.href='${url}'">編集</button>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/account/list/create" var="url" />
	<button onclick="location.href='${url}'">登録</button>
	&nbsp;&nbsp;
	<c:url value="/" var="url" />
	<button onclick="location.href='${url}'">戻る</button>
</BODY>
</HTML>
