package com.core.domain.entities.token;

import com.core.domain.entities.User;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class Sessao {
    private User user;
    private String token;
}
