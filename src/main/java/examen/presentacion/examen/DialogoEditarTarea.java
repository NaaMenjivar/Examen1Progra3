package examen.presentacion.examen;

import examen.logica.Estado;
import examen.logica.Prioridad;
import examen.logica.Tarea;

import javax.swing.*;
import java.awt.event.*;

public class DialogoEditarTarea extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox<Prioridad> comboPrioridad;
    private JComboBox<Estado> comboEstado;
    private JLabel nuevaPrioridadLbl;
    private JLabel nuevoEstadoLbl;

    // Referencias para la funcionalidad
    private Tarea tareaAEditar;
    private Controller controller;
    private View parentView;

    public DialogoEditarTarea() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // Inicializar combo boxes
        initComponents();

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initComponents() {
        // Inicializar combo boxes con los valores de los enums
        comboPrioridad.setModel(new DefaultComboBoxModel<>(Prioridad.values()));
        comboEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));
    }

    // Método para configurar la tarea a editar
    public void configurarTarea(Tarea tarea, Controller controller, View parentView) {
        this.tareaAEditar = tarea;
        this.controller = controller;
        this.parentView = parentView;

        // Establecer los valores actuales de la tarea
        comboPrioridad.setSelectedItem(tarea.getPrioridad());
        comboEstado.setSelectedItem(tarea.getEstado());

        // Configurar el tamaño y posición
        pack();
    }

    private void onOK() {
        try {
            // Obtener los nuevos valores seleccionados
            Prioridad nuevaPrioridad = (Prioridad) comboPrioridad.getSelectedItem();
            Estado nuevoEstado = (Estado) comboEstado.getSelectedItem();

            // Llamar al controlador para editar la tarea
            controller.editarTarea(tareaAEditar, nuevaPrioridad, nuevoEstado);

            // Cerrar el diálogo
            dispose();

            // Mostrar mensaje de éxito
            JOptionPane.showMessageDialog(parentView.getPanel(),
                    "Tarea editada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            // Mostrar mensaje de error
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        // Solo cerrar el diálogo sin hacer cambios
        dispose();
    }
}
