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
		  <script type="text/javascript">
		  $(document).ready(function () {
	            $("#updateBtn").click(function () {
	                if (!$("#id").val()) {
	                    alert("아이디 입력!!!");
	                    return;
	                } else if (!$("#password").val()) {
	                    alert("비밀번호 입력!!!");
	                    return;
	                } else if (!$("#name").val()) {
	                    alert("이름 입력!!!");
	                    return;
	                } else if (!$("#address").val()) {
	                    alert("주소 입력!!!");
	                    return;
	                } else if (!$("#phoneNumber").val()) {
	                    alert("핸드폰 번호 입력!!!");
	                    return;
	                } else {
	                    $("#updateForm").attr("action", "${root}/user").submit();
	                }
	            });
	            
	        });
    </script>
    <style>
        .table-wrapper {
            display: grid;
            grid-template-columns: minmax(0, auto) minmax(0, 1fr);
            grid-gap: 0;
        }

        .header-item,
        .description-item {
            padding: 1rem;
            border: px solid #aaa;
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
        
        <div class="content">
    <div class="wrapper">

      <header style="font-family: SUIT-Medium;">회원 정보 수정</header>
      <form action="#" method="post" id="updateForm">
      <input type="hidden" name="act" value="update">
        <div class="field email" style="font-family: SUIT-Medium;">
          <div class="input-area">
            <input type="text" placeholder="ID" id ="id" name="id" value="${userInfo.id }" readonly="readonly">

          </div>
          <div class="error error-txt">ID can't be blank</div>
        </div>
        <div class="field password" style="font-family: SUIT-Medium;">
          <div class="input-area">
            <input type="password" placeholder="Pass Word" id ="password" name="password" value="${userInfo.password }">

          </div>
          <div class="error error-txt">Password can't be blank</div>
        </div>
        <div class="field email" id="form_name" style="font-family: SUIT-Medium;">
          <div class="input-area">
            <input type="text" placeholder="Name" id="name" name ="name" value="${userInfo.name }">

          </div>
          <div class="error error-txt">Name can't be blank</div>
        </div>
        <div class="field email" id="form_address" style="font-family: SUIT-Medium;">
          <div class="input-area">
            <input type="text" placeholder="Address" name="address" id="address" value="${userInfo.address }">

          </div>
          <div class="error error-txt">Address can't be blank</div>
        </div>
        <div class="age field email" id="form_phoneNumber" style="font-family: SUIT-Medium;">
          <div class="input-area">
            <input type="text" placeholder="phoneNumber" name="phoneNumber" id="phoneNumber" value="${userInfo.phoneNumber }">

          </div>
          <div class="error error-txt">phoneNumber can't be blank</div>
        </div>
        <input type="submit" value="Update" id="updateBtn">
      </form>
    </div>
  </div>
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