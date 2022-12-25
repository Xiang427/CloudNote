/** book.js��װ�ʼǱ���ش��� **/
//�����û��ʼǱ��б�
function loadUserBooks(){
	//��ȡ�������
	var userId = getCookie("uid");
	//����ʽ
	if(userId == null){
		window.location.href="log_in.html";
	}else{
		//����Ajax����
		$.ajax({
			url:base_path+"/book/loadbooks.do",
			async:false,
			type:"post",
			data:{"userId":userId},
			dataType:"json",
			success:function(result){
				if(result.status == 0){
					//��ȡ���صıʼǱ�����
					var books = result.data;
					//ѭ�������б�Ԫ��
					for(var i=0;i<books.length;i++){
						var bookId = books[i].cn_notebook_id;      //��ȡ�ʼǱ�ID
						var bookName = books[i].cn_notebook_name;  //��ȡ�ʼǱ�����
						//�����б�Ԫ��
						var sli = "";
						sli= sli+'<li class="online">'
								+'	<a>'
								+'		<i class="fa fa-book" title="online" rel="tooltip-bottom">'
								+'		</i>'+bookName
								+'	</a>'
								+'</li>';
						//��bookId�󶨵�liԪ����
						var $li = $(sli);
						$li.data("bookId",bookId);
						//��liԪ����ӵ�ul�б���
						$("#book_ul").append($li);
					}
				}
			},
			error:function(){
				alert("���رʼǱ��б��쳣");
			}
		});
	}
}