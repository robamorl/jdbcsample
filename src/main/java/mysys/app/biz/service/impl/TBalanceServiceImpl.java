package mysys.app.biz.service.impl;

import java.math.BigDecimal;
import java.util.List;

import mysys.app.biz.domain.TBalanceDto;
import mysys.app.biz.domain.TBalanceHistoryDto;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.TBalanceDao;
import mysys.app.dao.dataaccess.TBalanceHistoryDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TBalanceServiceImpl implements TBalanceService {

    @Autowired
    TBalanceDao balanceDao;

    @Autowired
    TBalanceHistoryDao balanceHistoryDao;

    /**
     * {@inheritDoc}
     */
    public TBalanceDto execFind(Long accountId) {
        return balanceDao.find(accountId);
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceDto> execFindAllByAccountId(Long accountId) throws DataNotFoundException {
        try {
            return balanceDao.findAllByAccountId(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく残高が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceDto> execFindAll() throws DataNotFoundException {
        try {
            return balanceDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceDto execInsert(Long accountId) {
        // 口座の新規登録
        TBalanceDto balance = new TBalanceDto();
        balance.setAccountId(accountId);
        balance.setBalance(BigDecimal.ZERO);
        balanceDao.insert(balance);
        // 口座履歴の登録
        TBalanceHistoryDto history = new TBalanceHistoryDto(balance);
        balanceHistoryDao.insert(history);
        // 登録した口座履歴IDで口座を更新
        balance.setLatestBalanceHistoryId(history.getBalanceHistoryId());
        balanceDao.update(balance);
        return balanceDao.find(balance.getAccountId());
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceDto execUpdate(TBalanceDto balance) throws DataNotFoundException {
        try {
            balanceDao.update(balance);
            return balanceDao.find(balance.getAccountId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceDto execDelete(Long accountId) throws DataNotFoundException {
        TBalanceDto dto;
        try {
            dto = balanceDao.find(accountId);
            balanceDao.delete(dto.getAccountId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく残高が見つかりませんでした。");
        }

        return dto;

    }

    /**
     * {@inheritDoc}
     */
    public TBalanceDto execLogicalDelete(Long accountId) throws DataNotFoundException {
        try {
            balanceDao.logicalDelete(accountId);
            return balanceDao.findWithContainsDeleteRec(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座が見つかりませんでした。");
        }
    }

}
