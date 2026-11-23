import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * CLASE: SistemaAsistencia.
 * Administra los empleados y los registros de asistencia.
 * AGREGACIÓN: contiene empleados que se crean fuera de esta clase.
 * COMPOSICIÓN: crea y es dueña de la colección de registros.
 */
public class SistemaAsistencia {
    // VECTOR (arreglo) de tamaño fijo para guardar los empleados
    public Empleado[] empleados = new Empleado[100];
    public int cantidadEmpleados = 0; // Posiciones del vector realmente usadas

    // LISTA dinámica: crece automáticamente al agregar registros
    public ArrayList<RegistroAsistencia> registros = new ArrayList<>();

    /**
     * MÉTODO: agrega un empleado al vector si hay espacio y el código no existe.
     * Devuelve true si se agregó correctamente.
     */
    public boolean agregarEmpleado(Empleado empleado) {
        if (cantidadEmpleados >= empleados.length) {
            System.out.println("No hay espacio para mas empleados.");
            return false;
        }
        if (buscarEmpleado(empleado.codigo) != null) {
            System.out.println("Ese codigo ya esta registrado.");
            return false;
        }
        empleados[cantidadEmpleados++] = empleado;
        System.out.println("Empleado registrado correctamente.");
        return true;
    }

    /**
     * MÉTODO: recorre el vector y devuelve el empleado con ese código
     * (sin distinguir mayúsculas), o null si no existe.
     */
    public Empleado buscarEmpleado(String codigo) {
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].codigo.equalsIgnoreCase(codigo)) {
                return empleados[i];
            }
        }
        return null;
    }

    /**
     * MÉTODO: muestra la información de todos los empleados registrados.
     */
    public void mostrarEmpleados() {
        if (cantidadEmpleados == 0) {
            System.out.println("No hay empleados registrados.");
            return;
        }
        for (int i = 0; i < cantidadEmpleados; i++) {
            System.out.println("\n===== EMPLEADO =====");
            empleados[i].mostrarInformacion();
        }
    }

    /**
     * MÉTODO AUXILIAR: devuelve la hora actual sin segundos ni nanosegundos.
     */
    private LocalTime horaActual() {
        return LocalTime.now().withSecond(0).withNano(0);
    }

    /**
     * MÉTODO: registra el ingreso de un empleado. Crea (INSTANCIA) un nuevo
     * RegistroAsistencia, lo agrega a la lista y marca al empleado como dentro.
     */
    public void registrarEntrada(String codigo) {
        Empleado empleado = buscarEmpleado(codigo);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        if (empleado.estaDentro) {
            System.out.println("Error: el empleado ya ingreso.");
            return;
        }

        LocalDate fecha = LocalDate.now();
        LocalTime hora = horaActual();

        // El código del registro es el siguiente número según el tamaño de la lista
        registros.add(new RegistroAsistencia(registros.size() + 1, empleado, fecha, hora));
        empleado.estaDentro = true;

        System.out.println("\nINGRESO REGISTRADO");
        System.out.println("Empleado: " + empleado.nombre);
        System.out.println("Fecha: " + fecha);
        System.out.println("Hora: " + hora);
        System.out.println(empleado.horario.verificarRetraso(hora) ? "Estado: Llegada tarde." : "Estado: A tiempo.");
    }

    /**
     * MÉTODO: registra la salida de un empleado. Busca, de atrás hacia adelante,
     * su último registro sin finalizar y lo cierra.
     */
    public void registrarSalida(String codigo) {
        Empleado empleado = buscarEmpleado(codigo);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }
        if (!empleado.estaDentro) {
            System.out.println("Error: el empleado no ha ingresado.");
            return;
        }

        for (int i = registros.size() - 1; i >= 0; i--) {
            RegistroAsistencia registro = registros.get(i);

            if (registro.empleado == empleado && !registro.estaFinalizado()) {
                LocalTime hora = horaActual();
                registro.registrarSalida(hora);
                empleado.estaDentro = false;

                System.out.println("\nSALIDA REGISTRADA");
                System.out.println("Empleado: " + empleado.nombre);
                System.out.println("Hora de salida: " + hora);
                System.out.println("Horas trabajadas: " + registro.calcularHorasTrabajadas());
                return;
            }
        }
        System.out.println("No se encontro una entrada pendiente.");
    }

    /**
     * MÉTODO: muestra todos los registros de asistencia de un empleado.
     */
    public void consultarHistorial(String codigo) {
        Empleado empleado = buscarEmpleado(codigo);

        if (empleado == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        boolean encontrado = false;
        for (RegistroAsistencia registro : registros) { // Recorre la lista
            if (registro.empleado == empleado) {
                registro.mostrarRegistro();
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No tiene registros de asistencia.");
        }
    }

    /**
     * MÉTODO: muestra los empleados que actualmente están dentro de la empresa.
     */
    public void mostrarEmpleadosPresentes() {
        boolean encontrado = false;
        for (int i = 0; i < cantidadEmpleados; i++) {
            if (empleados[i].estaDentro) {
                System.out.println(empleados[i].codigo + " - " + empleados[i].nombre);
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No hay empleados dentro.");
        }
    }
}