		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.entidades.Usuario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>
			<div class="leftcontent">
				<aside>
				<header>
					<h5>DIARIOS</h5>
				</header>
				<nav id="leftnav">
					<ul class="leftmenu">
					<%
						boolean menupersonal= false;
						if(!(session.getAttribute("nivel")==null)){
							 if((Integer)(session.getAttribute("nivel"))==1){
								menupersonal= true;
							}
						}
						if(menupersonal){//(Integer)(session.getAttribute("nivel"))==2 ||
							JDO<Usuario> jdou=JDO.getInstance(Usuario.class);
							Usuario user = jdou.findByIdString((String)session.getAttribute("email"));
							List<String> lista_categoriass=user.getCategorias();
					
							JDO<Rss> jdorr=JDO.getInstance(Rss.class);
							List<Rss> lista_rsss=jdorr.findAll();
							
							for(int i=0;i<lista_categoriass.size();i++){
								String categoria=(String)lista_categoriass.get(i);
								out.print("<li class='item1'><a href='#'>"+categoria+"</a><ul>");
								for(int j=0;j<lista_rsss.size();j++){
									Rss rss=(Rss)lista_rsss.get(j);
									if(rss.getCategoria().equals(categoria)){
										out.print("<li class='subitem1'><a href='articulo.jsp?url="+rss.getURL()+"'>"+rss.getDiario()+"</a></li>");
									}
								}
								out.print("</ul></li>");								
							}
						
						}else{
							JDO<Categoria> jdocc=JDO.getInstance(Categoria.class);
							List<Categoria> lista_categoriass=jdocc.findAll();
					
							JDO<Rss> jdorr=JDO.getInstance(Rss.class);
							List<Rss> lista_rsss=jdorr.findAll();
					%>	
					
						<%for(int i=0;i<lista_categoriass.size();i++){
								Categoria categoria=(Categoria)lista_categoriass.get(i);
								out.print("<li class='item1'><a href='#'>"+categoria.getNombre()+"</a><ul>");
								for(int j=0;j<lista_rsss.size();j++){
									Rss rss=(Rss)lista_rsss.get(j);
									if(rss.getCategoria().equals(categoria.getNombre())){
										out.print("<li class='subitem1'><a href='articulo.jsp?url="+rss.getURL()+"'>"+rss.getDiario()+"</a></li>");
									}
								}
								out.print("</ul></li>");
								/*if((session.getAttribute("email")==null) && i==2 ){
									break;
								}
								//Este fragmento de codigo restringia el acceso de usuario no registrados a solo 3 categorias
								*/
							}
					}/*else{
						 JDO<Usuario> jdou=JDO.getInstance(Usuario.class);
						Usuario user = jdou.findByIdString((String)session.getAttribute("email"));
						List<String> lista_categoriass=user.getCategorias();
				
						JDO<Rss> jdorr=JDO.getInstance(Rss.class);
						List<Rss> lista_rsss=jdorr.findAll();
					}*/%>					
						
					</ul>
				</nav>
				</aside>				
			</div>