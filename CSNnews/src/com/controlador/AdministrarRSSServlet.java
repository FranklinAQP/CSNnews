package com.controlador;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;
import java.io.IOException;
import com.entidades.Categoria;
import com.entidades.Diario;
import com.entidades.Rss;

/** Servlet que ejecutará las operaciones de administración de RSS
 * tales como agregar Diarios, categorias, url de nuevas rss, eliminar y definir 
 * los diario y categorias predefinidas
 * */

@SuppressWarnings("serial")
public class AdministrarRSSServlet extends HttpServlet{
	/**
	 * Permite establecer contacto con datastore
	 */
	private PersistenceManager _managerFact;
	public AdministrarRSSServlet()
	{
		_managerFact = null;
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String d = req.getParameter("d");
		String c = req.getParameter("c");
		String r = req.getParameter("r");
		_managerFact = PersistenceMF.get().getPersistenceManager();
		if(!(d == null)){
			Diario diario = _managerFact.getObjectById(Diario.class, d);
			_managerFact.deletePersistent(diario);		
		}else if(!(c == null)){
			Categoria categoria = _managerFact.getObjectById(Categoria.class, c);
			_managerFact.deletePersistent(categoria);	
		}else if(!(r == null)){
			Rss rss = _managerFact.getObjectById(Rss.class, r);
			_managerFact.deletePersistent(rss);	
		}		
		_managerFact.close();
		resp.sendRedirect("Administrar_RSS.jsp");
	}
	
	/*Recibe las variables por POST (funciones de agregar)*/
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String url_diario = req.getParameter("url_diario");
		String nueva_categoria = req.getParameter("nueva_categoria");
		String url_rss = req.getParameter("url_rss");
		/*Para agregar un nuevo diario*/
		if(url_diario != null){
			String nombre_diario = req.getParameter("nombre_diario");
			Diario d = new Diario(nombre_diario, url_diario);
			_managerFact = PersistenceMF.get().getPersistenceManager();
			_managerFact.makePersistent(d);
			_managerFact.close();
			//resp.sendRedirect("Administrar_RSS.jsp?a=diario_agregado");
		}else if(nueva_categoria != null){
			Categoria c = new Categoria(nueva_categoria);
			_managerFact = PersistenceMF.get().getPersistenceManager();
			_managerFact.makePersistent(c);
			_managerFact.close();
			//resp.sendRedirect("Administrar_RSS.jsp?a=categoria_agregada");
		}else if(url_rss != null){
			String categoria = req.getParameter("categoria");
			String diario = req.getParameter("diario");
			Rss r = new Rss(url_rss, diario, categoria );
			_managerFact = PersistenceMF.get().getPersistenceManager();
			_managerFact.makePersistent(r);
			_managerFact.close();
			//resp.sendRedirect("Administrar_RSS.jsp?a=url_agregada");
		}		
		resp.sendRedirect("Administrar_RSS.jsp");
		
	}
}
