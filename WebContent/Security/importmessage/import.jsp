<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<title>信息录入</title>
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
		<span>信息录入</span>
	</div>
	<div class="m20">
		<label class="dpinline ml30 "><input type="checkbox" />显示上期数据
		</label> <label class="dpinline ml20"><input type="checkbox" />显示全部数据
		</label> <label class="dpinline ml20"><input type="checkbox" />只读 </label>
	</div>
	<table class="table table-bordered table-hover definewidth m10">
		<thead>
			<tr>
				<th>房号</th>
				<th colspan="2" style="text-align: center;">电表</th>
				<th colspan="2" style="text-align: center;">水表</th>
			</tr>
		</thead>
		<tr>
			<td></td>
			<td>上月数据</td>
			<td>本月数据</td>
			<td>上月数据</td>
			<td>本月数据</td>
		</tr>
		<s:iterator value="houseInfos" id="houseInfo">
			<tr>
				<td><s:property value="#houseInfo.serialNum" /></td>
				<td><span><s:property value="#houseInfo.preWaterDosage" /></span></td>
				<td><input type="text" /></td>
				<td><span><s:property
							value="#houseInfo.preElectricityDosage" /></span></td>
				<td><input type="text" /></td>
			</tr>
		</s:iterator>

		<tr>
			<td>操作</td>
			<td colspan="4">
				<!--<button type="submit" class="btn btn-primary ml10">确认</button>-->
				<button type="button" class="btn btn-success ml30">上一层</button> <span
				class="ml20">跳转至</span> <input type="text"
				style="width: 20px; margin-left: 5px; margin-top: 3px;" />
				<button type="button" class="btn btn-success ml30">下一层</button>
				<button type="submit" class="btn btn-primary ml30">清空</button>
			</td>
		</tr>
	</table>

</body>

</html>
<script>
	$(function() {

		$('#addnew').click(function() {

			window.location.href = "add.html";
		});

	});

	function del(id) {

		if (confirm("确定要删除吗？")) {

			var url = "index.html";

			window.location.href = url;

		}

	}

	
</script>