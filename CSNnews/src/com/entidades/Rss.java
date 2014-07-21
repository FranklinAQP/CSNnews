package com.entidades;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Rss {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String idurl;
	
	@Persistent
	private String url;
	
	@Persistent
	private String diario;
	
	@Persistent
	private String categoria;
	
	public Rss()
	{

	}
	public Rss(String _url, String _diario, String _categoria)
	{
		idurl = _url;
		url = _url;
		diario= _diario;
		categoria = _categoria;
	}	
	public void setURL(String _url)
	{
		url = _url;
	}
	public String getURL()
	{
		return url;
	}
	public void setDiario(String _diario)
	{
		diario= _diario;
	}
	public String getDiario()
	{
		return diario;
	}
	public void setCategoria(String _categoria)
	{
		categoria = _categoria;
	}
	public String getCategoria()
	{
		return categoria;
	}	
}
