package org.dnyanyog.dto;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Component
public class UserRequest {

	@NotBlank(message = "Username must not be blank")
	@Size(min = 5, max = 20, message = "Username must be between 5 and 20 characters")
	@Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username must consist of letters, numbers, and underscores only")
	private String username;

	@NotBlank(message = "Password must not be blank")
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, and one special character")
	private String password;

	@NotBlank(message = "Email must not be blank")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Age must not be blank")
	@Min(value = 18, message = "Age must be at least 18")
	private String age;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
