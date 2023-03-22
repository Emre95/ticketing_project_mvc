package com.cydeo.entity;

import com.cydeo.dto.UserDTO;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class Project {

    private String projectName;
    private String projectCode;
    private UserDTO assignedManager;
    private LocalDate projectStartDate;
    private LocalDate projectEndDate;
    private String projectDetail;

}
