		<%@page import="java.util.*" %>
		<%@ page import="java.util.List"%>
		<%@ page import="com.entidades.Diario"%>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Usuario"%>
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
						<!-- Llamamos al archivo FindFeed.js para buscar las noticias -->
						<script type="text/javascript">
							<%@ include file="/JavaScript/FindFeeds.js" %>
						</script>
						
					<section class="other_content">
					<div id="lcontain">
			  			<h1>Administrar USUARIOS</h1>
			  			<%
							JDO<Usuario> jdo=JDO.getInstance(Usuario.class);
							List<Usuario> lista_usuarios=jdo.findAll();
						 %>	
			  			<table class='tconsulta'><thead><tr><th>Nro.</th><th>Correo</th><th>Username</th><th>Estado</th><th>Cuenta</th><th>Eliminar</th></tr></thead><tbody>
						<%for(int i=0;i<lista_usuarios.size();i++){
								Usuario user=(Usuario)lista_usuarios.get(i);
								out.print("<tr name='fila'><td>"+(i+1)+"</td>");
								out.print("<td>"+user.getcorreo()+"</td>");
								out.print("<td>"+user.getnombreU()+"</td>");
								if(user.getValidate()){
									out.print("<td><img class='icono_img' alt='Correo Validado' title='Correo Validado' src='imagenes/correo_verificado.png'></td>");										
								}else{
									out.print("<td><img class='icono_img' alt='Correo NO Validado' title='Correo NO Validado' src='imagenes/correo_no_verificado.png'></td>");
								}								
								if(user.getSuspendido()){
									out.print("<td><a href='adminuser?h="+user.getcorreo()+"'><img class='icono_img' alt='Cuenta Suspendida' title='Cuenta Suspendida' src='imagenes/cuenta_suspendida.png'></a></td>");
								}else{
									out.print("<td><a href='adminuser?s="+user.getcorreo()+"'><img class='icono_img' alt='Cuenta Habilitada' title='Cuenta Habilitada' src='imagenes/cuenta_habilitada.png'></a></td>");
								}
								out.print("<td><a href='adminuser?d="+user.getcorreo()+"'>Eliminar</a></td>");
							}%>
						</tbody></table>
						
						<h1>Agregar Nuevo Administrador</h1>					
						<form class="contact_form" action="/adminuser" method="post">
						    <ul>						       
						       <li>
						           <label for="email">Email de usuario:</label>
						           <input type="email" name="correo" placeholder="micorreo@gmail.com" pattern="[A-Za-z0-9\_\-\.]{3,}@gmail\.com" title="Solo Correo de Gmail validado" required>
						       </li>						     
						       <li>
						           <label for="cargo">Cargo:</label>
						           <input type="text" name="cargo" placeholder="cargo" required>
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
		<!-- fin del cuerpo -->
		<!-- inicio del footer -->
		<%@ include file="/include/footer.jsp" %>