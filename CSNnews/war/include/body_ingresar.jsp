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
			  			<h1>Iniciar Sesi&oacute;n</h1>
						<br>
						<br>
						<form class="contact_form" action="/login" method="post">
						    <ul>
						       <li>
						       		<label for="tipo">Ingresar como:</label>
						       		<select name="tipo" required>
						       			<option value="usuario">Usuario</option>
						       			<option value="administrador">Administrador</option>
						       		</select>
						       </li>
						       <li>
						           <label for="correo"><img src="/imagenes/_user.png" class="icono_img">Correo:</label>
						           <input name="correo" type="email" placeholder="correo" required autofocus> 						       </li>
						       <li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Password:</label>
						           <input type="password" name="pass" placeholder="******" required >
						        </li><li>
						          <button class="submit" type="submit">Entrar</button>
						        </li>
						    </ul>
						</form>
			  		</div>
					</section>
				
				</section>
				
			</section>	
		<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<%@ include file="/include/rightcontent.jsp" %>
			<!-- fin de barra derecha -->
			
		</section>