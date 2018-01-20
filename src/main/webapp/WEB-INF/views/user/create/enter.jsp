<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入力画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
    <jsp:include page="../../header/login_info.jsp"/>
	<h1 id=header>入力画面</h1>
	<form:form modelAttribute="editUser">
		<dl>
			<dt>ユーザコード</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="userCode" maxlength="8" />
				<form:errors cssClass="error-message" path="userCode" />
			</dd>
			<dt>パスワード</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="password" maxlength="16" />
				<form:errors cssClass="error-message" path="password" />
			</dd>
			<dt>名前</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="userName" maxlength="100" />
				<form:errors cssClass="error-message" path="userName" />
			</dd>
			<dt>メールアドレス</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="mailAddress" maxlength="100" />
				<form:errors cssClass="error-message" path="mailAddress" />
			</dd>
		</dl>
		<button type="submit" name="_event_proceed" value="proceed">
			次へ</button>
			&nbsp;&nbsp;
		  <button type="submit" name="_event_back" value="back">戻る</button>
	</form:form>
</BODY>
</HTML>
