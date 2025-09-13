package examen.presentacion.examen;

import examen.logica.Estado;
import examen.logica.Prioridad;
import examen.logica.Usuario;
import examen.logica.Proyecto;
import examen.logica.Tarea;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class View implements PropertyChangeListener {
    private JPanel panelProyectos;
    private JPanel panelTareas;
    private JPanel panelPrincipal;
    private JPanel panelCrearProyecto;
    private JTable tablaProyectos;
    private JTable tablaTareas;
    private JButton crearButton;
    private JTextField txtDescripcionProyecto;
    private JComboBox<Usuario> usuariosComboBox;
    private JLabel descripcionLbl;
    private JLabel encargadoLbl;
    private JLabel lblProyectoSeleccionado;
    private JPanel panelCrearTarea;
    private JButton crearTarea;
    private JTextField txtDescripcionTarea;
    private JTextField txtFechaFin;
    private JComboBox<Prioridad> comboPrioridad;
    private JComboBox<Estado> comboEstado;
    private JComboBox<Usuario> comboResponsable;
    private JLabel lblDescripcionTarea;
    private JLabel venceLbl;
    private JLabel prioridadLbl;
    private JLabel estadoLbl;
    private JLabel responsableLbl;

    Controller controller;
    Model model;

    public View() {
        initComponents();

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
                            txtDescripcionTarea.getText(),
                            txtFechaFin.getText(),
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

    private void initComponents() {
        comboPrioridad.setModel(new DefaultComboBoxModel<>(Prioridad.values()));
        comboEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));

        lblProyectoSeleccionado.setText("No hay proyecto seleccionado");

        panelCrearTarea.setVisible(true);
        lblDescripcionTarea.setVisible(false);
        txtDescripcionTarea.setVisible(false);
        venceLbl.setVisible(false);
        txtFechaFin.setVisible(false);
        prioridadLbl.setVisible(false);
        comboPrioridad.setVisible(false);
        estadoLbl.setVisible(false);
        comboEstado.setVisible(false);
        responsableLbl.setVisible(false);
        comboResponsable.setVisible(false);
        crearTarea.setVisible(false);
    }

    private void setupEventListeners() {
        tablaProyectos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablaProyectos.getSelectedRow();
                if (selectedRow >= 0) {
                    TableModelProyectos tableModel = (TableModelProyectos) tablaProyectos.getModel();
                    Proyecto proyectoSeleccionado = tableModel.getRowAt(selectedRow);
                    controller.proyectoSeleccionado(selectedRow);
                } else {
                    controller.proyectoSeleccionado(-1);
                }
            }
        });

        tablaTareas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = tablaTareas.getSelectedRow();
                    if (selectedRow >= 0) {
                        TableModelTareas tableModel = (TableModelTareas) tablaTareas.getModel();
                        Tarea tareaSeleccionada = tableModel.getRowAt(selectedRow);
                        mostrarDialogoEditarTarea(tareaSeleccionada);
                    }
                }
            }
        });
    }

    public JPanel getPanel() {
        return panelPrincipal;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
        setupEventListeners();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()) {
            case Model.PROYECTOS:
                int[] cols = {TableModelProyectos.CODIGO, TableModelProyectos.DESCRIPCION,
                        TableModelProyectos.ENCARGADO, TableModelProyectos.NUMERO_TAREAS};
                tablaProyectos.setModel(new TableModelProyectos(cols, model.getProyectos()));
                tablaProyectos.setRowHeight(30);
                break;

            case Model.USUARIOS_COMBO:
                if (model.getUsuariosParaCombo() != null && !model.getUsuariosParaCombo().isEmpty()) {
                    Usuario[] usuariosArray = model.getUsuariosParaCombo().toArray(new Usuario[0]);

                    usuariosComboBox.setModel(new DefaultComboBoxModel<>(usuariosArray));
                    comboResponsable.setModel(new DefaultComboBoxModel<>(usuariosArray));
                }
                break;

            case Model.PROYECTO_SELECCIONADO:
                if (model.getProyectoSeleccionado() != null &&
                        model.getProyectoSeleccionado().getCodigo() != null &&
                        !model.getProyectoSeleccionado().getCodigo().isEmpty()) {

                    lblProyectoSeleccionado.setText(model.getProyectoSeleccionado().getDescripcion());
                    panelCrearTarea.setVisible(true);
                    lblDescripcionTarea.setVisible(true);
                    txtDescripcionTarea.setVisible(true);
                    venceLbl.setVisible(true);
                    txtFechaFin.setVisible(true);
                    prioridadLbl.setVisible(true);
                    comboPrioridad.setVisible(true);
                    estadoLbl.setVisible(true);
                    comboEstado.setVisible(true);
                    responsableLbl.setVisible(true);
                    comboResponsable.setVisible(true);
                    crearTarea.setVisible(true);
                } else {
                    lblProyectoSeleccionado.setText("No hay proyecto seleccionado");
                    panelCrearTarea.setVisible(false);
                    lblDescripcionTarea.setVisible(false);
                    txtDescripcionTarea.setVisible(false);
                    venceLbl.setVisible(false);
                    txtFechaFin.setVisible(false);
                    prioridadLbl.setVisible(false);
                    comboPrioridad.setVisible(false);
                    estadoLbl.setVisible(false);
                    comboEstado.setVisible(false);
                    responsableLbl.setVisible(false);
                    comboResponsable.setVisible(false);
                    crearTarea.setVisible(false);
                }
                break;

            case Model.TAREAS_PROYECTO:
                int[] colsTareas = {TableModelTareas.NUMERO, TableModelTareas.DESCRIPCION,
                        TableModelTareas.FECHA, TableModelTareas.PRIORIDAD,
                        TableModelTareas.ESTADO, TableModelTareas.RESPONSABLE};
                if (model.getTareasDelProyecto() != null) {
                    tablaTareas.setModel(new TableModelTareas(colsTareas, model.getTareasDelProyecto()));
                    tablaTareas.setRowHeight(25);
                } else {
                    tablaTareas.setModel(new TableModelTareas(colsTareas, new java.util.ArrayList<>()));
                }
                break;
        }
        this.panelPrincipal.revalidate();
        this.panelPrincipal.repaint();
    }

    private void limpiarFormularioProyecto() {
        txtDescripcionProyecto.setText("");
        usuariosComboBox.setSelectedIndex(-1);
    }

    private void limpiarFormularioTarea() {
        txtDescripcionTarea.setText("");
        txtFechaFin.setText("");
        comboPrioridad.setSelectedIndex(0);
        comboEstado.setSelectedIndex(0);
        comboResponsable.setSelectedIndex(-1);
    }

    public void limpiarFormulario() {
        limpiarFormularioProyecto();
        limpiarFormularioTarea();
    }

    private boolean validarFormularioProyecto() {
        if (txtDescripcionProyecto.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelPrincipal, "La descripción del proyecto no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (usuariosComboBox.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un encargado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean validarFormularioTarea() {
        if (txtDescripcionTarea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelPrincipal, "La descripción de la tarea no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtFechaFin.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(panelPrincipal, "La fecha de finalización no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (comboPrioridad.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar una prioridad", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (comboEstado.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un estado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (comboResponsable.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(panelPrincipal, "Debe seleccionar un responsable", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    public void mostrarDialogoEditarTarea(Tarea tarea) {
        if (tarea == null) {
            JOptionPane.showMessageDialog(panelPrincipal, "No hay tarea seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DialogoEditarTarea dialogo = new DialogoEditarTarea();

        dialogo.setTitle("Editando " + tarea.getNumero());
        dialogo.setLocationRelativeTo(panelPrincipal);
        dialogo.configurarTarea(tarea, controller, this);

        dialogo.setVisible(true);
    }
}
