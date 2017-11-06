package sample.customer.biz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import sample.customer.biz.domain.MUser;
import sample.customer.dao.dataaccess.MUserDao;

@Service
public class MUserServiceImpl implements MUserService {

    @Autowired
    MUserDao userDao;

    public MUser execFind(Long userId) {
        return userDao.find(userId);
    }

    public List<MUser> execFindAll() throws DataNotFoundException {
        try {
            return userDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    public MUser execInsert(MUser user) {
        userDao.insert(user);
        return userDao.find(user.getUserId());
    }

    public MUser execUpdate(MUser user) throws DataNotFoundException {
        try {
            userDao.update(user);
            return userDao.find(user.getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    public MUser execDelete(Long userId) throws DataNotFoundException {
        try {
            userDao.delete(userId);
            return userDao.find(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    public MUser execLogicalDelete(Long userId) throws DataNotFoundException {
        try {
            userDao.logicalDelete(userId);
            return userDao.find(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

}
