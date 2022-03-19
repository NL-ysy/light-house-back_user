package com.jo.user.PostList;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostListService {

    @Autowired
    private PostListRepository repository;

    public List<PostListEntity> create(final PostListEntity entity){
        validate(entity);

        repository.save(entity);
        log.warn("Entity Id : {} is saved.", entity.getId());
        return repository.findByUserId(entity.getUserId());
    }

    private void validate(final PostListEntity entity){
        if (entity == null){
            log.warn("Entitycannot be null");
            throw new RuntimeException("Entity cannot be null");
        }
        if (entity.getUserId() == null){
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
    public List<PostListEntity> retrieve(final String userId){
        return repository.findByUserId(userId);
    }

    public List<PostListEntity> update(final PostListEntity entity){
        validate(entity);
        final Optional<PostListEntity> original = repository.findById(entity.getId());

        original.ifPresent(postList ->{
            postList.setId(postList.getId());
            postList.setUserId(postList.getUserId());
            postList.setTitle(postList.getTitle());
            postList.setCategory(postList.getCategory());
            postList.setCreateDate(postList.getCreateDate());
            postList.setEditDate(postList.getEditDate());

            repository.save(postList);
        });
        return retrieve(entity.getUserId());
    }

    public List<PostListEntity> delete(final PostListEntity entity){
        validate(entity);
        try{repository.delete(entity);}
        catch (Exception e){
            log.error("error deleting entity", entity.getId(), e);
            throw new RuntimeException("error deleting entity" + entity.getId());}
        return retrieve(entity.getUserId());}
}