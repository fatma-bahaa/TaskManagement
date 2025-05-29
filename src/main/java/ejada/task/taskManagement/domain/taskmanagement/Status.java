package ejada.task.taskManagement.domain.taskmanagement;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    TO_DO,
    IN_PROGRESS,
    COMPLETED;
}
