package com.procecingData.procecingDataSpting.service.serviceImpl;

import com.procecingData.procecingDataSpting.entity.RoleEntity;
import com.procecingData.procecingDataSpting.repository.RoleRepository;
import com.procecingData.procecingDataSpting.service.serviceInterface.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveAllRole(Set<RoleEntity> roles) {
        roleRepository.saveAll(roles);
    }
}
