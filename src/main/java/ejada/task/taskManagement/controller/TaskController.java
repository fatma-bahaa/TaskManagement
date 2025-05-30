package ejada.task.taskManagement.controller;

import ejada.task.taskManagement.controller.dtos.CreateOrUpdateTaskRequestDTO;
import ejada.task.taskManagement.controller.dtos.CreatedTaskResponseModel;
import ejada.task.taskManagement.controller.dtos.TaskDetailsResponseModel;
import ejada.task.taskManagement.domain.task.TaskFilter;
import ejada.task.taskManagement.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/task")
public class TaskController {

    public static final String TASK_ID_PATH = "/{taskId}";
    public static final String TASK_DETAILS_PATH = "/details";

    @Autowired
    private TaskService taskService;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<TaskDetailsResponseModel> findAllTasks(final TaskFilter taskFilter, final Pageable pageable) {
        return taskService.findAllTasks(taskFilter, pageable);
    }

    @RequestMapping(value =  TASK_ID_PATH + TASK_DETAILS_PATH, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDetailsResponseModel> findTaskById(@PathVariable final Long taskId) {

        return new ResponseEntity<>(taskService.findTaskById(taskId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CreatedTaskResponseModel> createTask(@RequestBody final CreateOrUpdateTaskRequestDTO createOrUpdateTaskRequestDTO) {

        final CreatedTaskResponseModel createdTaskResponseModel = taskService.createTask(createOrUpdateTaskRequestDTO);

        return new ResponseEntity<>(createdTaskResponseModel, HttpStatus.CREATED);
    }

    @RequestMapping(value = TASK_ID_PATH, method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteTaskById(@PathVariable final Long taskId) {

        taskService.deleteTaskById(taskId);
    }

    @RequestMapping(value = TASK_ID_PATH, method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.OK)
    public void updateTaskById(@PathVariable final Long taskId, @RequestBody final CreateOrUpdateTaskRequestDTO createOrUpdateTaskRequestDTO) {

        taskService.updateTaskById(taskId, createOrUpdateTaskRequestDTO);
    }
}
