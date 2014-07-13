package com.modelo;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
//import com.google.appengine.api.datastore.PreparedQuery;
//import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;

//rss         (id_rss, url_rss, nombre_rss , categoria_rss )
//noticia     (id_noticia, id_rss, fecha_noticia)
//usuario     (id_usuario, nombre_usuario, apellido_usuario, correo_usuario, password_usuario)

//  comentarios(id_notica , id_rss, id_usuario, comentario)
//  interes     (id_noticia, id_rss, id_usuario)
//  vistas      (id_noticia, id_rss, contador)

// long numLong=Long.parseLong(numString); long n=4456465456456464L;

public class DataStoreConection {
	public DataStoreConection(){}
	///FUNCIONES DE INSERCION
	
	public List<Diario> getDiarios(){
		List<Diario> misdiarios = new ArrayList<Diario>();
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		Query q = new  Query ("diario");
		PreparedQuery pq = ds.prepare(q);
		//return pq.asList(FetchOptions.Builder.withLimit(5));
		for(Entity u1:pq.asIterable()){
			String nombre=u1.getProperty("nombre").toString();
			String url=u1.getProperty("url").toString();
			Diario nuevo = new Diario(nombre, url);
			misdiarios.add(nuevo);
		}
		return misdiarios;
	}
	
	
	//insertando un  rss --------------------------------------------------------------------
	public void insert_rss(String url,String diario ,String categoria ){	//url_rss  es el id 
		DatastoreService data_store_rss= DatastoreServiceFactory.getDatastoreService();	
		Entity rss =  new Entity("rss",url);
		rss.setProperty("url",url);
		rss.setProperty("diario",diario);
		rss.setProperty("categoria",categoria);
		data_store_rss.put(rss);
	}
	
	public void insert_diario(String nombre, String url ){	//url_rss  es el id 
		DatastoreService ds= DatastoreServiceFactory.getDatastoreService();	
		Entity diario =  new Entity("diario", nombre);
		diario.setProperty("nombre", nombre);
		diario.setProperty("url",url);
		ds.put(diario);
	}
	
	public void insert_categoria(String nombre){	//url_rss  es el id 
		DatastoreService ds= DatastoreServiceFactory.getDatastoreService();	
		Entity categoria =  new Entity("categoria", nombre);
		categoria.setProperty("nombre", nombre);
		ds.put(categoria);
	}

	public void insert_noticia(String id_noticia, String url_rss, String  fecha_noticia){ //el id viene de la noticia
		DatastoreService data_store_noticia= DatastoreServiceFactory.getDatastoreService();	
		Entity noticia =  new Entity("noticia",id_noticia);
		noticia.setProperty("url_rss",url_rss);
		noticia.setProperty("fecha_noticia",fecha_noticia);
		data_store_noticia.put(noticia);
		
	}

	public void insert_usuario(String username, String nombre, String correo, String sexo, String fecha_nacimiento, String correo_respaldo, String password, String intereses, String codigo){
		DatastoreService data_store_usuario= DatastoreServiceFactory.getDatastoreService();	
		Entity usuario =  new Entity("usuario",correo);
		usuario.setProperty("username",username);
		usuario.setProperty("nombre",nombre);
		usuario.setProperty("correo",correo);
		usuario.setProperty("sexo",sexo);
		usuario.setProperty("fecha_nacimiento",fecha_nacimiento);
		usuario.setProperty("correo_respaldo",correo_respaldo);
		usuario.setProperty("password",password);
		usuario.setProperty("intereses",intereses);
		usuario.setProperty("validado",0);
		usuario.setProperty("cod_validacion",codigo);
		usuario.setProperty("baneado",0);
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
	public Boolean validar_cuenta(String correo, String codigo){
		//extrayendo datos
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		//creando entidad sin identificador		
		@SuppressWarnings("deprecation")
		Query q = new  Query ("usuario").addFilter("correo", FilterOperator.EQUAL, correo).addFilter("cod_validacion", FilterOperator.EQUAL , codigo);
		PreparedQuery pq = ds.prepare(q);
		for(Entity u1:pq.asIterable()){
			String u_username=u1.getProperty("username").toString();
			String u_nombre=u1.getProperty("nombre").toString();
			String u_correo=u1.getProperty("correo").toString();
			String u_sexo=u1.getProperty("sexo").toString();
			String u_fecha_nacimiento=u1.getProperty("fecha_nacimiento").toString();
			String u_correo_respaldo=u1.getProperty("correo_respaldo").toString();
			String u_password=u1.getProperty("password").toString();
			String u_intereses=u1.getProperty("intereses").toString();
			String u_cod_validacion=u1.getProperty("cod_validacion").toString();
			
			Entity usuario =  new Entity("usuario",correo);	
			usuario.setProperty("username",u_username);
			usuario.setProperty("nombre",u_nombre);
			usuario.setProperty("correo",u_correo);
			usuario.setProperty("sexo",u_sexo);
			usuario.setProperty("fecha_nacimiento",u_fecha_nacimiento);
			usuario.setProperty("correo_respaldo",u_correo_respaldo);
			usuario.setProperty("password",u_password);
			usuario.setProperty("intereses",u_intereses);
			usuario.setProperty("validado",1);
			usuario.setProperty("cod_validacion",u_cod_validacion);
			usuario.setProperty("baneado",0);				
			ds.put(usuario);
			return true;			
		}
		return false;
	}
	
	/*Si no existe = 0, existe correo no validado = 1 o existe correo validado = 2*/
	public Integer existe_correo_validado(String correo){
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		@SuppressWarnings("deprecation")
		Query q = new  Query ("usuario").addFilter("correo", FilterOperator.EQUAL, correo);
		PreparedQuery pq = ds.prepare(q);
		for(Entity u1:pq.asIterable()){
			String validado=u1.getProperty("validado").toString();
			if(validado.equals("1")){
				return 2;
			}else{
				return 1;
			}
		}
		return 0;
	}
	
	public Boolean valida_sesion(String correo, String password){
		//extrayendo datos
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		//creando entidad sin identificador		
		@SuppressWarnings("deprecation")
		Query q = new  Query ("usuario").addFilter("correo", FilterOperator.EQUAL, correo).addFilter("password", FilterOperator.EQUAL , password);
		PreparedQuery pq = ds.prepare(q);
		for(Entity u1:pq.asIterable()){
			return true;			
		}
		return false;
	}
	/*
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
	*/
	
	
	
	
	
}



