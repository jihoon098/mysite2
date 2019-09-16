<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");
%>
<!DOCTYPE html>
<html>
<head>
<title>mysite2</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath()%>/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />

		<div id="content">
			<div id="guestbook">
				
				<form method='post' action='<%=request.getContextPath()%>/guestbook'>
					<input type='hidden' name="a" value='delete'> <input
						type='hidden' name="no" value='<%=no%>'>
					<table>
						<tr>
							<td>비밀번호</td>
							<td><input type="password" name="password"></td>
							<td><input type="submit" value="확인"></td>
							<td><a href="<%=request.getContextPath()%>/guestbook">메인으로
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