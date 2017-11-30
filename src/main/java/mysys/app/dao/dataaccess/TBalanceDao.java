package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.TBalanceDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface TBalanceDao {
    /**
    *
    * 主キーによる検索メソッド
    *
    * @param accountId PK
    * @return TBalanceDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceDto find(Long accountId) throws DataAccessException, IncorrectResultSizeDataAccessException;

    /**
     *
     * ユーザIDに紐づくTBalanceの検索
     *
     * @param accountId ユーザID
     * @return List<TBalanceDto>
     */
    public List<TBalanceDto> findAllByAccountId(Long accountId);

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param accountId PK
    * @return TBalanceDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public TBalanceDto findWithContainsDeleteRec(Long accountId) throws DataAccessException,
            IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<TBalanceDto>
     */
    public List<TBalanceDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param balance TBalanceDto
     */
    public void insert(TBalanceDto balance);

    /**
     *
     * 更新メソッド
     *
     * @param balance TBalanceDto
     * @throws DataAccessException
     */
    public void update(TBalanceDto balance) throws DataAccessException;

    /**
     *
     * 物理削除メソッド
     *
     * @param accountId PK
     * @throws DataAccessException
     */
    public void delete(Long accountId) throws DataAccessException;

    /**
     *
     * 論理削除メソッド
     *
     * @param accountId PK
     * @throws DataAccessException
     */
    public void logicalDelete(Long accountId) throws DataAccessException;
}
