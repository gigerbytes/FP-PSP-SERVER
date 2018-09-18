package py.org.fundacionparaguaya.pspserver.network.services.impl;

import com.google.common.collect.ImmutableMultimap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import py.org.fundacionparaguaya.pspserver.common.exceptions.CustomParameterizedException;
import py.org.fundacionparaguaya.pspserver.common.exceptions.UnknownResourceException;
import py.org.fundacionparaguaya.pspserver.common.pagination.PaginableList;
import py.org.fundacionparaguaya.pspserver.common.pagination.PspPageRequest;
import py.org.fundacionparaguaya.pspserver.config.ApplicationProperties;
import py.org.fundacionparaguaya.pspserver.families.dtos.FamilyFilterDTO;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity;
import py.org.fundacionparaguaya.pspserver.families.services.FamilyService;
import py.org.fundacionparaguaya.pspserver.network.dtos.ApplicationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.DashboardDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.GroupOrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.ApplicationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.GroupOrganizationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity;
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

    private GroupOrganizationRepository groups;
    private GroupOrganizationMapper groupOrganizationMapper;

    public GroupOrganizationServiceImpl(GroupOrganizationRepository groups, GroupOrganizationMapper groupOrganizationMapper) {
        this.groups = groups;
        this.groupOrganizationMapper = groupOrganizationMapper;
    }

    @Override
    public GroupOrganizationDTO addGroupOrganization(GroupOrganizationDTO groupOrganizationDTO) {
        groups
                .findOneByName(groupOrganizationDTO.getName())
                .ifPresent(organization -> {
                    throw new CustomParameterizedException("Organization group already exists",
                            new ImmutableMultimap.Builder<String, String>()
                                    .put("name", organization.getName())
                                    .build()
                                    .asMap());
                });

        GroupOrganizationEntity group = new GroupOrganizationEntity();
        BeanUtils.copyProperties(groupOrganizationDTO, group);


        return groupOrganizationMapper.entityToDto(groups.save(group));
    }


}