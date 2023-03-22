package com.cydeo.service;

import com.cydeo.dto.TaskDTO;
import com.cydeo.dto.UserDTO;
import org.springframework.core.task.TaskDecorator;

import java.util.List;

public interface TaskService extends CrudService<TaskDTO,Long>{

    List<TaskDTO> findTasksByManager(UserDTO manager);

    List<TaskDTO> findPendingTasks();

    List<TaskDTO> findCompletedTasks();

    void updateStatus(TaskDTO task);

}
