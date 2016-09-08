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
 * 邮件工具类
 * 
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 */
	public static void sendMail(String to,String code){
		/*
		 * 获取一个session对象
		 * 创建一个邮件对象message
		 * 发送邮件transport
		 */
		
		Properties props=new Properties();
		props.setProperty("mail.host", "localhost");
		Session session=Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "service");
			}
			
		});
		//创建邮件对象
		Message message=new MimeMessage(session);
		try {
			//设置发件人
			message.setFrom(new InternetAddress("service@shop.com"));
			//设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			//抄送cc 密送bcc
			//设置标题
			message.setSubject("激活邮件");
			//设置内容
			message.setContent("<h1>点击以下链接完成激活！</h1><a href='http://localhost:1234/ssh/user_active.action?code="+code+"'>http://localhost:1234/ssh/user_active.action?code="+code+"</a>","text/html;charset=UTF-8");
			//发送邮件
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
