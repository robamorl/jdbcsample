<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<HTML>
<HEAD>
  <meta charset="UTF-8">
  <sec:csrfMetaTags/>
</HEAD>
<BODY>
<jsp:include page="header/login_info.jsp"/>
<h1>トップページ</h1>
トップページです。
<ul>
  <sec:authorize access="hasRole('ADMIN')">
      <li><a href="user/">ユーザ管理</a></li>
  </sec:authorize>
  <li><a href="account/">口座管理</a></li>
  <li><a href="bop/">収支管理</a></li>
</ul>
<form action="logout" method="post">
  <sec:csrfInput />
  <button>ログアウト</button>
</form>
</BODY>
</HTML>
