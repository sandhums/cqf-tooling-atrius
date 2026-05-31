package org.opencds.cqf.tooling.modelinfo.ndhm;

import org.opencds.cqf.tooling.modelinfo.ModelInfoSettings;

public class NDHMModelInfoSettings extends ModelInfoSettings {

    public NDHMModelInfoSettings(String version) {
        super("NDHM", version, "https://nrces.in/ndhm/fhir/r4", "Patient", "birthDate", "fhir",
                "http://hl7.org/fhir");
    }
}
