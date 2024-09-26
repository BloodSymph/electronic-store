package com.company.auth.controller.client;

import com.company.auth.service.client.ProfileClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vi/auth-service/authorization")
public class ProfileClientController {

    private final ProfileClientService profileClientService;

    //todo:Make endpoints

}
