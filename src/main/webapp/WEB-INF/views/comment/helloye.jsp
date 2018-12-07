<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>评论管理</title>
    <link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="/static/bower_components/Ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
    <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
    <link rel="stylesheet" type="text/css" href="/static/dist/css/pages/index.css" />
</head>

<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <div class="content-wrapper">
        <section class="content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="box">
                        <div class="box-header comment-header">
                            <form action="">
                                <!--时间-->
                                <div class="form-group">
                                    <label class="control-label">时间：</label>
                                    <input type="text" class="form-control" id="test6">
                                </div>
                                <!--模块-->
                                <div class="form-group">
                                    <label class="control-label">模块：</label>
                                    <select name="" class="form-control">
                                        <option value="">全部</option>
                                        <option value="">旅游与摄影</option>
                                        <option value="">前端技术</option>
                                        <option value="">点滴生活</option>
                                        <option value="">书籍</option>
                                        <option value="">手工</option>
                                        <option value="">美食篇</option>
                                    </select>
                                </div>
                                <!--状态-->
                                <div class="form-group">
                                    <label class="control-label">状态：</label>
                                    <select name="" class="form-control">
                                        <option value="">全部</option>
                                        <option value="">正常</option>
                                        <option value="">已屏蔽</option>
                                        <option value="">已删除</option>
                                    </select>
                                </div>
                                <!--关键字-->
                                <div class="form-group">
                                    <label class="control-label">关键字：</label>
                                    <input type="text" name="" id="" />
                                </div>
                                <button class="btn btn-info">搜索</button>
                            </form>
                        </div>
                        <!-- /.box-header -->
                        <div class="box-body table-responsive no-padding">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <th>ID</th>
                                    <th>模块</th>
                                    <th>用户名</th>
                                    <th>评论内容</th>
                                    <th>联系方式</th>
                                    <th>时间</th>
                                    <th>状态</th>
                                    <th>操作</th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>前端技术</td>
                                    <td>John</td>
                                    <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                    <td>131458945689</td>
                                    <td>2018-05-05</td>
                                    <td>正常</td>
                                    <td class="comment-oprate"><span>屏蔽</span><span>删除</span></td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>前端技术</td>
                                    <td>John</td>
                                    <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                    <td>131458945689</td>
                                    <td>2018-05-05</td>
                                    <td>已屏蔽</td>
                                    <td class="comment-oprate"><span>正常</span><span>删除</span></td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>前端技术</td>
                                    <td>John</td>
                                    <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                    <td>131458945689</td>
                                    <td>2018-05-05</td>
                                    <td>正常</td>
                                    <td class="comment-oprate"><span>屏蔽</span><span>删除</span></td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>前端技术</td>
                                    <td>John</td>
                                    <td>Bacon ipsum dolor sit amet salami venison chicken flank fatback doner.</td>
                                    <td>131458945689</td>
                                    <td>2018-05-05</td>
                                    <td>正常</td>
                                    <td class="comment-oprate"><span>屏蔽</span><span>删除</span></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
            </div>

        </section>
    </div>

</div>

<script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
<script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="/static/dist/js/adminlte.min.js"></script>
<script src="/static/dist/js/header.js"></script>
<!--时间插件-->
<script src="/static/dist/js/laydate.js"></script>
<script type="text/javascript">
    //日期范围
    laydate.render({
        elem: '#test6'
        ,range: true
    });
</script>
</body>

</html>
