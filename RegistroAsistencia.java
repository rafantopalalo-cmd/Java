import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * CLASE: RegistroAsistencia.
 * Guarda una entrada/salida de un empleado en una fecha determinada.
 * AGREGACIÓN: el registro mantiene una referencia a un Empleado que ya existe
 * por separado; si el registro se elimina, el empleado sigue existiendo.
 */
public class RegistroAsistencia {
    // Atributos del registro
    public int codigoRegistro;
    public Empleado empleado;       // Objeto agregado (existe fuera del registro)
    public LocalDate fecha;
    public LocalTime horaEntrada;
    public LocalTime horaSalida;    // null mientras la jornada siga en curso

    /**
     * Constructor: crea el registro al momento de la entrada del empleado.
     */
    public RegistroAsistencia(int codigoRegistro, Empleado empleado,
                              LocalDate fecha, LocalTime horaEntrada) {
        this.codigoRegistro = codigoRegistro;
        this.empleado = empleado;
        this.fecha = fecha;
        this.horaEntrada = horaEntrada;
    }

    /**
     * MÉTODO: indica si el registro ya tiene salida (jornada terminada).
     */
    public boolean estaFinalizado() {
        return horaSalida != null;
    }

    /**
     * MÉTODO: guarda la hora de salida y cierra el registro.
     */
    public void registrarSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * MÉTODO: calcula las horas trabajadas; devuelve 0 si aún no hay salida.
     */
    public long calcularHorasTrabajadas() {
        return estaFinalizado() ? Duration.between(horaEntrada, horaSalida).toHours() : 0;
    }

    /**
     * MÉTODO: muestra por consola todos los datos del registro.
     */
    public void mostrarRegistro() {
        System.out.println("------------------------------");
        System.out.println("Registro: " + codigoRegistro);
        System.out.println("Empleado: " + empleado.nombre);
        System.out.println("Codigo: " + empleado.codigo);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora de entrada: " + horaEntrada);

        if (estaFinalizado()) {
            System.out.println("Hora de salida: " + horaSalida);
            System.out.println("Horas trabajadas: " + calcularHorasTrabajadas());
        } else {
            System.out.println("Hora de salida: Pendiente");
            System.out.println("Jornada en curso");
        }
    }
}