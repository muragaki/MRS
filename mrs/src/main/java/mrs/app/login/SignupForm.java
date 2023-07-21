package mrs.app.login;



import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import mrs.domain.model.RoleName;

@Data
public class SignupForm {
	@NotBlank
	@Size(min = 6, max = 20)
	private String userId;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String password;
	
	@NotBlank
	@Size(min = 8, max = 20)
	private String passwordConfirmation;
	
	@NotBlank
	@Size(min = 1, max = 255)
	private String firstName;
	
	@NotBlank
	@Size( min = 1, max = 255)
	private String lastName;
	
	private RoleName roleName = RoleName.USER;
	
    @AssertTrue(message = "パスワードとパスワード確認は同一にしてください。")
    public boolean isPasswordValid() {
        if (password == null || password.isEmpty()) {
            return true;
        }

        return password.equals(passwordConfirmation);
    } 

	
}
