<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                        <li class="nav-item"><a class="nav-link" href="#about" style="font-family: SUIT-Medium;">실거래가 조회</a></li>
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
    <br>
    <br>
	<table id="house-list" style="width:100%;">
		<thead>
			<tr>
				<th>번호</th>
				<th>아파트코드</th>
				<th>아파트명</th>
				<th>법정동</th>
				<th>건설연도</th>
				<th>지번</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${his}" var="houseinfo" varStatus="vs">
				<tr>
					<td>${vs.count }</td>
					<td><a href = "${pageContext.request.contextPath}/main?act=result&aptcode=${houseinfo.aptcode }">${houseinfo.aptcode }</td>
					<td>${houseinfo.aptname }</td>
					<td>${houseinfo.sidoname } ${houseinfo.gugunname } ${houseinfo.dongname }</td>
					<td>${houseinfo.buildyear }</td>
					<td>${houseinfo.jibun }
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
    <br>
    <br>
    <br>
	<!-- Footer-->
        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start" style="font-family: SUIT-Medium;">Copyright &copy; SSAFY 광주 4반 이승희_홍준표</div>
                    

                </div>
            </div>
        </footer>
</body>
</html>