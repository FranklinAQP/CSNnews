package com.modelo;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

//rss         (id_rss, url_rss, nombre_rss , categoria_rss )
//noticia     (id_noticia, id_rss, fecha_noticia)
//usuario     (id_usuario, nombre_usuario, apellido_usuario, correo_usuario, password_usuario)

//  comentarios(id_notica , id_rss, id_usuario, comentario)
//  interes     (id_noticia, id_rss, id_usuario)
//  vistas      (id_noticia, id_rss, contador)

// long numLong=Long.parseLong(numString); long n=4456465456456464L;



public class DataStoreConection {
	public DataStoreConection(){}
	///FUNCIONES DE INSRECION
	//insertando un  rss --------------------------------------------------------------------
	public void insert_rss(String url_rss,String nombre_rss ,String categoria_rss ){	//url_rss  es el id 
		DatastoreService data_store_rss= DatastoreServiceFactory.getDatastoreService();	
		Entity rss =  new Entity("rss",url_rss);
		rss.setProperty("nombre_rss",nombre_rss);
		rss.setProperty("categoria_rss",categoria_rss);
		data_store_rss.put(rss);
	}
	

	public void insert_noticia(String id_noticia, String url_rss, String  fecha_noticia){ //el id viene de la noticia
		DatastoreService data_store_noticia= DatastoreServiceFactory.getDatastoreService();	
		Entity noticia =  new Entity("noticia",id_noticia);
		noticia.setProperty("url_rss",url_rss);
		noticia.setProperty("fecha_noticia",fecha_noticia);
		data_store_noticia.put(noticia);
		
	}
	

	public void insert_usuario(String id_usuario, String nombre_usuario, String apellido_usuario, String correo_usuario, String password_usuario){
		DatastoreService data_store_usuario= DatastoreServiceFactory.getDatastoreService();	
		Entity usuario =  new Entity("usuario");
		usuario.setProperty("id_usuario",id_usuario);
		usuario.setProperty("nombre_usuario",nombre_usuario);
		usuario.setProperty("apellido_usuario",apellido_usuario);
		usuario.setProperty("correo_usuario",correo_usuario);
		usuario.setProperty("password_usuario",password_usuario);
		data_store_usuario.put(usuario);
	}
	

	public void insert_comentario(String id_noticia , String url_rss, String id_usuario, String comentario_){
		DatastoreService data_store_comentario= DatastoreServiceFactory.getDatastoreService();	
		Entity comentario =  new Entity("comentario");
		comentario.setProperty("id_noticia",id_noticia);
		comentario.setProperty("url_rss",url_rss);
		comentario.setProperty("id_usuario",id_usuario);
		comentario.setProperty("comentario",comentario_);
		data_store_comentario.put(comentario);
		
	}
	

	public void insert_interes(String id_noticia, String url_rss,String id_usuario){
		DatastoreService data_store_interes = DatastoreServiceFactory.getDatastoreService();	
		Entity interes =  new Entity("comentario");
		interes.setProperty("id_noticia",id_noticia);
		interes.setProperty("url_rss",url_rss);
		interes.setProperty("id_usuario",id_usuario);
		data_store_interes.put(interes);
	}
		
   //--------------------------------------------------

//---- funciones para eliminar tablas
	
	public void eliminar_registro(String tabla,String id){
		DatastoreService db= DatastoreServiceFactory.getDatastoreService();
		Key key=KeyFactory.createKey(tabla,id); 
		db.delete(key);
	}
	
	public void eliminar_rss( String url_rss ){ 
		this.eliminar_registro("rss",url_rss); 
	}
	
	public void eliminar_noticia( String id_noticia ){
		this.eliminar_registro("noticia",id_noticia); 
	}
	
	public void eliminar_usuario( String id_usuario ){ 
		this.eliminar_registro("usuario",id_usuario);
	}
 
//----- consultas get
	
	
	String get_dato (String tabla,String atributo, String dato){ // en la tabla USUARIO en atributo NOMBRE == "pedro" (dato)
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query(tabla).addFilter(atributo, Query.FilterOperator.EQUAL, dato);
		PreparedQuery pq = datastore.prepare(q);
		for (Entity result : pq.asIterable()) {
			  return (String) result.getProperty("nombre_usuario");
		}
		return "NULL";
	}
	
	String get_nombre_usuario(String id_usuario){ return get_dato("usuario","id_usuario", id_usuario);}
	
	String get_nombre_nombre_rss(String id_rss){ return get_dato("usuario","nombre_rss", id_rss);}
	
	String get_nombre_apellido(String id_usuario){ return get_dato("usuario","apellido_usuario", id_usuario);}
	
	
	
	
	
	
}



