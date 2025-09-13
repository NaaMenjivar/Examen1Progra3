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

    private Tarea tareaAEditar;
    private Controller controller;
    private View parentView;

    public DialogoEditarTarea() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void initComponents() {
        comboPrioridad.setModel(new DefaultComboBoxModel<>(Prioridad.values()));
        comboEstado.setModel(new DefaultComboBoxModel<>(Estado.values()));
    }

    public void configurarTarea(Tarea tarea, Controller controller, View parentView) {
        this.tareaAEditar = tarea;
        this.controller = controller;
        this.parentView = parentView;

        comboPrioridad.setSelectedItem(tarea.getPrioridad());
        comboEstado.setSelectedItem(tarea.getEstado());

        pack();
    }

    private void onOK() {
        try {
            Prioridad nuevaPrioridad = (Prioridad) comboPrioridad.getSelectedItem();
            Estado nuevoEstado = (Estado) comboEstado.getSelectedItem();

            controller.editarTarea(tareaAEditar, nuevaPrioridad, nuevoEstado);

            dispose();

            JOptionPane.showMessageDialog(parentView.getPanel(),
                    "Tarea editada exitosamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        dispose();
    }
}
