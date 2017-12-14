<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>口座登録画面</title>
</HEAD>
<BODY>
	<jsp:include page="../../header/login_info.jsp" />
	<h1>口座登録画面</h1>
	<form:form modelAttribute="editAccount">
		<dl>
			<dt>口座名</dt>
			<dd>
				<form:input path="accountName" />
				<form:errors path="accountName" />
			<dt>口座番号</dt>
			<dd>
				<form:input path="accountNumber" />
				<form:errors path="accountNumber" />
			</dd>
			<dt>口座区分</dt>
			<dd>
				<form:select path="accountKubun" items="${accountKubunList}"
					itemLabel="label" itemValue="value" />
				<form:errors path="accountKubun" />
			</dd>
		</dl>
		<button type="submit" name="_event_proceed" value="proceed">
			次へ</button>
			&nbsp;&nbsp;
		  <button type="submit" name="_event_back" value="back">戻る</button>
	</form:form>
</BODY>
</HTML>
