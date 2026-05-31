package org.opencds.cqf.tooling.modelinfo.atrius;

import org.opencds.cqf.tooling.modelinfo.ModelInfoSettings;

public class AtriusModelInfoSettings extends ModelInfoSettings {

    public AtriusModelInfoSettings(String version) {
        super("Atrius", version, "https://atrius.in/fhir/r4/atrius-core", "Patient", "birthDate", "atrius",
                "https://nrces.in/ndhm/fhir/r4");
    }
}
