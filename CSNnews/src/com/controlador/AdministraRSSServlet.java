package com.controlador;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.*;
import com.modelo.Categoria;
import com.modelo.DataStoreConection;
import com.modelo.Diario;
import com.modelo.Rss;

/* Servlet que ejecutará las operaciones de administración de RSS
 * tales como agregar Diarios, categorias, url de nuevas rss, eliminar y definir 
 * los diario y categorias predefinidas
 * */
@SuppressWarnings("serial")
public class AdministraRSSServlet extends HttpServlet {
	
	/*Recibe las variables por GET (funciones de eliminar)*/
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
					
		String correo = req.getParameter("correo");
		String codigo_validacion = req.getParameter("cod");		
		//Se crea un objeto clase para conexion con el dtastore 
		DataStoreConection ds = new DataStoreConection();
		/* Se edita el datastore con su función validar_cuenta(correo, codigo_validacion),
		 *  cambiando el valor predefinido del atributo validado en la entidad 
		 *  usuario de 0 a 1 y retornando true si se hizo satisfactoriamente y false caso contrario 
		 * */
		if(ds.validar_cuenta(correo, codigo_validacion)){
			/*Se redirecciona a notificaciones indicando por GET: m=cuenta_validada
			 * para que se muestre el mensaje de validación correspondiente*/
			resp.sendRedirect("notificaciones.jsp?m=cuenta_validada ");
		}else{
			/*Se redirecciona a notificaciones indicando por GET: m=datos_erroneos
			 * para que se muestre el mensaje correspondiente de datos no procesados
			 * por haber sido alterados o inexistentes*/
			resp.sendRedirect("notificaciones.jsp?m=datos_erroneos;correo="+correo+";cod="+codigo_validacion);
		}	
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
			d.saveDiario();
			resp.sendRedirect("Administrar_RSS.jsp?a=diario_agregado");
		}else if(nueva_categoria != null){
			Categoria c = new Categoria(nueva_categoria);
			c.saveCategoria();
			resp.sendRedirect("Administrar_RSS.jsp?a=categoria_agregada");
		}else if(url_rss != null){
			String categoria = req.getParameter("categoria");
			String diario = req.getParameter("diario");
			Rss r = new Rss(url_rss, diario, categoria );
			r.saveRSS();
			resp.sendRedirect("Administrar_RSS.jsp?a=url_agregada");
		}
		DataStoreConection ds = new DataStoreConection();
		List<Diario> diarios= ds.getDiarios();
		req.getSession().setAttribute("lista_diarios", diarios);		
		resp.sendRedirect("Administrar_RSS.jsp");
	}
}
