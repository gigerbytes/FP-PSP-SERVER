package py.org.fundacionparaguaya.pspserver.reports.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportFiltersDTO {

    public ReportFiltersDTO() {}

    @JsonProperty("applications")
    private List<Long> applications;

    @JsonProperty("organizations")
    private List<Long> organizations;

    @JsonProperty(value = "survey_id", required = true)
    private Long surveyId;

    @JsonProperty("fromDate")
    private Long fromDate;

    @JsonProperty("toDate")
    private Long toDate;

    @JsonProperty("multipleSnapshots")
    private Boolean multipleSnapshots;

    @JsonProperty("socioeconomicFilters")
    private Map<String, List<String>> socioeconomicFilters;

    @JsonProperty("indicatorsFilters")
    private Map<String, List<String>> indicatorsFilters;

    @JsonProperty("matchQuantifier")
    private String matchQuantifier;

    public List<Long> getApplications() {
        return applications;
    }

    public void setApplications(List<Long> applications) {
        this.applications = applications;
    }

    public List<Long> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Long> organizations) {
        this.organizations = organizations;
    }

    public Long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(Long surveyId) {
        this.surveyId = surveyId;
    }

    public Long getFromDate() {
        return fromDate;
    }

    public void setFromDate(Long fromDate) {
        this.fromDate = fromDate;
    }

    public Long getToDate() {
        return toDate;
    }

    public void setToDate(Long toDate) {
        this.toDate = toDate;
    }

    public Boolean getMultipleSnapshots() {
        return multipleSnapshots;
    }

    public void setMultipleSnapshots(Boolean multipleSnapshots) {
        this.multipleSnapshots = multipleSnapshots;
    }

    public Map<String, List<String>> getSocioeconomicFilters() {
        return socioeconomicFilters;
    }

    public void setSocioeconomicFilters(Map<String, List<String>> socioeconomicFilters) {
        this.socioeconomicFilters = socioeconomicFilters;
    }

    public Map<String, List<String>> getIndicatorsFilters() {
        return indicatorsFilters;
    }

    public void setIndicatorsFilters(Map<String, List<String>> indicatorsFilters) {
        this.indicatorsFilters = indicatorsFilters;
    }

    public String getMatchQuantifier() {
        return matchQuantifier;
    }

    public void setMatchQuantifier(String matchQuantifier) {
        this.matchQuantifier = matchQuantifier;
    }
}