<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE HTML>
<HTML>
<HEAD>
  <meta charset="UTF-8">
</HEAD>
<BODY>

<h1>ログインページ</h1>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
  <b style="color:#FF0000;">ログインできませんでした。</b><br>
  <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>
<form action="processLogin" method="post">
  <sec:csrfInput/>
  <dl>
    <dt>
      ログインID
    </dt>
    <dd>
      <input type="text" name="paramLoginId">
    </dd>
    <dt>
      パスワード
    </dt>
    <dd>
      <input type="password" name="paramPassword">
    </dd>
  </dl>
  <button>ログイン</button>
</form>
</BODY>
</HTML>
