//     1,qq号码：纯数字，不以0开头，5-11位     /^[1-9]\d{4,10}$/   
//     2,手机号:开头为1，第二个字符是3,4,5,7,8之间，最后9个数字 /^[1][34578]\d{9}$
//     3,Email:  格式为： 数字字母下划线或-或.  @  数字字母下划线-    .  2到3位小写字母
//     /^[0-9a-zA-Z._-]+[@][0-9a-zA-Z_-]+[.][a-z]{2,3}$/
//     4,用户名. 字母数字下划线10位以内, 必须是字母开头
// /^[a-zA-Z][0-9a-zA-Z_]{0,9}$/
//     5,密码. 任意字符, 6-16位	 
// /^.{6,16}$/	
							
						
$(function() {
	$("input").change(function() {
		
		// 获取要添加提示信息的div
		myDiv = $(this).parent().parent().children("div.col-sm-4")
		myDiv2 = $(this).parent().parent()

		var id = $(this).attr("id");
		if (id == "username") {
			$.ajax({
				url:"/api/checkUserName",
				type:"post",
				data:{"userName":$("#username").val()},
				success:function (info) {
					if(info.status==500){
						myDiv.html(
							'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">用户名已存在</span> '
						)
						e()
					}else {
						myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
						s()
					}
				}
			})
		} else if (id == "inpPWD") {
			var a = $(this).val();
			var regex = /^.{6,16}$/
			if (regex.test(a)) {
				myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
				s()
			} else {
				myDiv.html(
					'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">任意字符, 6-16位</span> '
				)
				e()
			}
		} else if (id == "inpPWD2") {
			var a = $(this).val();
			var b = $("#inpPWD").val();
			if (a == b) {
				myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
				s()
			} else {
				myDiv.html(
					'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">两次密码输入不一致</span> '
				)
				e()
			}
		} else if (id == "inpEmail") {
			var a = $(this).val();
			var regex = /^[0-9a-zA-Z._-]+[@][0-9a-zA-Z_-]+[.][a-z]{2,3}$/
			if (regex.test(a)) {
				myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
				s()
			} else {
				myDiv.html(
					'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">请输入正确的邮箱格式</span> '
				)
				e()
			}
			//检验验证码
		} else if (id == "phone") {
			var a = $(this).val();
			var regex = /^[1][34578]\d{9}$/
			if (regex.test(a)) {
				myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
				s()
			} else {
				myDiv.html(
					'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">请输入正确的手机号</span> '
				)
				e()
			}
			//检验验证码
		}
		else if(id == "inpYZM"){
			var val = $("#inpYZM").val().toLowerCase();
			var num = show_num.join("");
			if (val == num) {
				myDiv.html(' <p class="glyphicon glyphicon-ok text-success"></p>')
				s()
			} else {
				myDiv.html(
					'<p class="glyphicon glyphicon-remove text-danger"></p> <span class="text-danger">验证码错误</span> '
				)
				e()
			}
		}
	})
})

function s() {
	myDiv2.addClass("has-success")
	if (myDiv2.hasClass('has-error')) {
		myDiv2.removeClass('has-error')
	}
}

function e() {
	myDiv2.addClass("has-error")
	if (myDiv2.hasClass('has-success')) {
		myDiv2.removeClass('has-success')
	}
}

function register() {
    var userInfo = {};
    var userName = $("#username").val();
    var realName = $("#realName").val();
    var password = $("#inpPWD").val();
    var telPhone = $("#phone").val();
    var email = $("#inpEmail").val();
    var sex = $("input[name='sex']:checked").val();
    userInfo.userName = userName;
	userInfo.password = password;
	userInfo.telPhone = telPhone;
	userInfo.email = email;
	userInfo.sex = sex;
	userInfo.realName = realName;
    if(userName == ''  || password =='' || telPhone =='' || email =='' || sex ==''){
    	layer.alert("请完善信息！",{icon:0})
		return;
	}

	$.ajax({
		url:"/api/checkUserName",
		type:"post",
		data:{"userName":$("#username").val()},
		success:function (info) {
			if(info.status==200){
				$.ajax({
					url:"/api/register",
					type:"post",
					contentType: "application/json",
					data:JSON.stringify(userInfo),
					success:function (info) {
						if(info==null || info ==''){
							layer.msg(info.message,{icon:1,time:500});
							setTimeout(function () {
								location.href="/shopping/login";
							},500)
						}else{
							layer.alert(info.message,{icon:0});
						}
					}
				})
			}else {
				layer.alert("用户已存在",{icon:0});
				return;
			}
		}
	})



}


