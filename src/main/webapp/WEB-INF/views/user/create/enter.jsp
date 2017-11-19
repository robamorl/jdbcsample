<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力画面</title>
</HEAD>
<BODY>
    <jsp:include page="../../header/login_info.jsp"/>
	<h1>入力画面</h1>
	<form:form modelAttribute="editUser">
		<dl>
			<dt>ユーザコード</dt>
			<dd>
				<form:input path="userCode" />
				<form:errors path="userCode" />
			</dd>
			<dt>パスワード</dt>
			<dd>
				<form:input path="password" />
				<form:errors path="password" />
			</dd>
			<dt>名前</dt>
			<dd>
				<form:input path="userName" />
				<form:errors path="userName" />
			</dd>
			<dt>メールアドレス</dt>
			<dd>
				<form:input path="mailAddress" />
				<form:errors path="mailAddress" />
			</dd>
		</dl>
		<button type="submit" name="_event_proceed" value="proceed">
			次へ</button>
			&nbsp;&nbsp;
		  <button type="submit" name="_event_back" value="back">戻る</button>
	</form:form>
</BODY>
</HTML>
