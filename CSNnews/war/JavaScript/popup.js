/* funcion para abrir una pagina externa del login y register dentro de otra(home.jsp) */
function popup(url) {
	var contenedor_popup=document.getElementById("feeddiv");
	contenedor_popup.innerHTML="";
	$("#feeddiv").load(url);
}