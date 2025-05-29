package ejada.task.taskManagement.controller;

import ejada.task.taskManagement.controller.dtos.CreateOrUpdateTaskManagementRequestDTO;
import ejada.task.taskManagement.controller.dtos.CreatedTaskManagementResponseModel;
import ejada.task.taskManagement.controller.dtos.TaskManagementDetailsResponseModel;
import ejada.task.taskManagement.domain.taskmanagement.TaskManagementFilter;
import ejada.task.taskManagement.service.TaskManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task-management")
public class TaskManagementController {

    public static final String TASK_MANAGEMENT_ID_PATH = "/{taskManagementId}";
    public static final String TASK_MANAGEMENT_DETAILS_PATH = "/details";

    @Autowired
    private TaskManagementService taskManagementService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TaskManagementDetailsResponseModel> findAllTaskManagements(final TaskManagementFilter taskManagementFilter, final Pageable pageable) {
        return taskManagementService.findAllTaskManagement(taskManagementFilter, pageable);
    }

    @RequestMapping(value =  TASK_MANAGEMENT_ID_PATH + TASK_MANAGEMENT_DETAILS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskManagementDetailsResponseModel> findTaskManagementById(@PathVariable final Long taskManagementId) {

        return new ResponseEntity<>(taskManagementService.findTaskManagementById(taskManagementId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatedTaskManagementResponseModel> createTaskManagement(@RequestBody final CreateOrUpdateTaskManagementRequestDTO createOrUpdateTaskManagementRequestDTO) {

        final CreatedTaskManagementResponseModel createdTaskManagementResponseModel = taskManagementService.createTaskManagement(createOrUpdateTaskManagementRequestDTO);

        return new ResponseEntity<>(createdTaskManagementResponseModel, HttpStatus.CREATED);
    }

    @RequestMapping(value =  TASK_MANAGEMENT_ID_PATH, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskManagementById(@PathVariable final Long taskManagementId) {

        taskManagementService.deleteTaskManagementById(taskManagementId);
    }

    @RequestMapping(value =  TASK_MANAGEMENT_ID_PATH, method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskManagementById(@PathVariable final Long taskManagementId, @RequestBody final CreateOrUpdateTaskManagementRequestDTO createOrUpdateTaskManagementRequestDTO) {

        taskManagementService.updateTaskManagementById(taskManagementId, createOrUpdateTaskManagementRequestDTO);
    }
}
