package com.Entity;

import java.util.List;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "ecomusers")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserEntity 
{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID user_id;
//	@NotBlank(message = "Please Enter Your Name")
//	@Length(min = 3 , max = 12 , message = "Please Enter proper Name") 
//	@Pattern(regexp = "[a-zA-Z]+",message = "Please Enter Valid Name")
	private String first_name;
//	@NotBlank(message = "Please Enter Your Email")
//	@Pattern( regexp = "[a-zA-Z0-9]+@[a-zA-Z]+\\.[a-zA-Z0-9]{2,3}" , message = "Please Enter valid email")
	private String email;
//	@NotBlank(message = "Please Enter Your Password")
//	@Length(min = 8 , max = 15 , message = "Please Enter proper password")
//	@Pattern(regexp = "[a-zA-Z0-9]+@[0-9a-zA_Z]+" , message = "Please Enter valid password")
	private String password;
//	@NotBlank(message = "Profile picture required")
	private String profile_picture_path;
	private Boolean emailVerified;
	String role;
	String token;

	@OneToMany(mappedBy = "user")
	 @JsonIgnore
	List<EcomShippingEntity> ships;

}
