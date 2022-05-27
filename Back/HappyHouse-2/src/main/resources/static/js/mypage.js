$(document).ready(function () {
    // 로그인 여부
    const user = JSON.parse(localStorage.getItem("user"));


    if (user) {
        $(".description-item").eq(0).text(user.id);
        $(".description-item").eq(1).text(user.password);
        $(".description-item").eq(2).text(user.name);
        $(".description-item").eq(3).text(user.address);
        $(".description-item").eq(4).text(user.phone);
    }
    $(function(){
        $(".btn-btn").eq(0).click(function(){
            location.href = 'index.html';
        });
    });

    $(function(){
        $(".btn-btn").eq(1).click(function(){
            location.href = 'profileUpdate.html';
        });
    });

});



