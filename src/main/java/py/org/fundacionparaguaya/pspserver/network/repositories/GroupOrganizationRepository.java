package py.org.fundacionparaguaya.pspserver.network.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import py.org.fundacionparaguaya.pspserver.network.entities.GroupOrganizationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity;

import java.util.List;
import java.util.Optional;

public interface GroupOrganizationRepository extends PagingAndSortingRepository<GroupOrganizationEntity, Long>,
                                                JpaSpecificationExecutor<GroupOrganizationEntity> {

    GroupOrganizationEntity findById(Long id);

    Optional<GroupOrganizationEntity> findOneByName(String name);

    List<GroupOrganizationEntity> findAll();

    Page<GroupOrganizationEntity> findAll(Pageable page);
}
