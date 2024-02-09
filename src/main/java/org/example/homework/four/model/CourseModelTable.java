package org.example.homework.four.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class CourseModelTable extends AbstractTableModel {

    private List<Course> courses = new ArrayList<>();
    private String[] columns = {"ID","TITLE","DURATION","REGISTRATION","RATING"};

    @Override
    public String getColumnName(int column){

        return this.getColumns()[column];
    }

    @Override
    public int getRowCount() {
        return this.getCourses().size();
    }

    @Override
    public int getColumnCount() {
        return this.getColumns().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex){
            case 0:
                return this.getCourses().get(rowIndex).getId();
            case 1:
                return this.getCourses().get(rowIndex).getTitle();
            case 2:
                return this.getCourses().get(rowIndex).getRegistrationNumber();
            case 3:
                return this.getCourses().get(rowIndex).getDuration();
            case 4:
                return this.getCourses().get(rowIndex).getRaiting();
        }
        return null;
    }

    /**
     * Adds a new row to the table
     * @param course to be added to the student list
     */
    public void addRow(Course course){

        this.getCourses().add(course);

        //update the table
        this.fireTableDataChanged();
    }

    /**
     * @param row to be removed from the JTable.
     */

    public void removeRow(int row){
        this.getCourses().remove(row);
        this.fireTableRowsDeleted(row,row);
    }


}
