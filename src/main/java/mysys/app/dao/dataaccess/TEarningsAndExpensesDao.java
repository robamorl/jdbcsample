package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.TEarningsAndExpensesDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface TEarningsAndExpensesDao {
    /**
    *
    * 主キーによる検索メソッド
    *
    * @param earningsAndExpensesId PK
    * @return TEarningsAndExpensesDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TEarningsAndExpensesDto find(Long earningsAndExpensesId) throws DataAccessException, IncorrectResultSizeDataAccessException;

    /**
     *
     * アカウントIDに紐づくTEarningsAndExpensesの検索
     *
     * @param accountId アカウントID
     * @return List<TEarningsAndExpensesDto>
     */
    public List<TEarningsAndExpensesDto> findAllByAccountId(Long accountId);

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param earningsAndExpensesId PK
    * @return TEarningsAndExpensesDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TEarningsAndExpensesDto findWithContainsDeleteRec(Long earningsAndExpensesId) throws DataAccessException,
            IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<TEarningsAndExpensesDto>
     */
    public List<TEarningsAndExpensesDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param earningsAndExpenses TEarningsAndExpensesDto
     */
    public void insert(TEarningsAndExpensesDto earningsAndExpenses);

    /**
     *
     * 更新メソッド
     *
     * @param earningsAndExpenses TEarningsAndExpensesDto
     * @throws DataAccessException
     */
    public void update(TEarningsAndExpensesDto earningsAndExpenses) throws DataAccessException;

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
