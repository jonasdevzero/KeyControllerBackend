package com.core.services.dto.userData;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserData {
    private int id;
    private String matricula;
    private String nome_usual;
    private String cpf;
    private String rg;
    private String filiacao[];
    private String data_nascimento;
    private String naturalidade;
    private String tipo_sanguineo;
    private String email;
    private String url_foto_75x100;
    private String url_foto_150x200;
    private String tipo_vinculo;
    private UserLinkData vinculo;
}
