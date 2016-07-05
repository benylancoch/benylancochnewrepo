package com.airprz.model.misc;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.airprz.model.Transaction;

public class SendMail {
	
	private String to;
	private String from;
	private String host;
	private Properties properties;
	Session session;
	
	public SendMail() {
		// Recipient's email ID needs to be mentioned.
	      to = "abcd@gmail.com";

	      // Sender's email ID needs to be mentioned
	      from = "web@gmail.com";

	      // Assuming you are sending email from localhost
	      host = "localhost";

	      // Get system properties
	      properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);

	      // Get the default Session object.
	      session = Session.getDefaultInstance(properties);
	}
	
	public void sendOrderConfirmation(Transaction transaction) {
		try{
			// Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(transaction.getUser().getEmail()));

	         // Set Subject: header field
	         String subject = "AirPrz order #";
	         subject += transaction.getTransactionId();
	         message.setSubject(subject);

	         // Send the actual HTML message, as big as you like
	         String messageContent = "<h1>Thanks for your order ";
	         messageContent += transaction.getUser().getFirstname();
	         messageContent += " ";
	         messageContent += transaction.getUser().getLastname();
	         messageContent += "</h1><br/> You will recive ordered tickets right after we will recive your payments. <br/><br/>"
	         		+ "Our bank account data:<br/> AirPrz Fake company<br/>Fake address 123<br/>12-234 Fake City<br/>PL61 1090 1014 0000 0712 1981 2874<br/>"
	         		+ "Purpose: ";
	         messageContent += transaction.getTransactionId();
	         message.setContent(messageContent, "text/html" );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	}

}

