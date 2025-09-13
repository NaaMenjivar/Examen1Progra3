package examen.logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Usuario {
    @XmlID
    private String id;
    private String name;
    private String email;

    public Usuario(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Usuario() {
        this("", "", "");
    }

    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return name;
    }

}
