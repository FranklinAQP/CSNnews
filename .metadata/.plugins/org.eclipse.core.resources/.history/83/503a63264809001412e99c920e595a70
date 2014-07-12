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
						           <input type="text" value="MiAlias" readonly>
						       </li>
	
						       <li>
						           <label for="name">Nombre:</label>
						           <input type="text" value="Johan Ricardo" required="">
						       </li>
						       <li>
						           <label for="email">Email:</label>
						           <input type="email" name="email" value="jricardo@ejemplo.com" readonly>
						       </li>
						       <li>
						           <label for="website">Intereses:</label>
									<select>
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