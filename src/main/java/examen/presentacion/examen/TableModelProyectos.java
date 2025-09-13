package examen.presentacion.examen;

import examen.presentacion.BaseTableModel;
import examen.logica.Proyecto;

import javax.swing.*;
import java.util.List;
import javax.swing.table.TableModel;

public class TableModelProyectos extends BaseTableModel<Proyecto> implements TableModel  {
    public static int CODIGO=0, DESCRIPCION=1, ENCARGADO=2, NUMERO_TAREAS=3;

    public TableModelProyectos(int[] cols, List<Proyecto> rows) {
        super(cols, rows);
    }

    @Override
    protected Object getPropetyAt(Proyecto p, int col) {
        switch (cols[col]){
            case 0: return p.getCodigo();
            case 1: return p.getDescripcion();
            case 2: return p.getEncargado().getNombre();
            case 3: return p.getNumeroTareas();
            default: return null;
        }
    }

    public void initColNames() {
        colNames = new String[4];
        colNames[CODIGO] = "Código";
        colNames[DESCRIPCION] = "Descripción";
        colNames[ENCARGADO] = "Encargado";
        colNames[NUMERO_TAREAS] = "Número de Tareas";
    }

    public Class<?> getColumnClass(int col) {
        switch (cols[col]) {
            case 3:
                return Integer.class;
            default:
                return String.class;
        }
    }
}
