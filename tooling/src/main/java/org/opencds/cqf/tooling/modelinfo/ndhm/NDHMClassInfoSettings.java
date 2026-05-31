package org.opencds.cqf.tooling.modelinfo.ndhm;

import java.util.HashMap;
import java.util.HashSet;

import org.opencds.cqf.tooling.modelinfo.ClassInfoSettings;


/**
 * NDHM (ABDM) ClassInfo settings aligned with {@code USCoreClassInfoSettings}.
 * Same CQL primitive mappings and primary-code-path pattern as US Core generation.
 */
@SuppressWarnings("serial")
class NDHMClassInfoSettings extends ClassInfoSettings {

    public NDHMClassInfoSettings() {
        this.modelName = "NDHM";
        this.modelPrefix = "NDHM";
        this.helpersLibraryName = "FHIRHelpers";
        this.useCQLPrimitives = true;
        this.createSliceElements = true;
        this.flatten = false;

        this.urlToModel.put("https://nrces.in/ndhm/fhir/r4", "NDHM");

        this.codeableTypes = new HashSet<String>() {
            {
                add("System.String");
                add("System.Code");
                add("System.Concept");
            }
        };

        if (this.flatten) {
            this.urlToModel.put("http://hl7.org/fhir", "NDHM");
        }

        if (this.flatten) {
            this.primitiveTypeMappings = new HashMap<String, String>() {
                {
                    put("NDHM.base64Binary", "System.String");
                    put("NDHM.boolean", "System.Boolean");
                    put("NDHM.canonical", "System.String");
                    put("NDHM.code", "System.String");
                    put("NDHM.date", "System.Date");
                    put("NDHM.dateTime", "System.DateTime");
                    put("NDHM.decimal", "System.Decimal");
                    put("NDHM.id", "System.String");
                    put("NDHM.instant", "System.DateTime");
                    put("NDHM.integer", "System.Integer");
                    put("NDHM.markdown", "System.String");
                    put("NDHM.oid", "System.String");
                    put("NDHM.positiveInt", "System.Integer");
                    put("NDHM.string", "System.String");
                    put("NDHM.time", "System.Time");
                    put("NDHM.unsignedInt", "System.Integer");
                    put("NDHM.uri", "System.String");
                    put("NDHM.url", "System.String");
                    put("NDHM.uuid", "System.String");
                    put("NDHM.xhtml", "System.String");
                }
            };
        }
        else {
            this.primitiveTypeMappings = new HashMap<String, String>() {
                {
                    put("FHIR.boolean", "System.Boolean");
                    put("FHIR.date", "System.Date");
                    put("FHIR.dateTime", "System.DateTime");
                    put("FHIR.decimal", "System.Decimal");
                    put("FHIR.integer", "System.Integer");
                    put("FHIR.markdown", "System.String");
                    put("FHIR.string", "System.String");
                    put("FHIR.time", "System.Time");
                    put("FHIR.xhtml", "System.String");
                }
            };
        }

        if (this.flatten) {
            this.cqlTypeMappings = new HashMap<String, String>() {
                {
                    put("NDHM.xsd:base64Binary", "System.String");
                    put("NDHM.base64Binary", "System.String");
                    put("NDHM.xsd:boolean", "System.Boolean");
                    put("NDHM.boolean", "System.Boolean");
                    put("NDHM.canonical", "System.String");
                    put("NDHM.xsd:token", "System.String");
                    put("NDHM.code", "System.String");
                    put("NDHM.xsd:gYear OR xsd:gYearMonth OR xsd:date", "System.Date");
                    put("NDHM.xsd:date", "System.Date");
                    put("NDHM.date", "System.Date");
                    put("NDHM.xsd:gYear OR xsd:gYearMonth OR xsd:date OR xsd:dateTime", "System.DateTime");
                    put("NDHM.dateTime", "System.DateTime");
                    put("NDHM.xsd:decimal OR xsd:double", "System.Decimal");
                    put("NDHM.decimal", "System.Decimal");
                    put("NDHM.id", "System.String");
                    put("NDHM.xsd:dateTime", "System.DateTime");
                    put("NDHM.instant", "System.DateTime");
                    put("NDHM.xsd:int", "System.Integer");
                    put("NDHM.integer", "System.Integer");
                    put("NDHM.markdown", "System.String");
                    put("NDHM.oid", "System.String");
                    put("NDHM.xsd:positiveInteger", "System.Integer");
                    put("NDHM.positiveInt", "System.Integer");
                    put("NDHM.xsd:string", "System.String");
                    put("NDHM.string", "System.String");
                    put("NDHM.xsd:time", "System.Time");
                    put("NDHM.time", "System.Time");
                    put("NDHM.xsd:nonNegativeInteger", "System.Integer");
                    put("NDHM.unsignedInt", "System.Integer");
                    put("NDHM.xsd:anyURI", "System.String");
                    put("NDHM.uri", "System.String");
                    put("NDHM.url", "System.String");
                    put("NDHM.uuid", "System.String");
                    put("NDHM.xhtml:div", "System.String");
                    put("NDHM.xhtml", "System.String");
                    put("NDHM.Coding", "System.Code");
                    put("NDHM.CodeableConcept", "System.Concept");
                    put("NDHM.Period", "Interval<System.DateTime>");
                    put("NDHM.Range", "Interval<System.Quantity>");
                    put("NDHM.Quantity", "System.Quantity");
                    put("NDHM.Age", "System.Quantity");
                    put("NDHM.Distance", "System.Quantity");
                    put("NDHM.SimpleQuantity", "System.Quantity");
                    put("NDHM.Duration", "System.Quantity");
                    put("NDHM.Count", "System.Quantity");
                    put("NDHM.MoneyQuantity", "System.Quantity");
                    put("NDHM.Money", "System.Decimal");
                    put("NDHM.Ratio", "System.Ratio");
                }
            };
        }
        else {
            this.cqlTypeMappings = new HashMap<String, String>() {
                {
                    put("FHIR.xsd:boolean", "System.Boolean");
                    put("FHIR.boolean", "System.Boolean");
                    put("FHIR.xsd:gYear OR xsd:gYearMonth OR xsd:date", "System.Date");
                    put("FHIR.xsd:date", "System.Date");
                    put("FHIR.date", "System.Date");
                    put("FHIR.xsd:gYear OR xsd:gYearMonth OR xsd:date OR xsd:dateTime", "System.DateTime");
                    put("FHIR.xsd:dateTime", "System.DateTime");
                    put("FHIR.dateTime", "System.DateTime");
                    put("FHIR.xsd:decimal OR xsd:double", "System.Decimal");
                    put("FHIR.decimal", "System.Decimal");
                    put("FHIR.xsd:int", "System.Integer");
                    put("FHIR.integer", "System.Integer");
                    put("FHIR.xsd:string", "System.String");
                    put("FHIR.string", "System.String");
                    put("FHIR.xsd:time", "System.Time");
                    put("FHIR.time", "System.Time");
                    put("FHIR.xhtml:div", "System.String");
                    put("FHIR.xhtml", "System.String");
                    put("FHIR.Coding", "System.Code");
                    put("FHIR.CodeableConcept", "System.Concept");
                    put("FHIR.Period", "Interval<System.DateTime>");
                    put("FHIR.Range", "Interval<System.Quantity>");
                    put("FHIR.Quantity", "System.Quantity");
                    put("FHIR.Age", "System.Quantity");
                    put("FHIR.Distance", "System.Quantity");
                    put("FHIR.SimpleQuantity", "System.Quantity");
                    put("FHIR.Duration", "System.Quantity");
                    put("FHIR.Count", "System.Quantity");
                    put("FHIR.MoneyQuantity", "System.Quantity");
                    put("FHIR.Money", "System.Decimal");
                    put("FHIR.Ratio", "System.Ratio");
                }
            };
        }

        this.primaryCodePath = new HashMap<String, String>() {
            {
                put("ActivityDefinition", "topic");
                put("AdverseEvent", "event");
                put("AllergyIntolerance", "code");
                put("Appointment", "serviceType");
                put("Basic", "code");
                put("CarePlan", "category");
                put("CareTeam", "category");
                put("ChargeItemDefinition", "code");
                put("Claim", "type");
                put("ClaimResponse", "type");
                put("ClinicalImpression", "code");
                put("Communication", "topic");
                put("CommunicationRequest", "category");
                put("Composition", "type");
                put("Condition", "code");
                put("Consent", "category");
                put("Coverage", "type");
                put("DetectedIssue", "category");
                put("Device", "type");
                put("DeviceMetric", "type");
                put("DeviceRequest", "codeCodeableConcept");
                put("DeviceUseStatement", "device.code");
                put("DiagnosticReport", "code");
                put("DiagnosticReportImaging", "code");
                put("DiagnosticReportLab", "code");
                put("Encounter", "class");
                put("EpisodeOfCare", "type");
                put("ExplanationOfBenefit", "type");
                put("FamilyMemberHistory", "relationship");
                put("Flag", "code");
                put("Goal", "category");
                put("GuidanceResponse", "module");
                put("HealthcareService", "type");
                put("ImagingStudy", "procedureCode");
                put("Immunization", "vaccineCode");
                put("ImmunizationRecommendation", "recommendation.vaccineCode");
                put("Library", "topic");
                put("Location", "type");
                put("Measure", "topic");
                put("MeasureReport", "measure.topic");
                put("Medication", "code");
                put("MedicationAdministration", "medication");
                put("MedicationDispense", "medication");
                put("MedicationRequest", "medication");
                put("MedicationStatement", "medication");
                put("MessageDefinition", "event");
                put("Observation", "code");
                put("ObservationBodyMeasurement", "code");
                put("ObservationGeneralAssessment", "code");
                put("ObservationLifestyle", "code");
                put("ObservationPhysicalActivity", "code");
                put("ObservationVitalSigns", "code");
                put("ObservationWomenHealth", "code");
                put("OperationOutcome", "issue.code");
                put("Organization", "type");
                put("PractitionerRole", "code");
                put("Procedure", "code");
                put("ProcedureRequest", "code");
                put("Questionnaire", "name");
                put("ReferralRequest", "type");
                put("RelatedPerson", "relationship");
                put("RiskAssessment", "code");
                put("SearchParameter", "target");
                put("Sequence", "type");
                put("ServiceRequest", "code");
                put("Specimen", "type");
                put("Substance", "code");
                put("SupplyDelivery", "type");
                put("SupplyRequest", "category");
                put("Task", "code");
            }
        };
    }
}
