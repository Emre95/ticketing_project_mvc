package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO,String> implements UserService {


    @Override
    public UserDTO save(UserDTO user) {
        return super.save(user.getEmail(), user);
    }

    @Override
    public UserDTO findById(String email) {
        return super.findById(email);
    }

    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(String email) {
        super.deleteById(email);
    }

    @Override
    public void update(UserDTO user) {
        super.update(user.getEmail(), user);
    }


    @Override
    public List<UserDTO> findManagers() {
        return findAll().stream().filter(user -> user.getRoleDTO().getId() == 2).collect(Collectors.toList());
    }

    @Override
    public List<UserDTO> findEmployees() {
        return findAll().stream().filter(user -> user.getRoleDTO().getId() == 3).collect(Collectors.toList());
    }
}
