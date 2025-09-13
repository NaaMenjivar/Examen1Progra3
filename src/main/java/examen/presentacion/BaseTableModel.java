package examen.presentacion;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public abstract class BaseTableModel<E> extends AbstractTableModel implements javax.swing.table.TableModel {
    protected List<E> rows;
    protected int[] cols;
    protected String[] colNames;

    public BaseTableModel(int[] cols, List<E> rows){
        this.cols=cols;
        this.rows=rows;
        initColNames();
    }

    public int getColumnCount() { return cols.length; }
    public String getColumnName(int col){ return colNames[cols[col]]; }
    public Class<?> getColumnClass(int col){
        switch (cols[col]){
            default: return super.getColumnClass(col);
        }
    }
    public int getRowCount() { return rows.size(); }
    public Object getValueAt(int row, int col) {
        E e = rows.get(row);
        return getPropetyAt(e, col);
    }

    protected abstract Object getPropetyAt(E e, int col);
    public E getRowAt(int row) { return rows.get(row); }
    protected abstract void initColNames();
}
