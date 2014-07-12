package com.controlador;

import java.io.IOException;
//import java.io.PrintWriter;
import javax.servlet.http.*;
import com.modelo.DataStoreConection;

/* Este servlet validará el inicio de sesión de usuario*/

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	/*Recibe y procesa los datos de formulario enviados por metodo post*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//Capturamos valores de las variables
		String pass = req.getParameter("pass");
		String correo = req.getParameter("correo");
		//Creamos un objeto de conexion al datastore
		DataStoreConection ds = new DataStoreConection(); 
		/* Consulta la exitencia del correo ingresado en el datastore y  
		 * su comparación con el password ingresado con valida_sesion(correo, pass)
		 * existe y validado = 1, no existe o no validado = 0 
		 * */
		if(ds.valida_sesion(correo, pass)){
			//Se activa una sesión
			HttpSession sesion = req.getSession(true);
			//Se pasa el parametro correo como variable de sesión
			sesion.setAttribute("email",correo);
			//Se redirecciona al indice con la sesion activa
			resp.sendRedirect("index.jsp");
		}else{
			//Se redirecciona a notificaciones con m=sesion_invalida para el mensaje correspondiente
			resp.sendRedirect("notificaciones.jsp?m=sesion_invalida ");
		}
	}
}


