package crud;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ProjectForm extends JPanel {
    private JTextField txtProjectName;
    private JTextField txtStartDate;
    private JTextField txtEndDate;
    private JTextField txtStatus;
    private JTable table;
    private ProjectTableModel tableModel;
    private ProjectDAO projectDAO;

    public ProjectForm() {
        projectDAO = new ProjectDAO();
        setLayout(new BorderLayout());

        // Panel untuk form input
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama Proyek:"), gbc);
        txtProjectName = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtProjectName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Tanggal Mulai (yyyy-mm-dd):"), gbc);
        txtStartDate = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtStartDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        inputPanel.add(new JLabel("Tanggal Selesai (yyyy-mm-dd):"), gbc);
        txtEndDate = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtEndDate, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        inputPanel.add(new JLabel("Status:"), gbc);
        txtStatus = new JTextField(20);
        gbc.gridx = 1;
        inputPanel.add(txtStatus, gbc);

        // Panel untuk tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton btnAdd = new JButton("Tambah");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProject();
            }
        });
        buttonPanel.add(btnAdd);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProject();
            }
        });
        buttonPanel.add(btnUpdate);

        JButton btnDelete = new JButton("Hapus");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteProject();
            }
        });
        buttonPanel.add(btnDelete);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        inputPanel.add(buttonPanel, gbc);

        // Tambahkan inputPanel ke form
        add(inputPanel, BorderLayout.NORTH);

        // Tabel untuk menampilkan proyek
        tableModel = new ProjectTableModel(projectDAO.getAllProjects());
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Muat data proyek
        loadProjects();
    }

    private void addProject() {
        String name = txtProjectName.getText();
        String startDate = txtStartDate.getText();
        String endDate = txtEndDate.getText();
        String status = txtStatus.getText();
        projectDAO.addProject(new Project(name, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), status));
        loadProjects();
    }

    private void updateProject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int projectId = (int) table.getValueAt(selectedRow, 0);
            String name = txtProjectName.getText();
            String startDate = txtStartDate.getText();
            String endDate = txtEndDate.getText();
            String status = txtStatus.getText();
            projectDAO.updateProject(new Project(projectId, name, java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), status));
            loadProjects();
        }
    }

    private void deleteProject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int projectId = (int) table.getValueAt(selectedRow, 0);
            projectDAO.deleteProject(projectId);
            loadProjects();
        }
    }

    private void loadProjects() {
        List<Project> projects = projectDAO.getAllProjects();
        tableModel.setProjects(projects);
    }
}
