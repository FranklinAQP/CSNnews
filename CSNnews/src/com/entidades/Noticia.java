package com.entidades;

//import java.util.String;
//import java.util.List;
import java.util.Stack;

import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;

//import com.google.appengine.api.datastore.Key;


/**
 * Representa a la noticia con sus respectivos comentarios.
 * @author CSNews Group
 *
 */
@PersistenceCapable
public class Noticia {
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private String m_idNoticia;
	@Persistent
	private String m_idDiario;
	@Persistent
	private String m_idCategoria;
	@Persistent
	private String m_fecha;
	@Persistent 
	private Stack<Comentario> m_comentarios;
	
	/**
	 * Constructor.
	 * @param id Id de la noticia(url).
	 * @param idDiario Id del diario(url).
	 * @param idCate Id o nombre de la categoria.
	 * @param fecha Fecha de emisión de la noticia.
	 */
	public Noticia(String id, String idDiario, String idCate, String fecha)
	{
		m_idNoticia = id;
		m_idDiario = idDiario;
		m_idCategoria = idCate;
		m_fecha = fecha;
		m_comentarios = new Stack<Comentario>();
	}
	/**
	 * Constructor.
	 * @param id Id de la noticia(url).
	 * @param idDiario Id del diario(url).
	 * @param idCate Id o nombre de la categoria.
	 * @param fecha Fecha de emisión de la noticia.
	 * @param comentarios Comentarios realizados para esta noticia
	 */
	public Noticia(String id, String idDiario, String idCate, String fecha, Stack<Comentario> comentarios)
	{
		m_idNoticia 	= id;
		m_idDiario		= idDiario;
		m_idCategoria 	= idCate;
		m_fecha 		= fecha;
		m_comentarios 	= comentarios;
	}
	/**
	 * Setter del id de la noticia.
	 * @param id Nuevo id de la noticia.
	 */
	public void setId(String id)
	{
		m_idNoticia= id;
	}
	/**
	 * Setter de id del diario.
	 * @param idDiario Nuevo id del diario.
	 */
	public void setidD(String idDiario)
	{
		m_idDiario = idDiario;
	}
	
	/**
	 * Setter de la categoria de la noticia.
	 * @param idCate Nuevo id de la categoria de la noticia.
	 */
	public void setIdCate(String idCate)
	{
		m_idCategoria = idCate;
	}
	/**
	 * Setter de la fecha de la noticia.
	 * @param fecha Nueva fecha de la noticia.
	 */
	public void setFecha(String fecha)
	{
		m_fecha = fecha;
	}
	/**
	 * Setter de los comentarios de noticias.
	 * @param comentarios todos los comentarios actuales de la noticia.
	 */
	public void setComentarios(Stack<Comentario> comentarios)
	{
		m_comentarios = comentarios;
	}
	/**
	 * Agrega un comentario sobre la noticia.
	 * @param comentario Nuevo comentario a agregar.
	 */
	public void addComentario(Comentario comentario)
	{
		if(comentario==null)System.out.println("no hay nada");
		m_comentarios.add(comentario);
	}
	/**
	 * Getter del id de la Noticia
	 * @return Id de la noticia
	 */
	public String getId()
	{
		return m_idNoticia;
	}
	/**
	 * Getter del Id del Diario.
	 * @return Id del diario.
	 */
	public String getIdDiario()
	{
		return m_idDiario;
	}
	/**
	 * Getter del id de la Categoria.
	 * @return Id de la categoria de la noticia.
	 */
	public String getIdCate()
	{
		return m_idCategoria;
	}
	/**
	 * Getter de la fecha de la noticia.
	 * @return Fecha de la noticia.
	 */
	public String getFecha()
	{
		return m_fecha;
	}
	/**
	 * Getter de los comentarios hechos.
	 * @return Los comentarios sobre la noticia.
	 */
	public Stack<Comentario> getComentarios()
	{
		return m_comentarios;
	}
}
