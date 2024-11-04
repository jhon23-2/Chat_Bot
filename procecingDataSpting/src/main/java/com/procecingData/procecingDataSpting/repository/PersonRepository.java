package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.PersonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface PersonRepository extends CrudRepository<PersonEntity, Long> {

    Optional<PersonEntity> findPersonEntityByUsername(String username);

    @Query("SELECT u FROM PersonEntity u WHERE u.username = ?1")
    Optional<PersonEntity> findPersonUsername(String username);
}
