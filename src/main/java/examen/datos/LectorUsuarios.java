package examen.datos;

import examen.logica.Usuario;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;

public class LectorUsuarios {
    private final String archivo;

    public LectorUsuarios(String archivo) {
        this.archivo = archivo;
    }

    public List<Usuario> leerUsuarios() {
        try (FileInputStream fis = new FileInputStream(archivo)) {
            JAXBContext context = JAXBContext.newInstance(Data.class);
            Unmarshaller um = context.createUnmarshaller();
            Data data = (Data) um.unmarshal(fis);
            return data.getUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
