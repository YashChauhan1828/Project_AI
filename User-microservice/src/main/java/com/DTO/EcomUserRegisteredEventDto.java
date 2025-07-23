package com.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EcomUserRegisteredEventDto 
{
	String email;
	String first_name;
}
