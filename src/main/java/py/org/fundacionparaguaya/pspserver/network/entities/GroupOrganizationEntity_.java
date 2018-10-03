package py.org.fundacionparaguaya.pspserver.network.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author bsandoval
 */
@StaticMetamodel(GroupOrganizationEntity.class)
public class GroupOrganizationEntity_ {

    private static volatile SingularAttribute<GroupOrganizationEntity, Long> id;

    private static volatile SingularAttribute<GroupOrganizationEntity, String> name;

    private static volatile SingularAttribute<GroupOrganizationEntity, String> code;

    private static volatile SingularAttribute<GroupOrganizationEntity, String> description;

    private static volatile SingularAttribute<GroupOrganizationEntity, String> information;

    private static volatile SingularAttribute<GroupOrganizationEntity, ApplicationEntity> application;

    private static volatile SingularAttribute<GroupOrganizationEntity, OrganizationEntity> organization;

    private GroupOrganizationEntity_() {}

    public static SingularAttribute<GroupOrganizationEntity, Long> getId() {
        return id;
    }

    public static SingularAttribute<GroupOrganizationEntity, String> getName() {
        return name;
    }

    public static SingularAttribute<GroupOrganizationEntity, String> getCode() {
        return code;
    }

    public static SingularAttribute<GroupOrganizationEntity, String> getDescription() {
        return description;
    }

    public static SingularAttribute<GroupOrganizationEntity, String> getInformation() {
        return information;
    }

    public static SingularAttribute<GroupOrganizationEntity, ApplicationEntity> getApplication() {
        return application;
    }

    public static SingularAttribute<GroupOrganizationEntity, OrganizationEntity> getOrganization() {
        return organization;
    }
}