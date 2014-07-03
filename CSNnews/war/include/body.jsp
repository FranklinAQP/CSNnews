<section class="contenedor">
		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
			<!-- fin -->
		
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					<div id="feeddiv">Loading...</div>						
						<!-- Llamamos al archivo LoadFeed.js para cargar las noticias -->
						<script type="text/javascript">
							<%@ include file="/JavaScript/LoadFeed.js" %>
						</script>
						<!-- Llamamos al archivo FindFeed.js para buscar las noticias -->
						<script type="text/javascript">
							<%@ include file="/JavaScript/FindFeeds.js" %>
						</script>
						
					<section class="article_content">	
					<article>
						<header class="header_article">
							<hgroup>
								<h1></h1>
								<h2></h2>
							</hgroup>
						</header>
						<p><a href="#" class="article_link"></a></p>
						
						<footer class="footer_article">
							<p></p>
						</footer>
					</article>
					</section>
				
				</section>
				<section id="coment">
					<!-- Aqui van los comentarios  -->
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