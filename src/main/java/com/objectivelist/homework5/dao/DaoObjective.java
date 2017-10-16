package com.objectivelist.homework5.dao;

import com.objectivelist.homework5.entity.ObjectiveEntity;

import java.util.List;

/**
 * Created by nonal on 15.09.2017.
 */
public interface DaoObjective {
    void create(ObjectiveEntity objectiveEntity);

    ObjectiveEntity read(int id);

    void update(ObjectiveEntity objectiveEntity);

    void delete(ObjectiveEntity objectiveEntity);

    List<ObjectiveEntity> getAllObjectives();

}
