package by.ita.je.service;

import by.ita.je.entity.Coment;

public interface InterfaceComentService {
    Coment create(Coment coment);
    Coment update(Long id,Coment coment);
}
