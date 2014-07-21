package com.controlador;

import java.io.IOException;

import javax.servlet.http.*;

import com.entidades.Usuario;
//import com.modelo.DataStoreConection;

/** Servlet que validará los correos de las cuentas de usuarios registrados
 * al cual accederan por un enlace que se envia a los correos 
 * en el cual estan predefinidas las variables por GET: correo, y cod 
 * http://dominio.com/csnews?correo=...&cod=...
 * */
@SuppressWarnings("serial")
public class CSNnewsServlet extends HttpServlet {
	
	private UserConnection _userConnect;
	public CSNnewsServlet()
	{
		_userConnect = null;
	}
	
	/**Recibe las variables de validación por GET*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Se obtienen las variables
		String correo = req.getParameter("correo");
		String codigo_validacion = req.getParameter("cod");	
		//Se crea un objeto de conexion de usuario en base al correo
		_userConnect = new UserConnection(correo);
		if(_userConnect.getExist()){
			Usuario cliente = _userConnect.getUser();
			cliente.validarCodigo(codigo_validacion);
			/**
			 * Se redirecciona a notificaciones indicando por GET: m=cuenta_validada
			 * para que se muestre el mensaje de validaciï¿½n correspondiente
			 * 
			 */
			resp.sendRedirect("notificaciones.jsp?m=cuenta_validada ");
		}else{
			/*
			 * Se redirecciona a notificaciones indicando por GET: m=datos_erroneos
			 * para que se muestre el mensaje correspondiente de datos no procesados
			 * por haber sido alterados o inexistentes
			 */
			resp.sendRedirect("notificaciones.jsp?m=datos_erroneos;correo="+correo+";cod="+codigo_validacion);
		}		
	}
}
