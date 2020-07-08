
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="myModalLabel1">八阿哥详情</h4>
</div>
<div >
	<table class="table">
		<tr>
			<td>标题</td>
			<td>${bug.title}</td>
		</tr>
		<tr>
			<td>内容框</td>
			<td>${bug.content}</td>
		</tr>
		<tr>
			<td>等级</td>
			<td>${bug.level}</td>
		</tr>
		<tr>
			<td>创建时间</td>
			<td>${bug.createTimeStr}</td>
		</tr>
		<tr>
			<td>操作</td>
			<td>
				<select id="operationSel" class="form-control" style="width:40%" onchange="changeOperation(this.value)">
					<#if bug.status==1>
						<option value="-1">请选择</option>
						<option value="2">指派</option>
						<option value="3">确认</option>
						<option value="4">解决</option>
					</#if>
					<#if bug.status==2>
						<option value="-1">请选择</option>
						<option value="2">指派</option>
						<option value="4">解决</option>
					</#if>
					<#if bug.status==3>
						<option value="-1" >请选择</option>
						<option value="5">激活</option>
						<option value="6">关闭</option>
					</#if>
					<#if bug.status==4>
						<option value="-1">请选择</option>
						<option value="5">激活</option>
					</#if>
				</select>
				<div id="userDiv" style="display:none;">
					<span>指派给:</span>
					<select id="userSel" class="form-control" style="width:40%;">
					</select>
				</div>
			</td>
		</tr>
		<tr>
			<td>理由描述:</td>
			<td><textarea type="text" id="reason" class="form-control"></textarea></td>
		</tr>
	</table>
	<input type="hidden" id="bugId" value='${bug.id}'/>
	<hr>
	<ul id="bugRecord">

	</ul>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-primary" onclick="doSubmitBug()">提交</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<script>
		var bugId=$("#bugId").val();
		$.ajax({
			type:"post",
			url:"/bug/getBugRecord",
			data:{"bugId":bugId},
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						var operationType="";
						if(data[i].operationType==1){
							operationType="创建了该bug";
						}
						if(data[i].operationType==2){
							operationType="指派给了"+data[i].bugBelongerName;
						}
						if(data[i].operationType==3){
							operationType="确认了该bug";
						}
						if(data[i].operationType==4){
							operationType="解决了该bug";
						}
						if(data[i].operationType==5){
							operationType="激活了该bug";
						}
						if(data[i].operationType==6){
							operationType="关闭了该bug";
						}
						var reason="";
						if(data[i].reason){
							reason=data[i].reason
						}else{
							reason="";
						}
						str+="<li style='color:darkgrey'>"+data[i].operationUserName+operationType+"&nbsp;&nbsp;"+data[i].operationTimeStr+"<br>"+reason+"</li>";
					}
					$("#bugRecord").html(str);
				}
			},
			error:function(error){
				console.log(error);
			}
		});

		function changeOperation(value){
			if(value==2){
				$("#userDiv").css("display","");
				$.ajax({
					type:"post",
					url:"/user/getUserList",
					success:function(data){
						if(data){
							var str="<option value='-1'>请选择</option>";
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
			}else{
				$("#userDiv").css("display","none");
			}
		}

		function doSubmitBug(){
			var bugId=$("#bugId").val();
			var flag=false;
			var selStatus=$("#operationSel").val();
			var userId=$("#userSel").val();
			var reason=$("#reason").val();
			if(selStatus==2&&userId<=0){
				alert('请选择指派的人员');
			}else if(selStatus<=0){
				alert('请选择操作');
			}else{
				flag=true;
			}
			if(flag){
				$.ajax({
					type:"post",
					url:"/bug/updateBugStatus",
					data:{"bugId":bugId,"operationType":selStatus,"bugBelongerId":userId,"reason":reason},
					success:function(data){
						if(data&&data>0){
							alert('操作成功');
							$("#viewDetailModel").modal('hide');
							window.location.href="/bug/toBugPage";
						}
					},
					error:function(error){
						console.log(error)
						alert('程序异常，请联系管理员');
					}
				});

			}
		}
</script>