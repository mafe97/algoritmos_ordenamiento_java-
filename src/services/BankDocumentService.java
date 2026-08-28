package services;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import models.BankDocument;

/**
 * Service class responsible for file I/O operations and parsing CSV data into BankDocument objects.
 */
public class BankDocumentService {

    // Instance property storing the loaded list of BankDocument objects
    private List<BankDocument> documentos;

    // Constructor initializing an empty list
    public BankDocumentService() {
        this.documentos = new ArrayList<>();
    }

    /**
     * Getter for the list of BankDocument objects.
     * 
     * @return List of BankDocument
     */
    public List<BankDocument> getDocumentos() {
        return documentos;
    }

    /**
     * Setter for the list of BankDocument objects.
     * 
     * @param documentos New list of BankDocument to set
     */
    public void setDocumentos(List<BankDocument> documentos) {
        this.documentos = documentos;
    }

    /**
     * Returns the total count of loaded records.
     * 
     * @return Total number of BankDocument records
     */
    public int getCantidadRegistros() {
        return documentos != null ? documentos.size() : 0;
    }

    /**
     * Reads a plain text CSV file from disk, parses rows into BankDocument instances,
     * and updates the internal documentos property via setter.
     * 
     * @param fileName Path to the CSV file
     * @return Updated list of BankDocument
     */
    public List<BankDocument> loadData(String fileName) {
        try {
            var rows = Files.lines(Paths.get(fileName));

            List<BankDocument> listaCargada = rows
                    .skip(1)
                    .map(linea -> linea.split("[,;]"))
                    .map(textos -> new BankDocument(
                            textos.length > 0 ? textos[0].trim() : "",
                            textos.length > 1 ? textos[1].trim() : "",
                            textos.length > 2 ? textos[2].trim() : "",
                            textos.length > 3 ? textos[3].trim() : ""
                    ))
                    .collect(Collectors.toList());

            // Use setter to update internal list property
            setDocumentos(listaCargada);

        } catch (Exception ex) {
            setDocumentos(new ArrayList<>());
        }
        return getDocumentos();
    }

    /**
     * Populates the provided JTable component directly with the loaded BankDocument records.
     * 
     * @param tabla The JTable component to display data in
     */
    public void mostrar(JTable tabla) {
        if (tabla == null) {
            return;
        }

        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0);

        int index = 1;
        for (BankDocument doc : documentos) {
            dtm.addRow(new Object[] {
                    index++,
                    doc.getApellido(),
                    doc.getApellido2(),
                    doc.getNombre(),
                    doc.getDocumento()
            });
        }
    }

    /**
     * Intercambia dos elementos en la lista de documentos.
     * 
     * @param i Índice del primer elemento
     * @param j Índice del segundo elemento
     */
    private void intercambiar(int i, int j) {
        if (documentos != null && 0 <= i && i < documentos.size() && 0 <= j && j < documentos.size()) {
            BankDocument temp = documentos.get(i);
            documentos.set(i, documentos.get(j));
            documentos.set(j, temp);
        }
    }

    /**
     * Ordena la lista de documentos aplicando el algoritmo de Burbuja (Bubble Sort).
     * 
     * @param criterio Criterio de ordenamiento (0: Nombre Completo, Tipo de Documento; 1: Tipo de Documento, Nombre Completo)
     */
    public void ordenarBurbuja(int criterio) {
        if (documentos == null || documentos.isEmpty()) {
            return;
        }
        int n = documentos.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (documentos.get(j).esMayor(documentos.get(j + 1), criterio)) {
                    intercambiar(j, j + 1);
                }
            }
        }
    }

    /**
     * Ordena la lista de documentos aplicando el algoritmo QuickSort (Ordenamiento Rápido).
     * 
     * @param criterio Criterio de ordenamiento
     */
    public void ordenarRapido(int criterio) {
        if (documentos == null || documentos.isEmpty()) {
            return;
        }
        quickSort(0, documentos.size() - 1, criterio);
    }
    // dividir y volver a recorrer los elementos inestables
    private void quickSort(int inicio, int fin, int criterio) {
        if (inicio < fin) {
            int pivoteIndex = particion(inicio, fin, criterio);
            quickSort(inicio, pivoteIndex - 1, criterio);
            quickSort(pivoteIndex + 1, fin, criterio);
        }
    }

    private int particion(int inicio, int fin, int criterio) {
        BankDocument pivote = documentos.get(fin);
        int i = inicio - 1;
        for (int j = inicio; j < fin; j++) {
            if (!documentos.get(j).esMayor(pivote, criterio)) {
                i++;
                intercambiar(i, j);
            }
        }
        intercambiar(i + 1, fin);
        return i + 1;
    }

    /**
     * Ordena la lista de documentos aplicando el algoritmo de Inserción.
     * 
     * @param criterio Criterio de ordenamiento
     */
    public void ordenarInsercion(int criterio) {
        if (documentos == null || documentos.isEmpty()) {
            return;
        }
        int n = documentos.size();
        for (int i = 1; i < n; i++) {
            BankDocument clave = documentos.get(i);
            int j = i - 1;
            while (j >= 0 && documentos.get(j).esMayor(clave, criterio)) {
                documentos.set(j + 1, documentos.get(j));
                j--;
            }
            documentos.set(j + 1, clave);
        }
    }

    /**
     * Ordena la lista de documentos aplicando el algoritmo de Mezcla (Merge Sort).
     * 
     * @param criterio Criterio de ordenamiento
     */
    public void ordenarMezcla(int criterio) {
        if (documentos == null || documentos.size() <= 1) {
            return;
        }
        mergeSort(0, documentos.size() - 1, criterio);
    }

    // dividir y volver a recorrer los elementos estables
    private void mergeSort(int inicio, int fin, int criterio) {
        if (inicio < fin) {
            int medio = inicio + (fin - inicio) / 2;
            mergeSort(inicio, medio, criterio);
            mergeSort(medio + 1, fin, criterio);
            merge(inicio, medio, fin, criterio);
        }
    }

    // unir las listas ordenadas
    private void merge(int inicio, int medio, int fin, int criterio) {
        List<BankDocument> izquierda = new ArrayList<>(documentos.subList(inicio, medio + 1));
        List<BankDocument> derecha = new ArrayList<>(documentos.subList(medio + 1, fin + 1));

        int i = 0, j = 0, k = inicio;
        while (i < izquierda.size() && j < derecha.size()) {
            if (!izquierda.get(i).esMayor(derecha.get(j), criterio)) {
                documentos.set(k++, izquierda.get(i++));
            } else {
                documentos.set(k++, derecha.get(j++));
            }
        }

        while (i < izquierda.size()) {
            documentos.set(k++, izquierda.get(i++));
        }
        while (j < derecha.size()) {
            documentos.set(k++, derecha.get(j++));
        }
    }

    /**
     * Busca un documento por coincidencia parcial en nombre o documento.
     * 
     * @param texto Texto a buscar
     * @param criterio Criterio activo
     * @return Índice del documento encontrado o -1 si no existe
     */
    public int buscar(String texto, int criterio) {
        if (documentos == null || texto == null || texto.trim().isEmpty()) {
            return -1;
        }
        String busqueda = texto.trim().toLowerCase();
        for (int i = 0; i < documentos.size(); i++) {
            BankDocument doc = documentos.get(i);
            String valorComparar = (criterio == 0) ? doc.getNombreCompleto() : doc.getDocumento();
            if (valorComparar.toLowerCase().contains(busqueda)) {
                return i;
            }
        }
        return -1;
    }
}