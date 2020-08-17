<html>
<head>

</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" >&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增标签</h4>

</div>
<br>
<form id="bannerForm">
	<table class="table">
		<tr>
			<th>标签名</th>
			<th>是否置为顶部标题</th>
		</tr>
		<tbody id="listBody">
		<tr>
			<td><input type="text" class="form-control" name="labelName" /></td>
			<td><select name="isTitle" class="form-control"><option value="1">是</option><option value="0">否</option></select></td>
		</tr>
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
			url:"/manager/addLabel",
			data:formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(data){
				if(data&&data>0){
					//$("#viewAddModel").modal('hide');
					//showList();
					//alert('添加成功');
                    $("#viewEditModel").modal('hide');
                    window.top.location.reload();
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