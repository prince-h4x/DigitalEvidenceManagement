
package evidence.dao;

import evidence.core.CustodyEntry;
import evidence.database.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustodyDAO {

    // Add a custody record
    public boolean addCustodyEntry(CustodyEntry entry) {

        String sql = "INSERT INTO custody " +
                "(evidence_id, action, handled_by, notes) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, entry.getEvidenceId());
            ps.setString(2, entry.getAction());
            ps.setString(3, entry.getHandledBy());
            ps.setString(4, entry.getNotes());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Get custody history for specific evidence
    public List<CustodyEntry> getCustodyHistory(int evidenceId) {

        List<CustodyEntry> history = new ArrayList<>();

        String sql = "SELECT * FROM custody " +
                "WHERE evidence_id = ? ORDER BY entry_time";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, evidenceId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Timestamp timestamp = rs.getTimestamp("entry_time");

                    CustodyEntry entry = new CustodyEntry(
                            rs.getInt("custody_id"),
                            rs.getInt("evidence_id"),
                            rs.getString("action"),
                            rs.getString("handled_by"),
                            rs.getString("notes")
                    );

                    history.add(entry);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return history;
    }
}
