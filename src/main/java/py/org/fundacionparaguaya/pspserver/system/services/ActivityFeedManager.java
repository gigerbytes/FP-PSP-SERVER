package py.org.fundacionparaguaya.pspserver.system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import py.org.fundacionparaguaya.pspserver.families.entities.FamilyEntity;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityFeedDTO;

/**
 * Created by bsandoval on 05/05/18.
 */
public interface ActivityFeedManager {
    void createHouseholdFirstSnapshotActivity(UserDetailsDTO details, FamilyEntity family);

    void createHouseholdSnapshotActivity(UserDetailsDTO details, FamilyEntity family);

    /*void createRequestedReportActivity();*/

    Page<ActivityFeedDTO> showActivityFeedByUserDetails(Pageable pageable, UserDetailsDTO details);
}
