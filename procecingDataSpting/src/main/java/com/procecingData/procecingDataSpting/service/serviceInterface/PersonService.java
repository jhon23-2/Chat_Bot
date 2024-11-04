package com.procecingData.procecingDataSpting.service.serviceInterface;

import com.procecingData.procecingDataSpting.entity.PersonEntity;

import java.util.Optional;

public interface PersonService {
    void saveUser(PersonEntity person);
    Optional<PersonEntity> findByUsername(String username);
}
