package com.myportfolio.web.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.FileNotFoundException;

@ControllerAdvice("com.myportfolio.web")
public class GlobalCatcherController {

    @ExceptionHandler({NullPointerException.class, FileNotFoundException.class})
    public String catcher(Exception ex, Model m) {
        m.addAttribute("ex", ex);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String catcher2(Exception ex, Model m) {
        m.addAttribute("ex", ex);
        return "error";
    }
}
