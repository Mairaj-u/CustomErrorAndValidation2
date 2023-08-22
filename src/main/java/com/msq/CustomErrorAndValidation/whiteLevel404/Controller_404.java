package com.msq.CustomErrorAndValidation.whiteLevel404;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Controller_404 implements ErrorController {

    @GetMapping("/error")
  public String show_404_page(){
        return "404";
    }
}
