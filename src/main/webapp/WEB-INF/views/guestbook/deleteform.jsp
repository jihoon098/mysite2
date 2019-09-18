<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>mysite2</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="guestbook">
				
				<form method='post' action='${pageContext.servletContext.contextPath }/guestbook'>
					<input type='hidden' name="a" value='delete'> 
					<input type='hidden' name="no" value='${param.no }'>
					<table>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
							<td><input type="submit" value="확인"></td>
							<td><a href="${pageContext.servletContext.contextPath }/guestbook">메인으로
									돌아가기</a></td>
						</tr>
					</table>
				</form>

			</div>
		</div>

		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />

	</div>
</body>
</html>