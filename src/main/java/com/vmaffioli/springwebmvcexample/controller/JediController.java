package com.vmaffioli.springwebmvcexample.controller;

import com.vmaffioli.springwebmvcexample.model.Jedi;
import com.vmaffioli.springwebmvcexample.repository.JediRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class JediController {

    @Autowired
    private JediRepository repository;

    @GetMapping("/jedi")
    public ModelAndView jedi(){

        final ModelAndView mvc = new ModelAndView();
        mvc.setViewName("jedi");

        final Jedi luke = new Jedi("Luke","Skywlker");
        mvc.addObject("allJedi", repository.getAllJedi());

        return mvc;
    }

    @GetMapping("/new-jedi")
    public ModelAndView newJedi(){

        final ModelAndView mvc = new ModelAndView();
        mvc.setViewName("new-jedi");

        mvc.addObject("jedi", new Jedi());

        return mvc;
    }

    @PostMapping("/jedi")
    public String createJedi(@Valid @ModelAttribute Jedi jedi, BindingResult result){

        if(result.hasErrors()){
            return "new-jedi";
        }

        repository.add(jedi);

        return "redirect:jedi";
    }



}
