package by.ita.je.service.api;

import by.ita.je.module.Coment;

public interface InterfaceComentService {
    Coment create(Coment coment);
    Coment update(Long id,Coment coment);
}
