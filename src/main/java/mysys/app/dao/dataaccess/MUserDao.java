package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.MUserDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface MUserDao {
    /**
     *
     * 主キーによる検索メソッド
     *
     * @param userId PK
     * @return MUser
     * @throws DataAccessException
     * @throws IncorrectResultSizeDataAccessException
     */
    public MUserDto find(Long userId) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
     *
     * ユーザコードによる検索メソッド
     *
     * @param userCode ユーザコード
     * @return MUserDto
     * @throws DataAccessException
     * @throws IncorrectResultSizeDataAccessException
     */
    public MUserDto findByUserCode(String userCode) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param userId PK
    * @return MUser
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public MUserDto findWithContainsDeleteRec(Long userId) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<MUser>
     */
    public List<MUserDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param user MUser
     */
    public void insert(MUserDto user);

    /**
     *
     * 更新メソッド
     *
     * @param user MUser
     * @throws DataAccessException
     */
    public void update(MUserDto user) throws DataAccessException;

    /**
     *
     * 物理削除メソッド
     *
     * @param userId PK
     * @throws DataAccessException
     */
    public void delete(Long userId) throws DataAccessException;

    /**
     *
     * 論理削除メソッド
     *
     * @param userId PK
     * @throws DataAccessException
     */
    public void logicalDelete(Long userId) throws DataAccessException;


    /**
     *
     * 主キー採番メソッド
     *
     * @return 採番した主キー
     * @throws DataAccessException
     */
    public Long getPkByNextVal() throws DataAccessException;
}
