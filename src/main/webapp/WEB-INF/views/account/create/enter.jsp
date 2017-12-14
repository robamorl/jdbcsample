<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>口座登録画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1>口座登録画面</h1>
	<form:form modelAttribute="editAccount">
		<dl>
			<dt>口座名</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="accountName" maxlength="30"/>
				<form:errors cssClass="error-message" path="accountName"/>
			<dt>口座番号</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="accountNumber" maxlength="16" />
				<form:errors cssClass="error-message" path="accountNumber" />
			</dd>
			<dt>口座区分</dt>
			<dd>
				<form:select path="accountKubun" items="${accountKubunList}"
					itemLabel="label" itemValue="value" />
				<form:errors cssClass="error-message" path="accountKubun" />
			</dd>
		</dl>
		<button type="submit" name="_event_proceed" value="proceed">
			次へ</button>
			&nbsp;&nbsp;
		  <button type="submit" name="_event_back" value="back">戻る</button>
	</form:form>
</BODY>
</HTML>
