package com.edicursos.edicursos.util;

import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	public void enviarEmailSimples(String remetente, String destinatario, 
			String assunto, String mensagem) throws EmailException {
		
		SimpleEmail email = new SimpleEmail();
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setFrom(remetente, "EdiCursos");
		email.addTo(destinatario);
		email.setSubject(assunto);
		email.setMsg(mensagem);
		email.setSSLOnConnect(false); //465
		email.setStartTLSEnabled(true); //587
		email.setAuthenticator(new DefaultAuthenticator(remetente, "Minha1aSenha"));
		email.setDebug(true);
		email.send();
		
	}
	
	public void enviarEmailComHtml(String remetente, String destinatario, 
			String assunto, String mensagem, ByteArrayDataSource dataSource, String nomeArquivo) throws EmailException {
		
		HtmlEmail email = new HtmlEmail();
		
		//Se contem anexo
		if (dataSource != null) {
			email.attach(dataSource, nomeArquivo, "");
		}
		
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(587);
		email.setFrom(remetente, "EdiCursos");
		email.addTo(destinatario);
		email.setSubject(assunto);
		email.setHtmlMsg(mensagem);
		email.setSSLOnConnect(false); //465
		email.setStartTLSEnabled(true); //587
		email.setAuthenticator(new DefaultAuthenticator(remetente, "Minha1aSenha"));
		email.setDebug(true);
		email.setCharset(EmailConstants.UTF_8);
		email.send();
		
	}
	
}
