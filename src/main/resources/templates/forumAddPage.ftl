<html>
<head>

	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="/js/ueditor.all.min.js"> </script>
</head>
<body>
<#--<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" >&times;</button>
	<h4 class="modal-title" id="myModalLabel1">新增帖子</h4>

</div>-->
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
					<script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
					<textarea name="content" id="formContent" style="display: none;"></textarea>
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
	var ue = UE.getEditor('editor');

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
		});

		showKeywordList();
	})


	function showKeywordList(){

		$.ajax({
			type:"post",
			url:"/forum/getKeyWordList",
			success:function(data){
				if(data&&data.length>0){
					var str="";
					for(var i=0;i<data.length;i++){
						str+=" <option value='"+data[i].id+"'>"+data[i].keyword+"</option>";
					}
					$("#addKeyword").html(str);
				}
			}
		});
	}

	function doSubmit(){
		var contentHtml=UE.getEditor('editor').getAllHtml();
		$("#formContent").val(contentHtml)

		//alert($("#formContent").val());
		var formData=new FormData($("#subForm")[0]);
		console.log("-=====",formData);
		$.ajax({
			type:"post",
			url:"/manager/addForum",
			data:formData,
			async: false,
			cache: false,
			contentType: false,
			processData: false,
			success:function(data){
				if(data&&data>0){
					//$("#viewAddModel").modal('hide');
					//showList();
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