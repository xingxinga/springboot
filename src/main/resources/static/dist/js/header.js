 /** 获取url参数
  * @param name  参数名 
  * by wuyi
  **/
 //截取字段
// function GetQueryString(name) {
// 	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
// 	var r = window.location.search.substr(1).match(reg);
// 	if(r != null)
// 		return decodeURIComponent(r[2]);
// 	return null;
// }
 //点击头像跳转页面
	function ToPersonSetting(){
		window.location.href = site_url+'/me/liubin/me/meToBack/pages/login.jsp';
	}
	
var UrlFull = location.href,
 	UrlHead;
 if(~UrlFull.indexOf("https")) {
 	UrlHead = "https";
 } else {
 	UrlHead = "http";
 }
var site_url = UrlHead + '://' + location.host;
var aside =  '<aside class="main-sidebar">' +
				'<section class="sidebar">' +
					'<div class="user-panel">' +
					'<div class="pull-left image" onclick="ToPersonSetting()"><img src="'+site_url+'/me/liubin/me/meToBack/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image"></div>' +
					'<div class="pull-left info">' +
						'<p>hr</p><a href="#"><i class="fa fa-circle text-success"></i> Online</a></div>' +
					'</div>' +
					//侧边栏
					'<ul class="sidebar-menu" data-widget="tree">' +
						'<shiro:hasRole name="admin">'+
							 '<li>' +
								'<a href="'+site_url+'/me/liubin/me/meToBack/pages/head/indexManage.html">' +
									'<i class="fa fa-paw"></i><span>系统配置</span>' +
								'</a>'  +
							 '</li>' +
						 '</shiro:hasRole>'+
						'<li class="treeview">' +
							'<a href="#">' +
								'<i class="fa fa-paw"></i> <span>交易管理</span>' +
								'<span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>' +
							'</a>' +
							'<ul class="treeview-menu">' +
								'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/tourist.html">发票管理</a></li>' +
								/*'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/technology.html">前端技术</a></li>' +
								'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/myLife.html">点滴生活</a></li>' +
								'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/books.html">书籍篇</a></li>' +
								'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/doitmyself.html">手工篇</a></li>' +
								'<li><a href="'+site_url+'/me/liubin/me/meToBack/pages/model/foods.html">美食篇</a></li>' +*/
							'</ul>' +
						'</li>' +
						'<li>' +
							'<a href="'+site_url+'/me/liubin/me/meToBack/pages/comment/commentManage.html">' +
								'<i class="fa fa-paw"></i><span>评论管理</span>' +
							'</a>'  +
						'</li>' +
					'</ul>' +
				'</section>' +
			'</aside>';
var header =  '<header class="main-header">' +
				'<a href="'+site_url+'/me/liubin/me/meToBack/pages/login.jsp" class="logo"><span class="logo-lg">后台管理</span></a>' +
				'<nav class="navbar navbar-static-top">' +
					'<div class="navbar-custom-menu">' +
						'<ul class="nav navbar-nav">' +
							'<li class="dropdown user user-menu">'+
								'<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="../../dist/img/user2-160x160.jpg" class="user-image" alt="User Image">'+
								'</a><span></span>'+
							'</li>' +
						'</ul>' +
					'</div>' +
				'</nav>' +
			'</header>';
$('.wrapper').prepend(header);
$('.wrapper').prepend(aside);
$(function(){
	var url = window.location.href;
	var str_after = url.split('pages/')[1];
	var str_before = str_after.split('/')[0];
	var model_style =  ((str_after.split('/')[1]).split("?")[0]).split(".")[0];
	
	var nav = $('.main-sidebar>.sidebar').eq(0).show();
	if(str_before=='head'){
		var modular = nav.children(".sidebar-menu").children('li').eq(0).addClass('active');
	}else if(str_before=='model'){
		var modular = nav.children(".sidebar-menu").children('li').eq(1).addClass('menu-open');
		var modularList = modular.find('.treeview-menu').show();
		if(model_style == "tourist" || model_style == "updateTourist"){
			modularList.find('li').eq(0).addClass('active');
		}else if(model_style == "technology"){
			modularList.find('li').eq(1).addClass('active');
		}else if(model_style == "myLife" || model_style == "updateMyLife"){
			modularList.find('li').eq(2).addClass('active');
		}else if(model_style == "books"){
			modularList.find('li').eq(3).addClass('active');
		}else if(model_style == "doitmyself"){
			modularList.find('li').eq(4).addClass('active');
		}else if(model_style == "foods"){
			modularList.find('li').eq(5).addClass('active');
		}
		
	}else if(str_before=='comment'){
		var modular = nav.children(".sidebar-menu").children('li').eq(2).addClass('active');
	}
	
})
