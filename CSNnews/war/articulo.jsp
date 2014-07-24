		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.entidades.Usuario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>	
				
		<%@ include file="/include/headermainnav.jsp" %>
        <!-- fin del header -->
		<!-- inicio del cuerpo -->
		
		
		  
		<section class="contenedor">
		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
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
		<%@ include file="/include/footer.jsp" %>