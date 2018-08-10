package py.org.fundacionparaguaya.pspserver.system.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityFeedDTO;

import java.util.List;

public interface ActivityService {
    ActivityDTO getActivityById(Long activityId);

    ActivityDTO addActivity(ActivityDTO activityDTO);

    List<ActivityDTO> getAllActivities();

    Page<ActivityFeedDTO> getActivitiesByUserDetails(Pageable pageable, UserDetailsDTO userDetails);
}
