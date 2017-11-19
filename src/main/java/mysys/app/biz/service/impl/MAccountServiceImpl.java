package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.MAccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MAccountServiceImpl implements MAccountService {

    @Autowired
    MAccountDao accountDao;

    /**
     * {@inheritDoc}
     */
    public MAccountDto execFind(Long accountId) {
        return accountDao.find(accountId);
    }

    /**
     * {@inheritDoc}
     */
    public List<MAccountDto> execFindAllByUserId(Long userId) throws DataNotFoundException {
        try {
            return accountDao.findAllByUserId(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザに紐づく口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MAccountDto> execFindAll() throws DataNotFoundException {
        try {
            return accountDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execInsert(MAccountDto account) {
        accountDao.insert(account);
        return accountDao.find(account.getAccountId());
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execUpdate(MAccountDto account) throws DataNotFoundException {
        try {
            accountDao.update(account);
            return accountDao.find(account.getUserId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execDelete(Long accountId) throws DataNotFoundException {
        try {
            accountDao.delete(accountId);
            return accountDao.find(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execLogicalDelete(Long accountId) throws DataNotFoundException {
        try {
            accountDao.logicalDelete(accountId);
            return accountDao.find(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("ユーザが見つかりませんでした。");
        }
    }

}
