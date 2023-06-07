package com.core.domain.services.entidies;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class TokenObject {
    private String refresh;
    private String access;
    private String detail;
    private String headers;
}
