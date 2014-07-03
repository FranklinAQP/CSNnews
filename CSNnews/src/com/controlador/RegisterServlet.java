package com.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.servlet.http.*;

import com.google.appengine.api.datastore.*;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.modelo.Usuario;
@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
		//creando entidad sin identificador
		Entity e = new Entity("Persona");
		e.setProperty("Username", "Franklin");
		e.setProperty("Email", "frank_lacg@hotmail.com");
		e.setProperty("Edad", 31);
		
		ds.put(e);
		
		//creando entidad con identificador
		Entity user = new Entity("User",414);
		user.setProperty("id_user", 414);
		user.setProperty("Username", "Pedro2");
		user.setProperty("Password", "password2");
		user.setProperty("Email", "pedro2@gmail.com");
		user.setProperty("Edad", "22");
		//Creando o editando una entidad
		ds.put(user);
		
		//creando claves
		Key key = KeyFactory.createKey("User", 415);
		System.out.println("La clave es: "+key);
		
		//extrayendo datos
		@SuppressWarnings("deprecation")
		Query q = new  Query ("User").addFilter("Edad", FilterOperator.GREATER_THAN, 23);
		PreparedQuery pq = ds.prepare(q);
		for(Entity u1:pq.asIterable()){
			String id_user=u1.getProperty("id_user").toString();
			String usename=u1.getProperty("Username").toString();
			String password=u1.getProperty("Password").toString();
			System.out.println("El usuario id: "+id_user+" de Nombre: "+usename+ "Password: "+password);
		}
		
		
		
		/*
		//Obteneindo una entidad
		try {
			Entity e5 = ds.get(key);
			System.out.println("La entidad e5 es: "+e5);
		} catch (EntityNotFoundException e5) {			
			e5.printStackTrace();
		}*/
		/*
		//eliminando una entidad
		ds.delete(key);
		*/
		//creando una entidad de grupo
		//Entity emp = new Entity("Empleado",user.getKey());
		
		//creando clave ancestro
		Key key1 = new KeyFactory.Builder("User", "GratGrandPa").addChild("User", "GrandPa").addChild("User", "Pa").getKey();
		System.out.println("La clave es: "+key1);
		
		//grupo de entidades
		Entity e1 = new Entity("Emp1");
		Entity e2 = new Entity("Emp2");
		Entity e3 = new Entity("Emp3");
		List<Entity> e4 = Arrays.asList(e1, e2, e3);
		ds.put(e4);
		
		/*Actualizar cada n segundos
		 * Timer timer = new Timer (tiempoEnMilisegundos, new ActionListener () 
{ 
    public void actionPerformed(ActionEvent e) 
    { 
        // Aquí el código que queramos ejecutar.
     } 
}); 
...

timer.start();
		 * */
		
		resp.setContentType("text/plain");
		resp.getWriter().println("Hola, Bienvenido al Datastore");
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		String nombre = req.getParameter("nombre");
		String nombreU = req.getParameter("username");
		String correo = req.getParameter("correo");
		String correo2 = req.getParameter("correo");
		//String interes = req.getParameter("interes");
		String passa = req.getParameter("pass_a");
		String passb = req.getParameter("pass_b");
		if(!passa.equals(passb)){					
			resp.sendRedirect("registro.jsp"); 	       
		}else{
			resp.setContentType("text/html");			
			Usuario nuevo = new Usuario(nombreU, nombre, correo, correo2, passa);			
			PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
			try{
				pm.makePersistent(nuevo);
				req.getRequestDispatcher("index.jsp").forward(req,resp);

			}catch(Exception e){
				System.out.println(e);
				resp.getWriter().println("Ocurrio un error, <a href='registro.jsp'>vuelva a intentarlo</a>");
			}finally{
				pm.close();
			}
			/*PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");
			out.println("<html>");
			out.println("<head><title>Respuesta de Formulario </title></head>");
			out.println("<body>");		
			out.println("<h1>Datos Ingresados</h1>");
			if(passa.equals(passb)){
				out.println("pasword coincidentes </br>");
			}
			out.println("nombre: "+ nombre + "</br>");
			out.println("username: "+ username + "</br>");
			out.println("correo: "+ correo + "</br>");
			out.println("pass: "+ passa + "</br>");
			out.println("pass: "+ passb + "</br>");
			out.println("interes: "+ interes+ "</br>");
			out.println("</body></html>");
			*/
			/*resp.setContentType("text/plain");
			resp.getWriter().println("Hello, world");*/
			}
		}
}
