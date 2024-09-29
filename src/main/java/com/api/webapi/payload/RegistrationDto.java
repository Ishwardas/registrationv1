package com.api.webapi.payload;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class RegistrationDto {
    private String name;
    private String email;
    private String mobile;

}