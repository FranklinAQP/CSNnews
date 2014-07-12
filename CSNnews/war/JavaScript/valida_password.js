function validar(objpass_b) {
    var pass_a = document.getElementById("pass_a").value;	
    var pass_b = objpass_b.value;
    var errorMessage = 'El password ingresado no coincide con su password de verificaci\u00f3n';
    if (pass_a != pass_b) {
        alert(errorMessage);
        campo.focus();
    } 
}
