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
						<!-- Aqui saldran las noticias -->
					<section class="other_content">
					<div id="lcontain">
						
			  			<h1>Notificación</h1>					
						<%
						if(request.getParameter("m") != null) {  
							String m = request.getParameter("m"); %>
							<% if(m.equals("registrado")){ %>
							<p>Su cuenta ha sido registrada exitosamente, ahora puede <b>ingresar a su correo y validarla</b> para que pueda comentar y elegir sus fuentes de noticias en <b>CSNnews</b></p>
							<% }else if(m.equals("no_validado")){%>
							<p>El correo ingresado ya fue utilizado, pero aún no ha sido validado, revise los mensajes en su correo y utilice el enlace que se le envió para validar su cuenta o <b><a href="registro.jsp">registrese</a></b> usando una nueva dirección de correo electrónico.</p>
							<% }else if(m.equals("correo_utilizado")){%>
							<p>El correo ingresado ya fue utilizado, revise los mensajes en su correo e <b><a href="login.jsp">inicie sesión</a></b> con los datos que le enviamos anteriormente. </p>
							<% }else if(m.equals("password_incorrecto")){%>
							<p>El password ingresado no coincide con su password de verificación, intente nuevamente <b><a href="registro.jsp">registrarse</a></b> </p>
							<% }else if(m.equals("sesion_invalida")){%>
							<p>El correo y/o password ingresado(s) son incorrectos o no han sido validados, puede intentar nuevamente <b><a href="login.jsp">iniciar sesión</a></b> </p>
							<% }else if(m.equals("cuenta_validada")){%>
							<p>Gracias por verificar su correo electrónico, Ahora puede <b><a href="login.jsp">iniciar sesión</a></b> </p>
							<% }else{%>
							<p>Error no descifrado</p>
							<% } %>
						<% } %>
			  		</div>	
					</section>
				
				</section>
				
			</section>	
			<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<%@ include file="/include/rightcontent.jsp" %>
			<!-- fin de barra derecha -->
			
		</section>

		<!-- inicio del footer -->
		<%@ include file="/include/footer.jsp" %>