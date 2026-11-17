
package evidence.core;

import java.io.File;

public class Image extends Evidence {

    public Image(int id, int caseId, String name,
                 String description, File file) {

        super(id, caseId, name, description, file);
    }

    @Override
    public String getEvidenceType() {
        return "Image";
    }
}
