<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	int check = (int)request.getAttribute("check");
%>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="<%=request.getContextPath() %>/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="user">
				<p class="jr-success">
					<% if(check > 0) {%>
					회원 정보를  수정했습니다.
					<% } else {%>
					회원 정보 수정을 실패했습니다.
					<% } %>
					<br><br>
					<a href="<%=request.getContextPath() %>">홈으로 돌아가기</a>
				</p>				
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/includes/navigation.jsp" />
		<jsp:include page="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>