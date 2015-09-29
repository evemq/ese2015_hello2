package org.sample.controller;

import javax.validation.Valid;

import org.sample.controller.exceptions.InvalidTeamException;
import org.sample.controller.exceptions.InvalidUserException;
import org.sample.controller.pojos.SignupForm;
import org.sample.controller.pojos.CreateTeamForm;
import org.sample.controller.service.SampleService;
import org.sample.model.Team;
import org.sample.model.User;
import org.sample.model.dao.TeamDao;
import org.sample.model.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class IndexController {

    @Autowired
    SampleService sampleService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
    	ModelAndView model = new ModelAndView("index");
    	model.addObject("signupForm", new SignupForm());
    	model.addObject(sampleService.getTeamList());
    	
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveFrom(signupForm);
            	model = new ModelAndView("show");
            } catch (InvalidUserException e) {
            	model = new ModelAndView("index");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("index");
        }   	
    	if(model.getViewName().equals("index")){
        	model.addObject(sampleService.getTeamList());	
    	}
    	return model;
    }
    
    
    
    
    
    
    
    
    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ModelAndView team() {
    	ModelAndView model = new ModelAndView("new-team");
    	model.addObject("teamForm", new CreateTeamForm());    	
        return model;
    }

    @RequestMapping(value = "/createTeam", method = RequestMethod.POST)
    public ModelAndView createTeam(@Valid CreateTeamForm teamForm, BindingResult result, RedirectAttributes redirectAttributes) {
    	ModelAndView model;    	
    	if (!result.hasErrors()) {
            try {
            	sampleService.saveTeam(teamForm);
            	model = new ModelAndView("show-team");
            } catch (InvalidTeamException e) {
            	model = new ModelAndView("new-team");
            	model.addObject("page_error", e.getMessage());
            }
        } else {
        	model = new ModelAndView("new-team");
        }   	
    	return model;
    }
    
    @RequestMapping(value={"/profile"}, method = RequestMethod.GET)
    public ModelAndView profile(@RequestParam("userId") long userId) {
    	ModelAndView model = new ModelAndView("profile");
    	try{
    		model.addObject(sampleService.getUserById(userId));
    	}catch(java.lang.IllegalArgumentException e){
    		model.addObject("page_error", "User not found.");
    	}
        return model;
    }
    
    
    
    
    
    
    
    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }

}