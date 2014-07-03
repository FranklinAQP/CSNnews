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
						           <label for="correo"><img src="/imagenes/_user.png" class="icono_img">Correo:</label>
						           <input name="correo" type="email" placeholder="correo" required > 						       </li>
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