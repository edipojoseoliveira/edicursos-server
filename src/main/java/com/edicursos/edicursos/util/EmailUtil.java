package com.edicursos.edicursos.util;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailUtil {

	public void enviarEmail(String remetente, String destinatario, 
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
		email.setAuthenticator(new DefaultAuthenticator(remetente, "94.gr,ed"));
		email.setDebug(true);
		email.send();
		
	}
	
}
