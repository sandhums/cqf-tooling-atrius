package org.opencds.cqf.tooling.modelinfo.ndhm;

import java.util.HashMap;
import java.util.Map;

import org.hl7.elm_modelinfo.r1.ClassInfo;
import org.hl7.elm_modelinfo.r1.ClassInfoElement;
import org.hl7.elm_modelinfo.r1.ListTypeSpecifier;
import org.hl7.elm_modelinfo.r1.TypeInfo;
import org.hl7.elm_modelinfo.r1.TypeSpecifier;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.opencds.cqf.tooling.modelinfo.Atlas;
import org.opencds.cqf.tooling.modelinfo.ClassInfoBuilder;

public class NDHMClassInfoBuilder extends ClassInfoBuilder {

    private static final String NDHM_PROFILE_URL_PREFIX = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/";

    private final Map<String, StructureDefinition> structureDefinitionsByUrl = new HashMap<>();

    public NDHMClassInfoBuilder(Atlas atlas) {
        super(new NDHMClassInfoSettings(), atlas.getStructureDefinitions());
        for (Resource resource : atlas.getResources().values()) {
            if (resource instanceof StructureDefinition) {
                StructureDefinition sd = (StructureDefinition) resource;
                if (sd.getUrl() != null) {
                    this.structureDefinitionsByUrl.put(sd.getUrl(), sd);
                }
            }
        }
    }

    /**
     * Prefer NDHM constraint profiles by canonical URL. The atlas id index returns FHIR core
     * for duplicate ids such as Patient, Encounter, and Claim.
     */
    @Override
    protected StructureDefinition resolveStructureDefinition(String reference) {
        if (reference != null) {
            String ndhmUrl = reference.startsWith("http") ? reference : NDHM_PROFILE_URL_PREFIX + reference;
            StructureDefinition sd = this.structureDefinitionsByUrl.get(ndhmUrl);
            if (sd != null) {
                return sd;
            }
        }
        return super.resolveStructureDefinition(reference);
    }

    @Override
    protected void innerBuild() {
        // NDHM clinical + actor allowlist (AtriusCommon + common measure actors; no Claim)
        this.buildFor("NDHM", "Patient");
        this.buildFor("NDHM", "Encounter");
        this.buildFor("NDHM", "Practitioner");
        this.buildFor("NDHM", "PractitionerRole");
        this.buildFor("NDHM", "Organization");

        this.buildFor("NDHM", "Condition");
        this.buildFor("NDHM", "Observation");
        this.buildFor("NDHM", "ObservationBodyMeasurement");
        this.buildFor("NDHM", "ObservationGeneralAssessment");
        this.buildFor("NDHM", "ObservationLifestyle");
        this.buildFor("NDHM", "ObservationPhysicalActivity");
        this.buildFor("NDHM", "ObservationVitalSigns");
        this.buildFor("NDHM", "ObservationWomenHealth");

        this.buildFor("NDHM", "Medication");
        this.buildFor("NDHM", "MedicationRequest");

        this.buildFor("NDHM", "Immunization");
        this.buildFor("NDHM", "ImmunizationRecommendation");

        this.buildFor("NDHM", "Procedure");
    }

    @Override
    protected void afterBuild() {
        super.afterBuild();
        fixProfileStructuralBaseTypes("NDHM");
        fixFhirStructureDefinitionContentReferences("NDHM");
    }

    private void fixProfileStructuralBaseTypes(String modelNamespace) {
        for (TypeInfo typeInfo : this.getTypeInfos().values()) {
            if (!(typeInfo instanceof ClassInfo)) {
                continue;
            }
            ClassInfo classInfo = (ClassInfo) typeInfo;
            if (!modelNamespace.equals(classInfo.getNamespace())) {
                continue;
            }
            String baseType = classInfo.getBaseType();
            if (baseType == null) {
                continue;
            }
            if (baseType.equals(modelNamespace + ".BackboneElement") || baseType.equals(modelNamespace + ".Element")) {
                classInfo.setBaseType("FHIR." + classInfo.getName());
            }
        }
    }

    private void fixFhirStructureDefinitionContentReferences(String modelNamespace) {
        for (TypeInfo typeInfo : this.getTypeInfos().values()) {
            if (typeInfo instanceof ClassInfo) {
                ClassInfo classInfo = (ClassInfo) typeInfo;
                for (ClassInfoElement element : classInfo.getElement()) {
                    fixFhirContentReferenceOnElement(modelNamespace, element);
                }
            }
        }
    }

    private void fixFhirContentReferenceOnElement(String modelNamespace, ClassInfoElement element) {
        String elementType = element.getElementType();
        if (elementType != null && elementType.startsWith("http://hl7.org/fhir/StructureDefinition/")) {
            element.setElementType(toModelTypeName(modelNamespace, elementType));
        }
        TypeSpecifier specifier = element.getElementTypeSpecifier();
        if (specifier instanceof ListTypeSpecifier) {
            ListTypeSpecifier listType = (ListTypeSpecifier) specifier;
            String listElementType = listType.getElementType();
            if (listElementType != null && listElementType.startsWith("http://hl7.org/fhir/StructureDefinition/")) {
                listType.setElementType(toModelTypeName(modelNamespace, listElementType));
            }
        }
    }

    private static String toModelTypeName(String modelNamespace, String contentReference) {
        int hashIndex = contentReference.indexOf('#');
        if (hashIndex < 0 || hashIndex == contentReference.length() - 1) {
            return contentReference;
        }
        String path = contentReference.substring(hashIndex + 1);
        String[] parts = path.split("\\.");
        StringBuilder typeName = new StringBuilder(modelNamespace);
        for (String part : parts) {
            typeName.append('.');
            if (!part.isEmpty()) {
                typeName.append(Character.toUpperCase(part.charAt(0)));
                if (part.length() > 1) {
                    typeName.append(part.substring(1));
                }
            }
        }
        return typeName.toString();
    }
}
