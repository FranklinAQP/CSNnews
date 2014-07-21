package com.entidades;

import java.util.*;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.Inheritance;


@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class Usuario extends Persona{
	
	@Persistent
	private List<String> m_temas;
	@Persistent
	private List<String> m_categorias;
	@Persistent 
	String m_codigoV;
	@Persistent 
	boolean m_validado;
	@Persistent 
	boolean m_suspendido;
	
	public Usuario(String nombreU, String nombre, String correo,  String correo2, String pass, String codigo, String categoria)
	{
		super(nombreU, nombre, correo, correo2, pass);
		m_categorias =  new ArrayList<String>();
		m_categorias.add(categoria);
		m_codigoV = codigo;
		m_validado = false;
		m_suspendido = false;
	}
	
	public Usuario(){
		super();
	}
	public void setCodigo(String codigo)
	{
		m_codigoV = codigo;
	}
	public boolean validarCodigo(String codigo)
	{
		m_validado = (m_codigoV.equals(codigo));
		return m_validado;
	}
	public boolean getValidate()
	{
		return m_validado;
	}
	public boolean getSuspendido()
	{
		return m_suspendido;
	}
	public void setSuspendido(boolean s)
	{
		m_suspendido = s;
	}
	public String getCodigo()
	{
		return m_codigoV;
	}

	public void addTema(String tema)
	{
		m_temas.add(tema);
	}
	
	public void addCategoria(String categoria)
	{
		m_categorias.add(categoria);
	}
	
	public void deleteTema(String tema)
	{
		m_temas.remove(tema);
	}
	
	public void deleteCategoria(String categoria)
	{
		m_categorias.remove(categoria);
	}
	
	public List<String> getTemas()
	{
		return m_temas;
	}
	public void deleteAllCategorias()
	{
		m_categorias.clear();
	}
	public List<String> getCategorias()
	{
		return m_categorias;
	}
	
	public String toString() {
		String resp = m_nombre + " : " + m_nombreU + " : " +m_correo ;  
		return resp;
	}
	
	

	
	

}