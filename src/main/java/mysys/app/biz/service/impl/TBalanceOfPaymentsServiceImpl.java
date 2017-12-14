package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.TBalanceOfPaymentsService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.TBalanceOfPaymentsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TBalanceOfPaymentsServiceImpl implements TBalanceOfPaymentsService {

    @Autowired
    TBalanceService tBalanceService;
    @Autowired
    TBalanceOfPaymentsDao earningsAndExpensesDao;

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execFind(Long eaeId) {
        return earningsAndExpensesDao.find(eaeId);
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindAllByAccountId(Long accountId) throws DataNotFoundException {
        try {
            return earningsAndExpensesDao.findAllByAccountId(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindAll() throws DataNotFoundException {
        try {
            return earningsAndExpensesDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindByAccountIdList(List<Long> accountIds) throws DataNotFoundException {
        try {
            return earningsAndExpensesDao.findAllByAccountIds(accountIds);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execInsert(TBalanceOfPaymentsDto eae) {
        // 収支の登録
        earningsAndExpensesDao.insert(eae);
        // 残高の更新
//        tBalanceService.execInsert(eae.getBalanceOfPaymentsId());
        return earningsAndExpensesDao.find(eae.getBalanceOfPaymentsId());
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execUpdate(TBalanceOfPaymentsDto eae) throws DataNotFoundException {
        try {
            earningsAndExpensesDao.update(eae);
            return earningsAndExpensesDao.find(eae.getBalanceOfPaymentsId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execDelete(Long eaeId) throws DataNotFoundException {
        // 収支の削除
        TBalanceOfPaymentsDto dto;
        try {
            dto = earningsAndExpensesDao.find(eaeId);
            earningsAndExpensesDao.delete(dto.getBalanceOfPaymentsId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execLogicalDelete(Long eaeId) throws DataNotFoundException {
        try {
            earningsAndExpensesDao.logicalDelete(eaeId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }

        // 紐づく残高の削除
        tBalanceService.execLogicalDelete(eaeId);

        return earningsAndExpensesDao.findWithContainsDeleteRec(eaeId);
    }

}
