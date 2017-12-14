package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.TBalanceOfPaymentsDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface TBalanceOfPaymentsDao {
    /**
    *
    * 主キーによる検索メソッド
    *
    * @param earningsAndExpensesId PK
    * @return TBalanceOfPaymentsDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceOfPaymentsDto find(Long earningsAndExpensesId) throws DataAccessException, IncorrectResultSizeDataAccessException;

    /**
     *
     * アカウントIDに紐づくTBalanceOfPaymentsの検索
     *
     * @param accountId アカウントID
     * @return List<TBalanceOfPaymentsDto>
     */
    public List<TBalanceOfPaymentsDto> findAllByAccountId(Long accountId);

   /**
    *
    * アカウントID群に紐づくTBalanceOfPaymentsの検索
    *
    * @param accountIds アカウントIDのリスト
    * @return List<TBalanceOfPaymentsDto>
    */
    public List<TBalanceOfPaymentsDto> findAllByAccountIds(List<Long> accountIds);

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param earningsAndExpensesId PK
    * @return TBalanceOfPaymentsDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceOfPaymentsDto findWithContainsDeleteRec(Long earningsAndExpensesId) throws DataAccessException,
            IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<TBalanceOfPaymentsDto>
     */
    public List<TBalanceOfPaymentsDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param earningsAndExpenses TBalanceOfPaymentsDto
     */
    public void insert(TBalanceOfPaymentsDto earningsAndExpenses);

    /**
     *
     * 更新メソッド
     *
     * @param earningsAndExpenses TBalanceOfPaymentsDto
     * @throws DataAccessException
     */
    public void update(TBalanceOfPaymentsDto earningsAndExpenses) throws DataAccessException;

    /**
     *
     * 物理削除メソッド
     *
     * @param earningsAndExpensesId PK
     * @throws DataAccessException
     */
    public void delete(Long earningsAndExpensesId) throws DataAccessException;

    /**
     *
     * 論理削除メソッド
     *
     * @param earningsAndExpensesId PK
     * @throws DataAccessException
     */
    public void logicalDelete(Long earningsAndExpensesId) throws DataAccessException;

    /**
     *
     * 主キー採番メソッド
     *
     * @return 採番した主キー
     * @throws DataAccessException
     */
    public Long getPkByNextVal() throws DataAccessException;
}
