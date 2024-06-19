package cl.praxis.repository;
import cl.praxis.modelo.CategoriaEnum;
import cl.praxis.modelo.Cliente;
import cl.praxis.servicio.ClienteServicio;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.*;

public class ClienteServicioTest {
    private final static Logger logger = Logger.getLogger(ClienteServicioTest.class.getName());
    private final ClienteServicio clienteServicio = new ClienteServicio();

    @BeforeAll
    public static void init(){
        logger.warning("-> Inicio de los test de la clase Calculadora");
    }

    @BeforeEach
    public void initEach(){
        logger.info("-> Inicio del test");
    }

    @AfterEach
    public void closeEach(){
        logger.info("-> Fin del test");
    }

    @AfterAll
    public static void close(){
        logger.warning("-> Fin de los test de la clase Calculadora");

    }

    @Test
    public void testAgregarCliente() {
        logger.info("Agregar un cliente");
        Cliente cliente = new Cliente("12345678-9", "Juan", "Perez", "30", CategoriaEnum.ACTIVO);
        List<Cliente> listaClientes = new ArrayList<>();
        listaClientes.add(cliente);
        clienteServicio.setListaClientes(listaClientes);
        clienteServicio.agregarCliente("87654321-0", "Maria", "Gonzalez", "25");
        assertEquals(2, clienteServicio.getListaClientes().size());
        logger.info("El test para agregar un cliente funciona correctamente");
    }

    @Test
    public void testAgregarClienteNulo() {
        logger.info("Agregar un cliente nulo");
        assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente(null, "Nombre", "Apellido", "30"));
        assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("12345678-9", null, "Apellido", "30"));
        assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("12345678-9", "Nombre", null, "30"));
        assertThrows(IllegalArgumentException.class, () -> clienteServicio.agregarCliente("12345678-9", "Nombre", "Apellido", null));
        assertEquals(0, clienteServicio.getListaClientes().size());
        logger.info("El test para agregar un cliente nulo funciona correctamente");
    }
}
