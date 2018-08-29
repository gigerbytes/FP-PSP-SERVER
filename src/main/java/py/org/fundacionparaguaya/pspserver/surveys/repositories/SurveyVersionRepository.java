package py.org.fundacionparaguaya.pspserver.surveys.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SurveyEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SurveyVersionEntity;

import java.util.List;

/**
 * Created by pablo lopez on 29/08/2018.
 */
public interface SurveyVersionRepository extends JpaRepository<SurveyVersionEntity, Long>,
        JpaSpecificationExecutor<SurveyVersionEntity> {

}
