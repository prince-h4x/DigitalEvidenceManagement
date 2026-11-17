
package evidence.dao;

import evidence.core.Case;
import evidence.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CaseDAO {

    // 1. REGISTER A NEW CASE (INSERT)
    public boolean addCase(Case c) {

        String sql = "INSERT INTO cases " +
                "(case_title, description, investigator, case_date, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, c.getCaseTitle());
            ps.setString(2, c.getDescription());
            ps.setString(3, c.getInvestigator());
            ps.setDate(4, Date.valueOf(c.getCaseDate()));
            ps.setString(5, c.getStatus());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. DISPLAY ALL CASES (SELECT)
    public List<Case> getAllCases() {

        List<Case> cases = new ArrayList<>();

        String sql = "SELECT * FROM cases";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Case c = new Case(
                    rs.getInt("case_id"),
                    rs.getString("case_title"),
                    rs.getString("description"),
                    rs.getString("investigator"),
                    rs.getDate("case_date").toLocalDate(),
                    rs.getString("status")
                );

                cases.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cases;
    }

    // 3. UPDATE CASE STATUS (UPDATE)
    public boolean updateCaseStatus(int caseId, String status) {

        String sql = "UPDATE cases SET status = ? WHERE case_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, caseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. DELETE CASE (DELETE)
    public boolean deleteCase(int caseId) {

        String sql = "DELETE FROM cases WHERE case_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, caseId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 5. SEARCH CASE BY ID
    public Case searchCase(int caseId) {

        String sql = "SELECT * FROM cases WHERE case_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, caseId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return new Case(
                        rs.getInt("case_id"),
                        rs.getString("case_title"),
                        rs.getString("description"),
                        rs.getString("investigator"),
                        rs.getDate("case_date").toLocalDate(),
                        rs.getString("status")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
