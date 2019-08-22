//短信倒计时
var count=60;
//随机识别码
var uuid = null;
//二维码验证句柄
var qrcodeTimeout = null;

function changeLoginType(type){
	if(type == 'password'){
		$("#password-login").show();
		$("#qrcode-login").hide();
        clearTimeout(qrcodeTimeout);
	}else{
		$("#password-login").hide();
		$("#qrcode-login").show();
        generateQrCode();
	}
}
/**
 * 生成一个新的二维码
 */
function generateQrCode(){
    $.ajax({
        type: 'post',
        data: {},
        dataType: 'json',
        url: '/qr/generate?' + Math.random(),
        success: function (data) {
            $("#qrCodeImage").attr("src", "data:image/png;base64," + data['qrCode']);
            uuid = data['uuid'];
            clearTimeout(qrcodeTimeout);
            check();
            $("#qrCodeMsg").html('');
        },
        error: function (data) {
            $("#qrCodeMsg").html('获取登录二维码失败！');
        }
    });
}

/**
 * 检测二维码授权是否通过
 */
function check() {
    $.ajax({
        type: 'post',
        data: {},
        dataType: 'json',
        url: '/qr/check?uuid=' + uuid,
        success: function (data) {
            $("#qrCodeMsg").html('');
            var referrer = $("#referrer").val();
            var code = data['code'];
            var desc = data['desc'];
            if (code == '0000') {
                window.location.href = referrer;
            } else if (code == '0010') {
                //等待授权,2秒后查询
                qrcodeTimeout = setTimeout(function () {check();}, 2000)
            } else {
                $("#qrCodeMsg").html(desc);
                //设置循环检查是否通过绑定授权
            }
        },
        error: function (data) {
            $("#qrCodeMsg").html('获取登录信息失败！');
        }
    });
}
/**
 * 发送短信验证码
 */
function sendSmsCode(){
	if(count < 60){
		return;
	}
	//设置发送验证码短信倒计时
    smsTimer();

    $.post('/sms/send', {'mobileNo': $("#mobileNo").val()}, function (result) {
        if (result.code == "0009") {
            $("#smsCode").val(result.smsCode);
        }else if (result.code != "0000") {
            alert(result.desc);
        }
    });
}
/**
 * 短信倒计时
 */
function smsTimer(){
	var btn = $("#send-btn");
    btn.html(count+'s')
    var timer = setInterval(function () {
        count--;
        btn.html(count+'s')
        if(count<=0){
            btn.html('获取验证码')
            count=60;
            clearInterval(timer);
        }
    }, 1000);
}