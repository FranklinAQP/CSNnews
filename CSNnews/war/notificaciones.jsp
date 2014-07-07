<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>;
<%@page import="java.util.*" %>
		<%@ include file="/include/headermainnav.jsp" %>
		<!-- fin del nav -->
		<section class="contenedor">		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
			<!-- fin -->
			
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					<div id="feeddiv"></div>						
						<!-- Llamamos al archivo FindFeed.js para buscar las noticias -->
						<script type="text/javascript">
							<%@ include file="/JavaScript/FindFeeds.js" %>
						</script>
						
					<section class="other_content">
					<div id="lcontain">
						
			  			<h1>Notificación</h1>					
						<%
						if(request.getParameter("m") != null) {  
							String m = request.getParameter("m"); %>
							<% if(m.equals("registrado")){ %>
							<p>Su cuenta ha sido registrada exitosamente, ahora puede <b>ingresar a su correo y validarla</b> para que pueda comentar y elegir sus fuentes de noticias en <b>CSNnews</b></p>
							<% }else if(m.equals("sesion_invalida")){%>
							<p>El correo y/o password ingresado(s) son incorrectos o no han sido validados, puede intentar nuevamente <b><a href="login.jsp">iniciar sesión</a></b> </p>
							<% }else if(m.equals("cuenta_validada")){%>
							<p>Gracias por verificar su correo electrónico, Ahora puede <b><a href="login.jsp">iniciar sesión</a></b> </p>
							<% } %>
						<% } %>
			  		</div>	
					</section>
				
				</section>
				
			</section>	
			
			<!-- barra derecha -->
			<div class="rightcontent">
				<aside >
					<h1>Destacados</h1>
					<div id="destacadas">Loading...</div>						
						<!-- Llamamos al archivo Loaddestacadas.js para cargar las noticias -->
						
				</aside>
				<select id="seleccion_cat">
					<option value ="correo">Correo</option>
					<option value ="Otro diario">Otro diario</option>
				</select>
				<div id="categoriasdiv">  Categorias... </div>
			
				<aside >
					<figure>
						<img class="img_banner" src="imagenes/logo_CSNnews.jpg" alt="Logo de CSNnews" />
					</figure>
				</aside>
			</div>
			
		</section>

		<!-- inicio del footer -->
		<%@ include file="/include/footer.jsp" %>