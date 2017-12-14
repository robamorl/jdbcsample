package mysys.app.web.controller.advice;

import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

@Component
@ControllerAdvice(annotations = Controller.class)
public class ErrorHundlerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class,
                new StringTrimmerEditor(true));
    }

    @ExceptionHandler(DataNotFoundException.class)
    public String handleException(DataNotFoundException e, Model model) {
        model.addAttribute("exception", e);
        return "error/notfound";
    }

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        e.printStackTrace();
        model.addAttribute("exception", e);
        return "error/error";
    }
}
