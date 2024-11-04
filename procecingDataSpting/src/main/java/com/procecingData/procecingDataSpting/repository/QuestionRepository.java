package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {
}
