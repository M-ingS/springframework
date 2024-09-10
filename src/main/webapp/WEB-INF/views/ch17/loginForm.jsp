<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%-- include 지시자의 역할: 외부의 파일의 내용을 가져와서 삽입시켜줌 --%>
<%@ include file="/WEB-INF/views/common/top.jsp" %>


<div class="card">
	<div class="card-header">
		로그인
	</div>

	<div class="card-body">
		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
	    	<div class="alert alert-danger mb-2" role="alert">
	    		<c:if test="${SPRING_SECURITY_LAST_EXCEPTION.message == 'Bad credentials'}">
	    			아이디 또는 비밀번호가 틀립니다.
	    		</c:if>
	    		
	    	</div>
	    </c:if>
	
	    <form method="post" action="${pageContext.request.contextPath}/login">
	       <%--
	       <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	       --%>
	       <div class="form-group mb-2">
	           <label for="mid">Member ID</label>
	           <input type="text" class="form-control" id="mid" name="mid">
	       </div>
	       <div class="form-group mb-2">
	          <label for="mpassword">Member Password</label>
	          <input type="password" class="form-control" id="mpassword" name="mpassword">
	       </div>
	       <button type="submit" class="btn btn-info btn-sm mt-2">로그인</button>
	    </form>   
	    
	    
	</div>
</div>


<%-- include 액션의 역할: 외부의  jsp를 실행하고 그 결과를 삽입시켜줌--%>
<jsp:include page="/WEB-INF/views/common/bottom.jsp"/>