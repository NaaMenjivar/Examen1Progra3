package examen.presentacion.examen;

import examen.presentacion.BaseTableModel;
import examen.logica.Tarea;

import javax.swing.*;
import java.util.List;
import javax.swing.table.TableModel;

public class TableModelTareas extends BaseTableModel<Tarea> implements TableModel {
    public static int NUMERO=0, DESCRIPCION=1, FECHA=2, PRIORIDAD=3, ESTADO=4, RESPONSABLE=5;

    public TableModelTareas(int[]cols, List<Tarea> rows) {
        super(new int[]{NUMERO, DESCRIPCION, FECHA, PRIORIDAD, ESTADO, RESPONSABLE}, rows);
    }

    @Override
    protected Object getPropetyAt(Tarea t, int col) {
        switch (cols[col]){
            case 0: return t.getNumero();
            case 1: return t.getDescripcion();
            case 2: return t.getFechaFinalizacion();
            case 3: return t.getPrioridad();
            case 4: return t.getEstado();
            case 5: return t.getResponsable();
            default: return null;
        }
    }

    public void initColNames() {
        colNames = new String[]{"Número", "Descripción", "Fecha Finalización", "Prioridad", "Estado", "Responsable"};
    }

    public Class<?> getColumnClass(int col) {
        switch (cols[col]) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return String.class;
            default:
                return Object.class;
        }
    }
}
