/**
 * CLASE ABSTRACTA: Persona. explicacion.
 * Una clase abstracta es una plantilla que no se puede instanciar directamente;
 * sirve como base para otras clases (por ejemplo, Empleado) y puede obligar
 * a las clases hijas a implementar ciertos métodos.
 */
public abstract class Persona {
    // Atributos comunes a cualquier persona
    public String nombre;
    public String documento;
    public String telefono;

    /**
     * Constructor: inicializa los datos básicos de la persona.
     * Se ejecuta cuando una clase hija llama a super(...).
     */
    public Persona(String nombre, String documento, String telefono) {
        this.nombre = nombre;
        this.documento = documento;
        this.telefono = telefono;
    }

    /**
     * MÉTODO ABSTRACTO: no tiene cuerpo, cada clase hija debe implementarlo.
     * Devuelve el rol que cumple la persona dentro del sistema.
     */
    public abstract String obtenerRol();

    /**
     * MÉTODO: muestra por consola los datos básicos de la persona.
     */
    public void mostrarInformacion() {
        System.out.println("Rol: " + obtenerRol());
        System.out.println("Nombre: " + nombre);
        System.out.println("Documento: " + documento);
        System.out.println("Telefono: " + telefono);
    }
}
