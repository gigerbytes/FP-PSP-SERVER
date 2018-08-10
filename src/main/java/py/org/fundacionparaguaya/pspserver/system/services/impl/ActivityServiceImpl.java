package py.org.fundacionparaguaya.pspserver.system.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import py.org.fundacionparaguaya.pspserver.common.exceptions.UnknownResourceException;
import py.org.fundacionparaguaya.pspserver.security.dtos.UserDetailsDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityDTO;
import py.org.fundacionparaguaya.pspserver.system.dtos.ActivityFeedDTO;
import py.org.fundacionparaguaya.pspserver.system.entities.ActivityEntity;
import py.org.fundacionparaguaya.pspserver.system.mapper.ActivityMapper;
import py.org.fundacionparaguaya.pspserver.system.repositories.ActivityRepository;
import py.org.fundacionparaguaya.pspserver.system.services.ActivityService;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static py.org.fundacionparaguaya.pspserver.system.specifications.ActivityFeedSpecifications.byDetails;

@Service
public class ActivityServiceImpl implements ActivityService {

    private ActivityRepository activityRepository;

    private ActivityMapper activityMapper;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, ActivityMapper activityMapper) {
        this.activityRepository = activityRepository;
        this.activityMapper = activityMapper;
    }

    @Override
    public ActivityDTO getActivityById(Long activityId){
        checkArgument(activityId > 0, "Argument was %s but expected non negative", activityId);

        return Optional.ofNullable(activityRepository.findOne(activityId))
                .map(activityMapper::entityToDto)
                .orElseThrow(() -> new UnknownResourceException("Activity does not exist"));
    }

    @Override
    public ActivityDTO addActivity(ActivityDTO activityDTO) {
        ActivityEntity newActivity = activityRepository.save(activityMapper.dtoToEntity(activityDTO));
        return activityMapper.entityToDto(newActivity);
    }

    @Override
    public List<ActivityDTO> getAllActivities() {
        List<ActivityEntity> activities = activityRepository.findAll();
        return activityMapper.entityListToDtoList(activities);
    }

    @Override
    public Page<ActivityFeedDTO> getActivitiesByUserDetails(Pageable pageable, UserDetailsDTO userDetails) {
        Page<ActivityEntity> activities = activityRepository.findAll(byDetails(userDetails), pageable);
        Page<ActivityFeedDTO> activityFeedDTOPage = new PageImpl<ActivityFeedDTO>(
                activityMapper.entityListToActivityFeed(activities.getContent()), pageable, activities.getTotalElements());
        return activityFeedDTOPage;
    }

}
