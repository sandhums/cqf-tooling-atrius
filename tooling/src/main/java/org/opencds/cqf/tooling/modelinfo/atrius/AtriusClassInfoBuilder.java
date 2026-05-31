package org.opencds.cqf.tooling.modelinfo.atrius;

import java.util.Map;

import org.hl7.fhir.r4.model.StructureDefinition;
import org.opencds.cqf.tooling.modelinfo.ClassInfoBuilder;

/**
 * Builds Atrius profile ClassInfo entries for CQL ModelInfo generation.
 *
 * <p>Register in {@code StructureDefinitionToModelInfo}:
 * {@code else if (modelName.equals("Atrius")) { ... AtriusClassInfoBuilder ... AtriusModelInfoBuilder ... }}
 *
 * <p>Generate with:
 * <pre>
 *   -GenerateMIs -ip=&lt;spec-root&gt; -rp=4.0.1;ndhm.in;atrius -mn=Atrius -mv=0.1.0 -op=&lt;out&gt;
 * </pre>
 *
 * <p>CQL authors use two models (same pattern as QI-Core):
 * <pre>
 *   using FHIR version '4.0.1'
 *   using Atrius version '0.1.0'
 * </pre>
 *
 * <p>Profile ids below must match StructureDefinition.id values from the Atrius IG
 * ({@code atrius.fhir.r4.india}). Sync from {@code input/cql/atrius-profiles.list} when profiles change.
 *
 * <p>Base {@code atrius-condition} and {@code atrius-observation} are omitted intentionally —
 * measures should retrieve subtypes (aligned with Atrius evaluation strategy).
 */
public class AtriusClassInfoBuilder extends ClassInfoBuilder {

    public AtriusClassInfoBuilder(Map<String, StructureDefinition> structureDefinitions) {
        super(new AtriusClassInfoSettings(), structureDefinitions);
    }

    @Override
    protected void innerBuild() {
        // Actors
        this.buildFor("Atrius", "atrius-patient");
        this.buildFor("Atrius", "atrius-practitioner");
        this.buildFor("Atrius", "atrius-practitionerrole");
        this.buildFor("Atrius", "atrius-organization");
        this.buildFor("Atrius", "atrius-location");
        this.buildFor("Atrius", "atrius-relatedperson");

        // Clinical — condition (base atrius-condition omitted; use subtypes)
        this.buildFor("Atrius", "atrius-condition-encounter-diagnosis");
        this.buildFor("Atrius", "atrius-condition-problems-health-concerns");
        this.buildFor("Atrius", "atrius-allergyintolerance");
        this.buildFor("Atrius", "atrius-adverse-event");
        this.buildFor("Atrius", "atrius-bodystructure");
        this.buildFor("Atrius", "atrius-familymemberhistory");
        this.buildFor("Atrius", "atrius-flag");
        this.buildFor("Atrius", "atrius-goal");

        // Encounter & care coordination
        this.buildFor("Atrius", "atrius-encounter");
        this.buildFor("Atrius", "atrius-careplan");
        this.buildFor("Atrius", "atrius-careplan-assess-plan");
        this.buildFor("Atrius", "atrius-careteam");
        this.buildFor("Atrius", "atrius-communication");
        this.buildFor("Atrius", "atrius-communication-not-done");
        this.buildFor("Atrius", "atrius-communicationrequest");
        this.buildFor("Atrius", "atrius-task");
        this.buildFor("Atrius", "atrius-task-rejected");

        // Observation (base atrius-observation omitted; use subtypes)
        this.buildFor("Atrius", "atrius-observation-body-measurement");
        this.buildFor("Atrius", "atrius-observation-general-assessment");
        this.buildFor("Atrius", "atrius-observation-lifestyle");
        this.buildFor("Atrius", "atrius-observation-physical-activity");
        this.buildFor("Atrius", "atrius-observation-vital-signs");
        this.buildFor("Atrius", "atrius-observation-women-health");

        // Diagnostics & imaging
        this.buildFor("Atrius", "atrius-diagnosticreport-lab");
        this.buildFor("Atrius", "atrius-diagnosticreport-note");
        this.buildFor("Atrius", "atrius-imagingstudy");
        this.buildFor("Atrius", "atrius-specimen");

        // Procedures & service requests
        this.buildFor("Atrius", "atrius-procedure");
        this.buildFor("Atrius", "atrius-procedure-not-done");
        this.buildFor("Atrius", "atrius-servicerequest");
        this.buildFor("Atrius", "atrius-servicerequest-not-requested");

        // Medications
        this.buildFor("Atrius", "atrius-medication");
        this.buildFor("Atrius", "atrius-medicationrequest");
        this.buildFor("Atrius", "atrius-medicationrequest-requested");
        this.buildFor("Atrius", "atrius-medicationrequest-prohibited");
        this.buildFor("Atrius", "atrius-medicationstatement");
        this.buildFor("Atrius", "atrius-medicationadministration");
        this.buildFor("Atrius", "atrius-medicationadministration-not-done");
        this.buildFor("Atrius", "atrius-medicationdispense");
        this.buildFor("Atrius", "atrius-medicationdispense-declined");
        this.buildFor("Atrius", "atrius-nutritionorder");

        // Devices
        this.buildFor("Atrius", "atrius-device");
        this.buildFor("Atrius", "atrius-devicerequest");
        this.buildFor("Atrius", "atrius-devicerequest-requested");
        this.buildFor("Atrius", "atrius-devicerequest-prohibited");
        this.buildFor("Atrius", "atrius-deviceusestatement");

        // Immunizations
        this.buildFor("Atrius", "atrius-immunization");
        this.buildFor("Atrius", "atrius-immunization-done");
        this.buildFor("Atrius", "atrius-immunization-not-done");
        this.buildFor("Atrius", "atrius-immunizationevaluation");
        this.buildFor("Atrius", "atrius-immunizationrecommendation");

        // Financial
        this.buildFor("Atrius", "atrius-coverage");
        this.buildFor("Atrius", "atrius-claim");
        this.buildFor("Atrius", "atrius-claimresponse");

        // Other
        this.buildFor("Atrius", "atrius-questionnaireresponse");
        this.buildFor("Atrius", "atrius-substance");
    }

    // TODO: override resolveContentReference if Atrius observation subtypes need
    // content-reference fixups (see USCoreClassInfoBuilder Observation.referenceRange).
}
