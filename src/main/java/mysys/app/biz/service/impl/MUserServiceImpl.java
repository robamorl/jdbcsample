package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.MUserDto;
import mysys.app.biz.domain.MUserRoleDto;
import mysys.app.biz.service.MUserService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.MUserDao;
import mysys.app.dao.dataaccess.MUserRoleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MUserServiceImpl implements MUserService {

    @Autowired
    MUserDao userDao;
    @Autowired
    MUserRoleDao userRoleDao;

    /**
     * {@inheritDoc}
     */
    public MUserDto execFind(Long userId) {
        return userDao.find(userId);
    }

    /**
     * {@inheritDoc}
     */
    public MUserDto execFindByUserCode(String userCode) throws DataNotFoundException {
        try {
            return userDao.findByUserCode(userCode);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MUserDto> execFindAll() throws DataNotFoundException {
        try {
            return userDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserDto execInsert(MUserDto user) {
        userDao.insert(user);
        // ロールの登録
        MUserRoleDto userRole = new MUserRoleDto();
        // ユーザID
        userRole.setUserId(user.getUserId());
        // ロールは一般固定
        userRole.setRoleId(MUserRoleDao.ROLE_USER);
        userRole.setDefaultFlg(Boolean.TRUE);
        // 登録
        userRoleDao.insert(userRole);
        return userDao.find(user.getUserId());
    }

    /**
     * {@inheritDoc}
     */
    public MUserDto execUpdate(MUserDto user) throws DataNotFoundException {
        try {
            userDao.update(user);
            return userDao.find(user.getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserDto execDelete(Long userId) throws DataNotFoundException {
        try {
            MUserDto dto = userDao.find(userId);
            userDao.delete(dto.getUserId());
            return dto;
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserDto execLogicalDelete(Long userId) throws DataNotFoundException {
        try {
            userDao.logicalDelete(userId);
            return userDao.findWithContainsDeleteRec(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

}
