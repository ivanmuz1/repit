package com.example.repit.controllers;

import com.example.repit.entities.Advertisement;
import com.example.repit.entities.Tutor;
import com.example.repit.methods.AdvertisementMethods;
import com.example.repit.security.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class CreateApplicationsController {

    @Autowired
    AdvertisementMethods advertisementMethods;

    @GetMapping("/CreateApplication")
    public String CreateApplication(){
        return "CreateApplication";
    }

    @PostMapping("/CreateApplication")
    public String addNewAds(@RequestParam String subject, @RequestParam Integer price){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) auth.getPrincipal();
        com.example.repit.entities.Authentication tut = authDetails.getAuthentication();
        Tutor tutor = tut.getTutor();

        Advertisement advertisement = new Advertisement();

        advertisement.setTutor(tutor);
        advertisement.setPrice(price);
        advertisement.setSubject(subject);

        //System.out.println(advertisement);

        advertisementMethods.save(advertisement);

        return "redirect:/repitProfile";
    }

}
