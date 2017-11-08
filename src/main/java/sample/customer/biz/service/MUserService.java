package sample.customer.biz.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sample.customer.biz.domain.MUser;
import sample.customer.biz.service.exception.DataNotFoundException;

@Service
public interface MUserService {

    public MUser execFind(Long userId) throws DataNotFoundException;
    public List<MUser> execFindAll() throws DataNotFoundException;
    public MUser execInsert(MUser user);
    public MUser execUpdate(MUser user) throws DataNotFoundException;
    public MUser execDelete(Long userId) throws DataNotFoundException;
    public MUser execLogicalDelete(Long userId) throws DataNotFoundException;

}
