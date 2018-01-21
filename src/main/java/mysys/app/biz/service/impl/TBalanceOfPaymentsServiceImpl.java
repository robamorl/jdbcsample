package mysys.app.biz.service.impl;

import java.util.List;

import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.biz.service.TBalanceOfPaymentsService;
import mysys.app.biz.service.TBalanceService;
import mysys.app.biz.service.exception.DataNotFoundException;
import mysys.app.dao.dataaccess.TBalanceOfPaymentsDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TBalanceOfPaymentsServiceImpl implements TBalanceOfPaymentsService {

    @Autowired
    TBalanceService balanceService;
    @Autowired
    TBalanceOfPaymentsDao bopDao;

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execFind(Long bopId) {
        return bopDao.find(bopId);
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindAllByAccountId(Long accountId) throws DataNotFoundException {
        try {
            return bopDao.findAllByAccountId(accountId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindAll() throws DataNotFoundException {
        try {
            return bopDao.findAll();
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> execFindByAccountIdList(List<Long> accountIds) throws DataNotFoundException {
        try {
            return bopDao.findAllByAccountIds(accountIds);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("口座に紐づく収支が見つかりませんでした。");
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execInsert(TBalanceOfPaymentsDto bop) throws DataNotFoundException {
        // 収支の登録
        bopDao.insert(bop);
        balanceService.execUpdateByAmount(bop.getAccountId(), bop.getAmount(), bop.getBalanceOfPaymentsSign());
        return bopDao.find(bop.getBalanceOfPaymentsId());
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execUpdate(TBalanceOfPaymentsDto bop) throws DataNotFoundException {
        // 旧レコードの削除
        this.execLogicalDelete(bop.getBalanceOfPaymentsId());
        // 新しいレコードの登録
        this.execInsert(bop);

        return bop;
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execDelete(Long bopId) throws DataNotFoundException {
        // 収支の削除
        TBalanceOfPaymentsDto dto;
        try {
            dto = bopDao.find(bopId);
            bopDao.delete(dto.getBalanceOfPaymentsId());
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto execLogicalDelete(Long bopId) throws DataNotFoundException {
        // 収支を削除
        try {
            bopDao.logicalDelete(bopId);
        } catch (EmptyResultDataAccessException e) {
            throw new DataNotFoundException("収支が見つかりませんでした。");
        }
        TBalanceOfPaymentsDto bop = bopDao.findWithContainsDeleteRec(bopId);

        // 紐づく残高の更新
        // 収支サインは反転させる
        // 反転させるために-1を乗算
        Long sign = bop.getBalanceOfPaymentsSign() * -1;
        balanceService.execUpdateByAmount(bop.getAccountId(), bop.getAmount(), sign);

        return bop;
    }

}
