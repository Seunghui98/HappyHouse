<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/header.jsp" %>
<!DOCTYPE html>
<html lang="en">
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
           <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Bootstrap icons-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />

    </head>
    <body id="page-top">
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top" id="mainNav">
            <div class="container">
                <a class="navbar-brand" href="#page-top">Happy House</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars ms-1"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav text-uppercase ms-auto py-4 py-lg-0">
                        <li class="nav-item"><a class="nav-link" href="index.jsp" style="font-family: SUIT-Medium;">Home</a></li>
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
            </div>
        </nav>
        <!-- Masthead-->
        <header class="masthead">
            <div class="container">
                <div class="masthead-subheading">Welcome To Our Happy House!</div>
                <div class="masthead-heading text-uppercase">It's Nice To Meet You</div>
                <a class="btn btn-primary btn-xl text-uppercase" href="${root }/main?act=list" style="font-family: SUIT-Medium;">거래 둘러보기</a>
            </div>
        </header>
     
        <!-- Services-->
        <section class="py-5" id="features">
            <div class="container px-5 my-5">
                <div class="row gx-5">
                    <div class="col-lg-4 mb-5 mb-lg-0">
                        <h2 class="fw-bolder mb-0" style="font-family: SUIT-Medium;">더 좋은 집 구하기는&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Happy House</h2>
                    </div>
                    <div class="col-lg-8">
                        <div class="row gx-5 row-cols-1 row-cols-md-2">
                            <div class="col mb-5 h-100">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i
                                        class="bi bi-collection"></i></div>
                                <h2 class="h5" style="font-family: SUIT-Medium;">쉽고 간편한 정보</h2>
                                <p class="mb-0" style="font-family: SUIT-Medium;">관심 있는 지역에 대한 정보를 한눈에 보기</p>
                            </div>
                            <div class="col mb-5 h-100">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i
                                        class="bi bi-building"></i></div>
                                <h2 class="h5" style="font-family: SUIT-Medium;">실시간 매매 정보 제공</h2>
                                <p class="mb-0" style="font-family: SUIT-Medium;">전 지역에 실시간 매매 정보를 제공</p>
                            </div>
                            <div class="col mb-5 mb-md-0 h-100">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i
                                        class="bi bi-toggles2"></i></div>
                                <h2 class="h5" style="font-family: SUIT-Medium;">매매 집에 대한 자세한 정보 제공</h2>
                                <p class="mb-0" style="font-family: SUIT-Medium;">자세한 집 정보를 제공하는 많은 데이터 보유</p>
                            </div>
                            <div class="col h-100">
                                <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i
                                        class="bi bi-toggles2"></i></div>
                                <h2 class="h5" style="font-family: SUIT-Medium;">부동산 관련 정책 뉴스 정보 제공</h2>
                                <p class="mb-0" style="font-family: SUIT-Medium;">부동산 정책과 관련해서 중요한 뉴스 정보를 제공</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div id="map" style="width: 100%;height: 500px;font-family: SUIT-Medium;"></div>
            <!-- Blog preview section-->
        <section class="py-5 bg-light" style="font-family: SUIT-Medium;">
            <div class="container px-5 my-5">
                <div class="row gx-5 justify-content-center">
                    <div class="col-lg-8 col-xl-6">
                        <div class="text-center">
                            <h2 class="fw-bolder">부동산 정책 뉴스</h2>
                            <p class="lead fw-normal text-muted mb-5">중요한 부동산 정책과 관련된 뉴스 모아모아</p>
                        </div>
                    </div>
                </div>
                <div class="row gx-5">
                    <div class="col-lg-4 mb-5">
                        <div class="card h-100 shadow border-0">

                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">News</div>
                                <a class="text-decoration-none link-dark stretched-link" href="https://www.joongang.co.kr/article/25054936#home">
                                    <h5 class="card-title mb-3">'부동산 표심' 잡은 尹…1주택 종부세율, 文정부 전으로 돌린다</h5>
                                </a>
                                <p class="card-text mb-0">1세대 1주택자가 올해 내는 종합부동산세를 지난해 수준에서 동결하는 방안이 추진된다. 올해분 재산세를 2020년
                                    수준으로 낮추는 방안도 모색된다. 정부는 윤석열 20대 대통령 당선에 맞춰 이런 내용을 담은 부동산 보유세 부담 완화 방안을 22일 올해 공동주택
                                    공시가격안 공개에 맞춰 발표하는 방안을 검토 중이다.</p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">

                                        <div class="small">
                                            <div class="fw-bold">중앙일보 - 손해용기자</div>
                                            <div class="text-muted">March 13, 2021 &middot; 6 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 mb-5">
                        <div class="card h-100 shadow border-0">

                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">News</div>
                                <a class="text-decoration-none link-dark stretched-link" href="https://www.mk.co.kr/news/realestate/view/2022/03/231783/">
                                    <h5 class="card-title mb-3">집값 폭등 대출규제…차기 정부 최고의 난제 '부동산', 해결책은</h5>
                                </a>
                                <p class="card-text mb-0">1세대 1주택자가 올해 내는 종합부동산세를 지난해 수준에서 동결하는 방안이 추진된다. 올해분 재산세를 2020년
                                    수준으로 낮추는 방안도 모색된다. 정부는 윤석열 20대 대통령 당선에 맞춰 이런 내용을 담은 부동산 보유세 부담 완화 방안을 22일 올해 공동주택
                                    공시가격안 공개에 맞춰 발표하는 방안을 검토 중이다.</p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">

                                        <div class="small">
                                            <div class="fw-bold">매일경제 - 조성신기자</div>
                                            <div class="text-muted">March 13, 2021 &middot; 4 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4 mb-5">
                        <div class="card h-100 shadow border-0">

                            <div class="card-body p-4">
                                <div class="badge bg-primary bg-gradient rounded-pill mb-2">News</div>
                                <a class="text-decoration-none link-dark stretched-link" href="http://mbiz.heraldcorp.com/view.php?ud=20220313000126">
                                    <h5 class="card-title mb-3">전셋값 급등 주범 낙인…수술대 오르는 임대차3법 [부동산360]</h5>
                                </a>
                                <p class="card-text mb-0">윤석열 대통령 당선인이 부동산 핵심 공약으로 ‘임대차3법 전면 재검토’를 내걸면서 개편 방향에도 관심이 쏠린다.
                                    임대차3법은 당초 세입자의 주거권 보장을 위해 도입됐으나, 전세매물 감소와 전셋값 상승 등 부작용을 낳으면서 1순위로 손질될 부동산 정책으로 언급됐다.
                                    올해 7월은 새 임대차법 시행 2년차를 맞아 또 한 번 전세시장이 요동칠 가능성이 있는 만큼 시장 혼란을 최소화할 보완책 마련이 필요할 것이라고
                                    전문가들은 조언했다.</p>
                            </div>
                            <div class="card-footer p-4 pt-0 bg-transparent border-top-0">
                                <div class="d-flex align-items-end justify-content-between">
                                    <div class="d-flex align-items-center">

                                        <div class="small">
                                            <div class="fw-bold">헤럴드 경제 - 양영경기자</div>
                                            <div class="text-muted">March 13, 2021 &middot; 10 min read</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
       
        
  
        <!-- Footer-->
        <footer class="footer py-4">
            <div class="container">
                <div class="row align-items-center">
                    <div class="col-lg-4 text-lg-start" style="font-family: SUIT-Medium;">Copyright &copy; SSAFY 광주 4반 이승희_홍준표</div>
                    

                </div>
            </div>
        </footer>

        <!-- Kakao Map -->
        <script type="text/javascript"
        src="//dapi.kakao.com/v2/maps/sdk.js?appkey=2868ed705319b1f4db9d256b28473d86&libraries=services&autoload=false">

        </script>

        <script>

            kakao.maps.load(() => {
                const container = document.getElementById('map');
                const options = {
                    center: new kakao.maps.LatLng(37.50149403497245, 127.03962751156699),
                    level: 5,
                };
                const map = new kakao.maps.Map(container, options);
                var geocoder = new kakao.maps.services.Geocoder();
    
                // 주소로 좌표를 검색합니다
                geocoder.addressSearch('서울특별시 강남구 역삼동 테헤란로 212', function (result, status) {
    
                    // 정상적으로 검색이 완료됐으면 
                    if (status === kakao.maps.services.Status.OK) {
    
                        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
    
                        // 결과값으로 받은 위치를 마커로 표시합니다
                        var marker = new kakao.maps.Marker({
                            map: map,
                            position: coords
                        });
    
                        // 인포윈도우로 장소에 대한 설명을 표시합니다
                        var infowindow = new kakao.maps.InfoWindow({
                            content: '<div style="width:150px;text-align:center;padding:6px 0;">SSAFY</div>'
                        });
                        infowindow.open(map, marker);
    
                        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
                        map.setCenter(coords);
                    }
                });
    
            });
    
    
        </script>
        
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <!-- * *                               SB Forms JS                               * *-->
        <!-- * * Activate your form at https://startbootstrap.com/solution/contact-forms * *-->
        <!-- * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *-->
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>