package com.core.services.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class TokenObject {
    private String refresh;
    private String access;
}
