package com.controlador;
import java.io.IOException;

import javax.servlet.http.*;

import com.modelo.DataStoreConection;

@SuppressWarnings("serial")
public class CSNnewsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String username = req.getParameter("user");
		String correo = req.getParameter("correo");
		String codigo_validacion = req.getParameter("cod");		
		String validate = req.getParameter("validate");	
		//resp.sendRedirect("notificaciones.jsp?user="+username+";correo="+correo);
		DataStoreConection ds = new DataStoreConection();
		if(ds.validar_cuenta(correo, codigo_validacion)){
			resp.sendRedirect("notificaciones.jsp?m=cuenta_validada ");
		}else{
			resp.sendRedirect("notificaciones.jsp?m=datos_erroneos;correo="+correo+";codigo="+codigo_validacion);
		}		
	}
}
