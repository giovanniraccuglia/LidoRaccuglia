package com.raccuglia.utils;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class InvioEmail {
	//METODO PER L'INVIO AUTOMATICO DELL'EMAIL
		public static void sendEmail(String mail ,String messaggio, String oggetto) throws UnsupportedEncodingException{  
			final String fromEmail = "lido1mare@gmail.com"; //requires valid gmail id  //creare email personale 
			final String passw = "13Qwerty13"; // correct password for gmail id
			final String toEmail = mail; // can be any email id 
			
			System.out.println("SSLEmail Start");
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
			props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
			props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
			props.put("mail.smtp.port", "465"); //SMTP Port
			
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, passw);
				}
			};
			
			Session session = Session.getDefaultInstance(props, auth);
			System.out.println("Session created");
			EmailUtil.sendEmail(session, toEmail, oggetto, messaggio);
		}
}
