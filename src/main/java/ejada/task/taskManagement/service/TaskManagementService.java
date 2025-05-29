package ejada.task.taskManagement.service;

import ejada.task.taskManagement.controller.dtos.CreateOrUpdateTaskManagementRequestDTO;
import ejada.task.taskManagement.controller.dtos.CreatedTaskManagementResponseModel;
import ejada.task.taskManagement.controller.dtos.TaskManagementDetailsResponseModel;
import ejada.task.taskManagement.domain.taskmanagement.TaskManagement;
import ejada.task.taskManagement.domain.taskmanagement.TaskManagementFilter;
import ejada.task.taskManagement.repository.TaskManagementRepository;
import ejada.task.taskManagement.repository.TaskManagementSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TaskManagementService {

    @Autowired
    private TaskManagementRepository taskManagementRepository;
    @Autowired
    private UserService userService;

    public Page<TaskManagementDetailsResponseModel> findAllTaskManagement(final TaskManagementFilter taskManagementFilter , final Pageable pageable){
        return taskManagementRepository.findAll(new TaskManagementSpecification(taskManagementFilter)
                , pageable).map(this::mapToTaskManagementDetailsResponseModel);
    }

    public TaskManagementDetailsResponseModel findTaskManagementById(Long taskManagementId){
         Optional<TaskManagement> taskManagement = taskManagementRepository.findById(taskManagementId);
         return taskManagement.map(this::mapToTaskManagementDetailsResponseModel).orElse(null);

    }

    public void deleteTaskManagementById(Long taskManagementId){
         taskManagementRepository.deleteById(taskManagementId);
    }

    @Transactional
    public void updateTaskManagementById(Long taskManagementId, CreateOrUpdateTaskManagementRequestDTO createOrUpdateTaskManagementRequestDTO){
         Optional<TaskManagement> taskManagement = taskManagementRepository.findById(taskManagementId);

         if(taskManagement.isPresent()){
             if (createOrUpdateTaskManagementRequestDTO.getTitle() != null) {
                 taskManagement.get().setTitle(createOrUpdateTaskManagementRequestDTO.getTitle());
             }
             if (createOrUpdateTaskManagementRequestDTO.getDescription() != null) {
                 taskManagement.get().setDescription(createOrUpdateTaskManagementRequestDTO.getDescription());
             }
             if (createOrUpdateTaskManagementRequestDTO.getStatus() != null) {
                 taskManagement.get().setStatus(createOrUpdateTaskManagementRequestDTO.getStatus());
             }
             if (createOrUpdateTaskManagementRequestDTO.getPriority() != null) {
                 taskManagement.get().setPriority(createOrUpdateTaskManagementRequestDTO.getPriority());
             }
             if (createOrUpdateTaskManagementRequestDTO.getDueDate() != null) {
                 taskManagement.get().setDueDate(Instant.parse(createOrUpdateTaskManagementRequestDTO.getDueDate()));
             }
             if (createOrUpdateTaskManagementRequestDTO.getCreatedBy() != null) {
                 taskManagement.get().setUser(userService.getUserByName(createOrUpdateTaskManagementRequestDTO.getCreatedBy()));
             }
         }
    }

    public CreatedTaskManagementResponseModel createTaskManagement(CreateOrUpdateTaskManagementRequestDTO createOrUpdateTaskManagementRequestDTO){
        TaskManagement taskManagement = TaskManagement.builder()
                .title(createOrUpdateTaskManagementRequestDTO.getTitle())
                .description(createOrUpdateTaskManagementRequestDTO.getDescription())
                .status(createOrUpdateTaskManagementRequestDTO.getStatus())
                .priority(createOrUpdateTaskManagementRequestDTO.getPriority())
                .dueDate(Instant.parse(createOrUpdateTaskManagementRequestDTO.getDueDate()))
                .user(userService.getUserByName(createOrUpdateTaskManagementRequestDTO.getCreatedBy()))
                .build();
       return CreatedTaskManagementResponseModel.builder()
                .id(taskManagementRepository.save(taskManagement).getId())
                .build();
    }
    private TaskManagementDetailsResponseModel mapToTaskManagementDetailsResponseModel(TaskManagement taskManagement){
         return TaskManagementDetailsResponseModel.builder()
                .id(taskManagement.getId())
                .title(taskManagement.getTitle())
                .description(taskManagement.getDescription())
                .status(taskManagement.getStatus().name())
                .dueDate(taskManagement.getDueDate().toString())
                .priority(taskManagement.getPriority().name())
                .createdBy(taskManagement.getUser().getName())
                .build();
    }
}
