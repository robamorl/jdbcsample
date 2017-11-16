<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>例外発生</title>
</HEAD>
<BODY>
    <jsp:include page="../header/login_info.jsp"/>
	<dl>
		<dt>例外クラス</dt>
		<dd>${exception.getClass().name}</dd>
		<dt>メッセージ</dt>
		<dd>${exception.message}</dd>
	</dl>

</BODY>
</HTML>