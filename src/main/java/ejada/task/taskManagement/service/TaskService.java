package ejada.task.taskManagement.service;

import ejada.task.taskManagement.controller.dtos.CreateOrUpdateTaskRequestDTO;
import ejada.task.taskManagement.controller.dtos.CreatedTaskResponseModel;
import ejada.task.taskManagement.controller.dtos.TaskDetailsResponseModel;
import ejada.task.taskManagement.domain.task.Task;
import ejada.task.taskManagement.domain.task.TaskFilter;
import ejada.task.taskManagement.repository.TaskRepository;
import ejada.task.taskManagement.repository.TaskSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    public Page<TaskDetailsResponseModel> findAllTasks(final TaskFilter taskFilter , final Pageable pageable){
        return taskRepository.findAll(new TaskSpecification(taskFilter)
                , pageable).map(this::mapToTaskDetailsResponseModel);
    }

    public TaskDetailsResponseModel findTaskById(Long taskId){
         Optional<Task> task = taskRepository.findById(taskId);
         return task.map(this::mapToTaskDetailsResponseModel).orElse(null);

    }

    public void deleteTaskById(Long taskId){
         taskRepository.deleteById(taskId);
    }

    @Transactional
    public void updateTaskById(Long taskId, CreateOrUpdateTaskRequestDTO createOrUpdateTaskRequestDTO){
         Optional<Task> task = taskRepository.findById(taskId);

         if(task.isPresent()){
             if (createOrUpdateTaskRequestDTO.getTitle() != null) {
                 task.get().setTitle(createOrUpdateTaskRequestDTO.getTitle());
             }
             if (createOrUpdateTaskRequestDTO.getDescription() != null) {
                 task.get().setDescription(createOrUpdateTaskRequestDTO.getDescription());
             }
             if (createOrUpdateTaskRequestDTO.getStatus() != null) {
                 task.get().setStatus(createOrUpdateTaskRequestDTO.getStatus());
             }
             if (createOrUpdateTaskRequestDTO.getPriority() != null) {
                 task.get().setPriority(createOrUpdateTaskRequestDTO.getPriority());
             }
             if (createOrUpdateTaskRequestDTO.getDueDate() != null) {
                 task.get().setDueDate(Instant.parse(createOrUpdateTaskRequestDTO.getDueDate()));
             }
             if (createOrUpdateTaskRequestDTO.getCreatedBy() != null) {
                 task.get().setUser(userService.getUserByName(createOrUpdateTaskRequestDTO.getCreatedBy()));
             }
         }
    }

    public CreatedTaskResponseModel createTask(CreateOrUpdateTaskRequestDTO createOrUpdateTaskRequestDTO){
        Task task = Task.builder()
                .title(createOrUpdateTaskRequestDTO.getTitle())
                .description(createOrUpdateTaskRequestDTO.getDescription())
                .status(createOrUpdateTaskRequestDTO.getStatus())
                .priority(createOrUpdateTaskRequestDTO.getPriority())
                .dueDate(Instant.parse(createOrUpdateTaskRequestDTO.getDueDate()))
                .user(userService.getUserByName(createOrUpdateTaskRequestDTO.getCreatedBy()))
                .build();
       return CreatedTaskResponseModel.builder()
                .id(taskRepository.save(task).getId())
                .build();
    }
    private TaskDetailsResponseModel mapToTaskDetailsResponseModel(Task task){
         return TaskDetailsResponseModel.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus().name())
                .dueDate(task.getDueDate().toString())
                .priority(task.getPriority().name())
                .createdBy(task.getUser().getName())
                .build();
    }
}
