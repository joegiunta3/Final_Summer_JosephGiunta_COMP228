package org.example.db;

import org.example.model.EmploymentApplication;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmploymentApplicationDAO {

    public void insertApplication(EmploymentApplication app) throws SQLException {
        String sql = """
            INSERT INTO EmploymentApplication
            (applicant_id, date_available, desired_position, desired_salary,
             authorized_to_work, has_relatives, relatives_explanation)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, app.getApplicantId());
            ps.setDate(2, java.sql.Date.valueOf(app.getDateAvailable()));
            ps.setString(3, app.getDesiredPosition());
            ps.setBigDecimal(4, new java.math.BigDecimal(app.getDesiredSalary()));
            ps.setBoolean(5, app.isAuthorizedToWork());
            ps.setBoolean(6, app.isHasRelatives());
            ps.setString(7, app.getRelativesExplanation());

            ps.executeUpdate();
        }
    }
}
