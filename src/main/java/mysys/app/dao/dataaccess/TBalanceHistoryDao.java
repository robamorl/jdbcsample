package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.TBalanceHistoryDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface TBalanceHistoryDao {
    /**
    *
    * 主キーによる検索メソッド
    *
    * @param balanceHistoryId PK
    * @return TBalanceHistoryDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceHistoryDto find(Long balanceHistoryId) throws DataAccessException, IncorrectResultSizeDataAccessException;

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param balanceHistoryId PK
    * @return TBalanceHistoryDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceHistoryDto findWithContainsDeleteRec(Long balanceHistoryId) throws DataAccessException,
            IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<TBalanceHistoryDto>
     */
    public List<TBalanceHistoryDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param balanceHistory TBalanceHistoryDto
     */
    public void insert(TBalanceHistoryDto balanceHistory);

    /**
     *
     * 更新メソッド
     *
     * @param balanceHistory TBalanceHistoryDto
     * @throws DataAccessException
     */
    public void update(TBalanceHistoryDto balanceHistory) throws DataAccessException;

    /**
     *
     * 物理削除メソッド
     *
     * @param balanceHistoryId PK
     * @throws DataAccessException
     */
    public void delete(Long balanceHistoryId) throws DataAccessException;

    /**
     *
     * 論理削除メソッド
     *
     * @param balanceHistoryId PK
     * @throws DataAccessException
     */
    public void logicalDelete(Long balanceHistoryId) throws DataAccessException;

    /**
     *
     * 主キー採番メソッド
     *
     * @return 採番した主キー
     * @throws DataAccessException
     */
    public Long getPkByNextVal() throws DataAccessException;
}
