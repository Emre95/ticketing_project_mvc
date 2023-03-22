package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.TaskService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/task")
public class TaskController {

    private final ProjectService projectService;
    private final UserService userService;

    private final TaskService taskService;

    public TaskController(ProjectService projectService, UserService userService, TaskService taskService) {
        this.projectService = projectService;
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/create")
    public String taskCreate1(Model model) {

        model.addAttribute("task", new TaskDTO());
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());

        return "/task/create";
    }

    @PostMapping("/create")
    public String taskCreate2(TaskDTO task) {

        taskService.save(task);

        return "redirect:/task/create";
    }

    @GetMapping("/update")
    public String taskUpdate1(@RequestParam Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("projects", projectService.findAll());
        model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("tasks", taskService.findAll());


        return "/task/update";
    }

    @PostMapping("/update")
    public String taskUpdate(@RequestParam Long id, TaskDTO task) {

        task.setId(id);
        taskService.update(task);

        return "redirect:/task/create";
    }

    @GetMapping("/delete")
    public String taskDelete(@RequestParam Long id) {

        taskService.deleteById(id);

        return "redirect:/task/create";
    }

    @GetMapping("employee/archive")
    public String goToArchive(Model model) {

        model.addAttribute("tasks", taskService.findCompletedTasks());

        return "/task/archive";
    }

    @GetMapping("employee/pending-tasks")
    public String employeePendingTasks(Model model) {

        model.addAttribute("tasks", taskService.findPendingTasks());

        return "task/pending-tasks";
    }

    @GetMapping("employee/edit")
    public String employeeEditTask1(@RequestParam Long id, Model model) {

        model.addAttribute("task", taskService.findById(id));
        model.addAttribute("tasks", taskService.findPendingTasks());
        // model.addAttribute("projects", projectService.findAll());
        // model.addAttribute("employees", userService.findEmployees());
        model.addAttribute("statuses", Status.values());

        return "task/status-update";
    }

    @PostMapping("employee/edit")
    public String employeeEditTask2(TaskDTO task) {

        taskService.updateStatus(task);

        return "redirect:/task/employee/pending-tasks";
    }


}
