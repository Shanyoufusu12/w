package cn.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * �ʼ�������
 * 
 */
public class MailUtils {

	/**
	 * �����ʼ��ķ���
	 */
	public static void sendMail(String to,String code){
		/*
		 * ��ȡһ��session����
		 * ����һ���ʼ�����message
		 * �����ʼ�transport
		 */
		
		Properties props=new Properties();
		props.setProperty("mail.host", "localhost");
		Session session=Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "service");
			}
			
		});
		//�����ʼ�����
		Message message=new MimeMessage(session);
		try {
			//���÷�����
			message.setFrom(new InternetAddress("service@shop.com"));
			//�����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//����cc ����bcc
			//���ñ���
			message.setSubject("�����ʼ�");
			//��������
			message.setContent("<h1>�������������ɼ��</h1><a href='http://localhost:1234/ssh/user_active.action?code="+code+"'>http://localhost:1234/ssh/user_active.action?code="+code+"</a>","text/html;charset=UTF-8");
			//�����ʼ�
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
