/** note.js封装笔记相关操作* */
//根据笔记本ID加载笔记本列表
function loadBookNotes() {
	// 设置笔记本li选中效果
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 设置笔记选中状态
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// 获取请求参数
	var bookId = $(this).data("bookId"); // this指代li，是DOM对象，加$()转成jQuery
	addCookie("bookId",bookId,2);
	// 发送Ajax请求
	$.ajax({
				url : base_path + "/note/loadnotes.do",
				type : "post",
				data : {
					"bookId" : bookId
				},
				dataType : "json",
				success : function(result) {
					if (result.status == 0) {
						console.log(5);
						// 清空原有笔记列表
						// $("note_ul").empty();
						$("#note_ul li").remove();
						// 获取服务器返回的笔记集合信息
						var notes = result.data;
						// 循环生成笔记li元素
						console.log("666"+notes.length);
						for (var i = 0; i < notes.length; i++) {
							console.log(6);
							// 获取笔记ID和笔记标题
							var noteId = notes[i].cn_note_id;
							var noteTitle = notes[i].cn_note_title;
							// 创建一个笔记li元素
							var sli = "";
							sli = sli
									+ '<li class="online">'
									+ '<a  class="checked">'
									+ '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'
									+ noteTitle
									+ '		<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>'
									+ '</a>'
									+ '<div class="note_menu" tabindex="-1">'
									+ '	<dl>'
									+ '	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>'
									+ '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>'
									+ '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>'
									+ '	</dl>' + '</div>' + '</li>';
							// 将noteId绑定到li元素上
							var $li = $(sli);
							$li.data("noteId", noteId);
							// 将li元素添加到笔记列表ul中
							$("#note_ul").append($li);
						}
					}
				},
				error : function() {
					alert("加载笔记列表异常");
				}
			});
}

function addNote(){
	var bookId = getCookie("bookId");
	var userId = getCookie("uid");
	// console.log(bookId);
	var noteTitle =$("#input_note").val();
	$.ajax({
		url:base_path+"/note/addNote.do",
		async:false,
		type:"post",
		data:{"noteTitle":noteTitle,"bookId":bookId,"userId":userId},
		dataType:"json",
		success:function (result){
			if (result.status==0){
				location.reload();
			}else if (result.status==1){
				alert(result.msg);
			}
		},
		error:function (){
			alert("笔记本添加异常");
		}
	})
}

//根据笔记ID加载笔记信息
function loadNote(){
	//获取请求参数
	var noteId = $(this).data("noteId");
	addCookie("noteId",noteId,2);
	//发送Ajax请求
	$.ajax({
		url:base_path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var title = result.data.cn_note_title;   //获取笔记标题
				var body = result.data.cn_note_body;     //获取笔记内容
				//设置到编辑区域
				$("#input_note_title").val(title);
				um.setContent(body);
			}
		},
		error:function(){
			alert("加载笔记异常");
		}
	});
}
function updateNote(){
	var noteBody = $("#myEditor").val();
	var noteTitle = um.getContent();
	var noteId=getCookie("noteId");
	$.ajax({
		url:base_path+"/note/updateNote.do",
		type:"post",
		data:{"noteId":noteId,"noteBody":noteBody,"noteTitle":noteTitle},
		dataType:"json",
		success:function (){
			location.reload();
		},
		error:function (){
			alert("修改笔记异常");
		}
	})
}
