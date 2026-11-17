
package evidence.dao;

import evidence.core.Evidence;
import evidence.core.Document;
import evidence.core.Image;
import evidence.core.Log;
import evidence.core.EvidenceNotFoundException;
import evidence.database.DBConnection;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EvidenceDAO {

    // 1. ADD EVIDENCE
    public boolean addEvidence(Evidence e) {

        String sql = "INSERT INTO evidence " +
                "(case_id, evidence_name, evidence_type, description, file_path) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, e.getCaseId());
            ps.setString(2, e.getEvidenceName());
            ps.setString(3, e.getEvidenceType());
            ps.setString(4, e.getDescription());
            ps.setString(5,
                e.getFile() == null ? null : e.getFile().getAbsolutePath());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // 2. GET ALL EVIDENCE
    public List<Evidence> getAllEvidence() {

        List<Evidence> list = new ArrayList<>();

        String sql = "SELECT * FROM evidence";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(mapEvidence(rs));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return list;
    }

    // 3. SEARCH EVIDENCE BY ID
    public Evidence searchEvidence(int id)
            throws EvidenceNotFoundException {

        String sql = "SELECT * FROM evidence WHERE evidence_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return mapEvidence(rs);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        throw new EvidenceNotFoundException(
            "Evidence not found with ID: " + id
        );
    }

    // 4. UPDATE EVIDENCE
    public boolean updateEvidence(int id, String name,
                                  String description) {

        String sql = "UPDATE evidence SET evidence_name = ?, " +
                     "description = ? WHERE evidence_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // 5. DELETE EVIDENCE
    public boolean deleteEvidence(int id) {

        String sql = "DELETE FROM evidence WHERE evidence_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // CONVERT DATABASE RECORD INTO JAVA OBJECT
    private Evidence mapEvidence(ResultSet rs)
            throws SQLException {

        int id = rs.getInt("evidence_id");
        int caseId = rs.getInt("case_id");
        String name = rs.getString("evidence_name");
        String type = rs.getString("evidence_type");
        String description = rs.getString("description");

        String path = rs.getString("file_path");
        File file = path == null ? null : new File(path);

        switch (type == null ? "" : type.toLowerCase()) {

            case "document":
                return new Document(id, caseId, name, description, file);

            case "image":
                return new Image(id, caseId, name, description, file);

            case "log":
                return new Log(id, caseId, name, description, file);

            default:
                return new Document(id, caseId, name, description, file);
        }
    }
}
