<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- include 지시자의 역할: 외부의 파일의 내용을 가져와서 삽입시켜줌 --%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>


<div class="card">
	<div class="card-header">
		NullPointerException 처리
	</div>

	<div class="card-body">
		null인 상태의 필드나 메소드를 사용했습니다.
	</div>
</div>


<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>