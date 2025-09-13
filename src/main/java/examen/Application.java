package examen;

import examen.logica.Service;
import examen.presentacion.examen.Controller;
import examen.presentacion.examen.Model;
import examen.presentacion.examen.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {
    public static final Color BACKGROUND_ERROR = new Color(255, 200, 200);

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {

        }

        View view = new View();
        Model model = new Model();
        Controller controller = new Controller(view, model);

        controller.iniciar();

        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setTitle("Sistema de Gestión de Proyectos");
        window.setContentPane(view.getPanel());
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);
        tabbedPane.addTab("Gestión de Proyectos", view.getPanel());

        window.setLocationRelativeTo(null);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Service.instance().stop();
                System.exit(0);
            }
        });

        window.setVisible(true);
    }
}
