	
<!DOCTYPE html>
<html lang="es">
	<head>
		<meta charset="utf-8">		
		<title>CSN | Computer Science News</title>
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
    	<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
	    <!--[if lt IE 9]>
	      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	    <![endif]-->
	    <!--  librerias basicas para mejorar vista falta editarlas inicio-->
	    <!-- <link href="/styles/bootstrap.css" rel="stylesheet" type="text/css" media="all">  -->
    	 <!-- <link href="/styles/bootstrap-responsive.css" rel="stylesheet" media="all">  -->
    	<!-- <script src="/JavaScript/jquery.min.js" type="text/javascript"></script> -->
		<!-- <script src="/JavaScript/bootstrap.js"></script>	-->
		<!--  librerias basicas para mejorar vista falta editarlas fin-->
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
				<!--  <button type="button" class="btn btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button> -->
				<nav id="mainnav">
	        	<ul>
	            	<li><a href="index.jsp" title="Inicio">Inicio</a></li>
	            	<% if(!(session.getAttribute("email")==null)){ 
						if(!((String)session.getAttribute("email")).equals("admin@admin")){	            	
	            	%>        	
	            	<li><a href="/perfil.jsp" title="Mi Perfil"><% out.println(session.getAttribute("email")); %></a></li>
	            		<% }
						if((Integer)session.getAttribute("nivel")==2){ %>  
	            			<li><a href="/Administrar_RSS.jsp" title="Administrar RSS">adminRSS</a></li>
	            			<li><a href="/Administrar_Usuarios.jsp" title="Administrar Usuarios">adminUsers</a></li>
	            		<% } %>
	            	<li><a href="/logout.jsp" title="Salir">Salir</a></li>
	            	<% }else{ %> 
	            	<li><a href="/login.jsp" title="Ingresar">Ingresar</a></li>
	            	<li><a href="/registro.jsp" title="Registrarse">Registrarse</a></li>
	            	<% } %>
	            	<li><a href="manual_usuario.jsp" title="Manual de Usuario">?</a></li>
				</ul>
	        	</nav>
			</div>
			<div class="contenedor_buscador">
				<!-- aqui insertar formulario y codigo del buscador -->
				<form id="searchform" action="articulo.jsp" method="get">
					
					<input id="searchtext" class="search" type="search" name="search" required>
					<button id="searchbtn" class="boton" type="submit">buscar</button>
					<!--<input id="searchbtn" class="boton submit" value="search" name="buscar"> type="button"   -->
				</form>
			</div>			
		</div>   