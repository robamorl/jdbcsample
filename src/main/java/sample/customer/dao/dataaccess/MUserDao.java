package sample.customer.dao.dataaccess;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import sample.customer.biz.domain.MUser;

public interface MUserDao {
    /**
     *
     * 主キーによる検索メソッド
     *
     * @param userId PK
     * @return MUser
     * @throws DataAccessException,IncorrectResultSizeDataAccessException
     */
    public MUser find(Long userId) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<MUser>
     */
    public List<MUser> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param user MUser
     */
    public void insert(MUser user);

    /**
     *
     * 更新メソッド
     *
     * @param user MUser
     * @throws DataAccessException
     */
    public void update(MUser user) throws DataAccessException;

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
}
