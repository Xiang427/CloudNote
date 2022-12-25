/** book.js封装笔记本相关处理 **/
//加载用户笔记本列表
function loadUserBooks(){
	//获取请求参数
	var userId = getCookie("uid");
	//检查格式
	if(userId == null){
		window.location.href="log_in.html";
	}else{
		//发送Ajax请求
		$.ajax({
			url:base_path+"/book/loadbooks.do",
			async:false,
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					//获取返回的笔记本集合
					var books = result.data;
					//循环生成列表元素
					for(var i=0;i<books.length;i++){
						var bookId = books[i].cn_notebook_id;      //获取笔记本ID
						var bookName = books[i].cn_notebook_name;  //获取笔记本名称
						//构建列表元素
						var sli = "";
						sli= sli+'<li class="online">'
								+'	<a>'
								+'		<i class="fa fa-book" title="online" rel="tooltip-bottom">'
								+'		</i>'+bookName
								+'	</a>'
								+'</li>';
						//将bookId绑定到li元素上
						var $li = $(sli);
						$li.data("bookId",bookId);
						//将li元素添加到ul列表中
						$("#book_ul").append($li);
					}
				}
			},
			error:function(){
				alert("加载笔记本列表异常");
			}
		});
	}
}