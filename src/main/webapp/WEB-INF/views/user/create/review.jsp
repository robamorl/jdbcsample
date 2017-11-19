<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>確認画面</title>
</HEAD>
<BODY>
    <jsp:include page="../../header/login_info.jsp"/>
	<h1>確認画面</h1>
	<form method="post">
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
		 <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		<button type="submit" name="_event_confirmed">登録</button>
		<button type="submit" name="_event_revise">再入力</button>
	</form>
</BODY>
</HTML>