package com.modelo;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;


@PersistenceCapable
public class Comentario {

	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private int m_idComen;
	@Persistent
	private String m_nombreU;
	@Persistent
	private int m_idNoticia;
	@Persistent
	private String m_fecha;
	@Persistent
	private String m_texto;
	
	public Comentario(int id, String nombreU, int idNoti, String texto, String fecha)
	{
		m_idComen = id;
		m_nombreU = nombreU;
		m_idNoticia = idNoti;
		m_texto = texto;
		m_fecha = fecha;
	}
	
	public void setId(int id)
	{
		m_idComen = id;
	}
	public void setNombreCate(String nombreU)
	{
		m_nombreU = nombreU;
	}
	public void setIdNoti(int idNoti)
	{
		m_idNoticia = idNoti;
	}
	public void setTexto(String texto)
	{
		m_texto = texto;
	}
	public void setFecha(String fecha)
	{
		m_fecha = fecha;
	}
	public int getId()
	{
		return m_idComen;
	}
	public String getNombreU()
	{
		return m_nombreU;
	}
	public int getIdNoti()
	{
		return m_idNoticia;
	}
	public String getTexto()
	{
		return m_texto;
	}
	public String getFecha()
	{
		return m_fecha;
	}
}
