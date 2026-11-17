
package evidence.core;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Evidence implements Trackable {

    protected int evidenceId;
    protected int caseId;
    protected String evidenceName;
    protected String description;
    protected File file;
    protected LocalDateTime dateAdded;

    protected List<CustodyEntry> custodyHistory =
            new ArrayList<>();

    public Evidence(int evidenceId, int caseId,
                    String evidenceName, String description,
                    File file) {

        this.evidenceId = evidenceId;
        this.caseId = caseId;
        this.evidenceName = evidenceName.trim();
        this.description = description;
        this.file = file;
        this.dateAdded = LocalDateTime.now();
    }

    public int getEvidenceId() {
        return evidenceId;
    }

    public int getCaseId() {
        return caseId;
    }

    public String getEvidenceName() {
        return evidenceName;
    }

    public String getDescription() {
        return description;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file == null ? "No file" : file.getName();
    }

    public abstract String getEvidenceType();

    @Override
    public void addCustodyEntry(CustodyEntry entry) {
        custodyHistory.add(entry);
    }

    @Override
    public String getTrackingDetails() {

        StringBuilder details = new StringBuilder();

        for (CustodyEntry entry : custodyHistory) {
            details.append(entry.toString()).append("\n");
        }

        return details.toString();
    }
}
