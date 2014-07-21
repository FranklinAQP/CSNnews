package com.controlador;

import java.util.List;

import javax.jdo.PersistenceManager;

import com.entidades.Usuario;

import javax.jdo.*;

/**
 * Esta clase define una conexion Usuario con datastore de Google.
 * @author: CSNnews Group
 * @version: 22/09/2016/A
 */

public class UserConnection {
	
	/**
	 * Es la entidad a representar
	 */
	private Usuario _user;
	/**
	 * Permite la correcta conección con Datastore.
	 */
	private PersistenceManager _managerFact;
	/**
	 * Indicará si el usuario existe 
	 */
	private boolean _exist; 
	
	/**
	 * Constructor.
	 * @param correo Correo del usuario de user a conectar
	 */
	public UserConnection(String correo)
	{
		_managerFact = PersistenceMF.get().getPersistenceManager();
		_user = searchUser(correo);
		_exist = _user!=null;
	}
	/**
	 * Constructor.
	 * @param user Usuario a conectar.
	 */
	public UserConnection(Usuario user)
	{
		_user = user;
		_managerFact = PersistenceMF.get().getPersistenceManager();
		_exist = evalExistUser();
	}
	/**
	 * Retornara el usuario de la conección
	 * @return El usuario de la conección
	 */
	public Usuario getUser()
	{
		return _user;
	}
	/**
	 * Getter.
	 * @return Renorna el boolean de existencia de la clase.
	 */
	public boolean getExist()
	{
		return _exist;
	}
	/**
	 * Evalúa si el usuario existe en la base de datos.
	 * @return Indica si el ususario existe o no.
	 */
	public boolean evalExistUser()
	{
		if(_user == null)return false;
		Usuario tmp = searchUser(_user.getcorreo());
		return tmp!=null;
	}
	
	/**
	 * Regitrar al usuario en caso de no estarlo.
	 * @return Retorna un boolean que indicara si lo registro o no.
	 */
	public boolean registerUser()
	{
		if(_exist)
			return false;
		_managerFact.makePersistent(_user);
		return true;
	}
	/**
	 * Busca a un usuario con su correo en la base de datos.
	 * @param correo Correo de usuario.
	 * @return Usuario con dicho correo.
	 */
	public Usuario searchUser(String correo)
	{
		if(correo == "")return null;
		Query q = _managerFact.newQuery(Usuario.class);
		q.setFilter("m_correo == '"+correo+"'");
		@SuppressWarnings("unchecked")
		List<Usuario> usuarios = (List<Usuario>) q.execute();
		if(usuarios.isEmpty())return null;
		return usuarios.get(0);
		
	}
	/**
	 * Evalua si dicho password es del usuario
	 * @param pass contraseña del usuario
	 * @return Si es o no su contraseña
	 */
	public boolean validatePass(String pass)
	{
		if(pass=="" || _user==null)return false;
		return _user.getPass().equals(pass);
	}
	
	public boolean validateCorreo()
	{
		return _user.getValidate();
	}
	/**
	 * Cierra la connecion
	 */
	public void closeConnect()
	{
		_managerFact.close();
	}
	

}
