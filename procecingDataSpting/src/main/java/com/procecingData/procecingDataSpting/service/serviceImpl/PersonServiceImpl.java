package com.procecingData.procecingDataSpting.service.serviceImpl;

import com.procecingData.procecingDataSpting.entity.PersonEntity;
import com.procecingData.procecingDataSpting.repository.PersonRepository;
import com.procecingData.procecingDataSpting.service.serviceInterface.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public void saveUser(PersonEntity person) {
        personRepository.save(person);
    }
    @Override
    public Optional<PersonEntity> findByUsername(String username) {
        return personRepository.findPersonEntityByUsername(username);
    }
}
