package com.controlador;

import java.io.IOException;

//import java.util.Date;





//import javax.jdo.PersistenceManager;
//import java.io.PrintWriter;
import javax.servlet.http.*;

import com.entidades.Administrador;
import com.entidades.Usuario;

//import com.entidades.Comentario;

//import com.entidades.Comentario;

//import com.modelo.DataStoreConection;

/**
 *  Este servlet validará el inicio de sesion de cada usuario
 *  */

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	private UserConnection _userConnect;
	private AdminConnection _adminConnect;
	public LoginServlet()
	{
		_userConnect = null;
	}
	/**
	 * Recibe y procesa datos del formulario enviados por el mÃ©todo post, para su inicio de SesiÃ³n
	 * @param req Son los valores a recibir, de una instancia externa.
	 * @param resp Valores que devolverÃ¡
	 */
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		/**
		 * Capturamos valores de las variables tanto el nombre de ususario con su password
		 */
		String pass = req.getParameter("pass");
		String correo = req.getParameter("correo");
		String tipo = req.getParameter("tipo");
		try
		{
			if(tipo.equals("administrador")){
				_adminConnect = new AdminConnection(correo);
				if(!_adminConnect.thereAdmin()){
					if(pass.equals("admin") && correo.equals("admin@admin")){
						//Se activa una sesión
						HttpSession sesion = req.getSession(true);
						//Se pasa el parametro correo como variable de sesión
						sesion.setAttribute("email",correo);
						sesion.setAttribute("username","admin");
						sesion.setAttribute("nivel",new Integer(2));						
						//Se redirecciona al indice con la sesion activa
						resp.sendRedirect("index.jsp");
					}else{
						resp.sendRedirect("notificaciones.jsp?m=sesion_invalida1 ");
					}
				}else if(_adminConnect.getExist()){
					if(_adminConnect.validatePass(pass)){
						//Se activa una sesión
						Administrador admin = _adminConnect.getAdmin(); 
						HttpSession sesion = req.getSession(true);
						//Se pasa el parametro correo como variable de sesión
						sesion.setAttribute("email",correo);
						sesion.setAttribute("username",admin.getnombreU());
						sesion.setAttribute("nivel",new Integer(2));
						//sesion.setAttribute("valor", new Integer(22));
						//Se redirecciona al indice con la sesion activa
						resp.sendRedirect("index.jsp");
					}
					else{
						//Se redirecciona a notificaciones con m=sesion_invalida para el mensaje correspondiente
						resp.sendRedirect("notificaciones.jsp?m=sesion_invalida2 ");
					}
				}else{
					resp.sendRedirect("notificaciones.jsp?m=sesion_invalida1 ");
				}
			}else{			
				/**
				 * Creamos un objeto de conexion para el usuario(de esta manera _userConnect podra tener al usuario en caso que en la sesion
				 * se desee modificar algun dato del usuario, u otras cosas adicionales)
				 */
				_userConnect = new UserConnection(correo);
				if(_userConnect.getExist()){
					if(_userConnect.validatePass(pass)){
						//Se activa una sesión
						
						Usuario user = _userConnect.getUser();
						if(user.getValidate()){
						HttpSession sesion = req.getSession(true);
						//Se pasa el parametro correo como variable de sesión
						sesion.setAttribute("email",correo);
						sesion.setAttribute("username",user.getnombreU());
						sesion.setAttribute("nivel", new Integer(1));
						//Se redirecciona al indice con la sesion activa
						}
						resp.sendRedirect("index.jsp");
						
					}else{
						resp.sendRedirect("notificaciones.jsp?m=sesion_invalida");
					}
				}else{
					//Se redirecciona a notificaciones con m=sesion_invalida para el mensaje correspondiente
					resp.sendRedirect("notificaciones.jsp?m=sesion_invalida ");
				}
			}
		}catch(Exception e){
			System.out.println("Ocurrio un error, <a href='login.jsp'>vuelva a intentarlo</a>");
		}finally
		{
			//_userConnect.closeConnect();
		}
	}
}


