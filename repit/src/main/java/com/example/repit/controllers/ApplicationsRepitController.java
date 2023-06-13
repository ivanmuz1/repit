package com.example.repit.controllers;

import com.example.repit.entities.Lesson;
import com.example.repit.methods.LessonMethods;
import com.example.repit.methods.TutorMethods;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
public class ApplicationsRepitController {
    @Autowired
    TutorMethods tutorMethods;

    @Autowired
    LessonMethods lessonMethods;
    @GetMapping ("/ApplicationsRepit")
    public ModelAndView ApplicationsRepit(){

        ModelAndView modelAndView = new ModelAndView();
        List<Object[]> Applications = tutorMethods.Applications();
        System.out.println(Applications);
        modelAndView.addObject("Applications", Applications);

        return modelAndView;
    }

      @PostMapping("/ApplicationsRepit")
      @ResponseBody
      public List<Object[]> deleteItem(@RequestParam int id, @RequestParam String dateLesson) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = format.parse(dateLesson);

        Lesson lesson = lessonMethods.getByID(id);
        lesson.setAccept("yes");
        lesson.setStartLesson(date);

        List<Object[]> items = tutorMethods.Applications();
        return items;
    }

}
