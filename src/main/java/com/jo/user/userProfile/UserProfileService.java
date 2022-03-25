package com.jo.user.userProfile;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    public List<UserProfileEntity> create(final UserProfileEntity entity){
        validate(entity);
        repository.save(entity);
        log.warn("Entity Id : {} is saved.", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final UserProfileEntity entity){
        if (entity == null){
            log.warn("Entitycannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
    public List<UserProfileEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }
    public List<UserProfileEntity> update(final UserProfileEntity entity){
        validate(entity);
        final Optional<UserProfileEntity> original = repository.findById(entity.getId());

        original.ifPresent(userFrofile -> {
            userFrofile.setId(entity.getId());
            userFrofile.setUserId(entity.getUserId());
            userFrofile.setName(entity.getName());
            userFrofile.setPhoneNum(entity.getPhoneNum());
            userFrofile.setImg(entity.getImg());
            userFrofile.setGrade(entity.getGrade());
            userFrofile.setPoint(entity.getPoint());

            repository.save(userFrofile);});

        return retrieve(entity.getUserId());
    }
    public List<UserProfileEntity> delete(final UserProfileEntity entity){
        validate(entity);
        try {
            repository.delete(entity);
        }catch (Exception e){
            log.error("error deleting UserFrofile entity", entity.getId(), e);
            throw new RuntimeException("error deleting UserFrofile entity" + entity.getId());
        } return retrieve(entity.getUserId());
    }

}