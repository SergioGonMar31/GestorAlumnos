class Alumno(val nombre : String, val apellido: String, val nota: Double) {
    override fun toString(): String {
        return "Alumno(nombre='$nombre', apellido='$apellido', nota=$nota)"
    }
}