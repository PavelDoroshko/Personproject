package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IncorrectDataException  extends RuntimeException{
    public IncorrectDataException(String className){
        super("Введены некорректные данные для "+className);
        log.error("Data is incorrect {} ", className);
    }

}
