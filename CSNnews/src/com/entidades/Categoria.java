package com.entidades;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Categoria{
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String idnombre;
	
	@Persistent
	private String nombre;
	
	public Categoria()
	{

	}
	public Categoria(String _nombre)
	{
		idnombre = _nombre;
		nombre = _nombre;
	}
	public void setNombre(String _nombre)
	{
		nombre = _nombre;
	}
	public String getNombre()
	{
		return nombre;
	}
}
