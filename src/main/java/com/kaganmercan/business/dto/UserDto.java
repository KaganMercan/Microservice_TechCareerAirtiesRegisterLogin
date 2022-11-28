package com.kaganmercan.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2

public class UserDto {
    private Long id;
    @NotEmpty(message = "Username cannot be empty.")
    private String username;
    @NotEmpty(message = "Name cannot be empty.")
    private String name;
    @NotEmpty(message = "Password cannot be empty.")
    private String password;
    // private LocalDateTime createdDate;
    private Date createdDate;
}
