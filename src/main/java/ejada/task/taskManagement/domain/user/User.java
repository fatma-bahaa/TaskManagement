package ejada.task.taskManagement.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ejada.task.taskManagement.domain.user.User.TABLE_NAME;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name=TABLE_NAME)
public class User {

	protected static final String TABLE_NAME = "users";
	protected static final String NAME_COLUMN_NAME = "name";
	protected static final String USER_NAME_COLUMN_NAME = "user_name";
	protected static final String PASSWORD_COLUMN_NAME = "password";

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = NAME_COLUMN_NAME, nullable = false, unique = true)
	private String name;

	@Column(name = USER_NAME_COLUMN_NAME, nullable = false, unique = true)
	private String username;

	@Column(name = PASSWORD_COLUMN_NAME, nullable = false)
	private String password;

}
