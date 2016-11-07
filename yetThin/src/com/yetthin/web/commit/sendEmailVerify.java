package com.yetthin.web.commit;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
@Component(value="Sender")
public class sendEmailVerify {

	@Autowired
	private  JavaMailSenderImpl javaMailSender;
	
	  
	
	private static Executor excutor=Executors.newCachedThreadPool();
	
	private  String getFromAddress(){
		String fromName=javaMailSender.getUsername();
		return fromName;
	}
	
	public  void sendEmail(final String to,final String subject,final String text){
		
		if(to ==null&&text==null)
			return ;
		Runnable task=new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				MimeMessage msg=javaMailSender.createMimeMessage();
				MimeMessageHelper helper=null;
				try {
					helper = new MimeMessageHelper(msg,true,"utf-8");
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					helper.setTo(to);
					helper.setSubject(subject);
					helper.setText(text,true);
					
					helper.setFrom(getFromAddress());
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				javaMailSender.send(msg);
				System.out.println("send ---------");
			}
			
		};
		excutor.execute(task);
		System.out.println("main thread   ");
		
	}
	public static void main(String[] args) {
//		ApplicationContext context=new FileSystemXmlApplicationContext("classpath:applicationEmail.xml");
//		javaMailSender= (JavaMailSenderImpl) context.getBean("javaMailSenderImpl");
//		StringBuffer sb= new StringBuffer();
//		sb.append("<html><head></head><body><h2>点击下面链接激活账号，48小时生效，否则重新注册账号</h2><br/>");
//		sb.append("                        <a href=\"#\">www.cktime.net/yetThinTest</a></body></htmk>");

//		 new sendEmailVerify().sendEmail("1277677591@qq.com", "test", sb.toString());
	}
}
