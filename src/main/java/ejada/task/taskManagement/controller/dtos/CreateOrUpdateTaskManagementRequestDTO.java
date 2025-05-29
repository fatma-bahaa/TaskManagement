package ejada.task.taskManagement.controller.dtos;

import ejada.task.taskManagement.domain.taskmanagement.Priority;
import ejada.task.taskManagement.domain.taskmanagement.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrUpdateTaskManagementRequestDTO {

    private String title;

    private String description;

    private Status status;

    private Priority priority;

    private String createdBy ;

    private String dueDate;
}
