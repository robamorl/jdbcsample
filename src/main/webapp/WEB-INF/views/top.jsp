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
  <li><a href="user/">ユーザ一覧</a></li>
  <sec:authorize access="hasRole('ADMIN')">
    <li><a href="admin/admin.jsp">管理者専用ページへ</a></li>
  </sec:authorize>
</ul>
<form action="logout" method="post">
  <sec:csrfInput />
  <button>ログアウト</button>
</form>
</BODY>
</HTML>
