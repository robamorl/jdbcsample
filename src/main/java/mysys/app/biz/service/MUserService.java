package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.MUser;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface MUserService {

    public MUser execFind(Long userId) throws DataNotFoundException;
    public List<MUser> execFindAll() throws DataNotFoundException;
    public MUser execInsert(MUser user);
    public MUser execUpdate(MUser user) throws DataNotFoundException;
    public MUser execDelete(Long userId) throws DataNotFoundException;
    public MUser execLogicalDelete(Long userId) throws DataNotFoundException;

}
