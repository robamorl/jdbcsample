<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
<meta charset="UTF-8">
<style type="text/css">
#header {
	width: 100%;
	height: 8%;
	color: #000000;
	border-bottom: solid 1px #d1d1d1;
	border-right: solid 1px #d1d1d1
		/* Permalink - use to edit and share this gradient: http://colorzilla.com/gradient-editor/#e2e2e2+0,d1d1d1+11,d1d1d1+11,dbdbdb+50,fefefe+100 */

    background: rgb(226, 226, 226); /* Old browsers */
	background: -moz-linear-gradient(top, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* FF3.6-15 */
	background: -webkit-linear-gradient(top, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* Chrome10-25,Safari5.1-6 */
	background: linear-gradient(to bottom, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
	filter: progid: DXImageTransform.Microsoft.gradient( startColorstr= '#e2e2e2',
		endColorstr= '#fefefe', GradientType= 0); /* IE6-9 */
	border-radius: 0px 20px 20px 0px;
	background: -moz-linear-gradient(top, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* FF3.6-15 */
	background: -webkit-linear-gradient(top, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* Chrome10-25,Safari5.1-6 */
	background: linear-gradient(to bottom, rgba(226, 226, 226, 1) 0%,
		rgba(209, 209, 209, 1) 11%, rgba(209, 209, 209, 1) 11%,
		rgba(219, 219, 219, 1) 50%, rgba(254, 254, 254, 1) 100%);
	/* W3C, IE10+, FF16+, Chrome26+, Opera12+, Safari7+ */
	filter: progid:DXImageTransform.Microsoft.gradient( startColorstr='#e2e2e2',
		endColorstr='#fefefe', GradientType=0);
}
</style>
</HEAD>
<BODY>

	<h1 id=header>ログインページ</h1>
	<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
		<b style="color: #FF0000;">ログインできませんでした。</b>
		<br>
		<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
	</c:if>
	<form action="processLogin" method="post">
		<sec:csrfInput />
		<dl>
			<dt>ログインID</dt>
			<dd>
				<input type="text" name="paramLoginId">
			</dd>
			<dt>パスワード</dt>
			<dd>
				<input type="password" name="paramPassword">
			</dd>
		</dl>
		<button>ログイン</button>
	</form>
</BODY>
</HTML>
