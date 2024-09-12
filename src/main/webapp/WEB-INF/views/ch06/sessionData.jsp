<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>

<div class="card">
	<div class="card-header">
		session에 저장된 데이터 이용
	</div>

	<div class="card-body">
		<div>
			<p>mid: ${member.mid}</p>	<!-- request범위의 데이터를 먼저 찾고, 없으면 세션에서 찾음 -->
			<p>mname: ${member.mname}</p>
			<p>memail: ${member.memail}</p>
		</div>
	

	</div>
</div>

<%-- <%@ include file="/WEB-INF/views/common/bottom.jsp" %> --%>

<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>