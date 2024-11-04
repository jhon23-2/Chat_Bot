package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.ResponseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ReponseRepository extends CrudRepository<ResponseEntity, Long> {
}
