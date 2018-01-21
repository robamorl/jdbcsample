package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.MUserRoleDto;
import mysys.app.biz.service.MUserRoleService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.MUserRoleDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MUserRoleServiceImpl implements MUserRoleService {

    @Autowired
    MUserRoleDao userRoleDao;

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto execFind(Long userId, Long roleId) {
        return userRoleDao.find(userId, roleId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MUserRoleDto> execFindAll() throws DataNotFoundException {
        try {
            return userRoleDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto execInsert(MUserRoleDto userRole) {
        userRoleDao.insert(userRole);
        return userRoleDao.find(userRole.getUserId(), userRole.getRoleId());
    }

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto execUpdate(MUserRoleDto userRole) throws DataNotFoundException {
        try {
            userRoleDao.update(userRole);
            return userRoleDao.find(userRole.getUserId(), userRole.getRoleId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto execLogicalDelete(Long userId, Long roleId) throws DataNotFoundException {
        try {
            userRoleDao.logicalDelete(userId, roleId);
            return userRoleDao.findWithContainsDeleteRec(userId, roleId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

}
