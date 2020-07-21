<html>
<head>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" >&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增帖子</h4>
</div>
<div >
	<form action="/bug/addBug" method="post">
		<#--<input type="hidden" name="projectId" value='<#if projectId?exists>${projectId}</#if>'/>-->
	<table class="table" style="width:60%">
		<tr>
			<td>列表图片</td>
			<td><input type="file" class="form-control" name="mainPic"/></td>
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
			<td><input type="submit" class='btn btn-primary'  value="提交"/></td>
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
						str+="<input type='checkbox'  name='labelList'/>"+data[i].labelName+"<br>";
					}
					$("#addLabel").html(str);
				}
			}

		})

	})


</script>

</html>