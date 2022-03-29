<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!-- DTO를 참조하기 위해서 import 처리가 필요하다. -->

<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Happy House</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Font Awesome icons (free version)-->
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <!-- Google fonts-->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
        <link href="https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700" rel="stylesheet" type="text/css" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
		  <link rel="stylesheet" href="css/login.css">
		  <link rel="stylesheet" href="css/styles2.css">
		  <link href="css/styles.css" rel="stylesheet" />
		<style>
		* {
		font-family: SUIT-Medium;
		}
		</style>
</head>
<body>
		<c:if test="${!empty msg}">
		<script type="text/javascript">
		alert("${msg}");
		</script>
		</c:if>
        <!-- Navigation-->
        <div id="page-top">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top navbar-shrink" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">Happy House</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="index.html" style="font-family: SUIT-Medium;">Home</a></li>
              
                        <c:if test="${!empty userInfo }">
                        <li class="nav-item"><a class="nav-link" href="${root }/notice?act=mvnotice" style="font-family: SUIT-Medium;">공지사항</a></li> 
                        <li class="nav-item"><a class="nav-link" href="${root }/main?act=list" style="font-family: SUIT-Medium;">실거래가 조회</a></li>
                        <li class="nav-item"><a class="nav-link" href="#team" style="font-family: SUIT-Medium;">관심 지역 둘러보기</a></li>
                        <li class="nav-item"><a class="nav-link" href="#contact" style="font-family: SUIT-Medium;">관심 지역 둘러보기</a></li>
                        </c:if>
                        
    
                        <li>
                        <c:if test="${empty userInfo }">
                        <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-family: SUIT-Medium;">로그인
                                </button>
                                <div class="dropdown-menu">
                                  <a class="dropdown-item" href="${root }/user?act=mvlogin" style="font-family: SUIT-Medium;">로그인</a>
                                  <a class="dropdown-item" href="${root }/user?act=mvsignup" style="font-family: SUIT-Medium;">회원가입</a>
                                </div>
                        	</div>
                        </c:if>
                        <c:if test="${!empty userInfo }">
                        <div class="dropdown">
                                <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" style="font-family: SUIT-Medium;">로그아웃
                                </button>
                                <div class="dropdown-menu">
                                  <a class="dropdown-item" href="${root }/user?act=logout" style="font-family: SUIT-Medium;">로그아웃</a>
                                  <a class="dropdown-item" href="${root }/user?act=mypage" style="font-family: SUIT-Medium;">마이페이지</a>
                                </div>
                        	</div>
                        </c:if>
                        </li>
                        
                    </ul>
                </nav>
        </div>
    <br>
    <br>
    <br>
    <br>
	<h1>상세</h1>
	<h2>아파트 등록 정보</h2>
	<c:if test="${!empty houseinfo }">
		<table>
			<thead>
				<tr>
					<th>항목</th>
					<th>내용</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>아파트코드</td>
					<td>${houseinfo.aptcode }</td>
				</tr>
				<tr>
					<td>아파트명</td>
					<td>${houseinfo.aptname }</td>
				</tr>
				<tr>
					<td>법정동</td>
					<td>${houseinfo.sidoname } ${houseinfo.gugunname } ${houseinfo.dongname }</td>
				</tr>
				<tr>
					<td>건설연도</td>
					<td>${houseinfo.buildyear }</td>
				</tr>
				<tr>
					<td>지번</td>
					<td>${houseinfo.jibun }</td>
				</tr>
			</tbody>
			<div></div>
			<thead>
			<tr>
				<th>거래번호</th>
				<th>거래가</th>
				<th>거래일자</th>
				<th>면적</th>
				<th>층</th>
			</tr>
			</thead>
		<tbody>
			<c:forEach items="${dis}" var="housedeal" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td>${housedeal.dealamount } 만원</td>
					<td>${housedeal.dealyear }년 ${housedeal.dealmonth }월 ${housedeal.dealday }일</td>
					<td>${housedeal.area }</td>
					<td>${housedeal.floor }
				</tr>
				</c:forEach>
		</tbody>
		</table>
	</c:if>
	<c:if test="${empty houseinfo }">
		<p>등록된 정보가 없습니다.</p>
	</c:if>
	<li><a href="./search.jsp">검색</a>
	<a href="./main?act=list">전체 리스트</a> 
	<br>
    <br>
    <br>
    <br>

	
</body>
</html>