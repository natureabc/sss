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
<h4>标签列表</h4>
<br>
<a class='btn btn-primary' data-toggle='modal' data-target='#viewEditModel' data-backdrop='static' title='添加标签'

   href="/manager/toLabelAdd" > 添加标签</a>
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
			<th>标签名</th>
			<th>是否置为顶部标题</th>
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
			url:"/manager/getLabelList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						str+="<tr>" +
								"<td>" +
								"<form id='subForm"+data[i].id+"'>" +
								"<input type='hidden' name='id' value='"+data[i].id+"'/>" +
								"<input type='hidden' name='isTitle' value='"+data[i].isTitle+"' id='isTitle"+data[i].id+"' />" +
								"<input type='text' name='labelName'  value='"+data[i].labelName+"' />" +
								"</form>" +
								"</td>";
						str+="<td>" +
								"<select class='form-control' onchange='changeIsTitle(this.value,"+data[i].id+")'>";
						if(data[i].isTitle==1){
							str+="<option value='1' SELECTED='SELECTED'>是</option>" ;
							str+="<option value='0' >否</option>" ;
						}

						if(data[i].isTitle==0){
							str+="<option value='1' >是</option>" ;
							str+="<option value='0' SELECTED='SELECTED'>否</option>" ;
						}

						str+=		"</select>" +
								"</td>"
						str+="<td><button class='btn btn-primary' onclick='editLabel("+data[i].id+")'>提交</button>" +
								"<a href='#' class='btn btn-warning' onclick='delLabel("+data[i].id+")'>删除</a></td>";
						str+="</tr>";
					}
					$("#listBody").html(str);
				}
			}

		});

	}

	function editLabel(id){

		if(confirm("是否确认修改标签")){
			var formData=new FormData($("#subForm"+id)[0]);
			$.ajax({
				type:"post",
				url:"/manager/editLabel",
				data:formData,
				async: false,
				cache: false,
				contentType: false,
				processData: false,
				success:function(data){
					if(data&&data>0){
						//$("#viewAddModel").modal('hide');
						//showList();
						alert("标签修改成功");
						showList();
					}else{
						alert('标签修改失败，请联系管理员')
					}
				},
				error:function(data){
					console.log("error===",data);
					alert('网络错误，请联系管理员');
				}
			})
		}


	}


	function changeIsTitle(value,id){
		$("#isTitle"+id).val(value);


	}

	function delLabel(id){
	    if(confirm("是否确定要删除吗？")){
	        $.ajax({
				type:"post",
				data:{"id":id},
				url:"/manager/delLabel",
				success:function(data){
				    if(data>0){
                        alert("删除成功");
                        showList();
					}else{
                        alert("删除失败，请联系管理员");
					}
				},
				error:function (data){
                    alert("网络错误，请联系管理员");
				}

			});

		}
	}

	function setCurrentValue(value,id){
		$("#hiddenSort"+id).val(value);

	}

</script>
</html>