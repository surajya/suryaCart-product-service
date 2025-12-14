package mvc.ecom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "UserEntity")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;
	private String userName;
	private String userPassword;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	private String userType;

	public User(String userName2, String userPassword2, String userPhone2, String userEmail2, String userAddress2,
			String string) {
		// TODO Auto-generated constructor stub
	}

}
