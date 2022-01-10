package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoFoundEntityException extends RuntimeException {
    public NoFoundEntityException(String className) {
        super("Такой записи для " +className+" в базе данных не существует");
        log.error("Ошибка поиска данных в {} ",  className);

    }
}
