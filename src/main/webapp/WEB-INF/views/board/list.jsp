<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board">
			
				<form id="search_form" action="${pageContext.servletContext.contextPath}/board" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					
					<!-- 게시판에 있는 글 보여주기-->
					<c:set var = "count" value = '${fn:length(list) }'/>
					<c:forEach items = '${list }' var = 'boardVo' varStatus='status'  >
						<tr>
							<td>${count - status.index }</td>
							
							<%-- <td style='padding-left:${40*vo.depth}px'>
								<img src="${pageContext.servletContext.contextPath }/assets/images/reply.png" style='padding-left:${40*vo.depth}px'/>
								<a href="${pageContext.servletContext.contextPath}/board?a=view&no=${boardVo.no}">${boardVo.title }</a>
							</td> --%>
							<td><a href="${pageContext.servletContext.contextPath}/board?a=view&no=${boardVo.no}">${boardVo.title }</a></td>
							<td>${boardVo.user_name }</td>
							<td>${boardVo.hit }</td>
							<td>${boardVo.reg_date }</td>

							<c:choose>
								<c:when test='${empty authUser}'>
									<td><a href="" class=""></a></td>
								</c:when>
								<c:otherwise>
									<!-- authUser.no와 board.user_no가 같으면!!! 보여줘야함. -->
									<c:choose>
										<c:when test='${authUser.no == boardVo.user_no }'>
											<td><a href="${pageContext.servletContext.contextPath}/board?a=delete&no=${boardVo.no}" class="del">삭제</a></td>
										</c:when>
										<c:otherwise>
											<td><a href="" class=""></a></td>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>

						</tr>
					</c:forEach>
						
					
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:if test='${!empty authUser}'>
						<a href="${pageContext.servletContext.contextPath }/board?a=writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="board"/>
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>