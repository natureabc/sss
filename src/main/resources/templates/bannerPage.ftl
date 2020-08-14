<html>
<head>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div>
	<div style="float:left" style="width:20%">
		<div >
			<a  class='btn btn-primary' href="/manager/toAddPage"  >返回列表</a>
		</div>

	</div>
	<div style="float: right;">
		<#if user?exists>
			<span>用户名：${user.userName}</span>
		</#if>
		<a href="/login/logout">退出登录</a>
	</div>
</div>
<h4>轮播图列表</h4>
	<table class="table">
		<tr>
			<th>图片</th>
			<th>操作</th>
		</tr>
		<tbody id="listBody">

		</tbody>
	</table>
</body>


<script type="text/javascript">

	$(function(){
		showList();
	});



	function showList(){
		$.ajax({
			type:"post",
			url:"/manager/getBannerList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						str+="<tr><td><img src='"+data[i].bannerImg+"' style='width:49px;height:49px'/></td>";
						str+="<td><input type='file' name='bannerImg' value='上传轮播图'/>" +
								</td>";
						str+="</tr>";
					}
					$("#listBody").html(str);
				}
			},
			error:function(data){
				console.log(data);
			}
		})
	}


	function delForum(id){

		if(confirm("确定要删除吗?")){
			$.ajax({
				type:"post",
				data:{"id":id},
				url:"/manager/delForum",
				success:function(data){
					if(data&&data>0){
						alert('删除成功');
						showList();
					}
				},
				error:function(data){
					console.log(data);
					alert('网络错误，请联系管理员');
				}
			});
		}
	}

</script>
</html>