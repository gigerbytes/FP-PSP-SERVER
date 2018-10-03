package py.org.fundacionparaguaya.pspserver.network.entities;


import com.google.common.base.MoreObjects;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import py.org.fundacionparaguaya.pspserver.common.entities.LocalDateTimeConverter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Entity
@Table(name = "groups_organizations", schema = "ps_network")
public class GroupOrganizationEntity {

    @Id
    @GenericGenerator(
            name = "groupsOrganizationsSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = SequenceStyleGenerator.SCHEMA,
                            value = "ps_network"),
                    @Parameter(name = SequenceStyleGenerator.SEQUENCE_PARAM,
                            value = "organizations_id_seq"),
                    @Parameter(name = SequenceStyleGenerator.INITIAL_PARAM,
                            value = "1"),
                    @Parameter(name = SequenceStyleGenerator.INCREMENT_PARAM,
                            value = "1")
            }
    )
    @GeneratedValue(generator = "groupsOrganizationsSequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @ManyToOne(targetEntity = OrganizationEntity.class)
    @JoinColumn(name = "organization_id")
    private OrganizationEntity organization;

    @OneToMany(mappedBy = "group")
    private List<OrganizationEntity> organizations;

    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = ApplicationEntity.class)
    @JoinColumn(name = "application_id")
    private ApplicationEntity application;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public OrganizationEntity getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationEntity organization) {
        this.organization = organization;
    }

    public List<OrganizationEntity> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationEntity> organizations) {
        this.organizations = organizations;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ApplicationEntity getApplication() {
        return application;
    }

    public void setApplication(ApplicationEntity application) {
        this.application = application;
    }

    @PrePersist
    public void preSave() {
        this.createdDate = LocalDateTime.now(ZoneId.of("GMT+00:00"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (id == null || obj == null || getClass() != obj.getClass()) {
            return false;
        }
        GroupOrganizationEntity toCompare = (GroupOrganizationEntity) obj;
        return id.equals(toCompare.id);
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id.hashCode();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("description", description)
                .add("organization", organization)
                .toString();
    }

    public static GroupOrganizationEntity of() {
        return new GroupOrganizationEntity();
    }
}
