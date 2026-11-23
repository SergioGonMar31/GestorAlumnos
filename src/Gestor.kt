import utiles.Depurar

class Gestor {

    fun comenzar() {
        var alumnos: MutableList<Alumno> = mutableListOf<Alumno>()
        var salir = false
        while (!salir) {
            val opcion = Depurar.leerString(menu())
            when (opcion) {
                "0" -> salir = true
                "1" -> registrarAlumno(alumnos)
                "2" -> listarAlumno(alumnos)
                "3" -> calcularMedia(alumnos)
                "4" -> mostrarAprobados(alumnos)
                "5" -> mostrarSuspensos(alumnos)
                "6" -> buscarAlumno(alumnos)
                "7" -> eliminarAlumno(alumnos)
                else -> println("Opcion invalida")

            }
        }
    }

    private fun menu(): String {
        return "===== GESTOR DE ALUMNOS =====\n" +
                "1. Registrar alumno\n" +
                "2. Listar alumnos\n" +
                "3. Calcular media del grupo\n" +
                "4. Mostrar aprobados\n" +
                "5. Mostrar suspensos\n" +
                "6. Buscar alumno\n" +
                "7. Eliminar alumno\n" +
                "0. Salir\n" +
                "Seleccione una opción: "
    }

    private fun registrarAlumno(alumnos: MutableList<Alumno>) {
        val nombre = Depurar.leerString("Escribe el nombre: ")
        val apellido = Depurar.leerString("Escribe el apellido: ")
        var nota = 0.0
        do{
            nota = Depurar.leerDecimal("Escribe la nota: ")
            if(nota>10 || nota<0){
                println("Introduce una nota valida")
            }
        }while(nota>10 || nota<0)
        alumnos.add(Alumno(nombre, apellido, nota));
    }

    private fun listarAlumno(alumnos: MutableList<Alumno>) {
        if(alumnos.isEmpty()) {
            println("La lista esta vacia.")
            return
        }
        var i = 1
        println("==LISTA==")
        for (alumno in alumnos) {
            println("$i. $alumno")
            i++
        }
        println()
    }

    private fun calcularMedia(alumnos: MutableList<Alumno>) {
        if(alumnos.isEmpty()) {
            println("La lista esta vacia")
            return
        }
        var notas = 0.0
        for (alumno in alumnos) {
            notas += alumno.nota
        }
        val media = notas / (alumnos.size)
        println("La media es: $media")
    }

    private fun mostrarAprobados(alumnos: MutableList<Alumno>) {
        if (alumnos.isEmpty()) {
            println("La lista esta vacia")
            return
        }
        var i = 0
        for (alumno in alumnos) {
            if (alumno.nota >= 5.0) {
                i++
                println(alumno)
            }
        }
        if (i == 0) {
            println("No hay alumnos suspensos")
        }
    }

    private fun mostrarSuspensos(alumnos: MutableList<Alumno>) {
        if (alumnos.isEmpty()) {
            println("La lista esta vacia")
            return
        }
        var i = 0
        for (alumno in alumnos) {
            if (alumno.nota < 5.0) {
                i++
                println(alumno)
            }
        }
        if (i == 0) {
            println("No hay alumnos suspensos")
        }
    }

    private fun buscarAlumno(alumnos: MutableList<Alumno>) {
        if (alumnos.isEmpty()) {
            println("La lista esta vacia")
            return
        }
        val buscar = Depurar.leerString("Escribe el texto a buscar: ")
        var encontrado = false
        for (alumno in alumnos) {
            if (alumno.nombre.contains(buscar, true) || alumno.apellido.contains(buscar, true)) {
                encontrado = true
                println(alumno )
            }
        }
        if(!encontrado) {
            println("No se han encontrado alumnos.")
        }
    }

    private fun eliminarAlumno(alumnos: MutableList<Alumno>) {
        if(alumnos.isEmpty()) {
            println("La lista esta vacia")
            return
        }
        var salir = false
        while (!salir) {
            val opcion = Depurar.leerString("1. Por número de posición en el listado.\n" +
                    "2. Por nombre y apellidos.\n" +
                    "3. Mostrando previamente la lista y solicitando el número correspondiente.\n" +
                    "0. Salir")
            when(opcion) {
                "0" -> salir = true
                "1" -> {
                    val posicion = Depurar.leerInt("Introduce la posicion que quieres borrar.")
                    alumnos.removeAt(posicion-1)
                }
                "2" -> {
                    var alumnos2: MutableList<Alumno> = mutableListOf<Alumno>()
                    val nombre = Depurar.leerString("Introduce el nombre y apellidos")
                    for (alumno in alumnos) {
                        if (alumno.nombre.contains(nombre, true)) {
                        alumnos2.add(alumno)
                        }
                    }
                    if(alumnos2.isEmpty()){
                        println("No se han encontrado alumnos.")
                    }else{
                        listarAlumno(alumnos2)
                        val posicion = Depurar.leerInt("Introduce la posicion que quieres borrar.")
                        alumnos.remove(alumnos2[posicion-1])
                        alumnos2.removeAt(posicion-1)
                    }
                }
                "3" -> listarAlumno(alumnos)
            }
        }
    }
}