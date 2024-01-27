package com.in28minutes.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class loginController {

    private AuthenticationService authenticationService;

    //constructor is used for wiring due to spring i.e creating instance , used instead of new authenticationService()
    public loginController(AuthenticationService authenticationService) {
        super();
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="login",method = RequestMethod.GET)
    public String gotoLoginPage() {
        return "login";
    }

    @RequestMapping(value="login",method = RequestMethod.POST)
    //login?name=Ranga RequestParam
    //any value from controller to jsp we use Model
    public String gotoWelcomePage(@RequestParam String name,
                                  @RequestParam String password, ModelMap model) {

        if(authenticationService.authenticate(name, password)) {

            model.put("name", name);
            //Authentication
            //name - in28minutes
            //password - dummy

            return "welcome";
        }
        model.put("errorMessage","Invalid credentials please enter valid credential");
        return "login";
    }
}