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
			  			<h1>Administrar DIARIOS</h1>	
			  			<table class='tconsulta'><thead><tr><th>Nro.</th><th>Nombre del Diario</th><th>URL</th><th>Eliminar</th></tr></thead><tbody>
			  			<% Integer $i = 1; 
			  				List<Diario> diarios = (List<Diario>)session.getAttribute("lista_diarios"); 
			  			%>			  			
						<tr name='fila'><td>$i</td><td>correo</td><td>URL</td><td><a href='#'>Eliminar</a></td></tr>
						</tbody></table>
						
						<form class="contact_form" action="adminrss" method="post">
						    <ul>						       
						       <li>
						           <label for="agregar_Diario">Nombre del Diario:</label>
						           <input name="nombre_diario" type="text" placeholder="" required> 						       
						       </li>
						       <li>
						           <label for="url_diario">URL:</label>
						           <input name="url_diario" type="text" placeholder="" required> 						       
						       </li>
						       <li>
						          <button name="agregar" class="submit" type="submit">Agregar</button>
						        </li>
						    </ul>
						</form>
									  			
			  			<h1>Administrar CATEGORIAS</h1>
			  			<table class='tconsulta'><thead><tr><th>Nro.</th><th>Categoria</th><th>Eliminar</th></tr></thead><tbody>
			  			<% Integer $j = 1; 
			  			
			  		   		Persona q = (Persona)session.getAttribute("unaPersona");
			  			%>			  			
						<tr name='fila'><td>$i</td><td>Deportes</td><td><a href='#'>Eliminar</a></td></tr>
						</tbody></table>
						
						<form class="contact_form" action="adminrss" method="post">
						    <ul>						       
						       <li>
						           <label for="agregar_Categoria">Agregar Categoria:</label>
						           <input name="nueva_categoria" type="text" placeholder="" required> 						       
						       </li>
						       <li>
						          <button class="submit" type="submit">Agregar</button>
						        </li>
						    </ul>
						</form>
						
			  			<h1>Administrar URL DE RSS</h1>
						<table class='tconsulta'><thead><tr><th>Nro.</th><th>Diario</th><th>Categoria</th><th>atom/RSS</th><th>Predefinido<th>Eliminar</th></tr></thead><tbody>
			  			<% Integer $k = 1; 
			  				
			  			%>			  			
						<tr name='fila'><td>$i</td><td>Correo</td><td>Deportes</td><td><a href='#'>URL</a></td><td><a href="#">NO</a></td><td><a href='#'>Eliminar</a></td></tr>
						</tbody></table>
						
						<form class="contact_form" action="adminrss" method="post">
						    <ul>						       
						       <li>
						           <label for="url_rss">URL de feed/atom RSS:</label>
						           <input name="url_rss" type="text" placeholder="" required> 						       
						       </li>						       
						       <li>
						       		<label for="diario">Diario:</label>
						       		<select name="diario">
						       		<option value="correo"> correo</option>
						       </select></li>
						       <li>
						       		<label for="categoria">Categoria:</label>
						       		<select name="categoria">
						       		<option value="Deportes"> Deportes</option>
						       </select></li>
						       <li>
						          <button class="submit" type="submit">Agregar</button>
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