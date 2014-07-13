package com.modelo;

import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Diario {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String nombre;
	@Persistent
	private String url;
	
	public Diario(String nombreD, String m_url)
	{
		nombre = nombreD;		
		url = m_url;
	}	
	public void setNombre(String nombreD)
	{
		nombre = nombreD;
	}
	public void setURL(String _url)
	{
		url = _url;
	}
	public String getNombre()
	{
		return nombre;
	}
	public String getURL()
	{
		return url;
	}
	public void saveDiario(){
		DataStoreConection obj = new DataStoreConection();
		obj.insert_diario(nombre, url);
	}
}
