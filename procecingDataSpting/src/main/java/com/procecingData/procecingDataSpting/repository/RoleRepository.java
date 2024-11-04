package com.procecingData.procecingDataSpting.repository;

import com.procecingData.procecingDataSpting.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}
