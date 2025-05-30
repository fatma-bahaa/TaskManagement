package ejada.task.taskManagement.repository;

import ejada.task.taskManagement.domain.task.Task;
import ejada.task.taskManagement.domain.task.TaskFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


public class TaskSpecification implements Specification<Task> {

    private static final String STATUS = "status";
    private static final String PRIORITY = "priority";


    private final TaskFilter filter;

    public TaskSpecification(final TaskFilter taskFilter) {
        this.filter = taskFilter;
    }

    @Override
    public Predicate toPredicate(final Root<Task> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {

        final Optional<Predicate> taskStatusPredicate = buildFilterByStatus(root, criteriaBuilder);
        final Optional<Predicate> taskPriorityPredicate = buildFilterByPriority(root, criteriaBuilder);

        Predicate result=null;
        if(taskStatusPredicate.isPresent()){
            result = criteriaBuilder.and(taskStatusPredicate.get());
        }
        if(taskPriorityPredicate.isPresent()) {
            result=criteriaBuilder.and(taskPriorityPredicate.get());
        }
        return result;
    }

    private Optional<Predicate> buildFilterByStatus(final Root<Task> task,
                                                                              final CriteriaBuilder criteriaBuilder) {

        if (filter.isFilteredByStatus()) {

            return Optional
                    .of(criteriaBuilder.equal(task.get(STATUS), filter.getStatus().name()));
        }

        return Optional.empty();
    }

    private Optional<Predicate> buildFilterByPriority(final Root<Task> task,
                                                                              final CriteriaBuilder criteriaBuilder) {

        if (filter.isFilteredByPriority()) {

            return Optional
                    .of(criteriaBuilder.equal(task.get(PRIORITY), filter.getPriority().name()));
        }

        return Optional.empty();
    }
}

