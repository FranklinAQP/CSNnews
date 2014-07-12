package com.controlador;

import java.io.IOException;
import java.util.Random;
import javax.servlet.http.*;
import com.modelo.DataStoreConection;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/* Este servlet registrará un nuevo usuario y enviará mensaje de validación a su correo*/

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
		
	/*Recibe y procesa datos del formulario enviados por el método post*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {	
		//capturamos valores de variables
		String username = req.getParameter("username");
		String nombre = req.getParameter("nombre");		
		String correo = req.getParameter("correo");		
		String sexo = req.getParameter("sexo");
		String fechan = req.getParameter("fechan");
		String correo2 = req.getParameter("correo2");
		String passa = req.getParameter("pass_a");
		String passb = req.getParameter("pass_b");
		String intereses = req.getParameter("intereses");		
		//Verificamos si el password ingresado coincide con su password de verificación
		if(!passa.equals(passb)){
			//Si no coincide se redirecciona a notificaciones con m=password_incorrecto para el mensaje correspondiente
			resp.sendRedirect("notificaciones.jsp?m=password_incorrecto"); 				
		}else{
			//Si existe se crea un objeto de conexion al datastore
			DataStoreConection obj = new DataStoreConection();	
			//Se comprueba en el datastore si existe algun correo identico validado:
			//no existe correo identico=0
			//existe correo identico no validado=1
			//existe correo identico validado=2
			Integer val_correo = obj.existe_correo_validado(correo); 
			if(val_correo==0){		
				//creamos una variable que guardara una seuencia de 10 caracteres 
				//que sera el codigo para validacion de correo
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
				//ingreso de datos de nuevo usuario al datastore sin validación de correo
				obj.insert_usuario(username, nombre, correo, sexo, fechan, correo2, passa, intereses, cod_validacion);
				
				//a continuacion se envia mensaje a su correo para validacion de correo
				Properties props = new Properties();
				Session session = Session.getDefaultInstance(props, null);
				String msgBody = "Su cuenta en CSNnews ha sido creada satisfactoriamente, con los siguientes datos: \n";
				msgBody += "username: "+ username +"\n";
				msgBody += "password: "+ passa +"\n";
				msgBody += "Porfavor haga click en el siguiente enlace o abralo en una nueva pestaña de su navegador ";
				msgBody += "para validar su correo y tener acceso a los beneficios de su cuenta: \n http://csvistas12.appspot.com/csnnews?correo="+ correo +"&cod="+cod_validacion;
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
			}else if(val_correo==1){
				//Si el correo ya existe pero no ha sido validado se redirecciona a notificaciones
				resp.sendRedirect("notificaciones.jsp?m=no_validado");
			}else{
				//Si el correo ya existe y ha sido validado se redirecciona a notificaciones
				resp.sendRedirect("notificaciones.jsp?m=correo_utilizado");
			}
		}
	}
}
