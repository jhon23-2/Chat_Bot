package com.procecingData.procecingDataSpting.service.serviceImpl;

import com.procecingData.procecingDataSpting.entity.QuestionEntity;
import com.procecingData.procecingDataSpting.repository.QuestionRepository;
import com.procecingData.procecingDataSpting.service.serviceInterface.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void saveAllQuestion(List<QuestionEntity> questionEntities) {
        questionRepository.saveAll(questionEntities);
    }

}
