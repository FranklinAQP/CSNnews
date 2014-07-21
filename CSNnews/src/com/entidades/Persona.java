package com.entidades;


import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;


@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.SUBCLASS_TABLE)
public class Persona {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected String m_key;
	
	@Persistent
	protected String m_nombreU;
	@Persistent
	protected String m_nombre;
	@Persistent
	protected String m_correo;
	@Persistent
	protected String m_correo2;
	@Persistent
	protected String m_password;
	
	
	
	public Persona(String nombreU, String nombre, String correo,  String correo2, String pass)
	{
		m_key = correo;
		m_nombreU 	= nombreU;
		m_nombre	= nombre;
		m_correo 	= correo;
		m_correo2 	= correo2;
		m_password 	= pass;
	}
	
	public Persona() {
	}

	public void setNombreU(String nombreU)
	{
		m_nombreU = nombreU;
	}
	public void setNombre(String nombre)
	{
		m_nombre = nombre;
	}
	public void setCorreo(String correo)
	{
		m_correo = correo;
	}
	public void setCorreo2(String correo2)
	{
		m_correo2 = correo2;
	}
	public void setPass(String pass)
	{
		m_password = pass;
	}
	public String getnombreU()
	{
		return m_nombreU;
	}
	public String getnombre()
	{
		return m_nombre;
	}
	public String getcorreo()
	{
		return m_correo;
	}
	public String getcorreo2()
	{
		return m_correo2;
	}
	public String getPass()
	{
		return m_password;
	}
	@Override
	public String toString() {
		String resp = "vacio";  
		return resp;
	}
}