package ejada.task.taskManagement.controller.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskManagementDetailsResponseModel {

    private Long id;

    private String title;

    private String description;

    private String status;

    private String priority;

    private String createdBy ;

    private String dueDate;
}
