package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.MAccountService;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.MAccountDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class MAccountServiceImpl implements MAccountService {

    @Autowired
    TBalanceService tBalanceService;
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
    public MAccountDto execFindByAccountNumber(String accountNo) throws DataNotFoundException {
        try {
            return accountDao.findByAccountNumber(accountNo);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座番号に紐づく口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MAccountDto> execFindAllByUserId(Long userId) throws DataNotFoundException {
        try {
            return accountDao.findAllByUserId(userId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく口座が見つかりませんでした。");
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
        // 口座の登録
        accountDao.insert(account);
        // 残高の登録
        tBalanceService.execInsert(account.getAccountId());
        return accountDao.find(account.getAccountId());
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execUpdate(MAccountDto account) throws DataNotFoundException {
        try {
            accountDao.update(account);
            return accountDao.find(account.getAccountId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execDelete(Long accountId) throws DataNotFoundException {
        // 口座の削除
        MAccountDto dto;
        try {
            dto = accountDao.find(accountId);
            accountDao.delete(dto.getAccountId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }



        return dto;
    }

    /**
     * {@inheritDoc}
     */
    public MAccountDto execLogicalDelete(Long accountId) throws DataNotFoundException {
        try {
            accountDao.logicalDelete(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }

        // 紐づく残高の削除
        tBalanceService.execLogicalDelete(accountId);

        return accountDao.findWithContainsDeleteRec(accountId);
    }

}
