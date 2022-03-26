package com.jo.user.goalList;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class GoalListServiceT {

    @Autowired
    private GoalListRepositoryT repositoryT;

//    public String testService(){
//        GoalListEntity entity = GoalListEntity.builder().title("My Goal List").build();
//        repositoryT.save(entity);
//        GoalListEntity savedEntity = repositoryT.findById(entity.getId()).get();
//        return savedEntity.getTitle();
//    }

    public List<GoalListEntity> create(final GoalListEntity entity){
        validate(entity);

        repositoryT.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
       return repositoryT.findByUserId(entity.getUserId());
    }

    private void validate(final GoalListEntity entity) {
        if(entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if(entity.getUserId() == null) {
            log.warn("Unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
    public List<GoalListEntity> retrieve(final String userId) {
        return repositoryT.findByUserId(userId);
    }

    public List<GoalListEntity> update(final GoalListEntity entity) {
        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(entity);

        // (2) 넘겨받은 엔티티 id를 이용해 TodoEntity를 가져온다. 존재하지 않는 엔티티는 업데이트 할 수 없기 때문이다.
        final Optional<GoalListEntity> original = repositoryT.findById(entity.getId());



        original.ifPresent(goalList -> {
            // (3) 반환된 TodoEntity가 존재하면 값을 새 entity의 값으로 덮어 씌운다.
            goalList.setId(entity.getId());
            goalList.setUserId(entity.getUserId());
            goalList.setGoalId(entity.getGoalId());
            goalList.setGoalTitle(entity.getGoalTitle());
            goalList.setStartDay(entity.getStartDay());
            goalList.setEndDay(entity.getEndDay());
            goalList.setWeekCount(entity.getWeekCount());
            goalList.setPeriod(entity.getPeriod());
            goalList.setTotalCount(entity.getTotalCount());
            goalList.setCount(entity.getCount());
            goalList.setDoing(entity.getDoing());
            goalList.setState(entity.getState());
            goalList.setResult(entity.getResult());

            repositoryT.save(goalList);
        });

        return retrieve(entity.getUserId());
    }

    public List<GoalListEntity> delete(final GoalListEntity entity) {
        // (1) 저장 할 엔티티가 유효한지 확인한다. 이 메서드는 2.3.1 Create Todo에서 구현했다.
        validate(entity);

        try {
            // (2) 엔티티를 삭제한다.
            repositoryT.delete(entity);
        } catch(Exception e) {
            // (3) exception 발생시 id와 exception을 로깅한다.
            log.error("error deleting entity ", entity.getId(), e);

            // (4) 컨트롤러로 exception을 날린다. 데이터베이스 내부 로직을 캡슐화 하기 위해 e를 리턴하지 않고 새 exception 오브젝트를 리턴한다.
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        // (5) 새 Todo리스트를 가져와 리턴한다.
        return retrieve(entity.getUserId());
    }
}
