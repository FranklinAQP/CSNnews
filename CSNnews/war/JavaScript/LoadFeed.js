/*-----------------------------------VARIABLES GLOBALES------------------------------------*/
var feedcontainer=document.getElementById("feeddiv");//Aqui estaran las noticias
var feedurl="http://diariocorreo.pe/RSS-portlet/feed/correo/politica";
var url_banner="http://diariocorreo.pe/RSS-portlet/feed/correo/miscelanea";
var feedlimit=5;//limite de noticias a ver
var feed_banner=5;//limite de noticias a ver
var limit_historial=50;
var rssoutput="";//Salida en Formato HTML
var Loadfeed_nombre_categoria="Noticias";
//------Datos del resultado:function DisplayFeed
//AQUI ALMACENAMOS LA INFORMACION DEL LOAD
//sumamos por que apartir de la mitad se guardaran las noticias para el banner de la derecha
var feed_link=new Array(limit_historial+feed_banner);
var feed_titulo=new Array(limit_historial+feed_banner);
var feed_content=new Array(limit_historial+feed_banner);
var feed_contentsnipett=new Array(limit_historial+feed_banner);
var img_content=new Array(limit_historial);//sacamos el link con html de la imagen de feed_content
var date_content=new Array(limit_historial+feed_banner);
var tipo_comentario=new Array(2);
/*------------LISTA DE COMENTARIOS */
var list_comentarios=new Array();
var list_comentario_html="";
var tam_list_comentarios=0;
/*-------USUARIO-------------*/
var name_usuario="";

/*-----------Ejecutamos los metodos de Google Feed API-----------*/
function rssfeedsetup(){
	var feedpointer=new google.feeds.Feed(feedurl); //Google Feed API method: Controlador de Feeds
	feedpointer.includeHistoricalEntries(); // tell the API we want to have old entries too
	feedpointer.setNumEntries(limit_historial); //Google Feed API method: Limite de noticias a cargar
	feedpointer.load(displayfeed); //Google Feed API method: Cargamos el Feed en displayedfeed

}

/* Noticia para el Banner de la derecha */
function rssfeedsetup_banner_derecho(){
	var feed_banner=new google.feeds.Feed(url_banner);
	feed_banner.setNumEntries(feedlimit);
	feed_banner.load(display_banner);
}

/*----------------Mostramos el resutado en Formato HTML------------------------*/
function displayfeed(result){
	if (!result.error){
		var entradas_feeds=result.feed.entries;
		var tam_result=entradas_feeds.length;
		
		//Guardamos los resultados en algunas variables globales
		for (var i=0; i<tam_result; i++){
			feed_link[i]=entradas_feeds[i].link;
			feed_titulo[i]=entradas_feeds[i].title;
			feed_content[i]=entradas_feeds[i].content;
			date_content[i]= entradas_feeds[i].publishedDate;
			feed_contentsnipett[i]=entradas_feeds[i].contentSnippet;
			imagen_cont(i);//Sacamos la imagen de feed_content y la guardamos en (img_content);
			
		}	
		//mostramos los 5 primeros en html
		mostrar_noticias(1);
	}
	else
		alert("Error al captuar feeds!");
}

////mostramos 5 noticias en html
function mostrar_noticias(pagina){
	rssoutput="<br /><b><h1>" + Loadfeed_nombre_categoria +"</h1></b><br />";
	var tam=pagina*feedlimit;
	for(var i=(tam-feedlimit);i<tam;i++){
		rssoutput+="<section class='article_content'><article>";
		rssoutput+=img_content[i];
		rssoutput+="<h4><a href='javascript:links("+i+")' >" + feed_titulo[i] + "</a></h4>"+"<p class='date'>"+date_content[i]+"</p>";
		rssoutput+="<p>"+ feed_contentsnipett[i]+"</p></article></section>";
	}
	rssoutput+="<div id='historial'>historial </div>";
	
	feedcontainer.innerHTML=rssoutput;

	link_historial();//mostramos elhistorial Ej:1 2 3 4 5
}

//Historial de las Noticias
function link_historial(){
	var contenedor_historial=document.getElementById("historial");
	var historial_html="";
	
	var tam=limit_historial/feedlimit;
	for(var i=1;i<tam+1;i++){
		historial_html+="<a href='javascript:mostrar_noticias("+i+")' >" + i + "  </a>";
	}
	contenedor_historial.innerHTML=historial_html;
}

//Modificamos el html al darle click en los links y veremos cada noticia con su comentario
function links(i){
	rssoutput="";//limpiamos todo contenido
	rssoutput+="</br><b><h3><a href='" + feed_link[i] + "' target='_blank'>"+feed_titulo[i]+"</a><h3></b></br>";
	rssoutput+="<p class='date'>"+ date_content[i] + "</p>";
	rssoutput+="<div class='display_full'><p>"+feed_content[i]+"</p></div>";
	
	//--------------------------------Mostraremos los COMENTARIOS--------------------------------------------------------
	//llenamos los comentarios
	tipo_comentario[0]="CS News";
	tipo_comentario[1]="Facebook";
	
	rssoutput+="<section class='comentarios'>";
	rssoutput+="</br><p class='titulo_comentario'> Comentarios </p>";
	rssoutput+="<ul class='tabs'>";
	rssoutput+="<li><a href='javascript:comentario_csnews("+ i +")'>"+ tipo_comentario[0] +"</a></li>";
	rssoutput+="<li><a href='javascript:comentario_facebook("+ i +")'>"+ tipo_comentario[1] +"</a></li>";
	rssoutput+="</ul>";
	rssoutput+="<div class='clr'></div>";
	rssoutput+="<section class='block'>";
	rssoutput+="<article id='tabs'>";
	//rssoutput+="<div class='fb-comments' data-href='"+ feed_link[i] +"' data-num-posts='5' data-width='600'>Loading...</div>";
	rssoutput+="</article>";
	
	rssoutput+="</section></section>";
	feedcontainer.innerHTML=rssoutput;
	
}
//colocamos los comentarios solo para Facebook 
function comentario_facebook(i){
	
	var contenedor_tab=document.getElementById("tabs");
	var repositorio_2="<div class='fb-comments' data-href='"+ feed_link[i] +"' data-num-posts='5' data-width='600'>Loading...</div>";
	contenedor_tab.innerHTML=repositorio_2;
	FB.XFBML.parse(contenedor_tab);

}

//colocamos los comentarios solo para  CS_News
function comentario_csnews(i){
	var contenedor_tab1=document.getElementById("tabs");
	var img_usuario="/imagenes/usuario_img.jpg";
	var repositorio_1="<div class='cs_comentario'></br><img src='"+ img_usuario +"' class='comentario_img'>";
    repositorio_1+="<input id='text_comentario' type='text' placeholder='Agregue un comentario...'>";
    repositorio_1+="<input id='btn_comentar' value='Comentar' type='button' name='buscar' onClick='boton_comentar("+i+")'>";
    repositorio_1+="</div>";	
    //CREAMOS UN DIV donde se almacenaran los comentarios anteriores
    repositorio_1+="<div id='divlist_comentarios'>Loading Comentarios...</div>";
    repositorio_1+="<div id='mas_comentarios'><a href='javascript:mascomentarios()'>Mostrar más comentarios</a></div>";
	contenedor_tab1.innerHTML=repositorio_1;
	
	
    //Llenamos el la lista de comentaios
    llenar_lista_comentarios(i);
	
	//si queremos el link usamos feed_link[i]
}

//Llenamos el la lista de comentaios
function llenar_lista_comentarios(i){
	//tenemos que llenarlo llamando al servelet ........................
	
	$.ajax({
		type:'post',
	     url:'/comentario', // feed_titulo[i]
	     data: "idNoticia="+feed_link[i]+"&correo=john@jjsjsjsjs&categoria=nose&fecha="+date_content[i]+"&comentario=&usuario=",
	     success: function (data) {
	    	    list_comentarios=data;
	    	    tam_list_comentarios=list_comentarios.length-5;
	    	    mostrar_divlist_comentarios();
	    	    
	    }
	});
}
//mostramos los comentarios en divlist_comentarios HTML
function mostrar_divlist_comentarios(){
	var tam=list_comentarios.length;
	var img_usuario="/imagenes/usuario_img.jpg";
	var contenedor_html=document.getElementById("divlist_comentarios");
	list_comentario_html="";
	for(var i=tam-1;i>=0 && i>=tam_list_comentarios;i--){
		list_comentario_html+="<div id='cs_comentario_list'><img src='"+ img_usuario +"' class='listcomentario_img'></br>";
		//faltaria colocarle una img y el nombre del usuario
		list_comentario_html+="usuario:"+"</br>";
		list_comentario_html+=list_comentarios[i].m_texto;
		list_comentario_html+="</div>";
	}
	contenedor_html.innerHTML=list_comentario_html;
}
function mascomentarios(){
	if(tam_list_comentarios>0){tam_list_comentarios=tam_list_comentarios-5;}
	else{alert("No hay más comentarios!");}
	mostrar_divlist_comentarios();
}

//boton de comentar->solo añadimos a list_comentarios
function boton_comentar(i){
	var comentario=document.getElementById("text_comentario").value;
	if(comentario==""){return;}
	//PRIMERO debe de verificar si tiene cuenta...eso falta
	//if(name_usuario==""){alert("Primero debe de iniciar sesion para comentar!");return;}
	document.getElementById("text_comentario").value="";//limpiamos el input del comentario
	
	//faltaria colocarlo a la base de datos y usar el feed_link[i] para saber a que noticia le dio el coemntario
	$("#text_comentario").val("");
	$.ajax({
		type:'post',
	     url:'/comentario', // feed_titulo[i]
	     data: "idNoticia="+feed_link[i]+"&correo=john@jjsjsjsjs&categoria=nose&fecha="+date_content[i]+"&comentario="+comentario+"&usuario=hshshsh",
	     success: function (data) {
	    	    list_comentarios.push(data);
	    	    //tam_list_comentarios=list_comentarios.length-5;
	    	    mostrar_divlist_comentarios();
	    }
	});
}

/*---------------------------------------------------------FIN COMENTARIOS-----------------------------------------------------------*/
//Sacamos la imagen del contenido, La imagen siempre esta al inicio de 
//feed_content(Seria buenoverificarlo con un alert) <- Solo funciona con RPP y el correo
function imagen_cont(i){
	var j=0;
	var contenido=feed_content[i];
	img_content[i]="";
	//Si no tiene imagenes, nosotros le colocamos una
	if(contenido[0]=='<' && contenido[1]!='i'){
		img_content[i]+="<img src='/img/noticias.jpg' class='content_img'>";
		return;}
	//En caso tenga una
	while(contenido[j]!='>'){
		img_content[i]+=contenido[j];
		j++;
	}
	img_content[i]+=" class='content_img'>";
}

/* Funciones para el Banner de la derecha */
function display_banner(result){
	if (!result.error){
		var entradas_feeds=result.feed.entries;
		var contendedorbanner=document.getElementById("destacados");
		var tam_result=entradas_feeds.length;
		var banner_html="</br>";
		var j=limit_historial;
		//Guardamos los resultados en algunas variables globales
		for (var i=0; i<tam_result; i++){
			feed_link[j]=entradas_feeds[i].link;
			feed_titulo[j]=entradas_feeds[i].title;
			feed_content[j]=entradas_feeds[i].content;
			date_content[j]= entradas_feeds[i].publishedDate;
			banner_html+="<section class='article_banner'><article>";
			banner_html+="<h4><a href='javascript:links("+j+")' >" + feed_titulo[j] + "</a></h4>"+"<p class='date'>"+entradas_feeds[i].publishedDate+"</p>";
			banner_html+="<p>"+ entradas_feeds[i].contentSnippet+"</p></article></section>";
			j++;
		}	
		banner_html+="";
		contendedorbanner.innerHTML=banner_html;
	}
	else
		alert("Error al captuar feeds!");

}

//--------------------Ejecutamos la funcion rssfeedsetup al iniciar la pagina-----------------------------
window.onload=function(){
	rssfeedsetup();//carga una sola vez
	//setInterval carga varias veces la funcion en un determinado tiempo: segundos
	//setTimeout(setInterval(rssfeedsetup,2000),2000);
	//setTimeout(setInterval(rssfeedsetup_banner_derecho,0),2000);
	rssfeedsetup_banner_derecho();
}
