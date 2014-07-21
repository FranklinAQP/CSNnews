/*--- variable global */
//estoy colocando datos defrenta,luego lo bajaremos de la BD
var contenedorCategorias=document.getElementById("categoriasdiv");//Aqui estaran las categorias
var new_contenedor_cat="";//Aqui estaran las categorias
var num_categoria=6;
var rss_actual="";
var nombre_diario=new Array(2);//por ahora trabajaremos con dos diarios
var nombre_categorias=new Array(num_categoria);
var rss_categorias=new Array(num_categoria);

/*----------------INICIALIZAMOS EVENTOS----------------------*/
$(document).ready(inicializarEventos);

function inicializarEventos(){
	diarios();
}

//mostramos los diarios
function diarios(){
	var contenedor=document.getElementById("categoria_ul");
	var formato_html="";
	
	//llenamos el array de los diarios, sihay base de datos seria con un for
	nombre_diario[0]="La Republica";
	nombre_diario[1]="Correo";
	
	for(var i=0;i<nombre_diario.length;i++){
		formato_html+="<li><a href='javascript:mostrar_categorias("+i+")'>" + nombre_diario[i] + "</a><div id='"+ nombre_diario[i] +"'></div></li>";
	}
	contenedor.innerHTML=formato_html;
}

//Mostramos todas las categorias
function mostrar_categorias(diario){
	var repositorio_html="";//este es de correo
	var repositorio_rpp_html="";
	
	if(nombre_diario[diario]=="La Republica"){
		document.getElementById("Correo").innerHTML="";
		
		//como aun no tenemos BD, no hay de donde sacar info, si no seria todo un for o un bucle
		  nombre_categorias[0]="Politica";
		  rss_categorias[0]="http://www.larepublica.pe/rss/politica/politica";
		  nombre_categorias[1]="Copas Internacionales";
		  rss_categorias[1]="http://www.larepublica.pe/rss/deportes/copas-internacionales";
		  nombre_categorias[2]="Economia";
		  rss_categorias[2]="http://www.larepublica.pe/rss/economia/economia";
		  nombre_categorias[3]="Espectaculos";
		  rss_categorias[3]="http://www.larepublica.pe/rss/espectaculos/espectaculos";
		  nombre_categorias[4]="Mundo";
		  rss_categorias[4]="http://www.larepublica.pe/rss/mundo/mundo";
		  nombre_categorias[5]="Tecnologia";
		  rss_categorias[5]="http://www.larepublica.pe/rss/tecnologia/tecnologia";
		  nombre_categorias[6]="Autos";
		  rss_categorias[6]="http://www.larepublica.pe/rss/automovilismo/automovilismo";
		  
		  //cambiamos elcontenedor
		  new_contenedor_cat=document.getElementById(nombre_diario[diario]);
		  repositorio_rpp_html+="<ul>";
		  //mostramos en html
		  for(var i=0;i<num_categoria+1;i++){
			  repositorio_rpp_html+="<li><a href='javascript:call_loadfeeds("+i+")' >" + nombre_categorias[i] + "</a> </li>";
		  }
		  repositorio_rpp_html+="</ul>";
		  new_contenedor_cat.innerHTML=repositorio_rpp_html;
	}
	
	 if(nombre_diario[diario]=="Correo"){
		 document.getElementById("La Republica").innerHTML="";
		  //como aun no tenemos BD, no hay de donde sacar info, si no seria todo un for o un bucle
		  nombre_categorias[0]="Politica";
		  rss_categorias[0]="http://diariocorreo.pe/RSS-portlet/feed/correo/politica";
		  nombre_categorias[1]="Deportes";
		  rss_categorias[1]="http://diariocorreo.pe/RSS-portlet/feed/correo/deportes";
		  nombre_categorias[2]="Economia";
		  rss_categorias[2]="http://diariocorreo.pe/RSS-portlet/feed/correo/economia";
		  nombre_categorias[3]="Espectaculos";
		  rss_categorias[3]="http://diariocorreo.pe/RSS-portlet/feed/correo/espectaculos";
		  nombre_categorias[4]="Mundo";
		  rss_categorias[4]="http://diariocorreo.pe/RSS-portlet/feed/correo/mundo";
		  nombre_categorias[5]="Arequipa";
		  rss_categorias[5]="http://diariocorreo.pe/RSS-portlet/feed/correo/arequipa";
		  //cambiamos elcontenedor
		  new_contenedor_cat=document.getElementById(nombre_diario[diario]);
		  repositorio_html+="<ul>";
		  //mostramos en html
		  for(var i=0;i<num_categoria;i++){
			  repositorio_html+="<li><a href='javascript:call_loadfeeds("+i+")' >" + nombre_categorias[i] + "</a> </li>";
		  }
		  repositorio_html+="</ul>";
		  new_contenedor_cat.innerHTML=repositorio_html;
		  
	  }
	 
	

}

/*--------------------------------------------------------------------------------- */

//Llamamos a la funcion rssfeedsetup de archivo LoadFeeds.js
function call_loadfeeds(i){
	feedurl=rss_categorias[i];//feedurl es del archivo LoadFeeds.js
	Loadfeed_nombre_categoria=nombre_categorias[i];//Loadfeed_nombre_categoria es del archivo LoadFeeds.js
	rssfeedsetup();
	//limpiamos los cometarios<- aun no encuentro otra mejor forma
	var cometario_="";
	var containercomentario=document.getElementById("comentariodiv");
	containercomentario.innerHTML=cometario_;
}

