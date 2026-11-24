import java.time.LocalTime;

/**
 * CLASE: Empleado
 * HERENCIA: extends Persona indica que Empleado hereda nombre, documento,
 * teléfono y mostrarInformacion() de su clase padre.
 * COMPOSICIÓN: el Empleado crea y es dueño de su Horario (new Horario dentro
 * del constructor); el horario no existe sin el empleado.
 */
public class Empleado extends Persona {
    // Atributos propios del empleado
    public String codigo;
    public String cargo;
    public String departamento;
    public Horario horario;           // Objeto compuesto dentro del empleado
    public boolean estaDentro = false; // Indica si está dentro de la empresa

    /**
     * Constructor: INSTANCIA un empleado con todos sus datos.
     * Llama a super(...) para guardar los datos de la clase padre y crea
     * el Horario internamente (composición).
     */
    public Empleado(String codigo, String nombre, String documento, String telefono,
                    String cargo, String departamento,
                    LocalTime horaEntrada, LocalTime horaSalida) {
        super(nombre, documento, telefono);
        this.codigo = codigo;
        this.cargo = cargo;
        this.departamento = departamento;
        this.horario = new Horario(horaEntrada, horaSalida);
    }

    /**
     * MÉTODO IMPLEMENTADO: cumple el contrato del método abstracto de Persona.
     */
    @Override
    public String obtenerRol() {
        return "Empleado";
    }

    /**
     * MÉTODO SOBRESCRITO: amplía mostrarInformacion() de Persona agregando
     * código, cargo, departamento, horario y estado actual.
     */
    @Override
    public void mostrarInformacion() {
        System.out.println("Codigo: " + codigo);
        super.mostrarInformacion(); // Reutiliza el método del padre
        System.out.println("Cargo: " + cargo);
        System.out.println("Departamento: " + departamento);
        System.out.print("Horario: ");
        horario.mostrarHorario();
        System.out.println("Estado: " + (estaDentro ? "Dentro de la empresa" : "Fuera de la empresa"));
    }
}
