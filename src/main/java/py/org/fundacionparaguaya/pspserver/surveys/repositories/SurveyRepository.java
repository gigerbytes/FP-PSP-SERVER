package py.org.fundacionparaguaya.pspserver.surveys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SurveyEntity;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by rodrigovillalba on 10/17/17.
 */
public interface SurveyRepository extends JpaRepository<SurveyEntity, Long>,
        JpaSpecificationExecutor<SurveyEntity> {

    SurveyEntity findById(Long id);

    @Query("select distinct s from SurveyEntity s inner join s.surveysOrganizations so where" +
            " so.application.id = ?1 and" +
            " so.organization.id = ?2")
    List<SurveyEntity> findByAppAndOrg(Long appId, Long orgId);
}
