package py.org.fundacionparaguaya.pspserver.web.rest;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.org.fundacionparaguaya.pspserver.reports.dtos.OrganizationFamilyDTO;
import py.org.fundacionparaguaya.pspserver.reports.dtos.ReportDTO;
import py.org.fundacionparaguaya.pspserver.reports.dtos.ReportFiltersDTO;
import py.org.fundacionparaguaya.pspserver.reports.dtos.SnapshotFilterDTO;
import py.org.fundacionparaguaya.pspserver.reports.dtos.FamilySnapshotDTO;
import py.org.fundacionparaguaya.pspserver.reports.services.SnapshotReportManager;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.Snapshot;

/**
 *
 * @author mgonzalez
 *
 */
@RestController
@RequestMapping(value = "/api/v1/reports")
public class SnapshotReportController {

    private SnapshotReportManager familyReportService;

    public SnapshotReportController(SnapshotReportManager familyReportService) {
        this.familyReportService = familyReportService;
    }

    @GetMapping(path = "/family/organizations", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<OrganizationFamilyDTO>> getFamiliesByOrganization(
            @RequestParam(value = "application_id", required = false) Long applicationId,
            @RequestParam(value = "organizations[]", required = false) List<Long> organizations,
            @RequestParam(value = "family_id", required = false) Long familyId,
            @RequestParam(value = "date_from", required = true) String dateFrom,
            @RequestParam(value = "date_to", required = true) String dateTo) {

        SnapshotFilterDTO filters =
                new SnapshotFilterDTO(applicationId, organizations, familyId, dateFrom, dateTo, null);

        List<OrganizationFamilyDTO> families = familyReportService.listFamilyByOrganizationAndCreatedDate(filters);
        return ResponseEntity.ok(families);
    }

    @GetMapping(path = "/family/indicators", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<FamilySnapshotDTO>> getSnapshotsIndicatorsByFamily(
            @RequestParam(value = "application_id", required = false) Long applicationId,
            @RequestParam(value = "organizations[]", required = false) List<Long> organizations,
            @RequestParam(value = "family_id", required = true) Long familyId,
            @RequestParam(value = "date_from", required = true) String dateFrom,
            @RequestParam(value = "date_to", required = true) String dateTo) {

      SnapshotFilterDTO filters = new SnapshotFilterDTO(applicationId, organizations, familyId, dateFrom, dateTo, null);
      List<FamilySnapshotDTO> snapshots = familyReportService.listSnapshotByFamily(filters);
      return ResponseEntity.ok(snapshots);
    }

    @GetMapping(path = "/family/indicators/csv", produces = "application/octet-stream")
    public void generateCSVSnapshotByOrganizationAndCreatedDate(
            @RequestParam(value = "application_id", required = false) Long applicationId,
            @RequestParam(value = "organizations[]", required = false) List<Long> organizations,
            @RequestParam(value = "family_id", required = true) Long familyId,
            @RequestParam(value = "date_from", required = true) String dateFrom,
            @RequestParam(value = "date_to", required = true) String dateTo,
            HttpServletResponse response) throws IOException {

        SnapshotFilterDTO filters =
                new SnapshotFilterDTO(applicationId, organizations, familyId, dateFrom, dateTo, null);
        String csv = familyReportService.generateCSVSnapshotByOrganizationAndCreatedDate(filters);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"snapshots.csv\"");
        response.getWriter().write(csv);
        response.getWriter().close();
    }

    @GetMapping(path = "/snapshots/csv", produces = "application/octet-stream")
    public void downloadSnapshotsCSV(
                                @RequestParam(value = "date_from", required = true) String dateFrom,
                                @RequestParam(value = "date_to", required = true) String dateTo,
                                @RequestParam(value = "application_id", required = false) Long applicationId,
                                @RequestParam(value = "organizations[]", required = false) List<Long> organizations,
                                @RequestParam(value = "survey_id", required = true) Long surveyId,
                                HttpServletResponse response) throws IOException {

        SnapshotFilterDTO filters =
                new SnapshotFilterDTO(applicationId, organizations, null, dateFrom, dateTo, surveyId);
        String csv = familyReportService.downloadSnapshotsCSV(filters);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"snapshots.csv\"");
        response.getWriter().write(csv);
        response.getWriter().close();
    }

    @GetMapping(path = "/family/indicators/json", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ReportDTO> generateJSONSnapshotByOrganizationAndCreatedDate(
            @RequestParam(value = "application_id", required = false) Long applicationId,
            @RequestParam(value = "organizations[]", required = false) List<Long> organizations,
            @RequestParam(value = "family_id", required = false) Long familyId,
            @RequestParam(value = "date_from", required = true) String dateFrom,
            @RequestParam(value = "date_to", required = true) String dateTo) {

        SnapshotFilterDTO filters =
                new SnapshotFilterDTO(applicationId, organizations, familyId, dateFrom, dateTo, null);
        ReportDTO report = familyReportService.getSnapshotsReportByOrganizationAndCreatedDate(filters);
        return ResponseEntity.ok(report);
    }

    @PostMapping(path = "/snapshots/json",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Snapshot>> getSnapshotsByEnhancedFilters(@RequestBody ReportFiltersDTO filters,
                                                                   @AuthenticationPrincipal UserDetailsDTO user) {
        List<Snapshot> snapshots = familyReportService.getSnapshotsJSONByEnhancedFilters(filters, user);
        return ResponseEntity.ok(snapshots);
    }

    @PostMapping(path = "/snapshots/csv",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadCSVReportByEnhancedFilters(@RequestBody ReportFiltersDTO filters,
                                                   @AuthenticationPrincipal UserDetailsDTO user,
                                                   HttpServletResponse response) throws IOException {
        String csv = familyReportService.downloadSnapshotsCSVReportByEnhancedFilters(filters, user);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"snapshots.csv\"");
        response.getWriter().write(csv);
        response.getWriter().close();
    }
}
