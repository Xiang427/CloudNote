/** alert.js ��װ�Ի����� **/
//���������ʼǱ��Ի���
function alertAddBookWindow(){
	//�����Ի��� alert
	$("#can").load("alert/alert_notebook.html");
	$(".opacity_bg").show();
};

//�رնԻ���
function closeAlertWindow(){
	//�رղ���
	$("#can").empty();        //��նԻ�������
	$(".opacity_bg").hide();  //���ر���div
}

//�����������ʼǱ��Ի���
function alertRenameBookWindow(){
	$("#can").load("alert/alert_rename.html");
	$(".opacity_bg").show();
};

//���������ʼǶԻ���
// function alertAddNoteWindow(){
// 	$("#can").load("alert/alert_note.html");
// 	$(".opacity_bg").show();
// }

//���������ʼǶԻ���
function alertAddNoteWindow(){
	//���û��ѡ�еıʼǱ�����ʾ
	var $a = $("#book_ul a.checked");
	if($a.length==0){
		alert("��ѡ��ʼǱ�");
	}else{   //��ѡ�еıʼǱ����ٵ��������ʼǱ��Ի���
		$("#can").load("alert/alert_note.html");
		$(".opacity_bg").show();
	}
}

//����ɾ���ʼ�ȷ�϶Ի���
function alertDeleteNoteWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
};

//����ת�ƱʼǶԻ���
function alertMoveNoteWindow(){
	$(".opacity_bg").show();
	$("#can").load("alert/alert_move.html",function(){
		//Ϊalert_move.html��<select>��������
		var books = $("#book_ul li");    //��ȡbook�б�
		//ѭ��book�б�����
		for(var i=0; i<books.length; i++){
			var $li = $(books[i]);    //��ȡliԪ�ز�תΪjQuery����
			var bookId = $li.data("bookId");   //��ȡ�ʼǱ�id
			var bookName = $li.text().trim();
			//����һ��optionԪ�أ���ӵ�select�� 
			var sopt = ''
					 + '<option value = "'+bookId+'">'
					 + bookName
					 + '</option>';
			//��ӵ�select��
			$("#moveSelect").append(sopt);
		}
	});
	/*
	����ת�ƱʼǶԻ��򣬲���Ϊ�Ի�����<select>��ӱʼǱ�<option>
	function alertMoveNoteWindow(){
		//load����alert_move.html
		$("#can").load("alert/alert_move.html",function(){
			//��ȡbook_ul������li
			//ѭ��li����ȡ���ʼǱ�ID������
			//���ñʼǱ�ID����������<option>
			//��<option>��ӵ��Ի����<select>��
		});
		//������div��ʾ
	}
	*/
}