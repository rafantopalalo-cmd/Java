import java.time.Duration;
import java.time.LocalTime;

/**
 * CLASE: Horario.
 * Representa la jornada laboral (hora de entrada y de salida) de un empleado.
 * Es parte de Empleado: si el empleado deja de existir, su horario también.
 */
public class Horario {
    // Atributos: límites de la jornada
    public LocalTime horaEntrada;
    public LocalTime horaSalida;

    /**
     * Constructor: crea el horario con la hora de entrada y de salida.
     */
    public Horario(LocalTime horaEntrada, LocalTime horaSalida) {
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    /**
     * MÉTODO: indica si la hora real de llegada es posterior a la hora de entrada.
     * Devuelve true si el empleado llegó tarde.
     */
    public boolean verificarRetraso(LocalTime horaRealEntrada) {
        return horaRealEntrada.isAfter(horaEntrada);
    }

    /**
     * MÉTODO: calcula cuántas horas dura la jornada completa.
     */
    public long calcularJornada() {
        return Duration.between(horaEntrada, horaSalida).toHours();
    }

    /**
     * MÉTODO: muestra el horario con el formato "entrada - salida".
     */
    public void mostrarHorario() {
        System.out.println(horaEntrada + " - " + horaSalida);
    }
}