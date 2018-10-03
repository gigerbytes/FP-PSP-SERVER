package py.org.fundacionparaguaya.pspserver.network.services.impl;

import com.google.common.collect.ImmutableMultimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import py.org.fundacionparaguaya.pspserver.common.exceptions.CustomParameterizedException;
import py.org.fundacionparaguaya.pspserver.common.exceptions.UnknownResourceException;
import py.org.fundacionparaguaya.pspserver.common.pagination.PaginableList;
import py.org.fundacionparaguaya.pspserver.common.pagination.PspPageRequest;
import py.org.fundacionparaguaya.pspserver.config.ApplicationProperties;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyFilterDTO;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity;
import py.org.fundacionparaguaya.pspserver.families.services.FamilyService;
import py.org.fundacionparaguaya.pspserver.network.dtos.*;
import py.org.fundacionparaguaya.pspserver.network.entities.*;
import py.org.fundacionparaguaya.pspserver.network.mapper.ApplicationMapper;
import py.org.fundacionparaguaya.pspserver.network.mapper.GroupOrganizationMapper;
import py.org.fundacionparaguaya.pspserver.network.mapper.OrganizationMapper;
import py.org.fundacionparaguaya.pspserver.network.repositories.ApplicationRepository;
import py.org.fundacionparaguaya.pspserver.network.repositories.GroupOrganizationRepository;
import py.org.fundacionparaguaya.pspserver.network.repositories.OrganizationRepository;
import py.org.fundacionparaguaya.pspserver.network.services.GroupOrganizationService;
import py.org.fundacionparaguaya.pspserver.network.services.OrganizationService;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.security.services.UserService;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.SnapshotIndicators;
import py.org.fundacionparaguaya.pspserver.surveys.dtos.SurveyData;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotEconomicEntity;
import py.org.fundacionparaguaya.pspserver.surveys.entities.SnapshotIndicatorEntity;
import py.org.fundacionparaguaya.pspserver.surveys.enums.SurveyStoplightEnum;
import py.org.fundacionparaguaya.pspserver.surveys.mapper.SnapshotIndicatorMapper;
import py.org.fundacionparaguaya.pspserver.surveys.repositories.SnapshotEconomicRepository;
import py.org.fundacionparaguaya.pspserver.surveys.services.impl.SnapshotServiceImpl;
import py.org.fundacionparaguaya.pspserver.system.dtos.ImageDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ImageParser;
import py.org.fundacionparaguaya.pspserver.system.services.ImageUploadService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.data.jpa.domain.Specifications.where;
import static py.org.fundacionparaguaya.pspserver.network.specifications.OrganizationSpecification.*;

@Service
public class GroupOrganizationServiceImpl implements GroupOrganizationService {

    private static final Logger LOG = LoggerFactory.getLogger(GroupOrganizationServiceImpl.class);

    private GroupOrganizationRepository groupOrganizationRepository;
    private GroupOrganizationMapper groupOrganizationMapper;
    private OrganizationRepository organizationRepository;
    private OrganizationMapper organizationMapper;
    private ApplicationRepository applicationRepository;
    private ApplicationMapper applicationMapper;

    public GroupOrganizationServiceImpl(GroupOrganizationRepository groupOrganizationRepository, GroupOrganizationMapper groupOrganizationMapper,
                                        OrganizationRepository organizationRepository, OrganizationMapper organizationMapper, ApplicationRepository applicationRepository,
                                        ApplicationMapper applicationMapper) {

        this.groupOrganizationRepository = groupOrganizationRepository;
        this.groupOrganizationMapper = groupOrganizationMapper;
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
        this.applicationRepository = applicationRepository;
        this.applicationMapper = applicationMapper;
    }

    @Override
    public GroupOrganizationDTO addGroupOrganization(GroupOrganizationDTO groupOrganizationDTO) {

        groupOrganizationRepository.findOneByName(groupOrganizationDTO.getName())
                .ifPresent(organization -> {
                    throw new CustomParameterizedException("Organization group already exists",
                            new ImmutableMultimap.Builder<String, String>()
                                    .put("name", organization.getName())
                                    .build()
                                    .asMap());
                });

        GroupOrganizationEntity group = new GroupOrganizationEntity();
        BeanUtils.copyProperties(groupOrganizationDTO, group);

        // organization reference
        OrganizationEntity organization = organizationRepository.findById(groupOrganizationDTO.getOrganization().getId());
        group.setOrganization(organization);

        // application reference
        ApplicationEntity applicationEntity = applicationRepository.findById(groupOrganizationDTO.getApplication().getId());
        group.setApplication(applicationEntity);

        GroupOrganizationEntity master = groupOrganizationRepository.save(group);

        for (OrganizationDTO organizationDTO : groupOrganizationDTO.getOrganizations()) {

            OrganizationEntity org = organizationRepository.findById(organizationDTO.getId());
            org.setGroup(master);
            organizationRepository.save(org);
        }

        return groupOrganizationMapper.entityToDto(master);
    }

    @Override
    public GroupOrganizationDTO updateGroupOrganization(Long groupOrganizationId, GroupOrganizationDTO groupOrganizationDTO) {
        checkArgument(groupOrganizationId > 0, "Argument was %s but expected nonnegative", groupOrganizationId);

        return Optional.ofNullable(
                groupOrganizationRepository.findOne(groupOrganizationId))
                .map(group -> {
                    group.setName(groupOrganizationDTO.getName());
                    group.setDescription(groupOrganizationDTO.getDescription());
                    return groupOrganizationRepository.save(group);
                })
                .map(groupOrganizationMapper::entityToDto)
                .orElseThrow(() -> new UnknownResourceException("Group does not exist"));
    }

    @Override
    public GroupOrganizationDTO getGroupOrganizationById(Long groupOrganizationId) {
        checkArgument(groupOrganizationId > 0, "Argument was %s but expected nonnegative", groupOrganizationId);

        GroupOrganizationEntity group = groupOrganizationRepository.findById(groupOrganizationId);
        GroupOrganizationDTO groupDto = groupOrganizationMapper.entityToDto(group);



        List<OrganizationDTO> organizations = new ArrayList<>();

        for (OrganizationEntity org : group.getOrganizations()) {
            organizations.add(organizationMapper.entityToDto(org));
        }

        groupDto.setOrganizations(organizations);
        groupDto.setOrganization(organizationMapper.entityToDto(organizationRepository.findById(group.getOrganization().getId())));
        groupDto.setApplication(applicationMapper.entityToDto(applicationRepository.findById(group.getApplication().getId())));

        return groupDto;
    }
}