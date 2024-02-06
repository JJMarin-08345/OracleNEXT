//Variables globales para esta funcion:
let semestre = 1;
function agregarFormulario() {
    // Clona el formulario existente
    var formularioExistente = document.querySelector('.form-notas');
    var nuevoFormulario = formularioExistente.cloneNode(true);

    semestre++; //Incrementamos el semestre

    nuevoFormulario.querySelector('.title-form').textContent = 'Calculador Promedio Semestre ' + semestre; //Cambiamos el titulo del formulario

    // Borra los valores de los campos clonados
    var inputs = nuevoFormulario.querySelectorAll('input');
    inputs.forEach(function(input) {
        input.value = '';
    });

    // Anexa el nuevo formulario al documento
    var contenedorPrincipal = document.getElementById('contenedor-principal');
    contenedorPrincipal.appendChild(nuevoFormulario);
} 

/* EXPLICACION DE LA FUNCION crearElemento()
    document.querySelector('.form-notas'): Esto selecciona el primer elemento 
    del documento que tiene la clase CSS "form-notas". En tu caso, es el formulario existente.

    formularioExistente.cloneNode(true): Esto clona el formulario existente, incluyendo
    todos sus nodos secundarios (campos de entrada, etiquetas, etc.). El argumento true
    indica que también debe clonar los nodos secundarios. 

    nuevoFormulario.querySelectorAll('input'): Esto selecciona todos los elementos
    de entrada (<input>) dentro del nuevo formulario clonado.

    inputs.forEach(function(input) { input.value = ''; });: Esto itera sobre todos los
    elementos de entrada y establece sus valores en cadena vacía (''), borrando 
    así los valores del nuevo formulario clonado.
*/

/* FUNCION PARA CALCULAR EL PROMEDIO DEL CORTE */

function calcularPromedio() {
    let notas = document.querySelectorAll('.nota');
    let porcentaje = document.querySelectorAll('.percent');

    verificacionPorcentaje100(porcentaje);
    
}

function verificacionPorcentaje100(lstPorcentajes){
    let suma = 0; //Variable para rectificar que la suma de los porcentajes sea 100
    let suma_notas = [];
    let lst_notas = [];
    let index = 0;
    let corte = 1;
    let semestre_actual = 1;

    while(index < lstPorcentajes.length){ 
        
        if(index != 0 && index % 3 == 0 ){ 
            /* Son 3 cortes por semestre, entonces cuando llegue al indice 3, que sería el corte 4
            (corte que no existe en este caso) es porque ya pasó al siguiente semestre/formulario */
            console.log("Llego a un multiplo de 3");
            corte = 1;
            suma = 0;
            semestre_actual++;
            lst_notas = []; //Limpiamos la lista de notas
        }
        
        if(lstPorcentajes[index].value >= 100){
            // Uno de los campos de porcentaje tiene un 100, cosa que esta mal
            alert(`El porcentaje del corte ${corte} del semestre ${semestre_actual} no puede ser mayor a 100`);
            suma = -31415; //Retornamos este numero para manejarlo en un if mas abajo
            break;
        }else{
            console.log("Entre: "+suma);
            suma += isNaN(parseFloat(lstPorcentajes[index].value)) ? 0 : parseFloat(lstPorcentajes[index].value);
            lst_notas.push(parseFloat(lstPorcentajes[index].value));
        }

        if( suma != 100 && corte == 3){
            //Si la suma de los porcentajes no es 100, se muestra un mensaje de alerta
            alert(`La suma de los porcentajes del semestre ${semestre_actual} debe ser 100`);
            lst_notas = []; //Limpiamos la lista de notas
        }

        if(corte == 3){
            //Si ya llegamos al final de la lista de porcentajes, agregamos las notas a la lista de suma_notas
            suma_notas.push(lst_notas);
        }
        // console.log(suma);
        corte++;
        index++;
    };//Recorremos la lista y miramos si la suma de los porcentajes es 100

    if(suma == -31415){
        //Si el porcentaje de algun corte es 100, se muestra un mensaje de alerta
        console.log("El porcentaje de algun corte es 100");
        return false;
    }
    console.log(JSON.stringify(suma_notas, null));

    return true;
    //Si la suma de los porcentajes es 100, se toma que los porcentajes están bien
}
