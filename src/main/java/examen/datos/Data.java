package examen.datos;

import examen.logica.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElementWrapper(name = "usuarios")
    @XmlElement(name = "usuario")
    private List<Usuario> usuarios;

    @XmlElementWrapper(name = "proyectos")
    @XmlElement(name = "proyecto")
    private List<Proyecto> proyectos;

    @XmlElementWrapper(name = "tareas")
    @XmlElement(name = "tarea")
    private List<Tarea> tareas;

    public Data() {
        usuarios = new ArrayList<>();
        proyectos = new ArrayList<>();
        tareas = new ArrayList<>();
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }
}
