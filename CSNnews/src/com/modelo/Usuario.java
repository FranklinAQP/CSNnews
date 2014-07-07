package com.modelo;

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
	private List<String> m_diarios;
	
	
	public Usuario(String nombreU, String nombre, String correo,  String correo2, String pass/*, Date fecha*/)
	{
		super(nombreU, nombre, correo, correo2, pass/*, fecha*/);
	}
	
	public void addTema(String tema)
	{
		m_temas.add(tema);
	}
	
	public void addDiario(String diario)
	{
		m_diarios.add(diario);
	}
	
	public void deleteTema(String tema)
	{
		m_temas.remove(tema);
	}
	
	public void deleteDiario(String diario)
	{
		m_diarios.remove(diario);
	}
	
	public List<String> getTemas()
	{
		return m_temas;
	}
	public List<String> getDiarios()
	{
		return m_temas;
	}
	
	
	public String toString() {
		String resp = m_nombre + " : " + m_nombreU + " : " +m_correo ;  
		return resp;
	}
	
	

}
