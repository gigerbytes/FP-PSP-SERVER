package py.org.fundacionparaguaya.pspserver.network.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import py.org.fundacionparaguaya.pspserver.common.mapper.BaseMapper;
import py.org.fundacionparaguaya.pspserver.network.dtos.GroupOrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.entities.GroupOrganizationEntity;
import py.org.fundacionparaguaya.pspserver.network.entities.OrganizationEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GroupOrganizationMapper implements BaseMapper<GroupOrganizationEntity, GroupOrganizationDTO> {

	private final ModelMapper modelMapper;

	public GroupOrganizationMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}

	@Override
	public List<GroupOrganizationDTO> entityListToDtoList(List<GroupOrganizationEntity> entityList) {
		 return entityList.stream()
	                .filter(Objects::nonNull)
	                .map(this::entityToDto)
	                .collect(Collectors.toList());
	}

	@Override
	public GroupOrganizationDTO entityToDto(GroupOrganizationEntity entity) {
		GroupOrganizationDTO dto = modelMapper.map(entity, GroupOrganizationDTO.class);
	    return dto;
	}

	@Override
	public GroupOrganizationEntity dtoToEntity(GroupOrganizationDTO dto) {
		return modelMapper.map(dto, GroupOrganizationEntity.class);
	}

}
