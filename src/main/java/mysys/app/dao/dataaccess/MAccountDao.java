package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.MAccountDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface MAccountDao {
    /**
    *
    * 主キーによる検索メソッド
    *
    * @param accountId PK
    * @return MAccountDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public MAccountDto find(Long accountId) throws DataAccessException, IncorrectResultSizeDataAccessException;

    /**
     *
     * ユーザIDに紐づくMAccountの検索
     *
     * @param userId ユーザID
     * @return List<MAccountDto>
     */
    public List<MAccountDto> findAllByUserId(Long userId);

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param accountId PK
    * @return MAccountDto
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public MAccountDto findWithContainsDeleteRec(Long accountId) throws DataAccessException,
            IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<MAccountDto>
     */
    public List<MAccountDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param account MAccountDto
     */
    public void insert(MAccountDto account);

    /**
     *
     * 更新メソッド
     *
     * @param account MAccountDto
     * @throws DataAccessException
     */
    public void update(MAccountDto account) throws DataAccessException;

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

    /**
     *
     * 主キー採番メソッド
     *
     * @return 採番した主キー
     * @throws DataAccessException
     */
    public Long getPkByNextVal() throws DataAccessException;
}
