package com.controlador;

import java.util.Date;
import java.util.List;
//import java.util.Stack;


import javax.jdo.PersistenceManager;

import com.entidades.Comentario;
import com.entidades.Noticia;
//import com.entidades.Usuario;





import javax.jdo.*;

/**
 * Esta clase define una conexion Noticia con sus comentarios con datastore de Google.
 * @author: CSNnews Group
 * @version: 12/07/2014/A
 */

public class NewsConnection {
	
	/**
	 * Es la entidad a representar
	 */
	private Noticia _news;
	/**
	 * Permite la correcta conecciÃ³n con Datastore.
	 */
	private PersistenceManager _managerFact;
	/**
	 * Indicará si el usuario esta 
	 */
	private boolean _exist; 
	
	/**
	 * Constructor.
	 * @param idNoticia Id de la Noticia.
	 * @param idDiario Id del diario.
	 * @param idCategoria Id de la Categoria.
	 * @param fecha Fecha origen d la noticia.
	 */
	public NewsConnection(String idNoticia, String idDiario, String idCategoria, String fecha)
	{
		_managerFact = PersistenceMF.get().getPersistenceManager();
		
		/*esto nos ayudara para poder saber si almacenamos desde cero todo o modificamos la noticia en cuanto a los comentarios*/
		_news = searchNews(idNoticia);
		_exist = _news!=null;
		if(_news == null) _news = new Noticia(idNoticia,idDiario, idCategoria, fecha);
		
		
	}
	Comentario addComment(String texto, String user)
	{
		Date day = new Date();
		@SuppressWarnings("deprecation")
		Comentario com = new Comentario(user,_news.getId(),texto,day.toLocaleString().split(" ")[0]);//
		_news.addComentario(com);
		System.out.println("agrega comentario");
		return com;
	}
	/**
	 * Retornara la noticia de la conección
	 * @return Noticia de la conección
	 */
	public Noticia getNews()
	{
		return _news;
	}
	/**
	 * Getter de la existencia de la noticia.
	 * @return Renorna el boolean de existencia de la noticia.
	 */
	public boolean getExist()
	{
		return _exist;
	}
	/**
	 * Evalúa si la Noticia existe en la base de datos.
	 * @return Indica si el ususario existe o no.
	 */
	public boolean evalExistUser()
	{
		if(_news == null)return false;
		Noticia tmp = searchNews(_news.getId());
		return tmp!=null;
	}
	
	/**
	 * Regitrar la noticia en caso de no estarlo.
	 * @return Retorna un boolean que indicara si lo registro o no.
	 */
	public boolean registerNoticia()
	{
		if(_exist)
			return false;
		_managerFact.makePersistent(_news);
		return true;
	}
	/**
	 * Busa la noticias en la base de datos
	 * @param idNoticia Id de la noticia a buscar
	 * @return La noticia deseada.
	 */
	public Noticia searchNews(String idNoticia)
	{
		if(idNoticia == "")return null;
		try{
		Query q = _managerFact.newQuery(Noticia.class);
		q.setFilter("m_idNoticia == '"+idNoticia+"'");
		@SuppressWarnings("unchecked")
		List<Noticia> noticias = (List<Noticia>) q.execute();
		if(noticias.isEmpty())return null;
		return noticias.get(0);
		}catch(Exception e)
		{
			System.out.println("no hay tabla");
			return null;
		}
		/*try{
		Noticia n = _managerFact.getObjectById(Noticia.class,idNoticia);
		return n;
		}
		catch(Exception e)
		{
			return null;
		}*/
		
	}	
	/**
	 * Cierra la connecion
	 */
	public void closeConnect()
	{
		
		registerNoticia();
		_managerFact.refreshAll();
		_managerFact.close();
	}
	

}
