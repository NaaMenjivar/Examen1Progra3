package examen.presentacion.examen;

import examen.logica.Estado;
import examen.logica.Prioridad;
import examen.logica.Usuario;

import javax.swing.*;

public class View {
    private JPanel panelProyectos;
    private JPanel panelTareas;
    private JPanel panelPrincipal;
    private JPanel panelCrearProyecto;
    private JTable tablaProyectos;
    private JTable tablaTareas;
    private JButton crearButton;
    private JTextField txtDescripcionProyecto;
    private JComboBox usuariosComboBox;
    private JLabel descripcionLbl;
    private JLabel encargadoLbl;
    private JLabel lblProyectoSeleccionado;
    private JPanel panelCrearTarea;
    private JButton crearTarea;
    private JTextField txtDescripcionTarea;
    private JTextField txtFechaFin;
    private JComboBox comboPrioridad;
    private JComboBox comboEstado;
    private JComboBox comboResponsable;
    private JLabel lblDescripcionTarea;
    private JLabel venceLbl;
    private JLabel prioridadLbl;
    private JLabel estadoLbl;
    private JLabel responsableLbl;

    Controller controller;
    Model model;

    public View() {
        crearButton.addActionListener(e -> {
            if (validarFormularioProyecto()) {
                try {
                    controller.crearProyecto(
                            txtDescripcionProyecto.getText(),
                            (Usuario) usuariosComboBox.getSelectedItem()
                    );
                    limpiarFormularioProyecto();
                    JOptionPane.showMessageDialog(panelPrincipal, "PROYECTO CREADO", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        crearTarea.addActionListener(e -> {
            if (validarFormularioTarea()) {
                try {
                    controller.crearTarea(
                            (String) txtDescripcionTarea.getText(),
                            (String) txtFechaFin.getText(),
                            (Prioridad) comboPrioridad.getSelectedItem(),
                            (Estado) comboEstado.getSelectedItem(),
                            (Usuario) comboResponsable.getSelectedItem());
                    limpiarFormularioTarea();
                    JOptionPane.showMessageDialog(panelPrincipal, "TAREA CREADA", "", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(panelPrincipal, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    void initComponents() {
        comboPrioridad.setModel(new DefaultComboBoxModel<>(Prioridad.values()));
        comboEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    private void setupEventListeners(){
        tablaProyectos.getSelectionModel().addListSelectionListener(e -> {})
    }



    private void limpiarFormularioTarea() {
    }

    private boolean validarFormularioTarea() {
    }

    private void limpiarFormularioProyecto() {
    }

    private boolean validarFormularioProyecto() {
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
    }
}
