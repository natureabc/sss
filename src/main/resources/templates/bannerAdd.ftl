<html>
<head>

</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" >&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增轮播图</h4>

</div>
<br>
<form id="bannerForm">
	<table class="table">
		<tr>
			<th>上传新图片</th>
			<th>排序</th>
		</tr>
			<tbody id="listBody">
			<tr><td><input type="file" name="imgFile" value="上传轮播图"/></td><td><input type="text" name="sort"/></td></tr>
			</tbody>
	</table>
	<a class='btn btn-primary' href="#" onclick="doSubmit()">提交</a>
</form>
</body>


<script type="text/javascript">
	function doSubmit(){
		var formData=new FormData($("#bannerForm")[0]);
		//console.log("-=====",formData);
		$.ajax({
			type:"post",
			url:"/manager/addBanner",
			data:formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(data){
				if(data&&data>0){
					//$("#viewAddModel").modal('hide');
					//showList();
					alert('添加成功');
				}else{
					alert('添加失败，请联系管理员')
				}
			},
			error:function(data){
				console.log("error===",data);
				alert('网络错误，请联系管理员');
			}
		})


	}

</script>
</html>