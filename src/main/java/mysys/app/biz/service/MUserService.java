package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.MUserDto;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface MUserService {

    /**
     * PKによる検索
     *
     * @param userId PK
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public MUserDto execFind(Long userId) throws DataNotFoundException;

    /**
     *
     * ユーザコードによる検索
     *
     * @param userCode ユーザコード
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public MUserDto execFindByUserCode(String userCode) throws DataNotFoundException;

    /**
     *
     * 全件検索
     *
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public List<MUserDto> execFindAll() throws DataNotFoundException;

    /**
     *
     * データ新規挿入
     *
     * @param user MUserDto
     * @return 登録したデータが格納されたDTO
     */
    public MUserDto execInsert(MUserDto user);

    /**
     *
     * データ更新
     *
     * @param user MUserDto
     * @return 更新したデータが格納されたDTO
     * @throws DataNotFoundException
     */
    public MUserDto execUpdate(MUserDto user) throws DataNotFoundException;

    /**
     *
     * データ削除(物理削除)
     *
     * @param userId PK
     * @return 削除したデータが格納されたDTO
     * @throws DataNotFoundException
     */
    public MUserDto execDelete(Long userId) throws DataNotFoundException;

    /**
     *
     * データ削除(論理削除)
     *
     * @param userId PK
     * @return 削除したデータが格納されたDTO
     * @throws DataNotFoundException
     */
    public MUserDto execLogicalDelete(Long userId) throws DataNotFoundException;

}
