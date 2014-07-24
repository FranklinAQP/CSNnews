		<%@page import="java.util.*" %>
		<%@ page import="com.entidades.Categoria"%>
		<%@ page import="com.entidades.Usuario"%>
		<%@ page import="com.entidades.Administrador"%>
		<%@ page import="com.entidades.Rss"%>
		<%@ page import="com.modelo.JDO"%>
		<%@ include file="/include/headermainnav.jsp" %>
		
		<!-- fin del nav -->
		<!-- inicio del cuerpo -->
<section class="contenedor">
		
			<!-- barra izquierda inicio -->
			<%@ include file="/include/leftcontent.jsp" %>
			<!-- fin -->
		
			<!-- contenido central -->			
			<section class="maincontent">
				<section class="content">					
					
					<section class="other_content">
					<div id="lcontain">
			  			<h1>Mi Perfil</h1>		
			  			<%
			  			boolean isadmin=false;
			  			if(!(session.getAttribute("email")==null)){
				  			if((Integer)session.getAttribute("nivel")==2){
				  				isadmin=true;			  				
				  			}
			  			}
			  			Usuario user;
			  			Administrador admin;
			  			String nombre, username, correo, correo2, cargo;
			  			List<Categoria> all_categorias = null;
			  			List<String> current_categorias = null;
			  			if(!isadmin){
				  			JDO<Usuario> jdouser=JDO.getInstance(Usuario.class);
							user = jdouser.findByIdString((String)session.getAttribute("email"));
							current_categorias=user.getCategorias();
							nombre=user.getnombre();
							username=user.getnombreU();
							correo=user.getcorreo();
							correo2=user.getcorreo2();							
							JDO<Categoria> jdocat=JDO.getInstance(Categoria.class);
							all_categorias=jdocat.findAll();
			  			}else{
			  				JDO<Administrador> jdoadmin=JDO.getInstance(Administrador.class);
							admin = jdoadmin.findByIdString((String)session.getAttribute("email"));
							nombre=admin.getnombre();
							username=admin.getnombreU();
							correo=admin.getcorreo();
							correo2=admin.getcorreo2();
							cargo=admin.getCargo();
			  			}
			  			%>			
						<form class="contact_form" action="/perfil" method="post">
						    <ul>
						     	<li>
						           <label for="username"><img src="/imagenes/_user.png" class="icono_img">Username:</label>
						           <input name="username" type="text" value="<%= username %>" readonly>
						       </li>
	
						       <li>
						           <label for="name">Nombre:</label>
						           <input name="nombre" type="text" value="<%= nombre %>" required>
						       </li>
						       <li>
						           <label for="email">Email:</label>
						           <input name="correo" type="email" name="email" value="<%= correo %>" readonly>
						       </li>						       
						       <li>
						           <label for="email2">Email de respaldo:</label>
						           <input type="email" name="correo2" value="<%= correo2 %>" required>
						       </li>
						       <%if(!isadmin){ %>
						       <li>
						           <label for="website">Intereses:</label>
						           <%
						           	String checked="";
						            String micategoria="";
						            int j=0;
						           for(int i=0;i<all_categorias.size();i++){
										Categoria categoria=(Categoria)all_categorias.get(i);
										if(j<current_categorias.size())
											micategoria = current_categorias.get(j);{
											if(categoria.getNombre().equals(micategoria)){
												checked="checked";
												j++;
											}
										}
										out.print("<input class='check_box' type='checkbox' name='currency[]' value='"+categoria.getNombre()+"' "+checked+">"+categoria.getNombre()+"<br>");
										checked="";										
									}
									%>						           								
						       </li>
						       <%} %>
	
						        <li>
						          <button class="submit" type="submit">Editar</button>
						        </li>
						    </ul>
						</form></br>
						<h1>Cambiar Password</h1>
						<form class="contact_form" action="/perfil" method="post">
							<ul>
						     	<li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Nuevo Password:</label>
						           <input id="pass_a" name="pass_a" type="password" placeholder="*********" pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$" title="Al menos seis caracteres (letras y numeros) " required="">
						       </li>
	
						       <li>
						           <label for="password"><img src="/imagenes/_pass.png" class="icono_img">Confirmar Password:</label>
						           <input name="pass_b" type="password" placeholder="*********" pattern="(?!^[0-9]*$)(?!^[a-zA-Z]*$)^([a-zA-Z0-9]{6,})$" title="Al menos seis caracteres (letras y numeros) " onblur="validar(this);" required="">
						       </li>
						       
						        <li>
						          <button class="submit" type="submit">Enviar</button>
						        </li>
						     </ul>
						</form>
						<script type="text/javascript">
							<%@ include file="/JavaScript/valida_password.js" %>
						</script>
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