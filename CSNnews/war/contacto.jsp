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
					
					<section class="other_content">
					<div id="lcontain">
					<%if(request.getParameter("m") != null){ %>
						<p>Mensaje enviado</p>
					<% } %>
			  			<h1>Formulario de Contacto</h1>		
			  			<form class="contact_form" action="/contacto" method="post">
						    <ul>
						       <li>
						           <label for="name">Nombre:</label>
						           <input name="nombre" type="text" required>
						       </li>
						       <li>
						           <label for="correo">Email:</label>
						           <input name="correo" type="email" required>
						       </li>						       
						       <li>
						           <label for="tema">Tema:</label>
						           <select name="tema">
						           	<option value="consulta">consulta</option>
						           	<option value="consulta">DMCA</option>
						           	<option value="consulta">sugerencia</option>
						           </select>
						       </li>
						       <li>
						           <label for="mensaje">Mensaje:</label>
						           <textarea name="mensaje"></textarea>				           								
						       </li>
						        <li>
						          <button class="submit" type="submit">Enviar</button>
						        </li>
						    </ul>
						</form></br>						
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