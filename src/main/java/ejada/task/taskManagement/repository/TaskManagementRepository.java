package ejada.task.taskManagement.repository;

import ejada.task.taskManagement.domain.taskmanagement.TaskManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TaskManagementRepository extends JpaRepository<TaskManagement, Long>, JpaSpecificationExecutor<TaskManagement> {

}
