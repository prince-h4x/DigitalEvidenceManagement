
package evidence.core;

import java.io.File;

public class Log extends Evidence {

    public Log(int id, int caseId, String name,
               String description, File file) {

        super(id, caseId, name, description, file);
    }

    @Override
    public String getEvidenceType() {
        return "Log";
    }
}
