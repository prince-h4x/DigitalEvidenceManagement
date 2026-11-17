
package evidence.core;

import java.time.LocalDateTime;

public class CustodyEntry {

    private int custodyId;
    private int evidenceId;
    private String action;
    private String handledBy;
    private String notes;
    private LocalDateTime entryTime;

    public CustodyEntry(int custodyId, int evidenceId,
                        String action, String handledBy,
                        String notes) {

        this.custodyId = custodyId;
        this.evidenceId = evidenceId;
        this.action = action;
        this.handledBy = handledBy;
        this.notes = notes;
        this.entryTime = LocalDateTime.now();
    }

    public int getCustodyId() {
        return custodyId;
    }

    public int getEvidenceId() {
        return evidenceId;
    }

    public String getAction() {
        return action;
    }

    public String getHandledBy() {
        return handledBy;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return action + " by " + handledBy +
               " at " + entryTime;
    }
}
