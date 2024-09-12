<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- include 지시자의 역할: 외부의 파일의 내용을 가져와서 삽입시켜줌 --%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>


<div class="card">
	<div class="card-header">
		권한 불충분
	</div>

	<div class="card-body">
		로그인이 되어 있지 않거나 <br>
		현재 로그인된 사용자는 권한이 없습니다.	
	
	</div>
</div>


<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>