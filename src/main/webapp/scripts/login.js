/**login.js��װ��¼��ע�ᴦ��**/
//��¼����
function checkLogin(){
			//��ȡ�������
			var name = $("#count").val().trim();
			var password = $("#password").val().trim();
			//��������ʽ
			//�������ǰ��ʾ��Ϣ
			$("#count_span").html("");
			$("#password_span").html("");
			var ok = true;
			if(name==""){
				ok = false;
				$("#count_span").html("�û���Ϊ��");
			}
			if(password==""){
				ok = false;
				$("#password_span").html("����Ϊ��");
			}
			//����Ajax����
			if(ok){
				$.ajax({
					url:base_path+"/user/login.do",
					type:"post",
					data:{"name":name,"password":password},
					dataType:"json",
					success:function(result){
						/*
							result�Ƿ��������ص�JSON���{"status":xx,"msg":xx,"data":xx}
						*/
						if(result.status == 0){          //�ɹ�
							/*
								���û���Ϣд��Cookie
							*/
							var user = result.data;   //��ȡ���ص�user��Ϣ
							//д��Cookie
							addCookie("uid",user.cn_user_id,2);
							addCookie("uname",user.cn_user_name,2);
							window.location.href="edit.html";
						}else if(result.status == 1){    //�û�����
							$("#count_span").html(result.msg);
						}else if(result.status == 2){    //�����
							$("#password_span").html(result.msg);
						}     
					},
					error:function(){
						alert("��¼�쳣");
					}
				});
			}
		}

//ע�ᴦ��
function registUser(){
	//��ȡ�������
	var name = $("#regist_username").val().trim();
	var nick = $("#nickname").val().trim();
	var password = $("#regist_password").val().trim();
	var f_password = $("#final_password").val().trim();
	//��ʽ���
	//�����Ϣ
	$("#warning_1 span").html("");
	$("#warning_2 span").html("");
	$("#warning_3 span").html("");
	var ok = true;   //�Ƿ�ͨ����֤
	if(name == ""){
		ok = false;
		$("#warning_1").show();
		$("#warning_1 span").html("�û���Ϊ��");
	}
	if(password == ""){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("����Ϊ��");
	}else if(password.length < 6){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("���볤��̫��");
	}else if(password.length >18){
		ok = false;
		$("#warning_2").show();
		$("#warning_2 span").html("���볤��̫��");
	}
	if(f_password == ""){
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("ȷ������Ϊ��");
	}else if(f_password != password){
		ok = false;
		$("#warning_3").show();
		$("#warning_3 span").html("ȷ�����������벻һ��");
	}
	//����Ajax����
	if(ok){
		$.ajax({
			url:base_path+"/user/add.do",
			type:"post",
			data:{"name":name, "password":password, "nick":nick},
			dataType:"json",
			success:function(result){
				if(result.status==0){         //�ɹ�  
					alert(result.msg);        //��¼�ɹ���ʾ��
					$("#back").click();       //ת���¼����
				}else if(result.status==1){   //�û�����ռ��
					$("#warning_1").show();
					$("#warning_1 span").html(result.msg);
				}
			},
			error:function(){
				alert("ע���쳣");
			}
		});
	}
}

$(function(){     //ҳ���������
	//����¼��ť�󶨵���������()�Ǻ������ã�����()�Ǻ�����
	$("#login").click(checkLogin);
	//��ע�ᰴť�󶨵�������
	$("#regist_button").click(registUser);
});