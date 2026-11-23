import java.time.LocalTime;
import java.util.Scanner;

/**
 * CLASE PRINCIPAL: contiene el método main, punto de entrada del programa.
 * Muestra el menú, lee los datos del usuario y delega el trabajo
 * en los objetos del sistema.
 */
public class Principal {
    // Scanner compartido para leer datos del teclado
    static Scanner teclado = new Scanner(System.in);

    /**
     * MÉTODO AUXILIAR: muestra un mensaje y devuelve el texto escrito por el usuario.
     */
    static String leer(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    public static void main(String[] args) {
        // INSTANCIAR: se crea el objeto principal del sistema
        SistemaAsistencia sistema = new SistemaAsistencia();

        // INSTANCIAR: se crean dos empleados de ejemplo y se agregan al sistema
        sistema.agregarEmpleado(new Empleado("EMP001", "Carlos Perez", "123456789", "3001112233",
            "Auxiliar administrativo", "Recursos Humanos", LocalTime.of(8, 0), LocalTime.of(17, 0)));

        sistema.agregarEmpleado(new Empleado("EMP002", "Laura Gomez", "987654321", "3004445566",
            "Desarrolladora", "Tecnologia", LocalTime.of(9, 0), LocalTime.of(18, 0)));

        int opcion = 0;

        // Ciclo del menú: se repite hasta que el usuario elija salir (8)
        while (opcion != 8) {
            System.out.println("\n====================================");
            System.out.println(" SISTEMA DE CONTROL DE ASISTENCIA");
            System.out.println("====================================");
            System.out.println("1. Registrar empleado");
            System.out.println("2. Mostrar empleados");
            System.out.println("3. Buscar empleado");
            System.out.println("4. Registrar ingreso");
            System.out.println("5. Registrar salida");
            System.out.println("6. Consultar historial");
            System.out.println("7. Mostrar empleados presentes");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opcion: ");

            // Validación: la opción debe ser un número
            if (!teclado.hasNextInt()) {
                System.out.println("Debe ingresar un numero.");
                teclado.nextLine();
                continue;
            }

            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1: // Registrar un nuevo empleado con datos del usuario
                    String codigo = leer("Codigo: ");
                    String nombre = leer("Nombre: ");
                    String documento = leer("Documento: ");
                    String telefono = leer("Telefono: ");
                    String cargo = leer("Cargo: ");
                    String departamento = leer("Departamento: ");
                    String entrada = leer("Hora de entrada (HH:mm): ");
                    String salida = leer("Hora de salida (HH:mm): ");

                    try {
                        // INSTANCIAR: se crea el empleado; el parse puede fallar si el formato es incorrecto
                        sistema.agregarEmpleado(new Empleado(codigo, nombre, documento, telefono,
                            cargo, departamento, LocalTime.parse(entrada), LocalTime.parse(salida)));
                    } catch (Exception error) {
                        System.out.println("Formato incorrecto. Ejemplo: 08:00");
                    }
                    break;

                case 2: // Listar todos los empleados
                    sistema.mostrarEmpleados();
                    break;

                case 3: // Buscar un empleado por código y mostrar su información
                    Empleado encontrado = sistema.buscarEmpleado(leer("Codigo del empleado: "));
                    if (encontrado == null) {
                        System.out.println("Empleado no encontrado.");
                    } else {
                        encontrado.mostrarInformacion();
                    }
                    break;

                case 4: // Registrar la entrada de un empleado
                    sistema.registrarEntrada(leer("Codigo del empleado: "));
                    break;

                case 5: // Registrar la salida de un empleado
                    sistema.registrarSalida(leer("Codigo del empleado: "));
                    break;

                case 6: // Consultar el historial de asistencia de un empleado
                    sistema.consultarHistorial(leer("Codigo del empleado: "));
                    break;

                case 7: // Mostrar quiénes están dentro de la empresa
                    sistema.mostrarEmpleadosPresentes();
                    break;

                case 8: // Terminar el programa
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opcion no valida.");
            }
        }

        teclado.close();
    }
}