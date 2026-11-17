
package evidence.core;

import java.io.File;

public class Document extends Evidence {

    public Document(int id, int caseId, String name,
                    String description, File file) {

        super(id, caseId, name, description, file);
    }

    @Override
    public String getEvidenceType() {
        return "Document";
    }
}
