/** note.js��װ�ʼ���ز���* */
//���ݱʼǱ�ID���رʼǱ��б�
function loadBookNotes() {
	// ���ñʼǱ�liѡ��Ч��
	$("#book_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// ���ñʼ�ѡ��״̬
	$("#note_ul a").removeClass("checked");
	$(this).find("a").addClass("checked");
	// ��ȡ�������
	var bookId = $(this).data("bookId"); // thisָ��li����DOM���󣬼�$()ת��jQuery
	addCookie("bookId",bookId,2);
	// ����Ajax����
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
						// ���ԭ�бʼ��б�
						// $("note_ul").empty();
						$("#note_ul li").remove();
						// ��ȡ���������صıʼǼ�����Ϣ
						var notes = result.data;
						// ѭ�����ɱʼ�liԪ��
						console.log("666"+notes.length);
						for (var i = 0; i < notes.length; i++) {
							console.log(6);
							// ��ȡ�ʼ�ID�ͱʼǱ���
							var noteId = notes[i].cn_note_id;
							var noteTitle = notes[i].cn_note_title;
							// ����һ���ʼ�liԪ��
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
									+ '	<dt><button type="button" class="btn btn-default btn-xs btn_move" title="�ƶ���..."><i class="fa fa-random"></i></button></dt>'
									+ '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="����"><i class="fa fa-sitemap"></i></button></dt>'
									+ '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="ɾ��"><i class="fa fa-times"></i></button></dt>'
									+ '	</dl>' + '</div>' + '</li>';
							// ��noteId�󶨵�liԪ����
							var $li = $(sli);
							$li.data("noteId", noteId);
							// ��liԪ����ӵ��ʼ��б�ul��
							$("#note_ul").append($li);
						}
					}
				},
				error : function() {
					alert("���رʼ��б��쳣");
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
			alert("�ʼǱ�����쳣");
		}
	})
}

//���ݱʼ�ID���رʼ���Ϣ
function loadNote(){
	//��ȡ�������
	var noteId = $(this).data("noteId");
	addCookie("noteId",noteId,2);
	//����Ajax����
	$.ajax({
		url:base_path+"/note/load.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status == 0){
				var title = result.data.cn_note_title;   //��ȡ�ʼǱ���
				var body = result.data.cn_note_body;     //��ȡ�ʼ�����
				//���õ��༭����
				$("#input_note_title").val(title);
				um.setContent(body);
			}
		},
		error:function(){
			alert("���رʼ��쳣");
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
			alert("�޸ıʼ��쳣");
		}
	})
}
