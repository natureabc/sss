<html>
<head>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" onclick="doFlushPage()">&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增帖子</h4>
</div>
<div >
		<#--<input type="hidden" name="projectId" value='<#if projectId?exists>${projectId}</#if>'/>-->
	<form id="subForm" >
		<table class="table" style="width:60%">
			<tr>
				<td>列表图片</td>
				<img id="forumImg" style='width:49px;height:49px'/>
				<td><input type="file" class="form-control" name="imgFile"/></td>
			</tr>
			<tr>
				<td>标题</td>
				<td><input type="text" class="form-control" name="title" id="forumTitle"/></td>
			</tr>
			<tr>
				<td>内容</td>
				<td>
					<textarea  class="form-control" name="content" id="forumContent"></textarea>
				</td>
			</tr>
			<tr>
				<td>关键字</td>
				<td>
					<select id="addKeyword" name="keywordId" class="form-control">

					</select>

				</td>
			</tr>
			<tr>
				<td>标签</td>
				<td id="addLabel"></td>
			</tr>
			<tr>
				<td>是否标红</td>
				<td>
					<select name="isHot" class="form-control" id="forumIsHot">
						<option value="1">标红</option>
						<option value="0">未标</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>浏览量</td>
				<td><input type="text" class="form-control" name="browseNum" id="forumBrowseNum"/></td>
			</tr>
			<tr>
				<td><a href="#" class='btn btn-primary'  onclick="doSubmit()">提交</a> </td>
			</tr>
		</table>
			<input type="hidden" name="id" id="formId"/>
	</form>
</div>
</body>

<script type="text/javascript">
	$(function(){

		$('body').on('hidden.bs.modal', '.modal', function () {
			$(this).removeData('bs.modal');
		})


		getForumDetail();
	})


	function getLabelList(labelList){

		$.ajax({
			type:"post",
			url:"/forum/getLabelList",
			success:function(data){
				if(data){
					var str="";
					for(var i=0;i<data.length;i++){
						var checkedCount=0;
						if(labelList&&labelList.length>0){
							for(var j=0;j<labelList.length;j++){
								if(data[i].id==labelList[j].labelId){
									str+="<input type='checkbox' checked='checked'  name='labelArray' value='"+data[i].id+"'/>"+data[i].labelName+"<br>";
									checkedCount++;
								}
							}
						}
						if(checkedCount==0){
							str+="<input type='checkbox'  name='labelArray' value='"+data[i].id+"'/>"+data[i].labelName+"<br>";
						}
					}
					$("#addLabel").html(str);
				}
			}
		});
	}

	function getForumDetail(){
		var forumId="${forumId}";
		$.ajax({
			url:"/forum/getForumDetailById",
			type:"post",
			data:{"forumId":forumId},
			success:function(data){
				if(data){
					console.log(data);
					$("#forumImg").attr("src",data.mainPic);
					$("#forumTitle").val(data.title);
					$("#forumContent").val(data.content);
					showKeywordList(data.keywordId);
					getLabelList(data.labelList);
					$("#forumIsHot").val(data.isHot);
					$("#forumBrowseNum").val(data.browseNum);
					$("#formId").val(data.id);
				}
			}
		});


	}


	function showKeywordList(id){

		$.ajax({
			type:"post",
			url:"/forum/getKeyWordList",
			success:function(data){
				if(data&&data.length>0){
					var str="";
					for(var i=0;i<data.length;i++){
						if(data[i].id==id){
							str+=" <option value='"+data[i].id+"' selected='selected'>"+data[i].keyword+"</option>";
						}else{
							str+=" <option value='"+data[i].id+"'>"+data[i].keyword+"</option>";
						}
					}
					$("#addKeyword").html(str);
				}
			}
		});
	}

	function doSubmit(){
		var formData=new FormData($("#subForm")[0]);
		$.ajax({
			type:"post",
			url:"/manager/editForum",
			data:formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(data){
				if(data&&data>0){
					$("#viewEditModel").modal('hide');
					showList();
				}else if(data==-1){
					alert('编辑失败，id为空')
				}else{
					alert('编辑失败，请联系管理员')
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