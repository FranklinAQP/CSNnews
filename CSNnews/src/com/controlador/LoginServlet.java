package com.controlador;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.*;

import com.modelo.Usuario;
@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {		
		PersistenceManager pm = PersistenceMF.get().getPersistenceManager();
		String nombreU = req.getParameter("username");
		String password = req.getParameter("pass");
		
		
		//PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		//resp.encodeURL(arg0)
		
		Query q = pm.newQuery(Usuario.class);
		q.setFilter("m_nombreU == '"+nombreU+"' && m_password == '"+password+"'");
		
		try{
			//List<Persona> personas = (List<Persona>) q.execute("Azul");
			@SuppressWarnings("unchecked")
			List<Usuario> usuarios = (List<Usuario>) q.execute();
			if(usuarios.isEmpty())req.getRequestDispatcher("login.jsp").forward(req,resp);
			else req.getRequestDispatcher("index.jsp").forward(req,resp);
			
		}catch(Exception e){
			
		}finally{
			 q.closeAll();
		}
		
	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String correo = req.getParameter("correo");
		String pass = req.getParameter("pass");
		
		HttpSession sesion = req.getSession(true);
		sesion.setAttribute("email",correo);
		resp.sendRedirect("index.jsp");
	}
}


