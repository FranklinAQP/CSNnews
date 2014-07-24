		<%@page import="java.util.*" %>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.modelo.JDO"%>
		
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
						
			  			<h1>Registrarse</h1>					
						<form class="contact_form" action="/registro" method="post">
						    <ul>
						       <li>
						           <label for="username"><img src="/imagenes/_user.png" class="icono_img">Username:</label>
						           <input name="username" type="text" placeholder="Alias" required="" autofocus>
						       </li>
						       <li>
						           <label for="name">Nombre:</label>
						           <input name="nombre" type="text" placeholder="Nombre" required>
						       </li>
						       <li>
						           <label for="email">Email:</label>
						           <input type="email" name="correo" placeholder="micorreo@gmail.com" pattern="[A-Za-z0-9\_\-\.]{3,}@gmail\.com" title="Solo Correo de Gmail" required>
						       </li>						       
						     	<li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Password:</label>
						           <input id="pass_a" name="pass_a" type="password" placeholder="*********" pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$" title="Al menos seis caracteres (letras y numeros) " required>
						       </li>
	
						       <li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Confirmar Password:</label>
						           <input name="pass_b" type="password" placeholder="*********" pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$" title="Al menos seis caracteres (letras y numeros) " onblur="validar(this);" required>
						       </li>								
						       <li>
						           <label for="email2">Email de respaldo:</label>
						           <input type="email" name="correo2" placeholder="micorreo@ejemplo.com" required>
						       </li>
						       <%
									JDO<Categoria> jdoc=JDO.getInstance(Categoria.class);
									List<Categoria> lista_categorias=jdoc.findAll();
								 %>	
						       <li>
						           <label for="website">Intereses:</label>
									<select name="intereses">									
						       		<%for(int i=0;i<lista_categorias.size();i++){
										Categoria categoria=(Categoria)lista_categorias.get(i);
										out.print("<option value='"+categoria.getNombre()+"'>"+categoria.getNombre()+"</option>");
									}%>
						       		</select>
						       </li>
	
						        <li>
						          <button class="submit" type="submit">Enviar</button>
						        </li>
						    </ul>
						</form>
						<script type="text/javascript">
							<%@ include file="/JavaScript/valida_password.js" %>
						</script>
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