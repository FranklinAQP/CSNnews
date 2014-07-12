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
			  			<h1>Mi Perfil</h1>					
						<form class="contact_form" action="#" method="post">
						    <ul>
						     	<li>
						           <label for="username"><img src="/imagenes/_user.png" class="icono_img">Username:</label>
						           <input name="username" type="text" value="MiAlias" readonly>
						       </li>
	
						       <li>
						           <label for="name">Nombre:</label>
						           <input name="nombre" type="text" value="Johan Ricardo" required="">
						       </li>
						       <li>
						           <label for="email">Email:</label>
						           <input name="correo" type="email" name="email" value="jricardo@ejemplo.com" readonly>
						       </li>
						       <li>
						       		<label for="sexo">Sexo:</label>
						       		<select name="sexo" required>
						       			<option value="masculino">Masculino</option>
						       			<option value="femenino">Femenino</option>
						       		</select>
						       </li>	
						       <li>
						       		<label for="fechan">Fecha de Nacimiento:</label>
						       		<input type="date" name="fechan" required>
						       </li>
						       <li>
						           <label for="email2">Email de respaldo:</label>
						           <input type="email" name="correo2" placeholder="micorreo@ejemplo.com" required>
						       </li>
						       <li>
						           <label for="website">Intereses:</label>
									<select name="intereses">
									  <option value="deportes">Deportes</option>
									  <option value="internacionales">Internacionales</option>
									  <option value="tecnologia">Tecnología</option>									  
									</select>
						       </li>
	
						        <li>
						          <button class="submit" type="submit">Editar</button>
						        </li>
						    </ul>
						</form></br>
						<h1>Cambiar Password</h1>
						<form class="contact_form" action="#" method="post">
							<ul>
						     	<li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Nuevo Password:</label>
						           <input type="password" placeholder="*********" required="">
						       </li>
	
						       <li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Confirmar Password:</label>
						           <input type="password" placeholder="*********" required="">
						       </li>
						       
						        <li>
						          <button class="submit" type="submit">Enviar</button>
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