package py.org.fundacionparaguaya.pspserver.surveys.dtos;

/*
 * FP-PSP Server
 * A sample API to manage surveys
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
import java.io.Serializable;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import py.org.fundacionparaguaya.pspserver.common.exceptions.CustomParameterizedException;

/**
 * The MODEL SCHEMA definition of the survey
 */
@ApiModel(description = "The MODEL SCHEMA definition of the survey")
@JsonIgnoreProperties(ignoreUnknown = true)
public class SurveySchema implements Serializable {

    @JsonProperty("title")
    private String title = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("required")
    private List<String> required = null;

    @JsonProperty("properties")
    private Map<String, Property> properties = null;

    @JsonProperty("type")
    private String type = Property.TypeEnum.OBJECT.toString();

    @JsonProperty(value = "dependencies", required = false)
    private SurveyData dependencies;

    public SurveySchema title(String title) {
        this.title = title;
        return this;
    }

    public SurveyData getDependencies() {
        return dependencies;
    }

    public void setDependencies(SurveyData dependencies) {
        this.dependencies = dependencies;
    }

    /**
     * The title of this survey
     * @return title
     **/


    @JsonProperty("type")
    @ApiModelProperty(value = "The title of this survey")
    public String getType() {
        return type;
    }


    @JsonProperty("title")
    @ApiModelProperty(value = "The title of this survey")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public SurveySchema description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Text describing this survey
     * @return description
     **/
    @JsonProperty("description")
    @ApiModelProperty(value = "Text describing this survey")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SurveySchema required(List<String> required) {
        this.required = required;
        return this;
    }

    public SurveySchema addRequiredItem(String requiredItem) {
        if (this.required == null) {
            this.required = new ArrayList<String>();
        }
        this.required.add(requiredItem);
        return this;
    }

    /**
     * List of properties are required to be filled
     * @return required
     **/
    @JsonProperty("required")
    @ApiModelProperty(value = "List of properties are required to be filled")
    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public SurveySchema properties(Map<String, Property> properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get properties
     * @return properties
     **/
    @JsonProperty("properties")
    @ApiModelProperty(value = "")
    public Map<String, Property> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Property> properties) {
        this.properties = properties;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SurveySchema surveySchema = (SurveySchema) o;
        return Objects.equals(this.title, surveySchema.title) &&
                Objects.equals(this.description, surveySchema.description) &&
                Objects.equals(this.required, surveySchema.required) &&
                Objects.equals(this.properties, surveySchema.properties) &&
                equalSetOfPropertieValues(this.properties, surveySchema.properties);
    }

    private boolean equalSetOfPropertieValues(Map<String, Property> a, Map<String, Property> b){
        if (a == b)
            return true;
        else if (a == null || b == null)
            return false;

        for(String key : a.keySet()){
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                JsonNode jsonNodeA = objectMapper.valueToTree(a.get(key));
                JsonNode jsonNodeB = objectMapper.valueToTree(b.get(key));
                if (!Objects.equals(jsonNodeA,jsonNodeB)){
                    return false;
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new CustomParameterizedException("Invalid Survey schema");
            }
        }

        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, required, properties);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SurveySchema {\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    required: ").append(toIndentedString(required)).append("\n");
        sb.append("    properties: ").append(toIndentedString(properties)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }

}

