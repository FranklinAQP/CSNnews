package com.controlador;
import java.util.List;

import javax.jdo.PersistenceManager;

import com.entidades.Administrador;
//import com.entidades.Usuario;

import javax.jdo.*;

/**
 * Esta clase define una conexion Administrador con datastore de Google.
 * @author: CSNnews Group
 */

public class AdminConnection {
	/**
	 * Es la entidad Administrador a representar
	 */
	private Administrador _admin;
	/**
	 * Permite la correcta conección con Datastore.
	 */
	private PersistenceManager _managerFact;
	/**
	 * Indicará si el administrador existe 
	 */
	private boolean _exist; 
	
	/**
	 * Constructor.
	 * @param correo Correo del usuario de _admin a conectar
	 */
	public AdminConnection(String correo)
	{
		_managerFact = PersistenceMF.get().getPersistenceManager();
		_admin = searchAdmin(correo);
		_exist = _admin!=null;
	}
	/**
	 * Constructor.
	 * @param admin Administrador a conectar.
	 */
	public AdminConnection(Administrador admin)
	{
		_admin = admin;
		_managerFact = PersistenceMF.get().getPersistenceManager();
		_exist = evalExistAdmin();
	}
	/**
	 * Retornara el administrador de la conección
	 * @return El administrador de la conección
	 */
	public Administrador getAdmin()
	{
		return _admin;
	}
	/**
	 * si existe el administrador.
	 * @return Renorna el boolean de existencia de la clase.
	 */
	public boolean getExist()
	{
		return _exist;
	}
	/**
	 * Evalúa si el administrador existe en la base de datos.
	 * @return Indica si el administrador existe o no.
	 */
	public boolean evalExistAdmin()
	{
		if(_admin == null)return false;
		Administrador tmp = searchAdmin(_admin.getcorreo());
		return tmp!=null;
	}
	
	/**
	 * Regitrar al administrador 
	 * @return Retorna un boolean que indicara si lo registro o no.
	 */
	public boolean registerAdmin()
	{
		if(_exist)
			return false;
		_managerFact.makePersistent(_admin);
		return true;
	}
	/**
	 * Busca a un administrador con su correo en la base de datos.
	 * @param correo Correo de usuario.
	 * @return Administrador con dicho correo.
	 */
	public Administrador searchAdmin(String correo)
	{
		if(correo == "")return null;
		Query q = _managerFact.newQuery(Administrador.class);
		q.setFilter("m_correo == '"+correo+"'");
		@SuppressWarnings("unchecked")
		List<Administrador> admins = (List<Administrador>) q.execute();
		if(admins.isEmpty())return null;
		return admins.get(0);		
	}
	/**
	 * Determina si existen administradores registrados
	 * @return boolean
	 */
	public boolean thereAdmin(){
		Query q = _managerFact.newQuery(Administrador.class);
		@SuppressWarnings("unchecked")
		List<Administrador> admins = (List<Administrador>) q.execute();
		if(admins.isEmpty()) return false;
		return true;		
	}
	
	/**
	 * Evalua si dicho password es del administrador
	 * @param pass contraseÃ±a del usuario
	 * @return Si es o no su contraseña
	 */
	public boolean validatePass(String pass)
	{
		if(pass=="" || _admin==null)return false;
		return _admin.getPass().equals(pass);
	}	
	/**
	 * Cierra la connecion
	 */
	public void closeConnect()
	{
		_managerFact.close();
	}
	

}
