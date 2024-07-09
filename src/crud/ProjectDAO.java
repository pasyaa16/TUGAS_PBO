package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    public void addProject(Project project) {
        String query = "INSERT INTO projects (project_name, start_date, end_date, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, project.getName());
            pstmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            pstmt.setString(4, project.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        String query = "SELECT * FROM projects";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                projects.add(new Project(
                        rs.getInt("project_id"),
                        rs.getString("project_name"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public void updateProject(Project project) {
        String query = "UPDATE projects SET project_name = ?, start_date = ?, end_date = ?, status = ? WHERE project_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, project.getName());
            pstmt.setDate(2, new java.sql.Date(project.getStartDate().getTime()));
            pstmt.setDate(3, new java.sql.Date(project.getEndDate().getTime()));
            pstmt.setString(4, project.getStatus());
            pstmt.setInt(5, project.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProject(int projectId) {
        String query = "DELETE FROM projects WHERE project_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
