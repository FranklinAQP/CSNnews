package com.controlador;

import java.io.IOException;

//import java.io.PrintWriter;
import javax.servlet.http.*;

import com.modelo.DataStoreConection;


@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String pass = req.getParameter("pass");
		String correo = req.getParameter("correo");
		DataStoreConection ds = new DataStoreConection(); 
		if(ds.valida_sesion(correo, pass)){
			HttpSession sesion = req.getSession(true);
			sesion.setAttribute("email",correo);
			resp.sendRedirect("index.jsp");
		}else{
			resp.sendRedirect("notificaciones.jsp?m=sesion_invalida ");
		}
	}
}


