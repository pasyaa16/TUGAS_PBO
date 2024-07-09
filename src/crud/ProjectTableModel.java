package crud;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProjectTableModel extends AbstractTableModel {
    private List<Project> projects;
    private String[] columnNames = {"ID", "Nama Proyek", "Tanggal Mulai", "Tanggal Selesai", "Status"};

    public ProjectTableModel(List<Project> projects) {
        this.projects = projects;
    }

    ProjectTableModel() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return projects == null ? 0 : projects.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Project project = projects.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return project.getId();
            case 1:
                return project.getName();
            case 2:
                return project.getStartDate();
            case 3:
                return project.getEndDate();
            case 4:
                return project.getStatus();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
