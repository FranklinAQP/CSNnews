/*
*  Cómo utilizar el control de feed para recoger, analizar y mostrar los feeds.
*/

/*------------------- Variables Globales --------------*/
var numero_noticias=5;
var palabra_buscar;
var inicio=1;


/*----------------INICIALIZAMOS EVENTOS----------------------*/
$(document).ready(inicializarEventos);

function inicializarEventos(){
	var x="";
	/*palabra_buscar=$("#searchtext").val();
	if(inicio==0){google.setOnLoadCallback(OnLoad);}//no sirve
	else{google.setOnLoadCallback(searchMain);}*/
	
	x=$("#searchbtn");
	x.click(LoadSearch);
}

/*----------------Cargamos Google API Feed-------------------*/
function usar_apiFeed(){
	google.load("feeds", "1");
}

/*-------------------Cargamos los RSS con la API de Google---------------------*/
function OnLoad() {
  // Create a feed control
  var feedControl = new google.feeds.FeedControl();
  
  //Parametros importantes
  var URL_rss="http://diariocorreo.pe/RSS-portlet/feed/correo/politica";
  var nombre_diario="Correo";
  
  //Numero de noticias que se visualizaran
  feedControl.setNumEntries(numero_noticias);

  // Add feeds.
  feedControl.addFeed(URL_rss, nombre_diario);

  // Draw it.
  feedControl.draw(document.getElementById("contenido"));
}

/*------------------ Hacemos una una busqueda de una noticia en un diario------------------*/
function searchMain(){
	var url_diario="elcomercio.pe";
	palabra_buscar=$("#searchtext").val();
	var query="site:"+ url_diario +" " + palabra_buscar;
	google.feeds.findFeeds(query,findNews);alert(inicio);
}
//en result se guardara la busqueda hecha por google.feeds.findFeeds(...)
function findNews(result){
	if(!result.error){
		var contenido=document.getElementById("contenido");
		var html="";
		//imprimimos en html los links de las noticias encontradas
		for(var i=0;i<result.entries.length && i<numero_noticias;i++){
			var entry=result.entries[i];
			html += '<p><a href="' + entry.url + '">' + entry.title + '</a></p>' + '<p>' + entry.link + '</p>';
		}
		contenido.innerHTML = html;
	}
}
//ejecutamos la funcion searchMain
function LoadSearch(){
	palabra_buscar=$("#searchtext").val();
	$("#searchtext").val(palabra_buscar);
	$("#contenido").html("<p>"+palabra_buscar+"</p>");
	
}

//--------------------------- ejecutamos las funciones en google--------------------------
