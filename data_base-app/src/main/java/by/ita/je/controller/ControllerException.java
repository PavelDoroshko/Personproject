package by.ita.je.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class ControllerException {
    @ExceptionHandler(Exception.class)
    public String exception(Model model, Exception e){
        String message;
        int exceptNumber = Integer.parseInt(e.getMessage().substring(0,3));
        if(exceptNumber >= 500){
            message = "Ошибка сервера! Сервер не может обработать запрос!";
        }
        else message = "Неверный запрос!";
        model.addAttribute("message", message);
        model.addAttribute("exceptNumber", exceptNumber);
        return "Exception";
    }
}
