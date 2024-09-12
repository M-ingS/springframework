<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- include 지시자의 역할: 외부의 파일의 내용을 가져와서 삽입시켜줌 --%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>


<div class="card">
	<div class="card-header">
		exception 처리
	</div>

	<div class="card-body">
		알 수 없는 예외 발생이 되어 처리됨.
	</div>
</div>


<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>