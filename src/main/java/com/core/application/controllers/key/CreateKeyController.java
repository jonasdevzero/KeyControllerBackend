package com.core.application.controllers.key;

import com.core.application.dto.key.CreateKeyDTO;
import com.core.application.dto.key.KeyResponseDTO;
import com.core.application.services.key.CreateKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("key")
public class CreateKeyController {
    @Autowired
    private CreateKeyService service;

    @PostMapping
    public KeyResponseDTO handle(@RequestBody CreateKeyDTO data) {
        return this.service.execute(data);
    }
}
