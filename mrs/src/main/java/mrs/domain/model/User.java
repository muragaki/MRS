package mrs.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name = "usr")
public class User {
	@Id
	private String userId;
	private String password;
	private String firstName;
	private String lastName;
	@Enumerated(EnumType.STRING)
	private RoleName roleName;
}