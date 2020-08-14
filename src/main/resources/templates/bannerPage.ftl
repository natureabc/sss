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
			<a  class='btn btn-primary' href="/manager/index"  >返回列表</a>
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
<br>
<a class='btn btn-primary' data-toggle='modal' data-target='#viewEditModel' data-backdrop='static' title='新建轮播图'

   href="/manager/toBannerAdd" > 添加轮播图</a>
<div>
	<div class="modal fade " id="viewEditModel" >
		<div class="modal-dialog" style="width:1300px">
			<div class="modal-content" >

				<div class="modal-body">
				</div>

			</div>
		</div>
	</div>

</div>
	<table class="table">
		<tr>
			<th>图片</th>
			<th>排序</th>
			<th>上传新图片</th>
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
						str+="<td><input type='text'  value='"+data[i].sort+"' oninput='setCurrentValue(this.value,"+data[i].id+")'/></td>";
						str+="<td><form id='formId"+data[i].id+"'><input type='file' name='imgFile' value='上传轮播图'/>" +
								"<input type='hidden' id='hiddenSort"+data[i].id+"' name='sort' value='"+data[i].sort+"'/>" +
								"<input type='hidden' name='id' value='"+data[i].id+"'></form></td>";
						str+="<td><button class='btn btn-primary' onclick='changeBannerImg("+data[i].id+")'>提交</button></td>";
						str+="</tr>";
					}
					$("#listBody").html(str);
				}
			}

		});

	}

	function changeBannerImg(id){

		if(confirm("是否确认修改")){
			var formData=new FormData($("#formId"+id)[0]);
			$.ajax({
				type:"post",
				url:"/manager/changeBanner",
				data:formData,
				async: false,
				cache: false,
				contentType: false,
				processData: false,
				success:function(data){
					if(data&&data>0){
						//$("#viewAddModel").modal('hide');
						//showList();
						alert("banner修改成功");
						showList();
					}else{
						alert('banner修改失败，请联系管理员')
					}
				},
				error:function(data){
					console.log("error===",data);
					alert('网络错误，请联系管理员');
				}
			})
		}


	}

	function setCurrentValue(value,id){
		$("#hiddenSort"+id).val(value);

	}

</script>
</html>