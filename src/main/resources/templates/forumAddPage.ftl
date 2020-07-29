<html>
<head>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" >&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增帖子</h4>
</div>
<div >
		<#--<input type="hidden" name="projectId" value='<#if projectId?exists>${projectId}</#if>'/>-->
	<form id="subForm" >
		<table class="table" style="width:60%">
			<tr>
				<td>列表图片</td>
				<td><input type="file" class="form-control" name="imgFile"/></td>
			</tr>
			<tr>
				<td>标题</td>
				<td><input type="text" class="form-control" name="title"/></td>
			</tr>
			<tr>
				<td>内容</td>
				<td>
					<textarea  class="form-control" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>关键字</td>
				<td><input type="text" class="form-control" name="keyWord"/></td>
			</tr>
			<tr>
				<td>标签</td>
				<td id="addLabel"></td>
			</tr>
			<tr>
				<td>是否标红</td>
				<td>
					<select name="isHot" class="form-control">
						<option value="1">标红</option>
						<option value="0">未标</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>浏览量</td>
				<td><input type="text" class="form-control" name="browseNum"/></td>
			</tr>
			<tr>
				<td><a href="#" class='btn btn-primary'  onclick="doSubmit()">提交</a> </td>
			</tr>
		</table>
	</form>
</div>
</body>

<script type="text/javascript">
	$(function(){
		$.ajax({
			type:"post",
			url:"/forum/getLabelList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						str+="<input type='checkbox'  name='labelArray' value='"+data[i].id+"'/>"+data[i].labelName+"<br>";
					}
					$("#addLabel").html(str);
				}
			}
		})
	})

	function doSubmit(){
		var formData=new FormData($("#subForm")[0]);
		console.log("-=====",formData);
		$.ajax({
			type:"post",
			url:"/forum/addForum",
			data:formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(data){
				if(data&&data>0){
					$("#viewAddModel").modal('hide');
					showList();
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