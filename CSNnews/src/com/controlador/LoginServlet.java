package com.controlador;

import java.io.IOException;

//import java.util.Date;


//import javax.jdo.PersistenceManager;
//import java.io.PrintWriter;
import javax.servlet.http.*;

//import com.entidades.Comentario;

//import com.entidades.Comentario;

//import com.modelo.DataStoreConection;

/* Este servlet validar� el inicio de sesion de usuario*/

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private UserConnection _userConnect;
	public LoginServlet()
	{
		_userConnect = null;
	}
	/**
	 * Recibe y procesa datos del formulario enviados por el método post, para su inicio de Sesión
	 * @param req Son los valores a recibir, de una instancia externa.
	 * @param resp Valores que devolverá
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/*
		 * Capturamos valores de las variables tanto el nombre de ususario con su password
		 */
		String pass = req.getParameter("pass");
		String correo = req.getParameter("correo");
		try
		{
			/*
			 * Creamos un objeto de conexion para el usuario(de esta manera _userConnect podra tener al usuario en caso que en la sesion
			 * se desee modificar algun dato del usuario, u otras cosas adicionales)
			 */
			_userConnect = new UserConnection(correo);
			//NewsConnection nc = new NewsConnection("noticia1", "correo","tegnologia","1-2-3");
			//for(int i=0; i<10; i++)
			//{
				//nc.addComment("comentarioooooooo ", "user");
				//System.out.println("sisisisi");
			//}
			//nc.closeConnect();
			if(true)//_userConnect.validatePass(pass))
			{
				System.out.println("si existe");
				/*
				 * Se activa una sesion
				 */
				
				/**RESOLVER ESTO**/
				
				//HttpSession sesion = req.getSession(true);
				/*
				 * Se pasa el parametro correo como variable de sesi�n
				 */
				//sesion.setAttribute("email",correo);
				/*
				 * Se redirecciona al indice con la sesion activa
				 */
				//resp.sendRedirect("index.jsp");
			}else{
				/*
				 * Se redirecciona a notificaciones con m=sesion_invalida para el mensaje correspondiente
				 */
				resp.sendRedirect("notificaciones.jsp?m=sesion_invalida ");
			}
		}catch(Exception e){
			System.out.println("error");
		}finally
		{
			
		}
	}
}


