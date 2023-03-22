package com.cydeo.controller;

import com.cydeo.dto.ProjectDTO;
import com.cydeo.dto.UserDTO;
import com.cydeo.enums.Status;
import com.cydeo.service.ProjectService;
import com.cydeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/project")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String createProject1(Model model) {

        model.addAttribute("project", new ProjectDTO());
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());

        return "project/create";
    }

    @PostMapping("/create")
    public String createProject1(ProjectDTO project) {


        projectService.save(project);

        return "redirect:/project/create";
    }

    @GetMapping("/update")
    public String updateProject1(@RequestParam String projectCode,Model model) {

        model.addAttribute("project", projectService.findById(projectCode));
        model.addAttribute("managers", userService.findManagers());
        model.addAttribute("projects", projectService.findAll());


        return "/project/update";
    }

    @PostMapping("/update")
    public String updateProject2(ProjectDTO project) {

        projectService.update(project);

        return "redirect:/project/create";
    }


    @GetMapping("/delete")
    public String deleteProject(@RequestParam String projectCode) {

        projectService.deleteById(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/complete")
    public String completeProject(@RequestParam String projectCode) {

        projectService.changeStatus(projectCode);

        return "redirect:/project/create";
    }

    @GetMapping("/manager/project-status")
    public String projectStatus(Model model) {

        UserDTO manager = userService.findById("john@cydeo.com");
        List<ProjectDTO> projects =  projectService.getCountedListOfProjectDTO(manager);
        model.addAttribute("projects", projects);

        return "/manager/project-status";
    }

    @GetMapping("/manager/complete")
    public String projectStatus2(@RequestParam String projectCode) {

        projectService.complete(projectService.findById(projectCode));

        return "redirect:/project/manager/project-status";
    }

}
