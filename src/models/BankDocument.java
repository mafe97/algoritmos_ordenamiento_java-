package models;

/**
 * Model class representing a BankDocument.
 * Contains domain attributes and comparison logic (esMayor) for sorting algorithms.
 */
public class BankDocument {
    private String apellido;
    private String apellido2;
    private String nombre;
    private String documento;

    public BankDocument(String apellido, String apellido2, String nombre, String documento) {
        this.apellido = apellido != null ? apellido : "";
        this.apellido2 = apellido2 != null ? apellido2 : "";
        this.nombre = nombre != null ? nombre : "";
        this.documento = documento != null ? documento : "";
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * Helper method to concatenate first surname, second surname, and first name.
     * 
     * @return Full name as a single String
     */
    public String getNombreCompleto() {
        return (apellido + " " + apellido2 + " " + nombre).trim();
    }

    /**
     * Compares if this BankDocument is greater than another BankDocument based on the criterion.
     * 
     * @param otro The other BankDocument to compare against
     * @param criterio 0: "Nombre Completo, Tipo de Documento", 1: "Tipo de Documento, Nombre Completo"
     * @return true if this document is greater than otro, false otherwise
     */
    public boolean esMayor(BankDocument otro, int criterio) {
        if (otro == null) {
            return true;
        }

        if (criterio == 0) {
            // Criterio 0: Compare by Full Name, then by Document
            int compNombre = this.getNombreCompleto().compareToIgnoreCase(otro.getNombreCompleto());
            if (compNombre != 0) {
                return compNombre > 0;
            }
            return this.documento.compareToIgnoreCase(otro.documento) > 0;

        } else {
            // Criterio 1: Compare by Document, then by Full Name
            int compDoc = this.documento.compareToIgnoreCase(otro.documento);
            if (compDoc != 0) {
                return compDoc > 0;
            }
            return this.getNombreCompleto().compareToIgnoreCase(otro.getNombreCompleto()) > 0;
        }
    }

    @Override
    public String toString() {
        return "Bank_Documents [apellido=" + apellido + ", apellido2=" + apellido2 + ", nombre=" + nombre
                + ", documento=" + documento + "]";
    }
}
