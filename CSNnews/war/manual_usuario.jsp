		<%@page import="java.util.*" %>
		<%@ include file="/include/headermainnav.jsp" %>
		
		<!-- fin del nav -->
		<!-- inicio del cuerpo -->
<section class="contenedor">
		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
			<!-- fin -->
		
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					<div id="feeddiv"></div>						
						<!-- Aqui saldran las noticias -->
					<section class="other_content">
					<div id="lcontain">					
			  			<br></b><h1>Manual de Usuario</h1>	
			  			<ul class="contenedor_manual">
			  			<h3>REGÍSTRATE</h3>
			  			<img class="img_manual" src="/imagenes/registrate1.jpg">
			  			<img class="img_manual" src="/imagenes/registrate2.jpg">
			  			<br><br><br><h3>VALIDA TU CORREO</h3>
			  			<img class="img_manual" src="/imagenes/validar_correo.jpg">
			  			<img class="img_manual" src="/imagenes/validar_correo2.jpg">
			  			<br><br><br><h3>INICIA SESIÓN</h3>
			  			<img class="img_manual" src="/imagenes/iniciar_sesion.jpg">
			  			<br><br><br><h3>EDITA TU PERFIL</h3>
			  			<img class="img_manual" src="/imagenes/editar_perfil.jpg">			  			
			  			<img class="img_manual" src="/imagenes/editar_perfil2.jpg">
			  			<img class="img_manual" src="/imagenes/cambiar_password.jpg">			  			
			  			<br><br><br><h3>CONSULTA NOTICIAS</h3>
			  			<img src="/imagenes/navegar.jpg">
			  			<img class="img_manual" src="/imagenes/navegar2.jpg">
			  			<img class="img_manual" src="/imagenes/navegar3.jpg">
			  			<br><br><br><h3>REALIZA COMENTARIOS</h3>			  			
			  			<img class="img_manual" src="/imagenes/comentarios2.jpg">
			  			<img class="img_manual" src="/imagenes/comentarios3.jpg">
			  			<br><br><br><h3>BUSCA NOTICIAS</h3>
			  			<img  src="/imagenes/buscar.gif">
			  			</ul>					
			  		</div>	
					</section>
				
				</section>
				
			</section>	
			<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<%@ include file="/include/rightcontent.jsp" %>
			<!-- fin de barra derecha -->
			
		</section>
		<!-- fin del cuerpo -->
		<!-- inicio del footer -->
		<%@ include file="/include/footer.jsp" %>