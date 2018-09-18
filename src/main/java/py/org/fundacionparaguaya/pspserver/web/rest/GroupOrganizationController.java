package py.org.fundacionparaguaya.pspserver.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import py.org.fundacionparaguaya.pspserver.common.pagination.PaginableList;
import py.org.fundacionparaguaya.pspserver.common.pagination.PspPageRequest;
import py.org.fundacionparaguaya.pspserver.network.dtos.GroupOrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.dtos.OrganizationDTO;
import py.org.fundacionparaguaya.pspserver.network.services.GroupOrganizationService;
import py.org.fundacionparaguaya.pspserver.network.services.OrganizationService;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/organizations/groups")
public class GroupOrganizationController {

    private static final Logger LOG = LoggerFactory.getLogger(GroupOrganizationController.class);

    private GroupOrganizationService groups;


    @PostMapping()
    public ResponseEntity<GroupOrganizationDTO> addGroupOrganization(@Valid @RequestBody GroupOrganizationDTO groupOrganizationDTO)
                                                                                    throws URISyntaxException {
        GroupOrganizationDTO result = groups.addGroupOrganization(groupOrganizationDTO);
        return ResponseEntity
                .created(new URI("/api/v1/organizations/groups/" + result.getId()))
                .body(result);
    }
}