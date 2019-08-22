<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="login-wrap">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${title}</title>
    <link rel="stylesheet" href="static/bootstrap/css/bootstrap.css">
    <script src="static/jquery/jquery.js"></script>
    <script src="static/bootstrap/js/bootstrap.js"></script>
    <style>
        header div{
            background-image: linear-gradient(rgba(15,25,50,.3) 0,rgba(15,25,50,.3) 100%) !important;
        }
        .dropdown .open a:focus{
            /* background-color: #eee; */
            border-color: #337ab7 !important;
        }
        .dropdown-menu .divider {
            background-color: rgba(229, 229, 229, 0.5) !important;
        }
        #backgroundBody {
            width: 100vw;
            min-height: 100vh;
            background: no-repeat center;
            background-size: cover;
            background-attachment:fixed;
        }
        .iam-sys-item{overflow: hidden;margin: 20px 0px;cursor: pointer;display: block;transition: all .5s;-webkit-transition: all .5s;}
        .iam-sys-item:hover{transform: scale(1.15,1.15);-webkit-transform: scale(1.15,1.15);}
        .iam-sys-item>div{width: 75px;height: 75px;border-radius: 10px;margin: 0 auto 10px;
            background: no-repeat center center;
            background-size: 80% 80%;
            background-color: #ffffff;
        }
        .iam-sys-item>p{
            line-height: 24px;
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #FFFFFF;
            letter-spacing: 1.2px;
            text-align: center;
            white-space: nowrap;text-overflow: ellipsis;overflow: hidden;
        }
        .iam-sys{padding: 90px 15px;}
        .iam-sys>div{box-sizing: border-box;}


        @media only screen and (max-width: 767px) {
            header{height: 50px;}
            .iam-sys{padding: 60px 10px 60px 10px;}
            .iam-sys-item{margin: 15px 0; flex: 0 1 33.3333%;}
            .iam-sys-item>div{width: 60px;height: 60px;margin-bottom: 10px;}
            .iam-sys-item>p{font-size: 12px;line-height: 18px;width: 90%;margin: 0 auto;}
            .iam-sys>div{padding-left: 5px;padding-right: 5px;}
            .iam-userInfo>span{font-size: 12px;}
            .iam-userInfo {line-height: 30px;}
        }
        @media only screen and (max-width: 376px) {
            .iam-sys-item>p{width: 77px;}
        }
        .flex-box{display: flex;flex-wrap: wrap;justify-content: flex-start;align-content: space-between;}
        @media (min-width: 768px){
            .iam-sys-item{flex: 0 1 20%;}
        }
        @media (min-width: 992px){
            .iam-sys-item{flex: 0 1 16.66%;}
        }
        @media (min-width: 1200px){
            .iam-sys-item{flex: 0 1 14.2857%;}
        }
    </style>
</head>
<body id="backgroundBody">
<header>
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand">
                <span>${title}</span>
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        ${userDepartmentName}·${userBranchName}(${userCityName})
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                    <c:forEach items="${departments}" var="department" >
                        <li><a href="${changeDepartmentUrl}?departmentId=${department.departmentId}">${department.departmentName}·${department.branch.branchName}(${department.city.cityName})</a></li>
                    </c:forEach>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        知识库
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a id="btnHelp" href="#">帮助信息</a></li>
                    </ul>
                </li>
            </ul>
            <c:if test="${debug}">
            <ul class="nav navbar-nav">
                <li><a style="color:#F00;">调试模式</a></li>
            </ul>
            </c:if>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input id="keyword" type="text" class="form-control" placeholder="搜索关键字">
                </div>
                <div class="form-group">
                    <button id="btnSearch" class="btn btn-default">搜索</button>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" data-toggle="dropdown">
                        <img src="${user.userAvatar}" onerror="this.src='/avatars/default.jpg'" style="height:24px;width:24px;">
                        ${user.userName}·${user.nickName}
                        <b class="caret"></b>
                    </a>
                    <ul class="dropdown-menu">
                        <li><a id="btnMy" href="#">我的</a></li>
                        <li><a id="btnChangeBackground" href="#">切换墙纸</a></li>
                        <li><a id="btnLogout" href="#">退出</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</header>
<div class="container">
    <div class="main">
        <div class="flex-box">
            <c:forEach items="${apps}" var="app" >
                <div class="iam-sys-item"  onclick="window.location.href='${app.appUrl}'">
                    <div style="background-image: url(${app.appIcon});background-size:contain;"></div>
                    <p>${app.appTitle}</p>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script type="text/javascript">
    var backgrounds = [
        "static/image/background1.jpg",
        "static/image/background2.jpg",
        "static/image/background3.jpg",
        "static/image/background4.jpg"
    ];
    var index = Math.floor(Math.random() * backgrounds.length);
    var oBody=document.getElementById('backgroundBody');
    var oS=oBody.style;
    function bgChange(){
        if(index + 1< backgrounds.length){
            index++;
        }else{
            index = 0;
        }
        oS.backgroundImage='url('+ backgrounds[index] +')';
    };
    function bgPosition(){
        oS.backgroundRepeat='no-repeat';
        oS.backgroundPosition='center';
        oS.backgroundAttachment='fixed';
    };
    function changeBackground(){
        bgChange();
        bgPosition();
    };
    $("#btnHelp").click(function(){
        alert("等待开发");
    });
    $("#btnSearch").click(function(){
        alert("等待开发");
    });
    $("#btnChangeBackground").click(function(){
        changeBackground();
    });
    $("#btnMy").click(function(){
        alert("等待开发");
    });
    $("#btnLogout").click(function(){
        window.location.href='/logout'
    });
    $(document).ready(new function(){
        changeBackground();
    });
</script>
</body>

</html>