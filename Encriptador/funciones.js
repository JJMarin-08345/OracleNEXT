const btn_informativo = document.getElementById('btn-informativo'); // Boton que explica que hace el codigo
const btn_encriptado = document.getElementById('btn-crypt'); // Boton para encriptar el texto
const btn_desencriptado = document.getElementById('btn-descrypt'); // Boton para desencriptar el texto
const txt_ingresado = document.getElementById('msj-crypt'); // Texto ingresado por el usuario
const txt_resultado = document.getElementById('msj-descrypt'); // Texto resultante del encriptado o desencriptado
const btn_copy = document.getElementById('btn-copy'); // Boton para copiar el texto resultante

//Inhabillitamos los botones de inicio
btn_encriptado.disabled = true;
btn_desencriptado.disabled = true;

//----------------------------------------------------------------------------------------------------------------------------------------------------\\
// FUNCIONES

//Funcion para deshabilitar o habilitar los botones
const Unlock_Buttons = () => {
    if (txt_ingresado.value == '') { // Los deshabilitamos si no hay texto
        btn_encriptado.disabled = true;
        btn_desencriptado.disabled = true;
        txt_resultado.value = ''; //Limpiamos el texto de resultado
    } else { // Los habilitamos si hay texto
        btn_encriptado.disabled = false;
        btn_desencriptado.disabled = false;
    }
}



//METODOS DE ENCRIPTADO Y DESENCRIPTADO

// ENCRIPTADO \\
//Funcion para cambiar las vocaloes a encriptar por otras
const cambio_vocales = (letra) => {

    return (letra === 'a') ? 'ai'
        : (letra === 'e') ? 'enter'
            : (letra === 'i') ? 'imes'
                : (letra === 'o') ? 'ober'
                    : (letra === 'u') ? 'ufat'
                        : letra;

}

//Funcion para encriptar el texto
const Encriptar = () => {
    let texto = txt_ingresado.value.toLowerCase().split(''); //Convertimos el texto a minusculas y lo separamos por letras
    let i = 0; // Variable contadora
    //Recorremos el texto para cambiar las letras
    texto.forEach(letra => {
        letra = cambio_vocales(letra, true);//Cambiamos las vocales
        texto[i] = letra; //Guardamos la letra retornada en el arreglo
        i++; //Incrementamos i
    });
    texto = texto.join(''); //Unimos el texto en un solo string
    txt_resultado.value = texto; //Mostramos el texto encriptado
}

// DESENCRIPTADO \\
//Funcion para cambiar las palabras encriptadas a vocales
const cambio_encriptadas = (texto) => {
    return texto
        .replace(/ai/g, 'a')
        .replace(/enter/g, 'e')
        .replace(/imes/g, 'i')
        .replace(/ober/g, 'o')
        .replace(/ufat/g, 'u');
}

//Funcion para desencriptar el texto
const Desencriptar = () => {
    let texto = txt_ingresado.value.toLowerCase(); //Convertimos el texto a minusculas y lo separamos por letras
    texto = cambio_encriptadas(texto);
    console.log(texto);
    txt_resultado.value = texto; //Mostramos el texto encriptado
}

//----------------------------------------------------------------------------------------------------------------------------------------------------\\
// EVENTOS

//Evento para cuando se ingresa texto
txt_ingresado.addEventListener('keyup', (e) => {
    Unlock_Buttons();
});

//Evento para cuando se hace click en el boton de "Encriptar" (btn_encriptado)
btn_encriptado.addEventListener('click', () => {
    console.log('Encriptando...');
    Encriptar();
});

//Evento para cuando se hace click en el boton de "Desencriptar" (btn_desencriptado)
btn_desencriptado.addEventListener('click', () => {
    console.log('Desencriptando...');
    Desencriptar();
});

//Evento para cuando se hace click en el boton de "¿Qué es esto?" (btn_informativo)
btn_informativo.addEventListener('click', () => {
    alert("Este es un encriptador de texto, el cual cambia las vocales por otras letras\n\n"
        + "a = ai\ne = enter\ni = imes\no = ober\nu = ufat\n\n");
});

btn_copy.addEventListener('click', () => {
    txt_resultado.select();
    document.execCommand('copy');
    alert('Texto copiado al portapapeles');
});