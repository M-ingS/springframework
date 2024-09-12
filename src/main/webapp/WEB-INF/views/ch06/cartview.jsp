<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %> <!-- 외부 태그 라이브러리 사용 -->

<div class="card">
	<div class="card-header">
		장바구니
	</div>

	<div class="card-body">
		
		<ul>			<!-- get메소드를 호출해서 리턴값으로 contents를 받아서 사용(getter) -->
			<c:forEach items="${cart.contents}" var="item">	<!-- items에서 가져온 객체를 item에 넣음 -->
				<li class="m-2">
					<span>${item.pname}</span>
					<a href="deleteitem?pno=${item.pno}" class="btn btn-danger btn-sm">삭제</a></li>
			</c:forEach>		
		</ul>
	</div>
</div>

<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>