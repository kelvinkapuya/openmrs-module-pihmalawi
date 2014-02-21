/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.pihmalawi.reporting.reports;

import org.openmrs.module.pihmalawi.reporting.library.BasePatientDataLibrary;
import org.openmrs.module.pihmalawi.reporting.library.HivCohortDefinitionLibrary;
import org.openmrs.module.pihmalawi.reporting.library.HivPatientDataLibrary;
import org.openmrs.module.reporting.ReportingConstants;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.data.patient.library.BuiltInPatientDataLibrary;
import org.openmrs.module.reporting.dataset.definition.PatientDataSetDefinition;
import org.openmrs.module.reporting.evaluation.parameter.Mapped;
import org.openmrs.module.reporting.evaluation.parameter.Parameter;
import org.openmrs.module.reporting.report.ReportDesign;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArtRegister extends BaseReportManager {

	@Autowired
	private HivCohortDefinitionLibrary hivCohorts;

	@Autowired
	private BuiltInPatientDataLibrary builtInPatientData ;

	@Autowired
	private BasePatientDataLibrary basePatientData ;

	@Autowired
	private HivPatientDataLibrary hivPatientData ;

	public ArtRegister() {}

	@Override
	public String getUuid() {
		return "fa20c1ac-94ea-11e3-96de-0023156365e4";
	}

	@Override
	public String getName() {
		return "ART Register";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public List<Parameter> getParameters() {
		List<Parameter> l = new ArrayList<Parameter>();
		l.add(ReportingConstants.END_DATE_PARAMETER);
		l.add(ReportingConstants.LOCATION_PARAMETER);
		return l;
	}

	@Override
	public ReportDefinition constructReportDefinition() {

		ReportDefinition rd = new ReportDefinition();
		rd.setUuid(getUuid());
		rd.setName(getName());
		rd.setDescription(getDescription());
		rd.setParameters(getParameters());

		PatientDataSetDefinition dsd = new PatientDataSetDefinition();
		dsd.addParameters(getParameters());
		rd.addDataSetDefinition("patients", Mapped.mapStraightThrough(dsd));

		// Rows are defined as all patients who ever have been in the On Antiretrovirals state at the given location
		CohortDefinition everEnrolled = hivCohorts.getEverEnrolledInArtAtLocationByEndDate();
		dsd.addRowFilter(Mapped.mapStraightThrough(everEnrolled));

		addColumn(dsd, "ARV #", hivPatientData.getArvNumberAtLocation());
		addColumn(dsd, "All HCC #s (not filtered)", hivPatientData.getAllHccNumbers());
		addColumn(dsd, "All ARV #s (not filtered)", hivPatientData.getAllArvNumbers());

		addColumn(dsd, "ART initial date", hivPatientData.getFirstArtInitialEncounterDateByEndDate());
		addColumn(dsd, "ART initial location", hivPatientData.getFirstArtInitialEncounterLocationByEndDate());

		addColumn(dsd, "Given name", builtInPatientData.getPreferredGivenName());
		addColumn(dsd, "Last name", builtInPatientData.getPreferredFamilyName());
		addColumn(dsd, "Birthdate", builtInPatientData.getBirthdate());
		addColumn(dsd, "Current Age (yr)", builtInPatientData.getAgeAtEnd());
		addColumn(dsd, "Current Age (mth)", basePatientData.getAgeAtEndInMonths());
		addColumn(dsd, "M/F", builtInPatientData.getGender());
		addColumn(dsd, "Village", basePatientData.getVillage());
		addColumn(dsd, "TA", basePatientData.getTraditionalAuthority());
		addColumn(dsd, "District", basePatientData.getDistrict());

		addColumn(dsd, "Outcome", hivPatientData.getMostRecentHivTreatmentStatusStateAtLocationByEndDate());
		addColumn(dsd, "Outcome change date", hivPatientData.getMostRecentHivTreatmentStatusStateStartDateAtLocationByEndDate());
		addColumn(dsd, "Outcome location", hivPatientData.getMostRecentHivTreatmentStatusStateLocationAtLocationByEndDate());

		addColumn(dsd, "Enrollment date at location (ART or HCC) (not filtered)", hivPatientData.getEarliestOnArvsStateAtLocationByEndDate());
		addColumn(dsd, "1st time enrollment (ART or HCC) (not filtered)", hivPatientData.getEarliestOnArvsStateEnrollmentDateByEndDate());
		addColumn(dsd, "1st time enrollment (ART or HCC) (not filtered) location", hivPatientData.getEarliestOnArvsStateLocationByEndDate());
		addColumn(dsd, "1st time in Pre-ART date", hivPatientData.getEarliestPreArtStateStartDateByEndDate());
		addColumn(dsd, "1st time in Pre-ART location", hivPatientData.getEarliestPreArtStateLocationByEndDate());
		addColumn(dsd, "1st time in Exposed Child date", hivPatientData.getEarliestExposedChildStateStartDateByEndDate());
		addColumn(dsd, "1st time in Exposed Child location", hivPatientData.getEarliestExposedChildStateLocationByEndDate());
		addColumn(dsd, "1st time in ART date", hivPatientData.getEarliestOnArvsStateStartDateByEndDate());
		addColumn(dsd, "1st time in ART location", hivPatientData.getEarliestOnArvsStateLocationByEndDate());

		addColumn(dsd, "ARV start reasons", hivPatientData.getFirstArtInitialReasonForStartingArvs());
		addColumn(dsd, "Start date 1st line ARV", hivPatientData.getLatestFirstLineArvStartDateByEndDate());
		addColumn(dsd, "CD4 count", hivPatientData.getLatestCd4CountValueByEndDate());

		// TODO: Get all "Patient/Village Health Worker" and "Patient/Guardian" relationships, and add the most recent found givenName + " " + familyName + " (Guardian)" if that relationship type
		//addColumn(dsd, "VHW", null);

		// TODO: For 1st 3 and most recent encounter of type ART_FOLLOWUP, PART_FOLLOWUP, EXPOSED_CHILD_FOLLOWUP

		//addColumn(dsd, "Visit # 1 date in HIV (not filtered)", null); // encounterDatetime or (no encounter found)
		//addColumn(dsd, "Visit # 1 loc", null); // location.name or empty
		//addColumn(dsd, "Visit # 1 appt date", null); // appointmentDate.valueDatetime or empty
		//addColumn(dsd, "Visit # 1 type", null); // encounterType.name or empty

		addColumn(dsd, "Last Malawi Antiretroviral drugs received", hivPatientData.getLatestArvDrugsReceivedByEndDate());
		addColumn(dsd, "Last Malawi Antiretroviral drugs received Date", hivPatientData.getLatestArvDrugsReceivedDateByEndDate());
		addColumn(dsd, "Last TB status", hivPatientData.getLatestTbStatusByEndDate());
		addColumn(dsd, "Last TB status Date", hivPatientData.getLatestTbStatusDateByEndDate());
		addColumn(dsd, "Last Malawi ART side effects", hivPatientData.getLatestArtSideEffectsByEndDate());
		addColumn(dsd, "Last Malawi ART side effects Date", hivPatientData.getLatestArtSideEffectsDateByEndDate());

		// TODO: Get all active states in all programs for the patient, and display "programName: stateName (since state.startDate)" where date is yyyy-MM-dd
		//addColumn(dsd, "All Enrollments (not filtered)", null);



		// This formerly created an indicator backing this, and added to period indicator report as "breakdown"

		// 2 output formats:
		//  1. 		HtmlBreakdownDataSetDefinition dsd = new HtmlBreakdownDataSetDefinition();
		//			dsd.setPatientIdentifierType(Context.getPatientService().getPatientIdentifierTypeByName("ARV Number"));
		//			dsd.setHtmlBreakdownPatientRowClassname(ArtRegisterBreakdownRenderer.class.getName());
		//			rendered using Helper.createHtmlBreakdown
		//
		//  2. 		ApzuBMIPatientDataSetDefinition dsd = new ApzuBMIPatientDataSetDefinition();
		//			dsd.setNumericConcept(Context.getConceptService().getConcept(5089));

		// Adherence
		// Name:  ART Register Appointment Adherence
		// Parameters: SD, ED, Loc
		// rowCohort:  Same as ART Register (hivCohorts.getEverEnrolledInArtAtLocationOnDate();)
		// columns:  Defined in:
		//		dsd = new AppointmentAdherencePatientDataSetDefinition();
		//		dsd.setEncounterTypes(Arrays.asList(Metadata.encounterType("ART_FOLLOWUP")));
		//		dsd.setPatientIdentifierType(Context.getPatientService().getPatientIdentifierTypeByName("ARV Number"));
		//		rendered using Helper.createHtmlBreakdown

		// For all locations
		// Same as above, just rowCohort is not limited to a location - ApzuReportElementsArt.artEverEnrolledOnDate(prefix);
		// createHtmlBreakdown(rd, "ART Register For All Locations_");

		return rd;
	}

	@Override
	public List<ReportDesign> constructReportDesigns(ReportDefinition reportDefinition) {
		List<ReportDesign> l = new ArrayList<ReportDesign>();
		l.add(createExcelReportDesign(reportDefinition, null));
		return l;
	}

	@Override
	public String getVersion() {
		return "1.0-SNAPSHOT";
	}
}
