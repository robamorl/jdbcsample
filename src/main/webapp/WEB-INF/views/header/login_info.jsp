<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<sec:authorize access="isAnonymous()">
未ログイン
</sec:authorize>
<sec:authorize access="isAuthenticated()">
  <sec:authentication var="user" property="principal"/>
  ログインユーザ：${user.userNameJp}<BR>
  権限：${user.description}
</sec:authorize>