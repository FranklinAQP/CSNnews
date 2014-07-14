package com.entidades;


//import java.io.Serializable;

import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;

import com.google.appengine.api.datastore.Key;

/**
 * Permitica el manejo de comentarios
 * @author CSNews Group
 *
 */
@PersistenceCapable
public class Comentario{

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key m_idComen;
	@Persistent
	private String m_nombreU;
	@Persistent
	private String m_idNoticia;
	@Persistent
	private String m_fecha;
	@Persistent
	private String m_texto;
	
	/**
	 * Constructor.
	 * @param nombreU Nombre de usuario 
	 * @param idNoti Id de la noticia
	 * @param texto Texto del Comentario
	 * @param fecha Fecha en la que se emitio el comentario.
	 */
	public Comentario(String nombreU, String idNoti, String texto, String fecha)
	{
		//m_idComen = id;
		m_nombreU = nombreU;
		m_idNoticia = idNoti;
		m_texto = texto;
		m_fecha = fecha;
	}
	
	/*public void setId(int id)
	{
		m_idComen = id;
	}*/
	/**
	 * Setter modificador del nombre de Uruario
	 * @param nombreU Nombre del usuario
	 */
	public void setNombreCate(String nombreU)
	{
		m_nombreU = nombreU;
	}
	/**
	 * Setter modificador del id de la noticia
	 * @param idNoti Id de la noticia
	 */
	public void setIdNoti(String idNoti)
	{
		m_idNoticia = idNoti;
	}
	/**
	 * Setter modificador del texto del Comentario
	 * @param texto El cuerpo del comentario
	 */
	public void setTexto(String texto)
	{
		m_texto = texto;
	}
	/**
	 * Setter modificador del la fecha de emisión del comentario
	 * @param fecha Fecha del comentario.
	 */
	public void setFecha(String fecha)
	{
		m_fecha = fecha;
	}
	/*public int getId()
	{
		return m_idComen;
	}*/
	/**
	 * Getter del Nombre de usuario.
	 * @return retorna el nombre de ususario.
	 */
	public String getNombreU()
	{
		return m_nombreU;
	}
	/**
	 * Getter del id de la noticia.
	 * @return Id de la noticia.
	 */
	public String getIdNoti()
	{
		return m_idNoticia;
	}
	/**
	 * Getter del cuerpo del comentario.
	 * @return Retorna el cuerpo del comentario.
	 */
	public String getTexto()
	{
		return m_texto;
	}
	/**
	 * Getter de la Fecha de emisión.
	 * @return Retorna la fecha en que se creo el comentario.
	 */
	public String getFecha()
	{
		return m_fecha;
	}
}
