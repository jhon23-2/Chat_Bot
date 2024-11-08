package com.procecingData.procecingDataSpting.service.serviceImpl;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import com.procecingData.procecingDataSpting.repository.QuestionRepository;
import com.procecingData.procecingDataSpting.service.serviceInterface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void saveAllQuestion(List<QuestionEntity> questionEntities) {
        questionRepository.saveAll(questionEntities);
    }

    @Override
    public Optional<String> sendMessage(String message) {
        return questionRepository.findResponseByQuestion(message);
    }
    @Override
    public List<QuestionEntity> findSimilarQuestionWithResponse(String question) {
        return questionRepository.findSimilarQuestionsWithResponses(question);
    }
    @Override
    public List<QuestionEntity> findSimilarQuestion(String question) {
        return questionRepository.findSimilarQuestion(question);
    }
    @Override
    public List<QuestionEntity> findAllQuestion() {
        return (List<QuestionEntity>) questionRepository.findAll();
    }
}
