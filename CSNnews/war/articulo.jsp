		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.entidades.Usuario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>			
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">		
		<title>titulo del articulo</title>
		<link rel="stylesheet" href="styles/style.css" type="text/css" />
		<link rel="stylesheet" href="styles/style_comentario.css" type="text/css" />		
		<script src="http://connect.facebook.net/es_LA/all.js#appId=APP_ID&amp;xfbml=1" type="text/javascript"></script>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
		<script src="//www.google.com/jsapi?key=AIzaSyA5m1Nc8ws2BbmPRwKu5gFradvD_hgq6G0" type="text/javascript"></script>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.min.js" type="text/javascript"></script>
		
		<script src="/JavaScript/popup.js" type="text/javascript"></script>
		<script type="text/javascript">
			google.load("feeds", "1"); //Cargamos Google Ajax Feed API (version 1)*MUY NECESARIO
		</script>		
    	
	</head>
	<body>
	<div class="body">	
		<div class="contenedor_superior">
		        <div id="logo">		   	 
 		         	<h1>CSN</h1>
            		<p>Computer Science News</p>
			    </div>
			    <header id="mainheader">        
					<figure>
						<img class="logo" src="imagenes/logo_CSNnews.jpg" alt="Logo de CSNnews" />
					</figure>
				</header>
			<div class="contenedor_menu">
				
				<nav id="mainnav">
	        	<ul>
	            	<li><a href="index.jsp" title="Inicio">Inicio</a></li>
	            	<% if(!(session.getAttribute("email")==null)){ %>        	
	            	<li><a href="/perfil.jsp" title="Mi Perfil"><% out.println(session.getAttribute("email")); %></a></li>
	            		<% if((Integer)session.getAttribute("nivel")==2){ %>  
	            			<li><a href="/Administrar_RSS.jsp" title="Administrar RSS">adminRSS</a></li>
	            			<li><a href="/Administrar_Usuarios.jsp" title="Administrar Usuarios">adminUsers</a></li>
	            		<% } %>
	            	<li><a href="/logout.jsp" title="Salir">Salir</a></li>
	            	<% }else{ %> 
	            	<li><a href="/login.jsp" title="Ingresar">Ingresar</a></li>
	            	<li><a href="/registro.jsp" title="Registrarse">Registrarse</a></li>
	            	<% } %>
				</ul>
	        	</nav>
			</div>
			<div class="contenedor_buscador">
				<!-- aqui insertar formulario y codigo del buscador -->
				<form id="searchform" action="#" method="post">
					
					<input id="searchtext" class="search" type="search" name="search" required>
					<input id="searchbtn" class="boton" value="search" type="button" name="buscar">
					
				</form>
			</div>			
		</div>   
		<!-- fin del nav -->
        <!-- fin del header -->
		<!-- inicio del cuerpo -->
		
		
		  
		<section class="contenedor">
		
			<!-- barra izquierda inicio -->
		
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
								out.print("<li class='item1'><a href='#'>"+categoria+"<span>-</span></a><ul>");
								for(int j=0;j<lista_rsss.size();j++){
									Rss rss=(Rss)lista_rsss.get(j);
									if(rss.getCategoria().equals(categoria)){
										out.print("<li class='subitem1'><a href='articulo.jsp?url="+rss.getURL()+"'>"+rss.getDiario()+"<span>5</span></a></li>");
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
								out.print("<li class='item1'><a href='#'>"+categoria.getNombre()+"<span>"+session.getAttribute("nivel")+"</span></a><ul>");
								for(int j=0;j<lista_rsss.size();j++){
									Rss rss=(Rss)lista_rsss.get(j);
									if(rss.getCategoria().equals(categoria.getNombre())){
										out.print("<li class='subitem1'><a href='articulo.jsp?url="+rss.getURL()+"'>"+rss.getDiario()+"<span>5</span></a></li>");
									}
								}
								out.print("</ul></li>");
								if((session.getAttribute("email")==null) && i==2 ){
									break;
								}
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
			<!-- fin -->
		
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					<div id="feeddiv">Loading...</div>						
				</section>				
			</section>	
			<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<div class="rightcontent">
				<aside >
					<figure>
						<img class="img_banner" src="imagenes/logo_CSNnews.png" alt="Logo de CSNnews" />
					</figure>
				</aside>
				<aside >
					<h1>Destacados</h1>
					<div id="destacados">Loading...</div>						
						<!-- Llamamos al archivo Loaddestacadas.js para cargar las noticias -->
						
				</aside>
			
			</div>
			<!-- fin de barra derecha -->
			
		</section>
				
		<!-- inicio del footer -->
				<div id="contenedore">
			<footer id="mainfooter">
				<p class="copyright">Copyright &copy, 2014 <a href="#">CSN news</a> Todos los derechos reservados. <a href="contacto.jsp">Contáctenos</a></p>										
			</footer>
		</div>

</div>	
					<!-- Llamamos al archivo LoadFeed.js para cargar las noticias -->
					
						<%@ include file="/JavaScript/LoadFeed.jsp" %>
			
					<!-- Llamamos al archivo FindFeed.js para buscar las noticias -->
					<script type="text/javascript">
						<%@ include file="/JavaScript/FindFeeds.js" %>
					</script>
					<!-- Llamamos al archivo SeleccionCategoria.js para mostrar  categorias -->
					<script type="text/javascript">
						<%@ include file="/JavaScript/SeleccionCategoria.js" %>
					</script>	
</body>
</html>
		