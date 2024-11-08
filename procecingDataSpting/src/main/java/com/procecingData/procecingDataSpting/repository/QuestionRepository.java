package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends CrudRepository<QuestionEntity,Long> {

    // Método para buscar la respuesta exacta por el texto de la pregunta
    @Query("SELECT r.response FROM QuestionEntity q JOIN q.responseEntity r WHERE LOWER(q.question) = LOWER(:questionText)")
    Optional<String> findResponseByQuestion(@Param("questionText") String questionMessage);

    //Método para buscar preguntas similares y sus respuestas
    @Query("SELECT q FROM QuestionEntity q JOIN FETCH q.responseEntity r WHERE LOWER(q.question) LIKE LOWER(CONCAT('%', :questionText, '%'))")
    List<QuestionEntity> findSimilarQuestionsWithResponses(@Param("questionText") String questionText);

    //Método para buscar solo preguntas similares sin cargar las respuestas
    @Query("SELECT q FROM QuestionEntity q WHERE LOWER(q.question) LIKE LOWER(CONCAT('%', :questionText, '%'))")
    List<QuestionEntity> findSimilarQuestion(@Param("questionText") String question);
}
