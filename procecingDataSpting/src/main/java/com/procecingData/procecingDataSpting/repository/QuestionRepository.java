package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {

    //TODO... Finish this query Method

    /*@Query("SELECT p FROM QuestionEntity p LEFT JOIN FETCH p.responseEntity r WHERE LOWER(p.response) LIKE LOWER(CONCAT('%', :?1, '%'))")
    String findResponseByQuestion(String question);*/
}
