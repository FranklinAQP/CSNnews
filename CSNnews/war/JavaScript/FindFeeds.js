/*------------------VARIABLES GLOBALES-----------------------*/
var url_diario="http://elcomercio.pe/";
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

function searchload(search){
	var palabra_buscar=search;
	if(palabra_buscar==""){
		alert("¿Qué es lo que desea buscar?");
		return;}
	var query="site:"+url_diario+" "+palabra_buscar;
	google.feeds.findFeeds(query, mostrar_resultado);
}

/*-----------Ejecutamos los metodos de Google findFeed API-----------*/
function buscar_feed(){
	var palabra_buscar=$("#searchtext").val();
	if(palabra_buscar==""){
		alert("¿Qué es lo que desea buscar?");
		return;}
	var query="site:"+url_diario+" "+palabra_buscar;
	google.feeds.findFeeds(query, mostrar_resultado);
}

//Mostramos el resutado en HTML
function mostrar_resultado(resultado){
	if(!resultado.error){
		var tam_resultado=resultado.entries.length;
		salida_html="<b>Resultado de la Busqueda:</b><br /><ul>";
		//Guardamos los resultados en algunas variables globales
		//Llamamos algunas variables globales
		for(var i=0;i<tam_resultado && i<limite;i++){
			var entrada=resultado.entries[i];
			find_linkrss[i]=entrada.url;
			find_link[i]=entrada.link;
			find_titulo[i]=entrada.title;
			salida_html+="<section class='article_content'><article>";
			//salida_html+="<li><a href='javascript:mostrar_link(" + i + ")'>" + find_titulo[i] + "</a><br/></li>";
			salida_html+="<h4><a href='" + find_link[i] + "' target='_blank'>" + find_titulo[i] + "</a><br/></h4>";
			salida_html+="<p>" + entrada.contentSnippet+"</p></article></section>";
		}
		salida_html+="</ul>";
		contenido_html.innerHTML=salida_html;
	}
	else{
		alert("No hay resultados!");
	}
}
/*-------------------------------------PROBANDO aun sin uso-----------------------------------------------------*/
//Modificamos el html al darle click en los links y veremos cada noticia
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
		alert("Error al captuar feeds!");
}
