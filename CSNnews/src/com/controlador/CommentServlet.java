package com.controlador;


//import java.io.IOException;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.http.*;

import com.entidades.Comentario;
import com.google.gson.Gson;

@SuppressWarnings("serial")
public class CommentServlet extends HttpServlet{
	private NewsConnection _news;
	
	public CommentServlet(){
		_news = null;
	}
	public void doPost(HttpServletRequest rep, HttpServletResponse resp) throws IOException
	{
		String idNoticia = rep.getParameter("idNoticia");
		String correo = rep.getParameter("correo");
		String categoria = rep.getParameter("categoria");
		String fecha = rep.getParameter("fecha");
		String comentario = rep.getParameter("comentario");
		String usuario = rep.getParameter("usuario");
		
		_news = new NewsConnection(idNoticia, correo, categoria, fecha);
		String json;
		if(comentario!=""){
			Comentario comen = _news.addComment(comentario, usuario);
			_news.closeConnect();
			json = new Gson().toJson(comen);
		}
		else
		{
			Stack<Comentario> comentarios = _news.getNews().getComentarios();
			_news.closeConnect();
			json = new Gson().toJson(comentarios);
			//System.out.println(_news.getNews().getComentarios());
		
		}

		    resp.setContentType("application/json");
		    resp.setCharacterEncoding("UTF-8");
		    
		    resp.getWriter().write(json);
		    
		    
	}

}
