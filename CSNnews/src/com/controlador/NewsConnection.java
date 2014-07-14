package com.controlador;

import java.util.Date;
import java.util.List;
import java.util.Stack;

import javax.jdo.PersistenceManager;

import com.entidades.Comentario;
import com.entidades.Noticia;
//import com.entidades.Usuario;




import javax.jdo.*;

/**
 * Esta clase define una conexion Noticia con sus comentarios con datastore de Google.
 * @author: CSNnews Group
 * @version: 22/09/2016/A
 */

public class NewsConnection {
	
	/**
	 * Es la entidad a representar
	 */
	private Noticia _news;
	/**
	 * Permite la correcta conecci√≥n con Datastore.
	 */
	private PersistenceManager _managerFact;
	/**
	 * Indicar· si el usuario esta 
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
		_news = searchNews(idNoticia);
		/*esto nos ayudara para poder saber si almacenamos desde cero todo o modificamos la noticia en cuanto a los comentarios*/
		_exist = _news!=null;
		if(!_exist)_news = new Noticia(idNoticia, idDiario, idCategoria, fecha);
		else{
			Stack<Comentario> c = _news.getComentarios();
			for(int i=0; i<c.size(); i++)
				System.out.println(c.get(i).getTexto());
		}
	}
	void addComment(String texto, String user)
	{
		Date day = new Date();
		@SuppressWarnings("deprecation")
		Comentario com = new Comentario(user,_news.getId(),texto,day.toLocaleString().split(" ")[0]);
		_news.addComentario(com);
		
	}
	/**
	 * Retornara la noticia de la conecciÛn
	 * @return Noticia de la conecciÛn
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
	 * Eval˙a si la Noticia existe en la base de datos.
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
		
	}	
	/**
	 * Cierra la connecion
	 */
	public void closeConnect()
	{
		
		registerNoticia();
		_managerFact.close();
	}
	

}
