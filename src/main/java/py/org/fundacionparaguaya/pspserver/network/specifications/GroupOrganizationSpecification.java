package py.org.fundacionparaguaya.pspserver.network.specifications;

import org.springframework.data.jpa.domain.Specification;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.*;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class GroupOrganizationSpecification {

    private static final String ID_ATTRIBUTE = "id";

    private GroupOrganizationSpecification() {}

    public static Specification<GroupOrganizationEntity> byFilter(Long applicationId, Long organizationId) {
        return new Specification<GroupOrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<GroupOrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (applicationId != null) {
                    Join<GroupOrganizationEntity, ApplicationEntity> joinApplication =
                                                                        root.join(GroupOrganizationEntity_.getApplication());
                    Expression<Long> byApplicationId = joinApplication.<Long>get(ID_ATTRIBUTE);
                    predicates.add(cb.equal(byApplicationId, applicationId));
                }

                if (organizationId != null) {

                    Join<GroupOrganizationEntity, OrganizationEntity> joinOrganization =
                            root.join(GroupOrganizationEntity_.getOrganization());
                    Expression<Long> byOrganizationId = joinOrganization.<Long>get(ID_ATTRIBUTE);
                    predicates.add(cb.equal(byOrganizationId, organizationId));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<GroupOrganizationEntity> byLoggedUser(UserDetailsDTO userDetails) {
        return (Root<GroupOrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            ApplicationDTO application = userDetails.getApplication();
            if (application != null) {
                Join<GroupOrganizationEntity, ApplicationEntity> join = root.join(GroupOrganizationEntity_.getApplication());
                Expression<Long> applicationId = join.<Long>get(ApplicationEntity_.getId());
                predicates.add(builder.equal(applicationId, application.getId()));
            }

            OrganizationDTO organization = userDetails.getOrganization();
            if (organization != null) {
                Join<GroupOrganizationEntity, OrganizationEntity> join = root.join(GroupOrganizationEntity_.getOrganization());
                Expression<Long> organizationId = join.<Long>get(OrganizationEntity_.getId());
                predicates.add(builder.equal(organizationId, organization.getId()));
            }

            return builder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

    public static Specification<OrganizationEntity> byFilter(String filter) {
        return (Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (filter == null || filter.isEmpty()) {
                return null;
            }

            Expression<String> organizationName = root.get(OrganizationEntity_.getName());
            Expression<String> organizationCode = root.get(OrganizationEntity_.getCode());
            Expression<String> organizationDescription = root.get(OrganizationEntity_.getDescription());
            Expression<String> organizationInformation = root.get(OrganizationEntity_.getInformation());

            return builder.or(
                    builder.like(builder.lower(organizationName), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(organizationCode), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(organizationDescription), "%" + filter.toLowerCase() + "%"),
                    builder.like(builder.lower(organizationInformation), "%" + filter.toLowerCase() + "%")
            );
        };
    }

    public static Specification<OrganizationEntity> hasId(Long organizationId) {
        return new Specification<OrganizationEntity>() {
            @Override
            public Predicate toPredicate(Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Expression<Long> expression = root.get(OrganizationEntity_.getId());
                return cb.equal(expression, organizationId);
            }
        };
    }

    public static Specification<OrganizationEntity> isActive() {
        return (Root<OrganizationEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            Expression<Boolean> organizationIsActive = root.get(OrganizationEntity_.getIsActive());
            return builder.isTrue(organizationIsActive);
        };
    }
}