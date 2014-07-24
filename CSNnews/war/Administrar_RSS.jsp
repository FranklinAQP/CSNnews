		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>
		
		<%@ include file="/include/headermainnav.jsp" %>
		
		<!-- fin del nav -->
        <!-- fin del header -->
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
			  			<h1>Administrar DIARIOS</h1> 
			  			<%
			  		if(session.getAttribute("email")==null){
			  			
			  		}else if((Integer)session.getAttribute("nivel")==1){
			  			
			  		}else{
			  			
							JDO<Diario> jdo=JDO.getInstance(Diario.class);
							List<Diario> lista_diarios=jdo.findAll();
						 %>	
						
			  			<table class='tconsulta'><thead><tr><th>Nro.</th><th>Nombre del Diario</th><th>URL</th><th>Eliminar</th></tr></thead><tbody>
						<%for(int i=0;i<lista_diarios.size();i++){
								Diario diario=(Diario)lista_diarios.get(i);
								out.print("<tr name='fila'><td>"+(i+1)+"</td>");
								out.print("<td>"+diario.getNombre()+"</td>");
								out.print("<td>"+diario.getURL()+"</td>");
								out.print("<td><a href='adminrss?d="+diario.getNombre()+"'><img class='icono_img' src='/imagenes/i_delete.png' alt='Eliminar' title='Eliminar'></a></td></tr>");
							}%>
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
			  			<%
							JDO<Categoria> jdoc=JDO.getInstance(Categoria.class);
							List<Categoria> lista_categorias=jdoc.findAll();
						 %>	
			  			<table class='tconsulta'><thead><tr><th>Nro.</th><th>Categoria</th><th>Eliminar</th></tr></thead><tbody>
			  			<%for(int i=0;i<lista_categorias.size();i++){
								Categoria categoria=(Categoria)lista_categorias.get(i);
								out.print("<tr name='fila'><td>"+(i+1)+"</td>");
								out.print("<td>"+categoria.getNombre()+"</td>");
								out.print("<td><a href='adminrss?c="+categoria.getNombre()+"'><img class='icono_img' src='/imagenes/i_delete.png' alt='Eliminar' title='Eliminar'></a></td></tr>");
							}%>
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
			  			<%
							JDO<Rss> jdor=JDO.getInstance(Rss.class);
							List<Rss> lista_rss=jdor.findAll();
						 %>	
						<table class='tconsulta'><thead><tr><th>Nro.</th><th>atom/RSS</th><th>Diario</th><th>Categoria</th><th>Eliminar</th></tr></thead><tbody>
			  			<%for(int i=0;i<lista_rss.size();i++){
								Rss rss=(Rss)lista_rss.get(i);
								out.print("<tr name='fila'><td>"+(i+1)+"</td>");
								out.print("<td><a href='"+rss.getURL()+"' target='_blank'><img class='icono_img' alt='"+rss.getURL()+"' title='"+rss.getURL()+"' src='imagenes/rss.png'></a></td>");
								out.print("<td>"+rss.getDiario()+"</td>");
								out.print("<td>"+rss.getCategoria()+"</td>");
								out.print("<td><a href='adminrss?r="+rss.getURL()+"'><img class='icono_img' src='/imagenes/i_delete.png' alt='Eliminar' title='Eliminar'></a></td></tr>");
							} %>
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
						       		<%for(int i=0;i<lista_diarios.size();i++){
						       			Diario diario=(Diario)lista_diarios.get(i);
						       			out.print("<option value='"+diario.getNombre()+"'>"+diario.getNombre()+"</option>");
									}%>
						       		</select>
						       </li>
						       <li>
						       		<label for="categoria">Categoria:</label>
						       		<select name="categoria">
						       		<%for(int i=0;i<lista_categorias.size();i++){
										Categoria categoria=(Categoria)lista_categorias.get(i);
										out.print("<option value='"+categoria.getNombre()+"'>"+categoria.getNombre()+"</option>");
									}%>
						       </select></li>
						       <li>
						          <button class="submit" type="submit">Agregar</button>
						        </li>
						    </ul>
						</form>
						
				<% }%>		
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