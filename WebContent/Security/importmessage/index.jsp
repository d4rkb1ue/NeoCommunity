<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<title>选择输入项目</title>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="../../css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="../../css/style.css" />
<link rel="stylesheet" type="text/css"
	href="../../assets/css/main-min.css">
<script type="text/javascript" src="../../js/jquery.js"></script>
<script type="text/javascript" src="../../js/jquery.sorted.js"></script>
<script type="text/javascript" src="../../js/bootstrap.js"></script>
<script type="text/javascript" src="../../js/ckform.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>

<script>
	$(function() {

		$('#commit').click(function() {
			window.location.href = "importDosage";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "index.html";

			window.location.href = url;

		}

	}
</script>
<style type="text/css">
body {
	padding-bottom: 40px;
}

.sidebar-nav {
	padding: 9px 0;
}

@media ( max-width : 980px) {
	/* Enable use of floated navbar text */
	.navbar-text.pull-right {
		float: none;
		padding-left: 5px;
		padding-right: 5px;
	}
}

.ckbox {
	display: block;
	float: left;
	width: 80px;
	height: 20px;
	margin-left: 10px;
	margin-top: 10px;
}
</style>
</head>

<body>
	<div class="newhead">
		<span>选择输入项目</span>
	</div>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>项目</th>
				<th>选项</th>
			</tr>
		</thead>
		<tr>
			<td>楼栋</td>

			<td>
				<div style="width: 600px;">
					<label class="ckbox"> <input type="checkbox" name="01">海韵1
					</label> <label class="ckbox"> <input type="checkbox" name="02">海韵2
					</label> <label class="ckbox"> <input type="checkbox" name="03">海韵3
					</label> <label class="ckbox"> <input type="checkbox" name="04">海韵4
					</label> <label class="ckbox"> <input type="checkbox" name="05">海韵5
					</label> <label class="ckbox"> <input type="checkbox" name="06">海韵6
					</label> <label class="ckbox"> <input type="checkbox" name="07">海韵7
					</label> <label class="ckbox"> <input type="checkbox" name="08">海韵8
					</label> <label class="ckbox"> <input type="checkbox" name="09">海韵9
					</label> <label class="ckbox"> <input type="checkbox" name="10">海韵10
					</label> <label class="ckbox"> <input type="checkbox" name="11">海韵11
					</label> <label class="ckbox"> <input type="checkbox"
						name="quanxuanlou" />全选
					</label>
				</div>
			</td>
		</tr>
		<tr>
			<td>水电表</td>

			<td><label class="ckbox"> <input type="checkbox"
					name="waterCheckbox">水表
			</label> <label class="ckbox"> <input type="checkbox"
					name="electricityCheckbox">电表
			</label></td>
		</tr>
		<tr>
			<td>公共表</td>

			<td><label class="ckbox"> <input type="checkbox"
					name="pwaterCheckbox">水表
			</label> <label class="ckbox"> <input type="checkbox"
					name="pwaterCheckbox">水表
			</label></td>
		</tr>
		<tr>
			<td>输出选择</td>
			<td><label class="ml10"> <input type="radio"
					name="ouput">公共表单独列出
			</label> <br /> <label class="ml10"> <input type="radio"
					name="ouput">列出上期数据
			</label></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<button type="submit" class="btn btn-primary ml10" id="commit">确认</button>
				<button type="button" class="btn btn-success ml30">打印已录入信息</button>
			</td>
		</tr>
	</table>

</body>
</html>