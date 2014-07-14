package com.entidades;

import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Diario {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private int m_idDiario;
	@Persistent
	private String m_nombreD;
	@Persistent
	private int m_idCate;
	@Persistent
	private String m_url;
	
	public Diario(int id, String nombreD, int idCate, String url)
	{
		m_idDiario = id;
		m_nombreD = nombreD;
		m_idCate = idCate;
		m_url = url;
	}
	
	public void setId(int id)
	{
		m_idDiario = id;
	}
	public void setNombreD(String nombreD)
	{
		m_nombreD = nombreD;
	}
	public void setIdCate(int idCate)
	{
		m_idCate = idCate;
	}
	public void setURL(String url)
	{
		m_url = url;
	}
	public int getId()
	{
		return m_idDiario;
	}
	public String getNombreD()
	{
		return m_nombreD;
	}
	public int getIdCate()
	{
		return m_idCate;
	}
	public String getURL()
	{
		return m_url;
	}
}
