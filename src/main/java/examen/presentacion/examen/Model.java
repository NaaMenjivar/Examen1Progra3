package examen.presentacion.examen;

import examen.datos.LectorUsuarios;
import examen.logica.Proyecto;
import examen.logica.Tarea;
import examen.logica.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.beans.PropertyChangeListener;
import examen.presentacion.AbstractModel;

public class Model extends AbstractModel {
    Proyecto proyectoSeleccionado;
    List<Proyecto> proyectos;
    List<Usuario> usuariosParaCombo;
    List<Tarea> tareasDelProyecto;

    public static final String PROYECTO_SELECCIONADO = "proyectoSeleccionado";
    public static final String PROYECTOS = "proyectos";
    public static final String USUARIOS_COMBO = "usuariosCombo";
    public static final String TAREAS_PROYECTO = "tareasProyecto";

    public Model() {
        proyectoSeleccionado = new Proyecto();
        proyectos = new ArrayList<Proyecto>();
        usuariosParaCombo = new ArrayList<Usuario>();
        tareasDelProyecto = new ArrayList<Tarea>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(PROYECTO_SELECCIONADO);
        firePropertyChange(PROYECTOS);
        firePropertyChange(USUARIOS_COMBO);
        firePropertyChange(TAREAS_PROYECTO);
    }

    public Proyecto getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }
    public void setProyectoSeleccionado(Proyecto proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
        firePropertyChange(PROYECTO_SELECCIONADO);
        firePropertyChange(TAREAS_PROYECTO);
    }
    public List<Proyecto> getProyectos() {
        return proyectos;
    }
    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
        firePropertyChange(PROYECTOS);
    }
    public List<Usuario> getUsuariosParaCombo() {
        return usuariosParaCombo;
    }
    public void setUsuariosParaCombo(List<Usuario> usuariosParaCombo) {
        this.usuariosParaCombo = usuariosParaCombo;
        firePropertyChange(USUARIOS_COMBO);
    }
    public List<Tarea> getTareasDelProyecto() {
        return tareasDelProyecto;
    }
    public void setTareasDelProyecto(List<Tarea> tareasDelProyecto) {
        this.tareasDelProyecto = tareasDelProyecto;
        firePropertyChange(TAREAS_PROYECTO);
    }

    public void cargarUsuariosDesdeXML(String rutaArchivo) {
        LectorUsuarios lector = new LectorUsuarios(rutaArchivo);
        List<Usuario> lista = lector.leerUsuarios();
        setUsuariosParaCombo(lista);
    }

}
