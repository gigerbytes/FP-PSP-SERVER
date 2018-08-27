package py.org.fundacionparaguaya.pspserver.surveys.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import py.org.fundacionparaguaya.pspserver.common.entities.LocalDateTimeConverter;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.SurveyDefinition;
import py.org.fundacionparaguaya.pspserver.surveys.entities.types.SecondJSONBUserType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by rodrigovillalba on 10/16/17.
 */
@Entity
@Table(name = "survey_version", schema = "data_collect")

public class SurveyVersionEntity implements Serializable {

    @Id
    @GenericGenerator(name = "surveyVersionSequenceGenerator",
    strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
    parameters = {
            @Parameter(name = SequenceStyleGenerator.SCHEMA,
                    value = "data_collect"),
            @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM,
            value = "survey_version_id_seq"),
            @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM,
            value = "1"),
            @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM,
            value = "1") })
    @GeneratedValue(generator = "surveyVersionSequenceGenerator")
    private Long id;

    private Boolean current;

    @Column(name = "survey_definition")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
    }

    public SurveyDefinition getSurveyDefinition() {
        return surveyDefinition;
    }

    public void setSurveyDefinition(SurveyDefinition surveyDefinition) {
        this.surveyDefinition = surveyDefinition;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
