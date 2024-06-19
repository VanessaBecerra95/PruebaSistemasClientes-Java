package cl.praxis.modelo;

public class Cliente {
    private String runCliente;
    private String nombreCliente;
    private String apellidoCliente;
    private String aniosCliente;
    CategoriaEnum Nombrecategoria;

    public Cliente() {
    }

    public Cliente(String runCliente, String nombreCliente, String apellidoCliente, String aniosCliente, CategoriaEnum Nombrecategoria) {
        this.runCliente = runCliente;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.aniosCliente = aniosCliente;
        this.Nombrecategoria = Nombrecategoria;
    }

    public String getAniosCliente() {
        return aniosCliente;
    }

    public void setAniosCliente(String aniosCliente) {
        this.aniosCliente = aniosCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public CategoriaEnum getNombrecategoria() {
        return Nombrecategoria;
    }

    public void setNombrecategoria(CategoriaEnum nombrecategoria) {
        Nombrecategoria = nombrecategoria;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getRunCliente() {
        return runCliente;
    }

    public void setRunCliente(String runCliente) {
        this.runCliente = runCliente;
    }

    @Override
    public String toString() {
        return "RUN del cliente: " + runCliente + '\n' +
                "Nombre del cliente: " + nombreCliente + '\n' +
                "Apellido del cliente: " + apellidoCliente + '\n' +
                "Años como cliente: " + aniosCliente + '\n' +
                "Categoría del cliente: " + Nombrecategoria;
    }
}
