package ejada.task.taskManagement.domain.task;

import ejada.task.taskManagement.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import static ejada.task.taskManagement.domain.task.Task.TABLE_NAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name=TABLE_NAME)
public class Task {

	protected static final String TABLE_NAME = "task";
	protected static final String CREATED_BY_COLUMN_NAME = "created_by";
	protected static final String TITLE_COLUMN_NAME = "title";
	protected static final String DUE_DATE_COLUMN_NAME = "due_date";
	protected static final String STATUS_COLUMN_NAME = "status";
	protected static final String PRIORITY_COLUMN_NAME = "priority";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name=TITLE_COLUMN_NAME, nullable = false)
	private String title;

	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name=STATUS_COLUMN_NAME)
	private Status status;

	@Column(name=DUE_DATE_COLUMN_NAME)
	private Instant dueDate;

	@Enumerated(EnumType.STRING)
	@Column(name=PRIORITY_COLUMN_NAME)
	private Priority priority;

	@ManyToOne
	@JoinColumn(name = CREATED_BY_COLUMN_NAME, nullable = false)
	private User user;
}
