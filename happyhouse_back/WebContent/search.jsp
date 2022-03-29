<%@ page language="java" contentType="text/html; charset=UTF-8"%>
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
	<h1>검색</h1>
	<form method="post" action="main">
		<fieldset>
		<legend>검색 옵션</legend>
		<!-- front-controller pattern에서 요청을 구분하기 위한 parameter -->
		<input type="hidden" name="act" value="search">
		<label for="aptname">아파트명</label>
		<br>
		<select id="aptname" name="aptname">
            <option value="전체" selected>전체</option>
            <option value="청운현대">청운현대</option>
            <option value="광화문풍림스페이스본">광화문풍림스페이스본</option>
            <option value="경희궁의아침">경희궁의아침</option>
            <option value="대성스카이렉스">대성스카이렉스</option>
            <option value="현대뜨레비앙">현대뜨레비앙</option>
            <option value="효성주얼리시티">효성주얼리시티</option>
            <option value="CS타워">CS타워</option>
            <option value="송림아마레스아파트">송림아마레스아파트</option>
            <option value="아남">아남</option>
            <option value="창신쌍용1">창신쌍용1</option>
            <option value="창신쌍용2">창신쌍용2</option>
            <option value="일우">일우</option>
            <option value="두산">두산</option>
            <option value="창신이수">창신이수</option>
            <option value="창림">창림</option>
            <option value="롯데캐슬천지인">롯데캐슬천지인</option>
            <option value="종로청계힐스테이트">종로청계힐스테이트</option>
            <option value="종로동광모닝스카이">종로동광모닝스카이</option>
            <option value="종로센트레빌">종로센트레빌</option>
            <option value="할리우드파크아파트">할리우드파크아파트</option>
            <option value="삼전솔하임4차">삼전솔하임4차</option>
            <option value="경희궁자이">경희궁자이</option>
            <option value="대성아파트">대성아파트</option>
            <option value="삼성">삼성</option>
            <option value="앨리시아">앨리시아</option>
            <option value="일성빌라트">일성빌라트</option>
            <option value="현대">현대</option>
            <option value="인왕산아이파크">인왕산아이파크</option>
            <option value="인왕산2차아이파크">인왕산2차아이파크</option>
            <option value="경희궁롯데캐슬">경희궁롯데캐슬</option>
        </select>
        <br>
		<label for="dongname">법정동</label>
		<br>
		<select id="dongname" name="dongname">
            <option value="전체" selected>전체</option>
            <option value="청운동">청운동</option>
            <option value="사직동">사직동</option>
            <option value="내수동">내수동</option>
            <option value="견지동">견지동</option>
            <option value="익선동">익선동</option>
            <option value="인의동">인의동</option>
            <option value="충신동">충신동</option>
            <option value="명륜1가">명륜1가</option>
            <option value="명륜2가">명륜2가</option>
            <option value="창신동">창신동</option>
            <option value="숭인동">숭인동</option>
            <option value="평동">평동</option>
            <option value="홍파동">홍파동</option>
            <option value="교북동">교북동</option>
            <option value="행촌동">행촌동</option>
            <option value="평창동">평창동</option>
            <option value="무악동">무악동</option>
        </select>
        <br>
		<input type="submit" value="검색">
		<input type="reset" value="취소">
		</fieldset>
	</form>
</body>
</html>