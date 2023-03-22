package com.cydeo.service.impl;

import com.cydeo.dto.RoleDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T,ID> {

    public Map<ID,T> database = new HashMap<>();


    T save(ID id, T object) {
        database.put(id, object);
        return object;
    }

    T findById(ID id) {
        return database.get(id);
    }

    List<T> findAll() {
        return new ArrayList<>(database.values());
    }

    void deleteById(ID id) {
        database.remove(id);
    }

    void update(ID id, T object) {
        database.put(id,object);
    }


}
