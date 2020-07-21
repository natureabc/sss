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

			<a  class='btn btn-primary' data-toggle='modal' data-target='#viewAddModel' data-backdrop='static' title='新建帖子'

              href="/forum/toAddPage"  >新建帖子</a>
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
	<div class="modal fade " id="viewAddModel">
		<div class="modal-dialog">
			<div class="modal-content" >

				<div class="modal-body">
				</div>

			</div>
		</div>
	</div>
	<div class="modal fade hiddencleandata" id="viewDetailModel12121212">
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


<script type="text/javascript">

	$(function(){
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

						str+="<td><a  class='btn btn-primary' data-toggle='modal' " +
								"data-target='#viewDetailModel12121212' data-backdrop='static' title='查看详情' " +
								"href='#' >编辑</a></td>";
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



	function showOwn(){

		showList(projectId,);
	}

</script>
</html>