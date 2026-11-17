
package evidence.core;

import java.time.LocalDate;

public class Case {

    private int caseId;
    private String caseTitle;
    private String description;
    private String investigator;
    private LocalDate caseDate;
    private String status;

    public Case(int caseId, String caseTitle,
                String description, String investigator,
                LocalDate caseDate, String status) {

        this.caseId = caseId;
        this.caseTitle = caseTitle.trim();
        this.description = description;
        this.investigator = investigator;
        this.caseDate = caseDate;
        this.status = status;
    }

    public int getCaseId() {
        return caseId;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getInvestigator() {
        return investigator;
    }

    public LocalDate getCaseDate() {
        return caseDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCaseDetails() {
        return "Case ID: " + caseId +
               "\nTitle: " + caseTitle.toUpperCase() +
               "\nInvestigator: " + investigator +
               "\nStatus: " + status;
    }
}
