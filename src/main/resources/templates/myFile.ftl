<html>
<head>
	
</head>
<body>
	啦啦啦啦啦
</body>

<script src="/static/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	$.ajax({
		type:"post",
		url:"/index/getCountryList",
		success:function(data){
			console.log(data);
		},
		error:function(data){
			console.log(data);
		}
	})

</script>
</html>