package py.org.fundacionparaguaya.pspserver.surveys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SurveyVersionEntity;

/**
 * Created by pablo lopez on 29/08/2018.
 */
public interface SurveyVersionRepository extends JpaRepository<SurveyVersionEntity, Long>,
        JpaSpecificationExecutor<SurveyVersionEntity> {

}
