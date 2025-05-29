package ejada.task.taskManagement.repository;

import ejada.task.taskManagement.domain.taskmanagement.TaskManagement;
import ejada.task.taskManagement.domain.taskmanagement.TaskManagementFilter;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;


public class TaskManagementSpecification implements Specification<TaskManagement> {

    private static final String STATUS = "status";
    private static final String PRIORITY = "priority";


    private final TaskManagementFilter filter;

    public TaskManagementSpecification(final TaskManagementFilter taskManagementFilter) {
        this.filter = taskManagementFilter;
    }

    @Override
    public Predicate toPredicate(final Root<TaskManagement> root, final CriteriaQuery<?> query, final CriteriaBuilder criteriaBuilder) {

        final Optional<Predicate> taskManagementStatusPredicate = buildFilterByStatus(root, criteriaBuilder);
        final Optional<Predicate> taskManagementPriorityPredicate = buildFilterByPriority(root, criteriaBuilder);

        Predicate result=null;
        if(taskManagementStatusPredicate.isPresent()){
            result = criteriaBuilder.and(taskManagementStatusPredicate.get());
        }
        if(taskManagementPriorityPredicate.isPresent()) {
            result=criteriaBuilder.and(taskManagementPriorityPredicate.get());
        }
        return result;
    }

    private Optional<Predicate> buildFilterByStatus(final Root<TaskManagement> taskManagement,
                                                                              final CriteriaBuilder criteriaBuilder) {

        if (filter.isFilteredByStatus()) {

            return Optional
                    .of(criteriaBuilder.equal(taskManagement.get(STATUS), filter.getStatus().name()));
        }

        return Optional.empty();
    }

    private Optional<Predicate> buildFilterByPriority(final Root<TaskManagement> taskManagement,
                                                                              final CriteriaBuilder criteriaBuilder) {

        if (filter.isFilteredByPriority()) {

            return Optional
                    .of(criteriaBuilder.equal(taskManagement.get(PRIORITY), filter.getPriority().name()));
        }

        return Optional.empty();
    }
}

