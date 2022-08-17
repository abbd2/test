<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
<h1>restClient.jsp</h1>

<script>
//put: 자원의 전체 교체, 자원내 모든 필드 필요(만약 전체가 아닌 일부만 전달할 경우, 전달한 필드외 모두 null or 초기값 처리되니 주의!!)
//patch: //자원의 부분 교체, 자원내 일부 필드 필요
$.ajax({
	url:'board/restTest',
	//url:'rest/member/30/cha',
	//url: 'rest/check?h=130&w=100',
	method:'get', //type은 안되는 경우 있음 
	//data:{_method:"put"},  //put,patch,delete 모두 method:'post'설정 모든 브라우저에서 실행할때
	//method:'delete',
	//dataType:'json', //'xml','json' //rest컨트롤러 이용
	success:function(data, status, xhr){
		console.log(data);
		console.log(status);
		console.log(xhr);
	},
	error:function(xhr,status){
		console.log(xhr);
		console.log(status);
	}
});//ajax End
</script>
</body>
</html>