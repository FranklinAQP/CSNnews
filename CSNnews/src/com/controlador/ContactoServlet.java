package com.controlador;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class ContactoServlet extends HttpServlet{
	public void doPost(HttpServletRequest rep, HttpServletResponse resp) throws IOException 
	{
		String nombre = rep.getParameter("nombre");
		String correo = rep.getParameter("correo");
		String tema = rep.getParameter("tema");
		String mensaje = rep.getParameter("mensaje");
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);
		String msgBody = "Formulario de contacto de CSNnews\n";
		msgBody += "nombre: "+ nombre +"\n";
		msgBody += "correo: "+ correo +"\n";
		msgBody += "tema: "+ tema +"\n";
		msgBody += "mensaje: "+ mensaje;		
		try {
			//se crea un objeto para javamail
			Message msg = new MimeMessage(session);
			//se coloca datos del remitente (correo, nombre)
			msg.setFrom(new InternetAddress(correo, nombre));
			//se coloca datos del destinatario
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress("redinfoaqp@gmail.com", "CSNnews"));
			//Se coloca el titulo del mensaje 
			msg.setSubject("Formulario de Contacto CSNnews");
			msg.setText(msgBody);
			Transport.send(msg);

		} catch (AddressException e) {
			System.out.println(e);
			resp.getWriter().println("Ocurrio un error en la direccion del correo, <a href='registro.jsp'>vuelva a intentarlo</a>");
		} catch (MessagingException e) {
			System.out.println(e);
			resp.getWriter().println("Ocurrio un error en el mensaje del correo, <a href='registro.jsp'>vuelva a intentarlo</a>");
		}
			resp.sendRedirect("contacto.jsp?m=ok");
	}
}
