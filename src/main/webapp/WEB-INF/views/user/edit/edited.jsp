<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>更新完了</title>
</head>
<body>
<h1>更新完了</h1>
<dl>
  <dt>ユーザコード</dt>
  <dd><c:out value="${editUser.userCode}"/></dd>
  <dt>パスワード</dt>
  <dd><c:out value="${editUser.password}"/></dd>
  <dt>名前</dt>
  <dd><c:out value="${editUser.userName}"/></dd>
  <dt>Eメールアドレス</dt>
  <dd><c:out value="${editUser.mailAddress}"/></dd>
</dl>
<c:url var="url" value="/user"/>
<a href="${url}">戻る</a>
</body>
</html>