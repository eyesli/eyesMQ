package com.levil.design.api;

import com.levil.design.pojo.Big;
import com.levil.design.service.GenerateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class api {
    @Autowired
    GenerateService<Big> generateService;

    @GetMapping(value = "/api/invoice/email")
    public Object sendSingleEmail() {
        generateService.generate(new Big());
        return null;
    }
}
