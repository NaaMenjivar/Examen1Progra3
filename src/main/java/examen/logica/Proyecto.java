package examen.logica;

import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import examen.logica.Usuario;

@XmlAccessorType(XmlAccessType.FIELD)
public class Proyecto {
    @XmlID
    private String codigo;
    private String descripcion;
    @XmlIDREF
    private Usuario encargado;
    List<Tarea> tareas;
    static int contadorCodigo;

    public Proyecto(String codigo, String descripcion, Usuario encargado, List<Tarea> tareas) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.encargado = encargado;
        this.tareas = tareas;
    }
    public Proyecto() {
        this("P" + (++contadorCodigo), "", new Usuario(), List.of());
    }

    public String getCodigo() {return codigo;}
    public void setCodigo(String codigo) {this.codigo = codigo;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public Usuario getEncargado() {return encargado;}
    public void setEncargado(Usuario encargado) {this.encargado = encargado;}
    public List<Tarea> getTareas() {return tareas;}
    public void setTareas(List<Tarea> tareas) {this.tareas = tareas;}

    public String generarCodigo() {
        return "P" + (++contadorCodigo);
    }

    public int getNumeroTareas() {
        return tareas.size();
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
