package com.example.repit.controllers;

import com.example.repit.entities.Advertisement;
import com.example.repit.entities.Lesson;
import com.example.repit.entities.Student;
import com.example.repit.entities.Tutor;
import com.example.repit.methods.AdvertisementMethods;
import com.example.repit.methods.LessonMethods;
import com.example.repit.methods.StudentMethods;
import com.example.repit.methods.TutorMethods;
import com.example.repit.security.AuthDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentAdsController {

    @Autowired
    StudentMethods studentMethods;
    @Autowired
    LessonMethods lessonMethods;

    @Autowired
    AdvertisementMethods advertisementMethods;

    @GetMapping("/StudentAds")
    public String StudentAds(Model model){

        List<Object[]> advertisementList = studentMethods.AdsForStudent();
        model.addAttribute("advertisementList",advertisementList);

        return "StudentAds";
    }

    @PostMapping("/StudentAds")
    public String RemoveFromAds(@RequestParam int id){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AuthDetails authDetails = (AuthDetails) auth.getPrincipal();
        com.example.repit.entities.Authentication stud = authDetails.getAuthentication();
        Student student  = stud.getStudent();

        Lesson lesson = new Lesson();
        lesson.setStudent(student);
        lesson.setAdvertisement(advertisementMethods.getByID(id));
        lesson.setIdLesson(0);
        lesson.setAccept("no");

        lessonMethods.save(lesson);

        return "StudentAds";
    }

}

