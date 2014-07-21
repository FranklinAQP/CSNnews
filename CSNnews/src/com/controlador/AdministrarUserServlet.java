package com.controlador;

import java.io.IOException;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.entidades.Administrador;
import com.entidades.Usuario;

@SuppressWarnings("serial")
public class AdministrarUserServlet extends HttpServlet{
	/**
	 * Permite establecer contacto con datastore
	 */
	private PersistenceManager _managerFact;
	public AdministrarUserServlet()
	{
		_managerFact = null;
	}
	/*public void NuevoAdministrador(String correo)
	{
		UserConnection _userConnect= new UserConnection(correo);
		if (_userConnect.getExist()){			
			if(_userConnect.validateCorreo()){
				Usuario nadmin= _userConnect.getUser();
			}
		}
	}*/
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String eliminar_correo = req.getParameter("d");
		String suspender_correo = req.getParameter("s");
		String habilitar_correo = req.getParameter("h");
		_managerFact = PersistenceMF.get().getPersistenceManager();
		if(!(eliminar_correo == null)){
			Usuario user = _managerFact.getObjectById(Usuario.class, eliminar_correo);
			_managerFact.deletePersistent(user);		
		}else if(!(suspender_correo == null)){
			Usuario user = _managerFact.getObjectById(Usuario.class, suspender_correo);
			user.setSuspendido(true);
			_managerFact.makePersistent(user);	
		}else if(!(habilitar_correo == null)){
			Usuario user = _managerFact.getObjectById(Usuario.class, habilitar_correo);
			user.setSuspendido(false);
			_managerFact.makePersistent(user);	
		}	
		_managerFact.close();
		resp.sendRedirect("Administrar_Usuarios.jsp?eliminado=ok");
	}
	
	public void doPost(HttpServletRequest rep, HttpServletResponse resp) throws IOException {
		String correo = rep.getParameter("correo");
		String cargo = rep.getParameter("cargo");
		UserConnection _userConnect	= new UserConnection(correo);		
		if(_userConnect.getExist()){
			Usuario user = _userConnect.getUser();
			if(user.getValidate() && !user.getSuspendido()){
				//Se crea cuenta de administrador
				AdminConnection _adminConnect = new AdminConnection(correo);
				if(!_adminConnect.getExist()){
					_adminConnect = new AdminConnection(new Administrador(user.getnombreU(),user.getnombre(),user.getcorreo(), user.getcorreo2(), user.getPass(), cargo));
					_adminConnect.registerAdmin();
					_adminConnect.closeConnect();
				}else{
					resp.sendRedirect("notificaciones.jsp?m=ya_es_adminstrador");
				}
			}
		}else{
			//Se redirecciona a notificaciones con m=sesion_invalida para el mensaje correspondiente
			resp.sendRedirect("notificaciones.jsp?m=usuario_sin_permisos ");
		}
		
	}
}


