package examen.logica;

import java.util.List;
import java.util.Objects;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import examen.logica.Usuario;
import examen.logica.Prioridad;
import examen.logica.Estado;


@XmlAccessorType(XmlAccessType.FIELD)
public class Tarea {
    private String numero;
    private String descripcion;
    private String fechaFinalizacion;
    private Prioridad prioridad;
    private Estado estado;
    @XmlIDREF
    private Usuario responsable;
    static int contadorNumero;

    public Tarea(String numero, String descripcion, String fechaFinalizacion, Prioridad prioridad, Estado estado, Usuario responsable) {
        this.numero = numero;
        this.descripcion = descripcion;
        this.fechaFinalizacion = fechaFinalizacion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.responsable = responsable;
    }
    public Tarea() {
        this("T" + (++contadorNumero), "", "", Prioridad.BAJA, Estado.ABIERTA, new Usuario());
    }

    public String getNumero() {return numero;}
    public void setNumero(String numero) {this.numero = numero;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public String getFechaFinalizacion() {return fechaFinalizacion;}
    public void setFechaFinalizacion(String fechaFinalizacion) {this.fechaFinalizacion = fechaFinalizacion;}
    public Prioridad getPrioridad() {return prioridad;}
    public void setPrioridad(Prioridad prioridad) {this.prioridad = prioridad;}
    public Estado getEstado() {return estado;}
    public void setEstado(Estado estado) {this.estado = estado;}
    public Usuario getResponsable() {return responsable;}
    public void setResponsable(Usuario responsable) {this.responsable = responsable;}

    public String generarNumero() {
        return "T" + (++contadorNumero);
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
