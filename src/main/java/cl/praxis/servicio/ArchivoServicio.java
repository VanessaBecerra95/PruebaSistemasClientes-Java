package cl.praxis.servicio;

import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArchivoServicio extends Exportador {
    private Scanner scanner = new Scanner(System.in);
    private ExportadorCsv exportadorCsv = new ExportadorCsv();
    private ExportadorTxt exportadorTxt = new ExportadorTxt();


    public List<Cliente> cargarDatos() {
        System.out.println("---------Cargar Datos en Windows -----------");
        System.out.print("Ingresa la ruta completa del archivo DBClientes.csv: (src/main/resources/DBClientes.csv)\n");
        String rutaIngresada = scanner.nextLine();
        List<Cliente> clientesCargados = new ArrayList<>();
        if (rutaIngresada.equals("src/main/resources/DBClientes.csv")) {
            clientesCargados = cargarDatosDesdeArchivo(rutaIngresada);
            System.out.println("-----------------------------------------------");
            System.out.println("\nDatos cargados correctamente en la lista.\n");
        } else {
            System.out.println("Ruta incorrecta.");
        }
        return clientesCargados;
    }

    private List<Cliente> cargarDatosDesdeArchivo(String fileName) {
        List<Cliente> clientesCargados = new ArrayList<>();
        File archivo = new File(fileName);
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 5) {
                    try {
                        String runCliente = datos[0];
                        String nombreCliente = datos[1];
                        String apellidoCliente = datos[2];
                        String aniosCliente = datos[3];
                        CategoriaEnum nombreCategoria = CategoriaEnum.valueOf(datos[4].toUpperCase());
                        Cliente cliente = new Cliente(runCliente, nombreCliente, apellidoCliente, aniosCliente, nombreCategoria);
                        clientesCargados.add(cliente);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error al convertir los datos del cliente: " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea con formato incorrecto: " + linea);
                }
            }
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar datos: " + e.getMessage());
        }
        return clientesCargados;
    }

    @Override
    public void exportar(String fileName, List<Cliente> listaClientes) {
        System.out.println("---------Exportar Datos-----------");
        System.out.println("Seleccione el formato a exportar:");
        System.out.println("1.- Formato csv");
        System.out.println("2.- Formato txt");
        System.out.println("Ingrese una opción para exportar:");
        try {
            int opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println("----------------------------------");
            if (opcion == 1) {
                exportadorCsv.exportar(fileName, listaClientes);
            } else if (opcion == 2) {
                exportadorTxt.exportar(fileName, listaClientes);
            } else {
                System.out.println("Opción inválida");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número. Intente de nuevo.");
            scanner.nextLine();
        }
    }
}
