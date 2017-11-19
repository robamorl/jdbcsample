<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧画面</title>
</HEAD>
<BODY>
	<jsp:include page="../header/login_info.jsp" />
	<h1>ユーザ一覧画面</h1>
	<TABLE border="1">
		<TR>
			<TH>コード</TH>
			<TH>パスワード</TH>
			<TH>名前</TH>
			<TH>Eメールアドレス</TH>
			<TH>登録日時</TH>
			<TH>登録者</TH>
			<TH>更新日時</TH>
			<TH>更新者</TH>
			<TH></TH>
		</TR>
		<c:forEach items="${users}" var="user">
			<TR>
				<TD><c:out value="${user.userCode}" /></TD>
				<TD><c:out value="${user.password}" /></TD>
				<TD><c:out value="${user.userName}" /></TD>
				<TD><c:out value="${user.mailAddress}" /></TD>
				<TD><fmt:formatDate value="${user.entryDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /> <input type="hidden"
					value="${user.entryDate}" /></TD>
				<TD><c:out value="${user.entryUser}" /></TD>
				<TD><fmt:formatDate value="${user.updateDate}"
						pattern="yyyy/MM/dd HH:mm:ss" /> <input type="hidden"
					value="${user.updateDate}" /></TD>
				<TD><c:out value="${user.updateUser}" /></TD>
				<TD><c:url value="/user/list/${user.userId}" var="url" />
					<button onclick="location.href='${url}'">詳細</button> <c:url
						value="/user/list/${user.userId}/edit" var="url" />
					<button onclick="location.href='${url}'">編集</button>
			</TR>
		</c:forEach>
	</TABLE>
	<c:url value="/user/list/create" var="url" />
	<button onclick="location.href='${url}'">登録</button>
	&nbsp;&nbsp;
	<c:url value="/" var="url" />
	<button onclick="location.href='${url}'">戻る</button>
</BODY>
</HTML>
