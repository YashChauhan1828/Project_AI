package com.DTO;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomUserDto 
{
	UUID userId;
	String first_name;
	String email;
	String password;
	String role;
}
