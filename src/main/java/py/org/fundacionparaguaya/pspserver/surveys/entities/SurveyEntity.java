package py.org.fundacionparaguaya.pspserver.surveys.entities;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;

import org.modelmapper.ModelMapper;
import py.org.fundacionparaguaya.pspserver.common.entities.LocalDateTimeConverter;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.SurveyOrganizationEntity;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.SurveyDefinition;
import py.org.fundacionparaguaya.pspserver.surveys.entities.types.SecondJSONBUserType;

/**
 * Created by rodrigovillalba on 10/16/17.
 */
@Entity
@Table(name = "surveys", schema = "data_collect")

public class SurveyEntity implements Serializable {

    @Id
    @GenericGenerator(name = "surveysSequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
            @Parameter(name = SequenceStyleGenerator.SCHEMA,
                    value = "data_collect"),
            @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM,
            value = "surveys_id_seq"),
            @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM,
            value = "1"),
            @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM,
            value = "1") })
    @GeneratedValue(generator = "surveysSequenceGenerator")
    private Long id;

    private String title;

    private String description;


    @Transient
    @Type(
         type = "py.org.fundacionparaguaya.pspserver."
                 + "surveys.entities.types.SecondJSONBUserType",
         parameters = {
            @Parameter(name = SecondJSONBUserType.CLASS,
            value = "py.org.fundacionparaguaya.pspserver."
                    + "surveys.dtos.SurveyDefinition") })
    private SurveyDefinition surveyDefinition;

    @Column(name = "created_at")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "survey")
    private List<SurveyOrganizationEntity> surveysOrganizations = new ArrayList<SurveyOrganizationEntity>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "survey_id")
    private List<SurveyVersionEntity> surveyVersionEntityList = new ArrayList<SurveyVersionEntity>();

    public List<SurveyVersionEntity> getSurveyVersionEntityList() {
        return surveyVersionEntityList;
    }

    public void setSurveyVersionEntityList(List<SurveyVersionEntity> surveyVersionEntityList) {
        this.surveyVersionEntityList = surveyVersionEntityList;
    }

    public List<SurveyOrganizationEntity> getSurveysOrganizations() {
        return surveysOrganizations;
    }

    public void setSurveysOrganizations(
            List<SurveyOrganizationEntity> surveysOrganizations) {
        this.surveysOrganizations = surveysOrganizations;
    }

    public SurveyEntity() {
    }

    private SurveyEntity(String title, String description,
            SurveyDefinition definition) {
        this.title = title;
        this.description = description;

        SurveyVersionEntity surveyVersionEntity = new SurveyVersionEntity();
        surveyVersionEntity.setCurrent(true);
        surveyVersionEntity.setSurveyDefinition(definition);
        this.getSurveyVersionEntityList().add(surveyVersionEntity);

    }

    public SurveyEntity(Long surveyId) {
        this.id = surveyId;
    }

    public Long getId() {
        return id;
    }

    public SurveyDefinition getSurveyDefinition() {

        SurveyVersionEntity surveyVersionEntity =  this.getSurveyVersionEntityList().stream()
                .filter(version -> version.getCurrent())
                .findAny()
                .orElse(null);
        if (surveyVersionEntity == null){
            return null;
        }

        return surveyVersionEntity.getSurveyDefinition();
    }

    @JsonIgnore
    public SurveyVersionEntity getCurrentVersion(){
        SurveyVersionEntity surveyVersionEntity =  this.getSurveyVersionEntityList().stream()
                .filter(version -> version.getCurrent()==true)
                .findAny()
                .orElse(null);
        if (surveyVersionEntity == null){
            return null;
        }

        return surveyVersionEntity;
    }

    public void setSurveyDefinition(SurveyDefinition surveyDefinition) {
        this.surveyDefinition = surveyDefinition;
    }

    public static SurveyEntity of(String title, String description,
            SurveyDefinition definition) {
        checkNotNull(definition);
        return new SurveyEntity(title, description, definition);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public List<ApplicationDTO> getApplications() {
        return getSurveysOrganizations()
                .stream()
                .map(SurveyOrganizationEntity::getApplication)
                .filter(Objects::nonNull)
                .distinct()
                .map(application -> new ModelMapper().map(application, ApplicationDTO.class))
                .sorted(Comparator.comparing(ApplicationDTO::getId))
                .collect(Collectors.toList());
    }

    public List<OrganizationDTO> getOrganizations() {
        return getSurveysOrganizations()
                .stream()
                .map(SurveyOrganizationEntity::getOrganization)
                .filter(Objects::nonNull)
                .distinct()
                .map(organization -> new ModelMapper().map(organization, OrganizationDTO.class))
                .sorted(Comparator.comparing(OrganizationDTO::getId))
                .collect(Collectors.toList());
    }

    @PrePersist
    public void preSave() {
        this.createdAt = LocalDateTime.now();
    }


    @Transient
    public String getCreatedAtAsISOString() {
        if (this.createdAt != null) {
            return createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
        return null;
    }


}
