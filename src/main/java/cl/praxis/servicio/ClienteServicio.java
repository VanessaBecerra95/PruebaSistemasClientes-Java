package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.utilidades.Utilidad;

import java.util.*;

public class ClienteServicio {
    private List<Cliente> listaClientes;
    Scanner scanner;
    Utilidad utilidad;
    Cliente cliente;

    public ClienteServicio() {
        this.listaClientes = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.utilidad = new Utilidad();
        Cliente cliente = new Cliente();
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public void listarClientes() {
        if (listaClientes.isEmpty()) {
            Utilidad.imprimir("No hay clientes para mostrar.");
        } else {
            for (Cliente cliente : listaClientes) {
                Utilidad.imprimir("-------------Datos del Cliente------------- ");
                Utilidad.imprimir(cliente.toString());
                Utilidad.imprimir("-------------------------------------------");
            }
        }
    }

    public void agregarCliente(String run, String nombre, String apellido, String anios) {
        if (run == null || nombre == null || apellido == null || anios == null) {
            throw new IllegalArgumentException("Ninguno de los campos puede ser nulo");
        }
        Cliente cliente = new Cliente();
        cliente.setRunCliente(run);
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setAniosCliente(anios);
        cliente.setNombrecategoria(CategoriaEnum.ACTIVO);
        listaClientes.add(cliente);
    }


    public void agregarCliente() {
        Utilidad.imprimir("-------------Crear Cliente------------- ");
        try {
            Utilidad.imprimir("Ingresa el RUN del cliente: ");
            String run = scanner.nextLine();
            Utilidad.imprimir("Ingresa el Nombre del cliente: ");
            String nombre = scanner.nextLine();
            Utilidad.imprimir("Ingresa el Apellido del cliente: ");
            String apellido = scanner.nextLine();
            Utilidad.imprimir("Ingresa los años del cliente: ");
            String anios = scanner.nextLine();
            agregarCliente(run, nombre, apellido, anios);
            Utilidad.imprimir("--------------------------------------- ");
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    public void editarClientes() {
        Utilidad.imprimir("-------------Editar Cliente------------- ");
        Utilidad.imprimir("Seleccione qué desea hacer: ");
        Utilidad.imprimir("1.- Cambiar el estado del cliente");
        Utilidad.imprimir("2.- Editar datos del cliente");
        Utilidad.imprimir("Ingrese opcion: ");
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            Utilidad.imprimir("----------------------------------------");
            if (opcion == 1) {
                cambiarEstadoCliente();
            } else if (opcion == 2) {
                editarDatosCliente();
            } else {
                Utilidad.imprimir("Opción no válida, intente con un número entre 1 y 2.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    public void cambiarEstadoCliente(){
        System.out.print("Ingrese RUN del Cliente a editar: ");
        try {
            String runCliente = scanner.nextLine();
            Optional<Cliente> clienteOptional = listaClientes.stream()
                    .filter(cliente -> cliente.getRunCliente().equals(runCliente))
                    .findFirst();
            if (clienteOptional.isPresent()) {
                Cliente cliente = clienteOptional.get();
                Utilidad.imprimir("-----Actualizando estado del Cliente----");
                Utilidad.imprimir("El estado actual del cliente es: " + cliente.getNombrecategoria());
                Utilidad.imprimir("1.-Si desea cambiar el estado del Cliente a Inactivo");
                Utilidad.imprimir("2.-Si desea mantener el estado del cliente Activo");
                Utilidad.imprimir("Ingrese opcion: ");
                int opcionEstado = scanner.nextInt();
                scanner.nextLine();
                Utilidad.imprimir("----------------------------------------");
                switch (opcionEstado) {
                    case 1:
                        cliente.setNombrecategoria(CategoriaEnum.INACTIVO);
                        break;
                    case 2:
                        cliente.setNombrecategoria(CategoriaEnum.ACTIVO);
                        break;
                    default:
                        Utilidad.imprimir("Opción no válida, intente con un número entre 1 y 2.");
                }
                Utilidad.imprimir("Estado cambiado con éxito");
            } else {
                Utilidad.imprimir("Cliente con RUN " + runCliente + " no encontrado.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    public void editarDatosCliente() {
        try {
            System.out.print("Ingrese RUN del Cliente a editar:  ");
            String runCliente = scanner.nextLine();
            Optional<Cliente> clienteOpcional = listaClientes.stream()
                    .filter(cliente -> cliente.getRunCliente().equals(runCliente))
                    .findFirst();

            if (clienteOpcional.isPresent()) {
                Cliente cliente = clienteOpcional.get();
                Utilidad.imprimir("Datos del Cliente: \n");
                Utilidad.imprimir("1.-El RUN del Cliente es: \n" + cliente.getRunCliente());
                Utilidad.imprimir("2.-El Nombre del Cliente es: \n" + cliente.getNombreCliente());
                Utilidad.imprimir("3.-El Apellido del Cliente es: \n" + cliente.getApellidoCliente());
                Utilidad.imprimir("4.-Los años del Cliente son: \n" + cliente.getAniosCliente());
                System.out.print("Ingrese opcion a editar de los datos del cliente: \n");
                Utilidad.imprimir("---------------------------------------- ");
                int opcionEditar = scanner.nextInt();
                scanner.nextLine();
                switch (opcionEditar) {
                    case 1:
                        Utilidad.imprimir("1.-Ingrese nuevo RUN del Cliente:");
                        cliente.setRunCliente(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("2. -Ingrese el nuevo nombre del cliente: ");
                        cliente.setNombreCliente(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("3. -Ingrese el nuevo apellido del cliente: ");
                        cliente.setApellidoCliente(scanner.nextLine());
                        break;
                    case 4:
                        System.out.print("4. -Ingrese los nuevos años del cliente: ");
                        cliente.setAniosCliente(scanner.nextLine());
                        break;
                    default:
                        Utilidad.imprimir("Opción no válida, intente con un número entre 1 y 4.");
                }
                Utilidad.imprimir("Datos cambiados con éxito");
            } else {
                Utilidad.imprimir("Cliente con RUN " + runCliente + " no encontrado.");
            }
        } catch (InputMismatchException e) {
            Utilidad.imprimir("Error: Debe ingresar un número. Intente de nuevo.");
            scanner.nextLine();
        }
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ArrayList<Cliente> clientesCargados) {
    }

}