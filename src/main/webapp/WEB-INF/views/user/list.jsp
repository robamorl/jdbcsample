<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ一覧画面</title>
</head>
<body>
<h1>ユーザ一覧画面</h1>
<table border="1">
  <tr>
    <th>ID</th>
    <th>コード</th>
    <th>パスワード</th>
    <th>名前</th>
    <th>Eメールアドレス</th>
    <th>登録日時</th>
    <th>登録者</th>
    <th></th>
  </tr>
  <c:forEach items="${users}" var="user">
  <tr>
    <td><c:out value="${user.userId}"/></td>
    <td><c:out value="${user.userCode}"/></td>
    <td><c:out value="${user.password}"/></td>
    <td><c:out value="${user.userName}"/></td>
    <td><c:out value="${user.mailAddress}"/></td>
    <td><c:out value="${user.entryDate}"/></td>
    <td><c:out value="${user.entryUser}"/></td>
    <td>
      <c:url value="/user/${user.userId}" var="url"/>
      <a href="${url}">詳細</a>
      <c:url value="/user/${user.userId}/edit" var="url"/>
      <a href="${url}">編集</a>
    </td>
  </tr>
  </c:forEach>
</table>
</body>
</html>
