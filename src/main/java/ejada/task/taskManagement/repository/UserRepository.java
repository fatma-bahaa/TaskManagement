package ejada.task.taskManagement.repository;

import ejada.task.taskManagement.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByUsername(String username);
}
