package com.entidades;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Diario {

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	protected String idnombre;
	
	@Persistent
	private String nombre;
	
	@Persistent
	private String url;
	
	public Diario(String nombreD, String m_url)
	{
		this.idnombre = nombreD;
		this.nombre = nombreD;		
		this.url = m_url;
	}	
	public Diario()
	{	}

    public String getKey() {
        return idnombre;
    }
	
	public void setNombre(String nombreD)
	{
		this.nombre = nombreD;
	}
	public void setURL(String _url)
	{
		this.url = _url;
	}
	public String getNombre()
	{
		return nombre;
	}
	public String getURL()
	{
		return url;
	}
	@Override
	public String toString() {
		String resp = nombre + " : " + url ;  
		return resp;
	}

}

