package com.jo.user.badgeList;

import com.jo.user.goalList.GoalListEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BadgeListService {

    @Autowired
    private BadgeListRepository repository;

    public List<BadgeListEntity> create(final BadgeListEntity entity) {
        validate(entity);

        repository.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final BadgeListEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }
        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
    public List<BadgeListEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }

    public List<BadgeListEntity> update(final BadgeListEntity entity){
        validate(entity);
        final Optional<BadgeListEntity> original = repository.findById(entity.getId());

        original.ifPresent(badgeList ->{
            badgeList.setId(entity.getId());
            badgeList.setUserId(entity.getUserId());
            badgeList.setBadgeName(entity.getBadgeName());
            badgeList.setBadgeDesc(entity.getBadgeDesc());
            badgeList.setBadgeImg(entity.getBadgeImg());
            badgeList.setBadgePoint(entity.getBadgePoint());

            repository.save(badgeList);
        });

        return retrieve(entity.getUserId());
    }

    public List<BadgeListEntity> delete(final BadgeListEntity entity){

        validate(entity);

        try{repository.delete(entity);}
        catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity" + entity.getId());}

        return retrieve(entity.getUserId());
    }
}
