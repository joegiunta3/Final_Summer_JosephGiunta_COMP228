package org.example.db;

import org.example.model.Applicant;

import java.sql.*;

public class ApplicantDAO {

    public int insertApplicant(Applicant applicant) throws SQLException {
        String sql = """
            INSERT INTO Applicant (full_name, address, contact_number, email, education, gender)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, applicant.getFullName());
            ps.setString(2, applicant.getAddress());
            ps.setString(3, applicant.getContactNumber());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getEducation());
            ps.setString(6, applicant.getGender());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        throw new SQLException("Failed to retrieve applicant_id.");
    }
}
