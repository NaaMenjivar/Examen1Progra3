package examen.logica;

import examen.logica.Proyecto;
import examen.logica.Tarea;
import examen.logica.Usuario;
import java.util.List;
import java.util.Comparator;
import examen.datos.XmlPersister;
import java.util.stream.Collectors;
import examen.datos.Data;
import examen.datos.XmlPersister;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service() {
        try {
            data= XmlPersister.instance().load();
        }
        catch(Exception e) {
            data = new Data();
        }
    }

    public void stop(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // =============== USUARIOS ===============
    public void createUsuario(Usuario u) throws Exception {
            Usuario result = data.getUsuarios().stream()
                    .filter(i -> i.getId().equals(u.getId()))
                    .findFirst()
                    .orElse(null);
            if (result == null) {
                data.getUsuarios().add(u);
            } else {
                throw new Exception("Usuario ya existe");
            }
    }

    public Usuario readUsuario(Usuario u) throws Exception {
        Usuario result = data.getUsuarios().stream()
                .filter(i -> i.getId().equals(u.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Usuario no existe");
        }
    }

    public List<Usuario> findAllUsuarios() {
        return data.getUsuarios();
    }

    // =============== PROYECTOS ===============
    public void createProyecto(Proyecto p) throws Exception {
            Proyecto result = data.getProyectos().stream()
                    .filter(i -> i.getCodigo().equals(p.getCodigo()))
                    .findFirst()
                    .orElse(null);
            if (result == null) {
                data.getProyectos().add(p);
            } else {
                throw new Exception("Proyecto ya existe");
            }
    }

    public Proyecto readProyecto(Proyecto p) throws Exception {
        Proyecto result = data.getProyectos().stream()
                .filter(i -> i.getCodigo().equals(p.getCodigo()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Proyecto no existe");
        }
    }

    public List<Proyecto> findAllProyectos() {
        return data.getProyectos();
    }

    // =============== TAREAS ===============
    public void createTarea(Tarea t) throws Exception {
            Tarea result = data.getTareas().stream()
                    .filter(i -> i.getNumero().equals(t.getNumero()))
                    .findFirst()
                    .orElse(null);
            if (result == null) {
                data.getTareas().add(t);
            } else {
                throw new Exception("Tarea ya existe");
            }
    }

    public Tarea readTarea(Tarea t) throws Exception {
        Tarea result = data.getTareas().stream()
                .filter(i -> i.getNumero().equals(t.getNumero()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Tarea no existe");
        }
    }

    public List<Tarea> getTareasDeProyecto(Proyecto p) {
        return data.getTareas().stream()
                .filter(t -> t.getResponsable() != null && t.getResponsable().equals(p.getEncargado()))
                .sorted(Comparator.comparing(Tarea::getNumero))
                .collect(Collectors.toList());
    }

    public void addTareaAProyecto(Proyecto p, Tarea t) throws Exception {
        Proyecto proyecto = readProyecto(p);
        Tarea tarea = readTarea(t);
        if (!proyecto.getTareas().contains(tarea)) {
            proyecto.getTareas().add(tarea);
        } else {
            throw new Exception("La tarea ya está asignada al proyecto");
        }
    }

    public void updateTareaDeProyecto(Proyecto proyectoSeleccionado, Tarea t) {
        Proyecto proyecto = data.getProyectos().stream()
                .filter(p -> p.getCodigo().equals(proyectoSeleccionado.getCodigo()))
                .findFirst()
                .orElse(null);
        if (proyecto != null) {
            for (int i = 0; i < proyecto.getTareas().size(); i++) {
                if (proyecto.getTareas().get(i).getNumero().equals(t.getNumero())) {
                    proyecto.getTareas().set(i, t);
                    return;
                }
            }
        }
    }
}
