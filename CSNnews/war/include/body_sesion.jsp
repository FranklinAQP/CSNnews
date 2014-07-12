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
								<h1>Bienvenido</h1>
								<h2>Eres Bienvenido</h2>
							</hgroup>
						</header>
						<p><a href="csnnews">CSNnews</a></p>
						<figure>					
							<img src="" alt="" title=""/>
						</figure>
						<footer class="footer_article">
							<p>pie de pagina del articulo</p>
						</footer>
					</article>
					</section>
				
				</section>
				<section id="coment">
					Aqui van los comentarios
				</section>
			</section>	
			<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<%@ include file="/include/rightcontent.jsp" %>
			<!-- fin de barra derecha -->
			
		</section>