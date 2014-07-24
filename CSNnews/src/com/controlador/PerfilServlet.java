package com.controlador;

import java.io.IOException;

import javax.servlet.http.*;

import com.entidades.Administrador;
import com.entidades.Usuario;


/**
 *  Este servlet validar� los cambios hechos al perfil de usuario y administrador
 *  */
@SuppressWarnings("serial")
public class PerfilServlet extends HttpServlet {
	private UserConnection _userConnect;
	private AdminConnection _adminConnect;
	public PerfilServlet()
	{
		_userConnect = null;
		_adminConnect = null;
	}
	/**
	 * Recibe y procesa datos del formulario enviados por el método post, para su inicio de Sesión
	 * @param req Son los valores a recibir, de una instancia externa.
	 * @param resp Valores que devolverá
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/**
		 * Capturamos valores de las variables dependiendo si es usuario o administrador
		 */
		if(req.getSession().getAttribute("email")==null){
			resp.sendRedirect("index.jsp");
		}
		//String username = req.getParameter("username");
		String nombre = req.getParameter("nombre");		
		String correo = req.getParameter("correo");		
		String correo2 = req.getParameter("correo2");
		String passa = req.getParameter("pass_a");
		String passb = req.getParameter("pass_b");
		String[] intereses = req.getParameterValues("currency[]");
		if(passa!=null){
			if(!passa.equals(passb)){
				resp.sendRedirect("notificaciones.jsp?m=password_invalido ");
			}
		}
		/**
		 * En el caso de administrador
		 */
		if((Integer)req.getSession().getAttribute("nivel")==2){
			
			if(correo.equals("admin@admin")){
				resp.sendRedirect("notificaciones.jsp?m=operacion_invalida ");
			}else{
				_adminConnect = new AdminConnection((String)req.getSession().getAttribute("email"));
				Administrador admin = _adminConnect.getAdmin();
				if(passa==null){
					admin.setNombre(nombre);
					admin.setCorreo2(correo2);
				}else{
					admin.setPass(passa);
				}
				_adminConnect.closeConnect();
				resp.sendRedirect("perfil.jsp");
			}	
		/**
		 * En el caso de usuario
		 */
		}else if((Integer)req.getSession().getAttribute("nivel")==1){
			_userConnect = new UserConnection((String)req.getSession().getAttribute("email"));
			Usuario user = _userConnect.getUser();
			if(passa==null){
				user.setNombre(nombre);
				user.setCorreo2(correo2);
				user.deleteAllCategorias();
				for (int i = 0; i< intereses.length; i++) { 
					user.addCategoria(intereses[i]); 
					}
			}else{				
				user.setPass(passa);
			}			 
			_userConnect.closeConnect();
			resp.sendRedirect("perfil.jsp");
		}
		resp.sendRedirect("notificaciones.jsp?m=operacion_invalida ");
	}
}
			