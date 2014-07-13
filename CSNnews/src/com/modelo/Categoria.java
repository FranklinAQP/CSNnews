package com.modelo;

public class Categoria{

	private String nombre;
	
	public Categoria(String _nombre)
	{
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
	public void saveCategoria(){
		DataStoreConection obj = new DataStoreConection();
		obj.insert_categoria(nombre);
	}
}
