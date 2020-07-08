<html>
<head>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="modal-header">
	<h4 class="modal-title" id="myModalLabel1">新增八阿哥</h4>
</div>
<div >
	<form action="/bug/addBug" method="post">
		<input type="hidden" name="projectId" value='<#if projectId?exists>${projectId}</#if>'/>
	<table class="table" style="width:60%">
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
			<td>等级</td>
			<td>
				<select class="form-control" name="level">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>指派给:</td>
			<td>
				<select class="form-control" id="userSel" name="bugBelongerId">
				</select>
			</td>
		</tr>
		<tr>
			<td><input type="submit" class='btn btn-primary'  value="提交"/></td>
		</tr>
	</table>
	</form>
</div>
</body>

<script type="text/javascript">
	$(function (){
		showUserList();
	});

	function showUserList(){
		$.ajax({
			type:"post",
			url:"/user/getUserList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						str+="<option value='"+data[i].id+"'>"+data[i].userName+"</option>";
					}
					$("#userSel").html(str);
				}

			},
			error:function(error){
				console.log(error)
			}

		});

	}
</script>

</html>