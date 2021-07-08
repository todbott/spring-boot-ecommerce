package com.ecommerce.securedControllers;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secure")
public class SecuredController {

    @GetMapping
    public @ResponseBody String reachSecureEndpoint() {

        return "If your are reading this you reached a secure endpoint";
    }
}