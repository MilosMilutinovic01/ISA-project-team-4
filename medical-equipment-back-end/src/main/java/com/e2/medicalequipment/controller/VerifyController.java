package com.e2.medicalequipment.controller;

import com.e2.medicalequipment.model.User;
import com.e2.medicalequipment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/verify")
public class VerifyController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public String verifyUser(@PathVariable Long id){
        User user = userService.getUserById(id);
        user.setEnabled(true);
        if(userService.changeUserStatus(user) != null)
        {
            return "Sucessfull verification";
        }
        return "Verification error";
    }
}
