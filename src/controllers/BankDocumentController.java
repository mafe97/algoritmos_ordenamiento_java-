package controllers;
import java.util.List;
import javax.swing.JTable;
import models.BankDocument;
import services.BankDocumentService;

/**
 * Controller class in the MVC architecture.
 * Manages the state of documents and communicates with the Service layer.
 */
public class BankDocumentController {

    // Instance property for the service layer
    private BankDocumentService service;

    // Instance property storing the documents list in the controller state
    private List<BankDocument> documentos;

    // Constructor initializing the service and empty list
    public BankDocumentController() {
        this.service = new BankDocumentService();
        this.documentos = service.getDocumentos();
    }

    // Service Getter & Setter
    public BankDocumentService getService() {
        return service;
    }

    public void setService(BankDocumentService service) {
        this.service = service;
    }

    // Documentos Getter & Setter
    public List<BankDocument> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<BankDocument> documentos) {
        this.documentos = documentos;
    }

    /**
     * Obtains the total count of loaded records from the service.
     * 
     * @return Number of records
     */
    public int getCantidadRegistros() {
        return service.getCantidadRegistros();
    }

    /**
     * Controller action to load documents from a CSV file.
     * Reads file through service and updates internal state via setter.
     * 
     * @param rutaArchivo Path of the CSV file chosen by the user
     * @return Updated list of BankDocument objects
     */
    public List<BankDocument> cargarDocumentos(String rutaArchivo) {
        if (rutaArchivo == null || rutaArchivo.trim().isEmpty()) {
            rutaArchivo = "src/data/Datos.csv";
        }
        
        List<BankDocument> resultado = service.loadData(rutaArchivo);
        setDocumentos(resultado);
        
        return getDocumentos();
    }

    /**
     * Delegates populating the specified JTable to the service layer.
     * 
     * @param tabla The JTable component to populate
     */
    public void mostrar(JTable tabla) {
        service.mostrar(tabla);
    }

    /**
     * Ordena los documentos por Burbuja y retorna el tiempo transcurrido en nanosegundos.
     */
    public long ordenarBurbuja(int criterio) {
        long inicio = System.nanoTime();
        service.ordenarBurbuja(criterio);
        long fin = System.nanoTime();
        setDocumentos(service.getDocumentos());
        return fin - inicio;
    }

    /**
     * Ordena los documentos por QuickSort y retorna el tiempo transcurrido en nanosegundos.
     */
    public long ordenarRapido(int criterio) {
        long inicio = System.nanoTime();
        service.ordenarRapido(criterio);
        long fin = System.nanoTime();
        setDocumentos(service.getDocumentos());
        return fin - inicio;
    }

    /**
     * Ordena los documentos por Inserción y retorna el tiempo transcurrido en nanosegundos.
     */
    public long ordenarInsercion(int criterio) {
        long inicio = System.nanoTime();
        service.ordenarInsercion(criterio);
        long fin = System.nanoTime();
        setDocumentos(service.getDocumentos());
        return fin - inicio;
    }

    /**
     * Ordena los documentos por Mezcla (MergeSort) y retorna el tiempo transcurrido en nanosegundos.
     */
    public long ordenarMezcla(int criterio) {
        long inicio = System.nanoTime();
        service.ordenarMezcla(criterio);
        long fin = System.nanoTime();
        setDocumentos(service.getDocumentos());
        return fin - inicio;
    }

    /**
     * Busca un documento según el criterio especificado.
     */
    public int buscar(String texto, int criterio) {
        return service.buscar(texto, criterio);
    }
}
