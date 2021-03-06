<html>
<head>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor.config.js"></script>


</head>
<body>
<div>
	<div style="float:left" style="width:20%">
		<div >

			<#--<a  class='btn btn-primary' data-toggle='modal' data-target='#viewAddModel' data-backdrop='static' title='新建帖子'

              href="/manager/toAddPage"  >新建帖子</a>-->
			<a  class='btn btn-primary' href="/manager/toAddPage">新建帖子</a>
			<a  class='btn btn-primary' href="/manager/toBannerPage"  >编辑轮播图</a>
			<a  class='btn btn-primary' href="/manager/toLabelPage"  >编辑标签</a>
		</div>

	</div>
	<div style="float: right;">
		<#if user?exists>
			<span>用户名：${user.userName}</span>
		</#if>
		<a href="/login/logout">退出登录</a>
	</div>
</div>
<#--<div>
	<div class="modal fade " id="viewAddModel" >
		<div class="modal-dialog" style="width:1300px">
			<div class="modal-content" >

				<div class="modal-body">
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade hiddencleandata" id="viewEditModel">
		<div class="modal-dialog" style="width:1300px">
			<div class="modal-content" >

				<div class="modal-body">
				</div>

			</div>
		</div>
	</div>
</div>-->
	<table class="table">
		<tr>
			<th>图片</th>
			<th>标题</th>
			<th>关键字</th>
			<th>标签</th>
			<th>浏览量</th>
			<th>是否标红</th>
			<th>操作</th>
		</tr>
		<tbody id="listBody">

		</tbody>
	</table>
</body>

<script type="text/javascript" charset="utf-8" src="/js/ueditor.all.min.js"> </script>
<script type="text/javascript">

//	var ue = UE.getEditor('editor');
//	var ue = UE.getEditor('editor1');
	$(function(){``
		showList();
	});



	function showList(){
		$.ajax({
			type:"post",
			url:"/manager/getForumList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){

						var lables="";
						if(data[i].labelList){
							for(var j=0;j<data[i].labelList.length;j++){
								lables+=data[i].labelList[j].labelName+" ";
							}
						}

						str+="<tr><td><img src='"+data[i].mainPic+"' style='width:49px;height:49px'/></td>";
						str+="<td>"+data[i].title+"</td>";
						str+="<td>"+data[i].keyWord+"</td>";
						str+="<td>"+lables+"</td>";
						str+="<td>"+data[i].browseNum+"</td>";
						if(data[i].isHot==1){
							str+="<td style='color=red;'>标红</td>";
						}else{
							str+="<td>未标</td>";
						}

						str+="<td><a  class='btn btn-primary' href='/manager/toEditPage?forumId="+data[i].id+"' >编辑</a> <a class='btn btn-warning' onclick='delForum("+data[i].id+")'>删除</a></td>";
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