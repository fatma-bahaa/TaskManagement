package ejada.task.taskManagement.domain.task;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {

    TO_DO,
    IN_PROGRESS,
    COMPLETED;
}
