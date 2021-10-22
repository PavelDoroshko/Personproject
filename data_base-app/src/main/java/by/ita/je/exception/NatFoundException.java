package by.ita.je.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NatFoundException extends RuntimeException {
    public NatFoundException(String string) {
        super("NotFoundException");
        log.error("Exception_Exception");
    }
}
