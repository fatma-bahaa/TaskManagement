package ejada.task.taskManagement.domain.task;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class TaskFilter {

    private Status status;
    private Priority priority;

    @JsonIgnore
    public boolean isFilteredByStatus() {
        if(status != null && !status.name().isEmpty())
            return true;
        return false;
    }
    @JsonIgnore
    public boolean isFilteredByPriority() {
        if(priority != null && !priority.name().isEmpty())
            return true;
        return false;
    }
}
