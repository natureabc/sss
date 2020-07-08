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
			<span>当前项目：</span>
			<select id="proj" class="form-control" onchange="getBugListByProjId(this)">
			</select>
			<button class="btn btn-primary" onclick="addBug()">新建bug</button><button class="btn btn-primary" onclick="showOwn()">只看自己</button>
		</div>

	</div>
	<div style="float: right;">
		<#if user?exists>
			<span>用户名：${user.userName}</span>
		</#if>
		<a href="/login/logout">退出登录</a>
	</div>
</div>
<div>
	<div class="modal fade hiddencleandata" id="viewDetailModel">
		<div class="modal-dialog">
			<div class="modal-content" >

				<div class="modal-body">
				</div>

			</div>
		</div>
	</div>

</div>
	<table class="table">
		<tr>
			<th>bugid</th>
			<th>标题</th>
			<th>归属人</th>
			<th>时间</th>
			<th>等级</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<tbody id="listBody">

		</tbody>
	</table>
</body>


<script type="text/javascript">

	$('body').on('hidden.bs.modal', '.modal', function () {
		$(this).removeData("bs.modal");
		$(".modal-body").children().remove();
	});

	$(function(){
		showProject();
		showList();
	});

	function showProject(){

		var projectId= ${Session.user.projectId?if_exists}


		$.ajax({
			type:"post",
			url:"/bug/getProjectList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						if(projectId&&projectId==data[i].id){
							str+="<option value='"+data[i].id+"' selected='selected' >"+data[i].name+"</option>";
						}else{
							str+="<option value='"+data[i].id+"'>"+data[i].name+"</option>";
						}

					}
					$("#proj").html(str);
				}
			},
			error:function(error){
				console.log(error)
			}
		});
	}


	function showList(projectId){
		$.ajax({
			type:"post",
			url:"/bug/getBugList",
			data:{"projectId":projectId},
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						str+="<tr><td>"+data[i].id+"</td>";
						str+="<td>"+data[i].title+"</td>";
						str+="<td>"+data[i].userName+"</td>";
						str+="<td>"+data[i].createTimeStr+"</td>";
						if(data[i].level&&data[i].level==1){
							str+="<td ><a class='btn btn-danger'>"+data[i].level+"</a></td>";
						}else if(data[i].level&&data[i].level==2){
							str+="<td ><a class='btn btn-warning'>"+data[i].level+"</td>";
						}else if(data[i].level&&data[i].level==3){
							str+="<td ><a class='btn btn-info'>"+data[i].level+"</td>";
						}else if(data[i].level&&data[i].level==4){
							str+="<td ><a class='btn btn-success'>"+data[i].level+"</td>";
						}
						if(data[i].status==1){
							str+="<td>未解决</td>";
						}else if(data[i].status==2){
							str+="<td>已确认</td>";
						}else if(data[i].status==3){
							str+="<td>已解决</td>";
						}else if(data[i].status==4){
							str+="<td>已关闭</td>";
						}

						str+="<td><a  class='btn btn-primary' data-toggle='modal' " +
								"data-target='#viewDetailModel' data-backdrop='static' title='查看详情' " +
								"href='/bug/getDetail?id="+data[i].id+"' >查看详情</a></td>";
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

	function getBugListByProjId(obj){
		var projId=obj.value;
		showList(projId);
	}

	function addBug(){
		var projId=$("#proj").val();
		window.location.href="/bug/toAddPage?projectId="+projId;
	}


	function showOwn(){

		showList(projectId,);
	}

</script>
</html>