import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import controllers.BankDocumentController;

/**
 * View class (Swing JFrame UI) in the MVC pattern.
 * Displays the user interface controls and JTable.
 */
public class FrmOrdenamiento extends JFrame {

    // Reference to the Controller layer
    private BankDocumentController controller;

    // UI components
    private JComboBox<String> cmbCriterio;
    private JTextField txtTiempo;
    private JButton btnBuscar;
    private JTextField txtBuscar;
    private JButton btnCargar;
    private JLabel lblRegistros;

    // JTable for displaying the loaded BankDocument records
    private JTable tblDocumentos;

    private String[] encabezados = new String[] {
            "#", "Primer Apellido", "Segundo Apellido", "Nombres", "Documento"
    };

    public FrmOrdenamiento() {
        // Line 1: Initialize the Controller instance for handling business logic
        controller = new BankDocumentController();

        // Line 2: Create the toolbar container for buttons and controls
        JToolBar tbOrdenamiento = new JToolBar();
        btnCargar = new JButton();
        JButton btnOrdenarBurbuja = new JButton();
        JButton btnOrdenarInsercion = new JButton();
        JButton btnOrdenarMezcla = new JButton();
        JButton btnOrdenarRapido = new JButton();
        cmbCriterio = new JComboBox<>();
        txtTiempo = new JTextField();

        btnBuscar = new JButton();
        txtBuscar = new JTextField();
        lblRegistros = new JLabel(" Total registros: 0");

        // Line 3: Create the JTable UI component
        tblDocumentos = new JTable();

        // Line 4: Create a DefaultTableModel using the defined column headers constant
        var dtm = new DefaultTableModel(null, encabezados);

        // Line 5: Attach the table model to the JTable
        tblDocumentos.setModel(dtm);

        // Line 6: Wrap the JTable inside a scroll pane so user can scroll through long
        // datasets
        JScrollPane spDocumentos = new JScrollPane(tblDocumentos);

        // Window configurations
        setSize(700, 400);
        setTitle("Ordenamiento Documentos");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Configure "Cargar Datos" button
        btnCargar.setText("Cargar Datos");
        btnCargar.setToolTipText("Cargar datos desde archivo CSV");
        btnCargar.addActionListener(evt -> cargarDatos());
        tbOrdenamiento.add(btnCargar);

        // Configure sorting action buttons
        btnOrdenarBurbuja.setIcon(new ImageIcon(getClass().getResource("/iconos/Ordenar.png")));
        btnOrdenarBurbuja.setToolTipText("Ordenar Burbuja");
        btnOrdenarBurbuja.addActionListener(evt -> {
            btnOrdenarBurbuja();
        });
        tbOrdenamiento.add(btnOrdenarBurbuja);

        btnOrdenarRapido.setIcon(new ImageIcon(getClass().getResource("/iconos/OrdenarRapido.png")));
        btnOrdenarRapido.setToolTipText("Ordenar Rapido");
        btnOrdenarRapido.addActionListener(evt -> {
            btnOrdenarRapido();
        });
        tbOrdenamiento.add(btnOrdenarRapido);

        btnOrdenarMezcla.setIcon(new ImageIcon(getClass().getResource("/iconos/OrdenarMezcla.png")));
        btnOrdenarMezcla.setToolTipText("Ordenar Mezcla");
        btnOrdenarMezcla.addActionListener(evt -> {
            btnOrdenarMezcla();
        });
        tbOrdenamiento.add(btnOrdenarMezcla);

        btnOrdenarInsercion.setIcon(new ImageIcon(getClass().getResource("/iconos/OrdenarInsercion.png")));
        btnOrdenarInsercion.setToolTipText("Ordenar Inserción");
        btnOrdenarInsercion.addActionListener(evt -> {
            btnOrdenarInsercion();
        });
        tbOrdenamiento.add(btnOrdenarInsercion);

        cmbCriterio.setModel(new DefaultComboBoxModel<String>(
                new String[] { "Nombre Completo, Tipo de Documento", "Tipo de Documento, Nombre Completo" }));
        tbOrdenamiento.add(cmbCriterio);
        tbOrdenamiento.add(txtTiempo);

        btnBuscar.setIcon(new ImageIcon(getClass().getResource("/iconos/Buscar.png")));
        btnBuscar.setToolTipText("Buscar");
        btnBuscar.addActionListener(evt -> {
            btnBuscar();
        });
        tbOrdenamiento.add(btnBuscar);
        tbOrdenamiento.add(txtBuscar);

        // Layout components inside the frame
        getContentPane().add(tbOrdenamiento, java.awt.BorderLayout.NORTH);
        getContentPane().add(spDocumentos, java.awt.BorderLayout.CENTER);
        getContentPane().add(lblRegistros, java.awt.BorderLayout.SOUTH);
    }

    // --- ENCAPSULATION GETTERS AND SETTERS ---

    public BankDocumentController getController() {
        return controller;
    }

    public void setController(BankDocumentController controller) {
        this.controller = controller;
    }

    public JComboBox<String> getCmbCriterio() {
        return cmbCriterio;
    }

    public void setCmbCriterio(JComboBox<String> cmbCriterio) {
        this.cmbCriterio = cmbCriterio;
    }

    public JTextField getTxtTiempo() {
        return txtTiempo;
    }

    public void setTxtTiempo(JTextField txtTiempo) {
        this.txtTiempo = txtTiempo;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public void setBtnBuscar(JButton btnBuscar) {
        this.btnBuscar = btnBuscar;
    }

    public JTextField getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(JTextField txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public JButton getBtnCargar() {
        return btnCargar;
    }

    public void setBtnCargar(JButton btnCargar) {
        this.btnCargar = btnCargar;
    }

    public JLabel getLblRegistros() {
        return lblRegistros;
    }

    public void setLblRegistros(JLabel lblRegistros) {
        this.lblRegistros = lblRegistros;
    }

    public JTable getTblDocumentos() {
        return tblDocumentos;
    }

    public void setTblDocumentos(JTable tblDocumentos) {
        this.tblDocumentos = tblDocumentos;
    }

    public String[] getEncabezados() {
        return encabezados;
    }

    public void setEncabezados(String[] encabezados) {
        this.encabezados = encabezados;
    }

    // --- LOGIC DELEGATION ---

    /**
     * Triggered when user clicks "Cargar Datos".
     * Prompt file selection via Archivo GUI and delegate to controller.
     */
    private void cargarDatos() {
        String rutaArchivo = Archivo.elegirArchivo();
        controller.cargarDocumentos(rutaArchivo);
        controller.mostrar(tblDocumentos);
        lblRegistros.setText(" Total registros: " + controller.getCantidadRegistros());
    }

    private void btnOrdenarBurbuja() {
        int criterio = cmbCriterio.getSelectedIndex();
        if (criterio >= 0) {
            long duracionNs = controller.ordenarBurbuja(criterio);
            controller.mostrar(tblDocumentos);
            txtTiempo.setText(String.format("%.3f ms", duracionNs / 1e6));
        }
    }

    private void btnOrdenarRapido() {
        int criterio = cmbCriterio.getSelectedIndex();
        if (criterio >= 0) {
            long duracionNs = controller.ordenarRapido(criterio);
            controller.mostrar(tblDocumentos);
            txtTiempo.setText(String.format("%.3f ms", duracionNs / 1e6));
        }
    }

    private void btnOrdenarInsercion() {
        int criterio = cmbCriterio.getSelectedIndex();
        if (criterio >= 0) {
            long duracionNs = controller.ordenarInsercion(criterio);
            controller.mostrar(tblDocumentos);
            txtTiempo.setText(String.format("%.3f ms", duracionNs / 1e6));
        }
    }

    private void btnOrdenarMezcla() {
        int criterio = cmbCriterio.getSelectedIndex();
        if (criterio >= 0) {
            long duracionNs = controller.ordenarMezcla(criterio);
            controller.mostrar(tblDocumentos);
            txtTiempo.setText(String.format("%.3f ms", duracionNs / 1e6));
        }
    }

    private void btnBuscar() {
        String texto = txtBuscar.getText();
        int criterio = cmbCriterio.getSelectedIndex();
        if (texto != null && !texto.trim().isEmpty() && criterio >= 0) {
            int pos = controller.buscar(texto, criterio);
            if (pos >= 0) {
                tblDocumentos.setRowSelectionInterval(pos, pos);
                tblDocumentos.scrollRectToVisible(tblDocumentos.getCellRect(pos, 0, true));
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Documento / Nombre no encontrado: " + texto, "Búsqueda", javax.swing.JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}