package com.msq.CustomErrorAndValidation.dto;

import lombok.*;

import javax.validation.constraints.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {
   @NotNull(message = "name should not be null")

    private  String name;
   @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",message = "invalid email address")
    private String email;
   @Pattern(regexp = "^\\d{10}$",message = "invalid mobile number")
    private  String mobile;
    private String gender;
    @Min(18)
    @Max(60)
    private int age;
    @NotBlank
    private String nationality;
}
