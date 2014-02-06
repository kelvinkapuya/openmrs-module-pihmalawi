package org.openmrs.module.pihmalawi.web.controller;

import java.util.Arrays;

import org.openmrs.api.context.Context;
import org.openmrs.module.pihmalawi.reports.ReportHelper;
import org.openmrs.module.pihmalawi.reports.experimental.historicAppointmentAdherence.SetupAppointmentAdherence;
import org.openmrs.module.pihmalawi.reports.setup.*;
import org.openmrs.module.pihmalawi.reports.setup.outdated.SetupHivWeeklyOutcome;
import org.openmrs.module.pihmalawi.reports.setup.outdated.SetupPreArtMissedAppointment;
import org.openmrs.module.pihmalawi.reports.setup.outdated.SetupPreArtWeekly;
import org.openmrs.module.pihmalawi.reports.setup.outdated.SetupProgramChanges;
import org.openmrs.module.reporting.report.definition.ReportDefinition;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PihReportFormController {

	@RequestMapping("/module/pihmalawi/remove_exposed_dna.form")
	public void removeExposedDna() {
		new SetupHivDnaPcrResults(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_exposed_dna.form")
	public void registerExposedDna() throws Exception {
		new SetupHivDnaPcrResults(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_pih_xsite.form")
	public void removePihXSite() {
		new SetupPihQuarterlyCrossSite(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_pih_xsite.form")
	public void registerPihXSite() throws Exception {
		new SetupPihQuarterlyCrossSite(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_apzu_hiv.form")
	public void removeApzuHiv() {
		new SetupApzuHivIndicators(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_apzu_hiv.form")
	public void registerApzuHiv() throws Exception {
		new SetupApzuHivIndicators(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_tb_register.form")
	public void removeTbRegister() {
		new SetupTbRegister(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_tb_register.form")
	public void registerTbRegister() throws Exception {
		new SetupTbRegister(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_cc_visits.form")
	public void removeCcVisits() {
		new SetupChronicCareVisits(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_cc_visits.form")
	public void registerCcVisits() throws Exception {
		new SetupChronicCareVisits(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_hiv_visits.form")
	public void removeHivVisits() {
		new SetupHivVisits(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_hiv_visits.form")
	public void registerHivVisits() throws Exception {
		new SetupHivVisits(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_hccmissedappointment_lowerneno.form")
	public void removeHccMissedAppointmentLowerNeno() {
		new SetupHccMissedAppointment(new ReportHelper(), false)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_hccmissedappointment_lowerneno.form")
	public void registerHccMissedAppointmentLowerNeno() throws Exception {
		new SetupHccMissedAppointment(new ReportHelper(), false).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_partregister.form")
	public void removePreArtRegister() {
		new SetupPreArtRegister(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_partregister.form")
	public void registerPreArtRegister() throws Exception {
		new SetupPreArtRegister(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_partmissedappointment_lowerneno.form")
	public void removePreArtMissedAppointmentLowerNeno() {
		new SetupPreArtMissedAppointment(new ReportHelper(), false)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_partmissedappointment_lowerneno.form")
	public void registerPreArtMissedAppointmentLowerNeno() throws Exception {
		new SetupPreArtMissedAppointment(new ReportHelper(), false).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_artmissedappointment_lowerneno.form")
	public void removeArtMissedAppointmentLowerNeno() {
		new SetupArtMissedAppointment(new ReportHelper(), false)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_artmissedappointment_lowerneno.form")
	public void registerArtMissedAppointmentLowerNeno() throws Exception {
		new SetupArtMissedAppointment(new ReportHelper(), false).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_partmissedappointment.form")
	public void removePreArtMissedAppointment() {
		new SetupPreArtMissedAppointment(new ReportHelper(), true)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_partmissedappointment.form")
	public void registerPreArtMissedAppointment() throws Exception {
		new SetupPreArtMissedAppointment(new ReportHelper(), true).setup(false);
	}

	@RequestMapping("/module/pihmalawi/register_hccmissedappointment.form")
	public void registerHccMissedAppointment() throws Exception {
		new SetupHccMissedAppointment(new ReportHelper(), true).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_hccmissedappointment.form")
	public void removeHccMissedAppointment() {
		new SetupHccMissedAppointment(new ReportHelper(), true)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/remove_hccregister.form")
	public void removeHccRegister() {
		new SetupHccRegister(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_hccregister.form")
	public void registerHccRegister() throws Exception {
		new SetupHccRegister(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_artregister.form")
	public void removeArtRegister() {
		new SetupArtRegister(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_artregister.form")
	public void registerArtRegister() throws Exception {
		new SetupArtRegister(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_artmissedappointment.form")
	public void removeArtMissedAppointment() {
		new SetupArtMissedAppointment(new ReportHelper(), true)
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_artmissedappointment.form")
	public void registerArtMissedAppointment() throws Exception {
		new SetupArtMissedAppointment(new ReportHelper(), true).setup(false);
	}

	@RequestMapping("/module/pihmalawi/register_hivweeklyoutcome.form")
	public void registerHivWeeklyOutcome() throws Exception {
		new SetupHivWeeklyOutcome(new ReportHelper()).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_hivweeklyoutcome.form")
	public void removeHivWeeklyOutcome() {
		new SetupHivWeeklyOutcome(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_preartweekly.form")
	public void registerPreArtWeekly() throws Exception {
		new SetupPreArtWeekly(new ReportHelper()).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_preartweekly.form")
	public void removePreArtWeekly() {
		new SetupPreArtWeekly(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_weeklyencounter.form")
	public void registerWeeklyEncounter() throws Exception {
		new SetupWeeklyEncounter(new ReportHelper()).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_weeklyencounter.form")
	public void removeWeeklyEncounter() {
		new SetupWeeklyEncounter(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_hivprogramchanges.form")
	public void registerHivProgramChanges() throws Exception {
		new SetupProgramChanges(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_hivprogramchanges.form")
	public void removeHivProgramChanges() {
		new SetupProgramChanges(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_arvquarterly.form")
	public void registerArvQuarterly() throws Exception {
		new SetupArvQuarterly(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_arvquarterly.form")
	public void removeArvQuarterly() {
		new SetupArvQuarterly(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_arvregimen.form")
	public void registerArvRegimen() throws Exception {
		new SetupArvRegimen(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_arvregimen.form")
	public void removeArvRegimen() {
		new SetupArvRegimen(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_hccquarterly.form")
	public void registerHccQuarterly() throws Exception {
		new SetupHccQuarterly(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_hccquarterly.form")
	public void removeHccQuarterly() {
		new SetupHccQuarterly(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_hivdataquality.form")
	public void registerHivDataQuality() throws Exception {
		new SetupHivDataQuality(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_hivdataquality.form")
	public void removeHivDataQuality() {
		new SetupHivDataQuality(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/remove_findpatientstomerge.form")
	public void removeDuplicateHivPatients() {
		new SetupFindPatientsToMergeSoundex(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_findpatientstomerge.form")
	public void registerDuplicateHivPatients() throws Exception {
		new SetupFindPatientsToMergeSoundex(new ReportHelper()).setup();
	}

	// Chronic Care
	@RequestMapping("/module/pihmalawi/remove_chroniccaremissedappointment.form")
	public void removeChronicCareMissedAppointment() {
		new SetupChronicCareMissedAppointment(new ReportHelper())
				.deleteReportElements();
	}

	@RequestMapping("/module/pihmalawi/register_chroniccaremissedappointment.form")
	public void registerChronicCareMissedAppointment() throws Exception {
		new SetupChronicCareMissedAppointment(new ReportHelper()).setup(false);
	}

	@RequestMapping("/module/pihmalawi/remove_chroniccareregister.form")
	public void removeChronicCareRegister() {
		new SetupChronicCareRegister(new ReportHelper()).delete();
	}

	@RequestMapping("/module/pihmalawi/register_chroniccareregister.form")
	public void registerChronicCareRegister() throws Exception {
		new SetupChronicCareRegister(new ReportHelper()).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_chroniccareappadherence.form")
	public void removeChronicCareAppAdherence() {
		new SetupAppointmentAdherence(new ReportHelper(), "adcc", "Chronic Care", null,
				Arrays.asList(Context.getEncounterService().getEncounterType(
						"CHRONIC_CARE_FOLLOWUP")), false).delete();
	}

	@RequestMapping("/module/pihmalawi/register_chroniccareappadherence.form")
	public void registerChronicCareAppAdherence() throws Exception {
		new SetupAppointmentAdherence(new ReportHelper(), "adcc", "Chronic Care", null,
				Arrays.asList(Context.getEncounterService().getEncounterType(
						"CHRONIC_CARE_FOLLOWUP")), false).setup();
	}

	@RequestMapping("/module/pihmalawi/remove_artappadherence.form")
	public void removeArtAppAdherence() {
		new SetupAppointmentAdherence(new ReportHelper(), "adart", "ART", Context
				.getProgramWorkflowService().getProgramByName("HIV program")
				.getWorkflowByName("Treatment status")
				.getStateByName("On antiretrovirals"), Arrays.asList(Context
				.getEncounterService().getEncounterType("ART_INITIAL"), Context
				.getEncounterService().getEncounterType("ART_FOLLOWUP")), false)
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_artappadherence.form")
	public void registerArtAppAdherence() throws Exception {
		new SetupAppointmentAdherence(new ReportHelper(), "adart", "ART", Context
				.getProgramWorkflowService().getProgramByName("HIV program")
				.getWorkflowByName("Treatment status")
				.getStateByName("On antiretrovirals"), Arrays.asList(Context
				.getEncounterService().getEncounterType("ART_INITIAL"), Context
				.getEncounterService().getEncounterType("ART_FOLLOWUP")), false)
				.setup();
	}

	@RequestMapping("/module/pihmalawi/remove_appointments.form")
	public void removeAppointments() {
		new SetupAppointmentsForLocation(new ReportHelper())
				.delete();
	}

	@RequestMapping("/module/pihmalawi/register_appointments.form")
	public void registerAppointments() throws Exception {
		new SetupAppointmentsForLocation(new ReportHelper())
				.setup();
	}

	@RequestMapping("/module/pihmalawi/register_ksregister.form")
	public String registerKsRegister() throws Exception {
		ReportDefinition[] rds = new SetupKsRegister(new ReportHelper()).setup();
		if (rds.length == 1) {
			return "redirect:/module/reporting/run/runReport.form?reportId=" + rds[0].getUuid();
		}
		else {
			return "redirect:/module/reporting/dashboard/index.form";
		}
	}

	@RequestMapping("/module/pihmalawi/remove_ksregister.form")
	public String removeKsRegister() {
		new SetupKsRegister(new ReportHelper()).delete();
		return "redirect:/module/reporting/dashboard/index.form";
	}

	@RequestMapping("/module/pihmalawi/registerArtEncounterExport.form")
	public void registerArtEncounterExport() throws Exception {
		new SetupArtEncounterReport().setup();
	}

	@RequestMapping("/module/pihmalawi/removeArtEncounterExport.form")
	public void removeArtEncounterExport() throws Exception {
		new SetupArtEncounterReport().delete();
	}
}
