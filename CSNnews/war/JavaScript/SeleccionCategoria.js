/*--- variable global */
//estoy colocando datos defrenta,luego lo bajaremos de la BD
var contenedorCategorias=document.getElementById("categoriasdiv");//Aqui estaran las categorias
var num_categoria=6;
var rss_actual="";
var nombre_categorias=new Array(num_categoria);
var rss_categorias=new Array(num_categoria);

/*----------------INICIALIZAMOS EVENTOS----------------------*/
$(document).ready(inicializarEventos);

function inicializarEventos(){
	var x=$("#seleccion_cat");
	x.change(comboInit);
	
	//x.click(mostrar_cat);
}


//Capturamos la informacion del combobox
function comboInit()
{
  var theinput = document.getElementById("seleccion_cat");  
  var idx = theinput.selectedIndex;
  mostrar_cat(theinput.options[idx].value);	
}

//Mostramos las categorias del diario seleccionado
function mostrar_cat(diario)
{
	//var diario="correo";
	var repositorio_html="<ul>";
	
  if(diario=="correo"){
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
	  nombre_categorias[5]="Miscelanea";
	  rss_categorias[5]="http://diariocorreo.pe/RSS-portlet/feed/correo/miscelanea";
	  //mostramos en html
	  for(var i=0;i<num_categoria;i++){
		  repositorio_html+="<li><a href='javascript:call_loadfeeds("+i+")'' >" + nombre_categorias[i] + "</a> </li>";
	  }
  }
  repositorio_html+="</ul>";
  contenedorCategorias.innerHTML=repositorio_html;
}

//Llamamos a la funcion rssfeedsetup de archivo LoadFeeds.js
function call_loadfeeds(i){
	feedurl=rss_categorias[i];
	Loadfeed_nombre_categoria=nombre_categorias[i];
	rssfeedsetup();
}
