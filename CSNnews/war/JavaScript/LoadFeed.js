/*-----------------------------------VARIABLES GLOBALES------------------------------------*/
var feedcontainer=document.getElementById("feeddiv");//Aqui estaran las noticias
var diariosinimg = /elcomercio/;
var tieneimg = true; 
var feedurl="http://diariocorreo.pe/RSS-portlet/feed/correo/economia";//http://diariocorreo.pe/RSS-portlet/feed/correo/politica
var feedlimit=5;//limite de noticias a ver
var rssoutput="";//Salida en Formato HTML
//------Datos del resultado:function DisplayFeed
var feed_link=new Array(feedlimit);
var feed_titulo=new Array(feedlimit);
var feed_content=new Array(feedlimit);
var img_content=new Array(feedlimit);//sacamos el link con html de la imagen de feed_content
var date_content=new Array(feedlimit);
var temp = "";

/*Para detectar la url del feed por metodo GET*/
function locationVars (vr)
{		
		var src = String( window.location.href ).split('?')[1];
        var vrs = src.split('&');
        
        for (var x = 0, c = vrs.length; x < c; x++) 
        {
        	if (vrs[x].indexOf(vr) != -1)
        	{
        		return decodeURI( vrs[x].split('=')[1] );
        		break;
        	};
        };
        return false;
};



/*-----------Ejecutamos los metodos de Google Feed API-----------*/
function rssfeedsetup(){
	var ruta=String( window.location.href );
	if(ruta.indexOf('?') != -1){		
		if(locationVars("url")){
			feedurl = locationVars ("url");
		}
	}else{
		feedurl="http://diariocorreo.pe/RSS-portlet/feed/correo/economia";
	}	
	tieneimg = !diariosinimg.test(feedurl);
	//alert(feedurl);
	var feedpointer=new google.feeds.Feed(feedurl); //Google Feed API method: Controlador de Feeds
	feedpointer.setNumEntries(feedlimit); //Google Feed API method: Limite de noticias a cargar
	feedpointer.load(displayfeed); //Google Feed API method: Cargamos el Feed en displayedfeed
}

/*----------------Mostramos el resutado en Formato HTML------------------------*/
function displayfeed(result){
	if (!result.error){
		var entradas_feeds=result.feed.entries;
		var tam_result=entradas_feeds.length;
		rssoutput="<h2><b>Actualidad:</b></h2><br />";//Podria ir la categoria:
		//Guardamos los resultados en algunas variables globales
		for (var i=0; i<tam_result; i++){
			feed_link[i]=entradas_feeds[i].link;
			feed_titulo[i]=entradas_feeds[i].title;
			feed_content[i]=entradas_feeds[i].content;
			date_content[i]= entradas_feeds[i].publishedDate;			
			imagen_cont(i);//Sacamos la imagen de feed_content y la guardamos en (img_content);
			rssoutput+="<section class='article_content'><article>";
			if(tieneimg){
				rssoutput+=img_content[i];
			}
			rssoutput+="<h4><a href='javascript:links("+i+")' >" + feed_titulo[i] + "</a></h4>"+"<p class='date'>"+entradas_feeds[i].publishedDate+"</p>";
			rssoutput+="<p>"+ entradas_feeds[i].contentSnippet+"</p></article></section>";
			
		}	
		rssoutput+="";
		feedcontainer.innerHTML=rssoutput;
	}
	else
		alert("Error al capturar feeds!");
}

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

//--------------------Ejecutamos la funcion rssfeedsetup al iniciar la pagina-----------------------------
window.onload=function(){
	rssfeedsetup();//carga una sola vez
	//setInterval carga varias veces la funcion en un determinado tiempo: segundos
	//setTimeout(setInterval(rssfeedsetup,2000),2000);
}
