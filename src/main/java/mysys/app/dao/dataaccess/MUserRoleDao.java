package mysys.app.dao.dataaccess;

import java.util.List;

import mysys.app.biz.domain.MUserRoleDto;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

public interface MUserRoleDao {
    /** ロール：管理ロール */
    public static Long ROLE_ADMIN = Long.valueOf(-2L);
    /** ロール：一般ロール */
    public static Long ROLE_USER = Long.valueOf(-1L);

    /**
     *
     * 主キーによる検索メソッド
     *
     * @param userId PK
     * @param roleId PK
     * @return MUserRole
     * @throws DataAccessException
     * @throws IncorrectResultSizeDataAccessException
     */
    public MUserRoleDto find(Long userId, Long roleId) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
    *
    * 主キーによる検索メソッド(削除済含む)
    *
    * @param userId PK
    * @param roleId PK
    * @return MUserRole
    * @throws DataAccessException
    * @throws IncorrectResultSizeDataAccessException
    */
    public MUserRoleDto findWithContainsDeleteRec(Long userId, Long roleId) throws DataAccessException,IncorrectResultSizeDataAccessException;

    /**
     *
     * 全件検索メソッド
     *
     * @return List<MUser>
     */
    public List<MUserRoleDto> findAll();

    /**
     *
     * 挿入メソッド
     *
     * @param userRole MUser
     */
    public void insert(MUserRoleDto userRole);

    /**
     *
     * 更新メソッド
     *
     * @param userRole MUser
     * @throws DataAccessException
     */
    public void update(MUserRoleDto userRole) throws DataAccessException;

    /**
     *
     * 論理削除メソッド
     *
     * @param userId PK
     * @param roleId PK
     * @throws DataAccessException
     */
    public void logicalDelete(Long userId, Long roleId) throws DataAccessException;


    /**
     *
     * 主キー採番メソッド
     *
     * @return 採番した主キー
     * @throws DataAccessException
     */
    public Long getPkByNextVal() throws DataAccessException;
}
