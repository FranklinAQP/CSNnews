package com.modelo;

public class Rss {
	private String url;
	private String diario;
	private String categoria;
	
	public Rss(String _url, String _diario, String _categoria)
	{
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
	public void saveRSS(){
		DataStoreConection obj = new DataStoreConection();
		obj.insert_rss(url, diario, categoria);
	}
}
