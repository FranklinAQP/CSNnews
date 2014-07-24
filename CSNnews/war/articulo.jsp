		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.entidades.Usuario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>	
				
		<%@ include file="/include/headermainnav.jsp" %>
        <!-- fin del header -->
		<!-- inicio del cuerpo -->
		
		
		  
		<section class="contenedor">
		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
			<!-- fin -->
		
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					<div id="feeddiv">Loading...</div>						
				</section>				
			</section>	
			<!-- fin de contenido central -->
			
			<!-- barra derecha -->
			<div class="rightcontent">
				<aside >
					<figure>
						<img class="img_banner" src="imagenes/logo_CSNnews.png" alt="Logo de CSNnews" />
					</figure>
				</aside>
				<aside >
					<h1>Destacados</h1>
					<div id="destacados">Loading...</div>						
						<!-- Llamamos al archivo Loaddestacadas.js para cargar las noticias -->
						
				</aside>
			
			</div>
			<!-- fin de barra derecha -->
			
		</section>
				
		<!-- inicio del footer -->
				<div id="contenedore">
			<footer id="mainfooter">
				<p class="copyright">Copyright &copy, 2014 <a href="#">CSN news</a> Todos los derechos reservados. <a href="contacto.jsp">Contáctenos</a></p>										
			</footer>
		</div>
		<script type="text/javascript">
		$(function() {
		 
		    var menu_ul = $('.leftmenu > li > ul'),
		        menu_a  = $('.leftmenu > li > a');
		     
		    menu_ul.hide();
		 
		    menu_a.click(function(e) {
		        e.preventDefault();
		        if(!$(this).hasClass('active')) {
		            menu_a.removeClass('active');
		            menu_ul.filter(':visible').slideUp('normal');
		            $(this).addClass('active').next().stop(true,true).slideDown('normal');
		        } else {
		            $(this).removeClass('active');
		            $(this).next().stop(true,true).slideUp('normal');
		        }
		    });
		 
		});
		</script>
</div>	
					<!-- Llamamos al archivo LoadFeed.js para cargar las noticias -->
					
						<%@ include file="/JavaScript/LoadFeed.jsp" %>
			
					<!-- Llamamos al archivo FindFeed.js para buscar las noticias -->
					<script type="text/javascript">
						<%@ include file="/JavaScript/FindFeeds.js" %>
					</script>
					<!-- Llamamos al archivo SeleccionCategoria.js para mostrar  categorias -->
					<script type="text/javascript">
						<%@ include file="/JavaScript/SeleccionCategoria.js" %>
					</script>	
</body>
</html>
		