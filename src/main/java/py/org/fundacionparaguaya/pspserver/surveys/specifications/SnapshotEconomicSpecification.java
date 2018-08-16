package py.org.fundacionparaguaya.pspserver.surveys.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity_;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity_;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity_;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.security.entities.UserEntity_;
import py.org.fundacionparaguaya.pspserver.surveys.entities.PropertyAttributeEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity_;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotIndicatorEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.StopLightGroup;
import py.org.fundacionparaguaya.pspserver.surveys.mapper.PropertyAttributeSupport;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author mcespedes
 *
 */
@Component
public class SnapshotEconomicSpecification {

    private static final String SHORT_DATE_FORMAT = "dd/MM/yyyy";

    private static final long MONTH_AGO = 12;

    private static PropertyAttributeSupport propertyAttributeSupport;

    private SnapshotEconomicSpecification(PropertyAttributeSupport propertyAttributeSupport) {
        this.propertyAttributeSupport = propertyAttributeSupport;
    }

    public static Specification<SnapshotEconomicEntity> byLoggedUser(UserDetailsDTO user) {
        return (root, query, builder) ->
                builder.and(
                        byApplication(Optional.ofNullable(user)
                                .map(UserDetailsDTO::getApplication)
                                .map(ApplicationDTO::getId)
                                .orElse(null))
                                .toPredicate(root, query, builder),
                        byOrganization(Optional.ofNullable(user)
                                .map(UserDetailsDTO::getOrganization)
                                .map(OrganizationDTO::getId)
                                .orElse(null))
                                .toPredicate(root, query, builder));
    }

    public static Specification<SnapshotEconomicEntity> byApplication(Long applicationId) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (applicationId != null) {
                    //FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
                    predicates.add(cb.equal(root.join("family").join("application").get("id"), applicationId));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byApplications(List<Long> applications) {
        return (root, query, builder) -> {
            if (applications == null) {
                return builder.conjunction();
            }
            Path<Long> applicationIdPath = root.join(SnapshotEconomicEntity_.getFamily())
                    .get(FamilyEntity_.getApplication()).get(ApplicationEntity_.getId());
            return applicationIdPath.in(applications);
        };
    }

    public static Specification<SnapshotEconomicEntity> byOrganization(Long organizationId) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (organizationId == null) {
                return builder.conjunction();
            }
            Expression<Long> organizationIdExpression =
                    root.join(SnapshotEconomicEntity_.getFamily())
                            .join(FamilyEntity_.getOrganization())
                            .get(OrganizationEntity_.getId());

            return builder.equal(organizationIdExpression, organizationId);
        };
    }

    public static Specification<SnapshotEconomicEntity> byOrganizations(List<Long> organizations) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (organizations != null) {
                    //FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
                    predicates.add(root.join("family").join("organization").get("id").in(organizations));
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byUser(Long userId) {
        return (Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (userId == null) {
                return null;
            }
            Expression<Long> userIdExpression = root.join(SnapshotEconomicEntity_.getUser()).get(UserEntity_.getId());
            return builder.equal(userIdExpression, userId);
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtLess2Months() {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {

                LocalDateTime limit = LocalDateTime.now();
                limit = limit.minusMonths(MONTH_AGO).withDayOfMonth(1);

                return cb.and(cb.greaterThan(root.<LocalDateTime>get(SnapshotEconomicEntity_.getCreatedAt()), limit));

            }
        };
    }

    public static Specification<SnapshotEconomicEntity> createdAtBetween2Dates(String dateFrom, String dateTo) {
        return new Specification<SnapshotEconomicEntity>() {
            @Override
            public Predicate toPredicate(Root<SnapshotEconomicEntity> root, CriteriaQuery<?> query,
                    CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();

                if (dateFrom != null && dateTo != null) {

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(SHORT_DATE_FORMAT);

                    predicates.add(cb.greaterThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateFrom, formatter).atStartOfDay()));
                    predicates.add(cb.lessThan(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                            LocalDate.parse(dateTo, formatter).plusDays(1).atStartOfDay()));
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byTimePeriod(Long fromDate, Long toDate) {
        return (root, query, builder) -> {
            if (fromDate != null && toDate != null) {
                return builder.and(
                        builder.greaterThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                                LocalDateTime.ofInstant(Instant.ofEpochMilli(fromDate), ZoneId.of("UTC"))),
                        builder.lessThanOrEqualTo(root.get(SnapshotEconomicEntity_.getCreatedAt()),
                                LocalDateTime.ofInstant(Instant.ofEpochMilli(toDate), ZoneId.of("UTC"))));
            } else {
                return builder.conjunction();
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> byMultipleSnapshots(Boolean multipleSnapshots) {
        return (root, query, builder) -> {
            if (multipleSnapshots == null) {
                return builder.conjunction();
            }

            if (multipleSnapshots) {
                return builder.conjunction();
            } else {
                return builder.conjunction();
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> bySocioeconomicFilters(
            Map<String, List<String>> socioeconomicFilters) {
        return (root, query, builder) -> {
            if (socioeconomicFilters == null) {
                return builder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

//            Join<SnapshotEconomicEntity, SurveyEntity> survey = root.join(SnapshotEconomicEntity_.getSurvey());
//
//            Expression<Map<String, Property>> propertiesMap = survey.get(SurveyEntity_.getSurveyDefinition())
//                    .get(SurveyDefinition_.getSurveySchema())
//                    .get(SurveySchema_.getProperties());
//
//            List<Selection<?>> propertiesList = propertiesMap.getCompoundSelectionItems();
//
//            List<String> economics = propertyAttributeSupport.getPropertyAttributesByGroup(StopLightGroup.ECONOMIC)
//                    .stream()
//                    .map(PropertyAttributeEntity::getPropertySchemaName)
//                    .collect(Collectors.toList());
//
//            socioeconomicFilters.forEach((key, filters) -> {
//
//                if (economics.contains(key)) {
//
//                    for (Selection<?> property : propertiesList) {
//                        if (property.getAlias().equals(key)) {
//
//                            // if property is of number format
//                            if (property.get("format").equals("number")) {
//                                Expression<String> valueStored = root.get(key);
//                                Predicate predicate = builder.and(
//                                        builder.greaterThanOrEqualTo(valueStored, filters.get(0)),
//                                        builder.lessThanOrEqualTo(valueStored, filters.get(1)));
//                                predicates.add(predicate);
//                            }
//
//                            // if property is of dropdown format
//                            if (property.get("format").equals("string")) {
//                                Expression<String> valueStored = root.get(key);
//                                Predicate predicate = valueStored.in(filters);
//                                predicates.add(predicate);
//                            }
//
//                        }
//                    }
//
//
//                }
//
//            });

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public static Specification<SnapshotEconomicEntity> byIndicatorsFilters(
            Map<String, List<String>> indicatorsFilters, String matchQuantifier) {
        return (root, query, builder) -> {
            if (indicatorsFilters == null) {
                return builder.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();

            List<String> indicators = propertyAttributeSupport.getPropertyAttributesByGroup(StopLightGroup.INDICATOR)
                    .stream()
                    .map(PropertyAttributeEntity::getPropertySchemaName)
                    .collect(Collectors.toList());

            indicatorsFilters.forEach((key, colorsFilters) -> {
                Join<SnapshotEconomicEntity, SnapshotIndicatorEntity> snapshotIndicator =
                        root.join(SnapshotEconomicEntity_.getSnapshotIndicator());
                Expression<String> indicatorValue;
                if (indicators.contains(key)) {
                    indicatorValue = snapshotIndicator.get(key);
                    predicates.add(indicatorValue.in(colorsFilters));
                }
//                else {
//                    // Not core indicators should be managed here
//                }
            });

            if (matchQuantifier == null
                    || matchQuantifier.equalsIgnoreCase("ALL")) {
                return builder.and(predicates.toArray(new Predicate[0]));
            } else if (matchQuantifier.equalsIgnoreCase("ANY")) {
                return builder.or(predicates.toArray(new Predicate[0]));
            } else {
                return builder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

    public static Specification<SnapshotEconomicEntity> forFamily(Long familyId) {
        return (root, query, cb) -> {
            if (familyId == null) {
                return null;
            }
            // FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
            return cb.equal(root.join("family").get("familyId"), familyId);
        };
    }

    public static Specification<SnapshotEconomicEntity> forSurvey(Long surveyId) {
        return (root, query, cb) -> {
            if (surveyId == null) {
                return cb.disjunction();
            }
            // FIXME These join expressions are not typesafe and can lead to errors, use metamodels to avoid this
            return cb.equal(root.join("surveyDefinition").get("id"), surveyId);
        };
    }
}
