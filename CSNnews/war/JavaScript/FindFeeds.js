/*------------------VARIABLES GLOBALES-----------------------*/
var url_diario="elcomercio.pe";
var contenido_html=document.getElementById("feeddiv");//Aqui estaran las noticias
var salida_html="";//se escribira en formato html
var limite=5;//Numero de noticias a mostrar
//DATOS PARA VISUALIZAR LA NOTICIA
var find_linkrss=new Array(limite);
var find_link=new Array(limite);
var find_titulo=new Array(limite);
var find_content=new Array(limite);

/*----------------INICIALIZAMOS EVENTOS----------------------*/
$(document).ready(inicializarEventos);

function inicializarEventos(){
	var x=$("#searchbtn");
	x.click(buscar_feed);
}

/*-----------Ejecutamos los metodos de Google findFeed API-----------*/
function buscar_feed(){
	var palabra_buscar=$("#searchtext").val();
	var query="site:"+url_diario+" "+palabra_buscar;
	google.feeds.findFeeds(query, mostrar_resultado);
}

//Mostramos el resutado en HTML
function mostrar_resultado(resultado){
	if(!resultado.error){
		var tam_resultado=resultado.entries.length;
		salida_html="<h2><b>Resultados de la Busqueda:</b></h2><br />";
		//Guardamos los resultados en algunas variables globales
		//Llamamos algunas variables globales
		for(var i=0;i<tam_resultado && i<limite;i++){
			var entrada=resultado.entries[i];
			find_linkrss[i]=entrada.url;
			find_link[i]=entrada.link;
			find_titulo[i]=entrada.title;
			find_content[i]=entrada.content;//++
			date_content[i]= entrada.publishedDate;//++
			imagen_cont(i);//Sacamos la imagen de feed_content y la guardamos en (img_content);
			salida_html+="<section class='article_content'><article>";
			salida_html+=img_content[i];
			salida_html+="<h4><a href='javascript:links("+i+")' >" + find_titulo[i] + "</a></h4>"+"<p class='date'>"+entrada.publishedDate+"</p>";
			salida_html+="<p>"+ entrada.contentSnippet+"</p></article></section>";
			
			//salida_html+="<section class='article_content'><article><h4><a href='" + find_link[i] + "' target='_blank'>" + find_titulo[i] + "</a></h4>";
			//salida_html+="<p>"+ entrada.contentSnippet + "</p></article></section>";
		}
		salida_html+="";
		contenido_html.innerHTML=salida_html;
	}
	else{
		alert("No hay resultados!");
	}
}
/*-------------------------------------PROBANDO-----------------------------------------------------*/
//Modificamos el html al darle click en los links y veremos cada noticia
function links(i){
	rssoutput="";//limpiamos todo contenido
	rssoutput+="<h3><b>"+feed_titulo[i]+"</b></h3></br>";	
	//rssoutput+="<a href='index.jsp'> Home</a> <br />";//para regresar a la pag principal
	rssoutput+="<p class='date'>"+ date_content[i] + "</p>";
	rssoutput+="<div class='display_full'><p>"+feed_content[i]+"</p></div>";
	feedcontainer.innerHTML=rssoutput;
}

//Sacamos la imagen del contenido, La imagen siempre esta al inicio de 
//feed_content(Seria buenoverificarlo con un alert) <- Solo funciona con RPP y el correo
function imagen_cont(i){
	var j=0;
	var contenido=feed_content[i];
	img_content[i]="";
	while(contenido[j]!='>'){
		img_content[i]+=contenido[j];
		j++;
	}
	img_content[i]+=" class='content_img'>";
}

function mostrar_link(i){
	var feedJSON = new google.feeds.Feed(find_linkrss[i]);alert("aui");
	feedJSON.load(mostrar_enHTML);
}
//html
function mostrar_enHTML(result){
	if (!result.error){
		var entradas_feeds=result.feed.entries;
		var tam_result=entradas_feeds.length;
		salida_html="<b>Resultado del link:</b><br /><ul>";
		//Guardamos los resultados en algunas variables globales
		for (var i=0; i<tam_result; i++){
			find_titulo[i]=entradas_feeds[i].title;
			salida_html+="<li><a href='"+entradas_feeds[i].link+"' >" + find_titulo[i] + "</a>"+"<br/></li>";
		}	
		salida_html+="</ul>";
		contenido_html.innerHTML=salida_html;
	}
	else
		alert("Error al capturar feeds!");
}
