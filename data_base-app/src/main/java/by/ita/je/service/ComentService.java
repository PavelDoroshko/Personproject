package by.ita.je.service;

import by.ita.je.dao.ComentDao;
import by.ita.je.exception.NoFoundEntityException;
import by.ita.je.module.Coment;
import org.springframework.stereotype.Service;
import by.ita.je.service.api.InterfaceComentService;

import javax.transaction.Transactional;

@Service
@Transactional
public class ComentService implements InterfaceComentService {
    private ComentDao comentDao;

    public ComentService(ComentDao comentDao) {
        this.comentDao = comentDao;
    }

    @Override
    @Transactional
    public Coment create(Coment coment) {
        Coment createComent = Coment.builder()
                .message("")
                .build();
        return comentDao.save(createComent);
    }

    @Override
    @Transactional
    public Coment update(Long id, Coment coment) {
        Coment coment1 = comentDao.findById(id).orElseThrow(() -> new NoFoundEntityException("Coment"));
        coment1.setMessage(coment.getMessage());
        return comentDao.save(coment1);
    }
}
