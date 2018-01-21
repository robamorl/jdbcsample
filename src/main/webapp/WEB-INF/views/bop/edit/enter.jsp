<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>収支更新画面</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/common.css" />">
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1 id=header>収支更新画面</h1>
	<form:form modelAttribute="editBop">
		<dl>
			<dt>処理口座</dt>
			<dd>
				<form:select cssErrorClass="error-input" path="accountId" items="${transactionAccountList}"
					itemLabel="label" itemValue="value"/>
				<form:errors cssClass="error-message" path="accountId" />
			<dt>収入/支出</dt>
			<dd>
				<form:select cssErrorClass="error-input" path="balanceOfPaymentsKubun" items="${bopKubunList}"
					itemLabel="label" itemValue="value" />
				<form:errors cssClass="error-message" path="balanceOfPaymentsKubun" />
			</dd>
			<dt>費目</dt>
			<dd>
				<form:select cssErrorClass="error-input" path="expensesKubun" items="${expensesKubunList}"
					itemLabel="label" itemValue="value" />
				<form:errors cssClass="error-message" path="expensesKubun" />
			</dd>
			<dt>金額</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="amount" />
				<form:errors cssClass="error-message" path="amount" />
			</dd>
			<dt>収支発生日</dt>
			<dd>
				<form:input cssErrorClass="error-input" path="transactionDate" />
				<form:errors cssClass="error-message" path="transactionDate" />
			</dd>
		</dl>
		<button type="submit" name="_event_proceed" value="proceed">
			次へ</button>
			&nbsp;&nbsp;
		  <button type="submit" name="_event_back" value="back">戻る</button>
	</form:form>
</BODY>
</HTML>
