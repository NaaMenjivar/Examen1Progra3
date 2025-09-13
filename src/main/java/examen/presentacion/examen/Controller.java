package examen.presentacion.examen;

import examen.logica.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);

        cargarDatosIniciales();
        iniciar();
    }

    private void cargarDatosIniciales() {
        try {
            List<Usuario> usuarios = Service.instance().findAllUsuarios();
            model.setUsuariosParaCombo(usuarios);

            List<Proyecto> proyectos = Service.instance().findAllProyectos();
            model.setProyectos(proyectos);

            model.setProyectoSeleccionado(new Proyecto());
            model.setTareasDelProyecto(null);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void cargarDatos() {
        if (model.getProyectoSeleccionado() != null && model.getProyectoSeleccionado().getCodigo() != null && !model.getProyectoSeleccionado().getCodigo().isEmpty()) {
            try {
                Proyecto p = Service.instance().readProyecto(model.getProyectoSeleccionado());
                model.setProyectoSeleccionado(p);
                model.setTareasDelProyecto(p.getTareas());
            } catch (Exception ex) {
                model.setProyectoSeleccionado(new Proyecto());
                model.setTareasDelProyecto(null);
            }
        } else {
            model.setProyectoSeleccionado(new Proyecto());
            model.setTareasDelProyecto(null);
        }
    }

    public void crearProyecto(String descripcion, Usuario encargado) throws Exception {
        Proyecto p = new Proyecto();
        p.setCodigo(p.generarCodigo());
        p.setDescripcion(descripcion);
        p.setEncargado(encargado);
        p.setTareas(new ArrayList<>());
        Service.instance().createProyecto(p);
        model.setProyectos(Service.instance().findAllProyectos());
    }

    public void crearTarea(String descripcion, String fechaFinalizacion, Prioridad prioridad, Estado estado, Usuario responsable) throws Exception {
        if (model.getProyectoSeleccionado() != null && model.getProyectoSeleccionado().getCodigo() != null
                && !model.getProyectoSeleccionado().getCodigo().isEmpty()) {
            Tarea t = new Tarea();
            t.setNumero(t.generarNumero());
            t.setDescripcion(descripcion);
            t.setFechaFinalizacion(fechaFinalizacion);
            t.setPrioridad(prioridad);
            t.setEstado(estado);
            t.setResponsable(responsable);
            Service.instance().createTarea(t);
            Service.instance().addTareaAProyecto(model.getProyectoSeleccionado(), t);

            cargarDatos();

        } else {
            throw new Exception("No hay ningún proyecto seleccionado");
        }
    }

    void proyectoSeleccionado(int fila) {
        if (fila >= 0) {
            model.setProyectoSeleccionado(model.getProyectos().get(fila));
        } else {
            model.setProyectoSeleccionado(new Proyecto());
        }
        cargarDatos();
    }

    void editarTarea(Tarea t, Prioridad nuevaPrioridad, Estado nuevoEstado) throws Exception {
        if (t != null && t.getNumero() != null) {
            t.setPrioridad(nuevaPrioridad);
            t.setEstado(nuevoEstado);
            Service.instance().updateTareaDeProyecto(model.getProyectoSeleccionado(), t);
            cargarDatos();
        } else {
            throw new Exception("No hay ninguna tarea seleccionada");
        }
    }
    public void iniciar() {
        model.cargarUsuariosDesdeXML("data.xml");
    }

}
