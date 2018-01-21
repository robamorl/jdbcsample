package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.MUserRoleDto;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface MUserRoleService {

    /**
     * PKによる検索
     *
     * @param userId PK
     * @param roleId PK
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public MUserRoleDto execFind(Long userId, Long roleId) throws DataNotFoundException;

    /**
     *
     * 全件検索
     *
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public List<MUserRoleDto> execFindAll() throws DataNotFoundException;

    /**
     *
     * データ新規挿入
     *
     * @param userRole MUserRoleDto
     * @return 登録したデータが格納されたDTO
     */
    public MUserRoleDto execInsert(MUserRoleDto userRole);

    /**
     *
     * データ更新
     *
     * @param userRole MUserRoleDto
     * @return 更新したデータが格納されたDTO
     * @throws DataNotFoundException
     */
    public MUserRoleDto execUpdate(MUserRoleDto userRole) throws DataNotFoundException;

    /**
     *
     * データ削除(論理削除)
     *
     * @param userId PK
     * @param roleId PK
     * @return 削除したデータが格納されたDTO
     * @throws DataNotFoundException
     */
    public MUserRoleDto execLogicalDelete(Long userId, Long roleId) throws DataNotFoundException;

}
