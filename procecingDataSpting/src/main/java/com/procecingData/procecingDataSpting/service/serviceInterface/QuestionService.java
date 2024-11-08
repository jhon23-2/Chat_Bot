package com.procecingData.procecingDataSpting.service.serviceInterface;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    void saveAllQuestion(List<QuestionEntity> questionEntities);
    Optional<String> sendMessage(String message);

    List<QuestionEntity> findSimilarQuestionWithResponse(String question);
    List<QuestionEntity> findSimilarQuestion(String question);
    List<QuestionEntity> findAllQuestion();
}
