package com.controlador;

import java.io.IOException;
//import java.util.Random;

import java.util.Random;

import javax.mail.Session;
import javax.servlet.http.*;

import com.entidades.*;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;

import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/* 
 * Este servlet registra un nuevo usuario y envia mensaje de validacion a su correo
 * 
 */
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	private UserConnection _userConnect;
	public RegisterServlet()
	{
		_userConnect = null;
	}
	/**
	 * Recibe y procesa datos del formulario enviados por el método post
	 * @param req Son los valores a recibir, de una instancia externa.
	 * @param resp Valores que devolverá
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		/*
		 * capturamos valores de variables
		 */
		String username = req.getParameter("username");
		String nombre = req.getParameter("nombre");		
		String correo = req.getParameter("correo");		
		String correo2 = req.getParameter("correo2");
		String passa = req.getParameter("pass_a");
		String passb = req.getParameter("pass_b");
		String intereses = req.getParameter("intereses");
		
		/*
		 * Verificamos si el password ingresado coincide con su password de verificacion
		 */
		if(!passa.equals(passb)){
			/*
			 * Si no coincide se redirecciona a notificaciones con m=password_incorrecto para el mensaje correspondiente
			 */
			resp.sendRedirect("notificaciones.jsp?m=password_incorrecto"); 				
		}else{
			/**
			 * Creamos una conexion del datastore con el usuario Ingresado
			 * para registrarlo 
			 */
			try{
				String codigo = generateCode();
				_userConnect = new UserConnection(new Usuario(username,nombre,correo, correo2, passa, codigo, intereses));
				if(!_userConnect.getExist()){					
					//resp.sendRedirect("notificaciones.jsp?i="+intereses);
					_userConnect.registerUser();
					_userConnect.closeConnect();
					//a continuacion se envia mensaje a su correo para validacion de correo
					Properties props = new Properties();
					Session session = Session.getDefaultInstance(props, null);
					String msgBody = "Su cuenta en CSNnews ha sido creada satisfactoriamente, con los siguientes datos: \n";
					msgBody += "username: "+ username +"\n";
					msgBody += "password: "+ passa +"\n";
					msgBody += "Porfavor haga click en el siguiente enlace o abralo en una nueva pesta�a de su navegador ";
					msgBody += "para validar su correo y tener acceso a los beneficios de su cuenta: \n http://csnewsunsa.appspot.com/csnnews?correo="+ correo +"&cod="+codigo;
					try {
						//se crea un objeto para javamail
						Message msg = new MimeMessage(session);
						//se coloca datos del remitente (correo, nombre)
						msg.setFrom(new InternetAddress("redinfoaqp@gmail.com", "CSNnews"));
						//se coloca datos del destinatario
						msg.addRecipient(Message.RecipientType.TO, new InternetAddress(correo, nombre));
						//Se coloca el titulo del mensaje 
						msg.setSubject("Su cuenta en CSNnews ha sido creada");
						msg.setText(msgBody);
						Transport.send(msg);
		
					} catch (AddressException e) {
						System.out.println(e);
						resp.getWriter().println("Ocurrio un error en la direccion del correo, <a href='registro.jsp'>vuelva a intentarlo</a>");
					} catch (MessagingException e) {
						System.out.println(e);
						resp.getWriter().println("Ocurrio un error en el mensaje del correo, <a href='registro.jsp'>vuelva a intentarlo</a>");
					}
						resp.sendRedirect("notificaciones.jsp?m=registrado");
				}else{
					//Si el correo ya ha sido registrado se redirecciona a notificaciones
					resp.sendRedirect("notificaciones.jsp?m=correo_utilizado");
				}
			}catch(Exception e){
				System.out.println(e);
				resp.getWriter().println("Ocurrio un error, <a href='registro.jsp'>vuelva a intentarlo</a> donde interes="+intereses);
			}finally{
				//_userConnect.closeConnect();
			}
		}
	}
	
	/*Genera el codigo de validacion*/ 
	public String generateCode()
	{
		Integer longitud=10;
		String cod_validacion = "";
		long milis = new java.util.GregorianCalendar().getTimeInMillis();
		Random r = new Random(milis);
		int i = 0;
		while ( i < longitud){
			char c = (char)r.nextInt(255);
			if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
				cod_validacion += c;
				i ++;
			}
		}
		return cod_validacion;
	}
	
	
}
