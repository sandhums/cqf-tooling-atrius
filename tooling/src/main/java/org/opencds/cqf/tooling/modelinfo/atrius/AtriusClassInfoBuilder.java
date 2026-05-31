package org.opencds.cqf.tooling.modelinfo.atrius;

import java.util.Map;

import org.hl7.elm_modelinfo.r1.ChoiceTypeSpecifier;
import org.hl7.elm_modelinfo.r1.ClassInfo;
import org.hl7.elm_modelinfo.r1.ClassInfoElement;
import org.hl7.elm_modelinfo.r1.IntervalTypeSpecifier;
import org.hl7.elm_modelinfo.r1.ListTypeSpecifier;
import org.hl7.elm_modelinfo.r1.NamedTypeSpecifier;
import org.hl7.elm_modelinfo.r1.TypeInfo;
import org.hl7.elm_modelinfo.r1.TypeSpecifier;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.opencds.cqf.tooling.modelinfo.ClassInfoBuilder;

/**
 * CQL allowlist for Atrius CQL libraries. Add profiles when new libraries introduce retrieves
 * or typed parameters — keep NDHM parents in sync.
 */
public class AtriusClassInfoBuilder extends ClassInfoBuilder {

    public AtriusClassInfoBuilder(Map<String, StructureDefinition> structureDefinitions) {
        super(new AtriusClassInfoSettings(), structureDefinitions);
    }

    @Override
    protected void innerBuild() {
        this.buildFor("Atrius", "atrius-patient");
        this.buildFor("Atrius", "atrius-encounter");
        this.buildFor("Atrius", "atrius-practitioner");
        this.buildFor("Atrius", "atrius-practitionerrole");
        this.buildFor("Atrius", "atrius-organization");

        this.buildFor("Atrius", "atrius-condition-encounter-diagnosis");
        this.buildFor("Atrius", "atrius-condition-problems-health-concerns");

        this.buildFor("Atrius", "atrius-observation-body-measurement");
        this.buildFor("Atrius", "atrius-observation-general-assessment");
        this.buildFor("Atrius", "atrius-observation-lifestyle");
        this.buildFor("Atrius", "atrius-observation-physical-activity");
        this.buildFor("Atrius", "atrius-observation-vital-signs");
        this.buildFor("Atrius", "atrius-observation-women-health");

        this.buildFor("Atrius", "atrius-medication");
        this.buildFor("Atrius", "atrius-medicationrequest");
        this.buildFor("Atrius", "atrius-medicationrequest-prohibited");

        this.buildFor("Atrius", "atrius-immunization");
        this.buildFor("Atrius", "atrius-immunization-done");
        this.buildFor("Atrius", "atrius-immunization-not-done");
        this.buildFor("Atrius", "atrius-immunizationrecommendation");

        this.buildFor("Atrius", "atrius-procedure");
        this.buildFor("Atrius", "atrius-procedure-not-done");
    }

    @Override
    protected void afterBuild() {
        super.afterBuild();
        alignNestedElementTypesWithNdhmParent();
    }

    private void alignNestedElementTypesWithNdhmParent() {
        for (TypeInfo typeInfo : this.getTypeInfos().values()) {
            if (!(typeInfo instanceof ClassInfo)) {
                continue;
            }
            ClassInfo classInfo = (ClassInfo) typeInfo;
            if (!"Atrius".equals(classInfo.getNamespace())) {
                continue;
            }
            String baseType = classInfo.getBaseType();
            if (baseType == null || !(baseType.startsWith("NDHM.") || baseType.startsWith("Atrius."))) {
                continue;
            }
            for (ClassInfoElement element : classInfo.getElement()) {
                rewriteAtriusNestedTypeReferences(element);
            }
        }
    }

    private void rewriteAtriusNestedTypeReferences(ClassInfoElement element) {
        String elementType = element.getElementType();
        if (elementType != null && elementType.startsWith("Atrius.") && elementType.contains(".")) {
            element.setElementType("NDHM." + elementType.substring("Atrius.".length()));
        }
        rewriteAtriusNestedTypeReferences(element.getElementTypeSpecifier());
    }

    private void rewriteAtriusNestedTypeReferences(TypeSpecifier specifier) {
        if (specifier == null) {
            return;
        }
        if (specifier instanceof NamedTypeSpecifier) {
            NamedTypeSpecifier namedType = (NamedTypeSpecifier) specifier;
            if ("Atrius".equals(namedType.getNamespace()) && namedType.getName().contains(".")) {
                namedType.setNamespace("NDHM");
            }
            return;
        }
        if (specifier instanceof ListTypeSpecifier) {
            ListTypeSpecifier listType = (ListTypeSpecifier) specifier;
            String listElementType = listType.getElementType();
            if (listElementType != null && listElementType.startsWith("Atrius.") && listElementType.contains(".")) {
                listType.setElementType("NDHM." + listElementType.substring("Atrius.".length()));
            }
            rewriteAtriusNestedTypeReferences(listType.getElementTypeSpecifier());
            return;
        }
        if (specifier instanceof ChoiceTypeSpecifier) {
            ChoiceTypeSpecifier choiceType = (ChoiceTypeSpecifier) specifier;
            for (TypeSpecifier choice : choiceType.getChoice()) {
                rewriteAtriusNestedTypeReferences(choice);
            }
            return;
        }
        if (specifier instanceof IntervalTypeSpecifier) {
            IntervalTypeSpecifier intervalType = (IntervalTypeSpecifier) specifier;
            rewriteAtriusNestedTypeReferences(intervalType.getPointTypeSpecifier());
        }
    }
}
