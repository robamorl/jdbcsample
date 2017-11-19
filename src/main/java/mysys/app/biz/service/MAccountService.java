package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.MAccountDto;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface MAccountService {

    /**
     * PKによる検索
     *
     * @param accountId PK
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public MAccountDto execFind(Long accountId) throws DataNotFoundException;

    /**
     *
     * ユーザIDに紐づくデータの検索
     *
     * @param accountId ユーザID
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public List<MAccountDto> execFindAllByUserId(Long accountId) throws DataNotFoundException;

    /**
    *
    * 全件検索
    *
    * @return 検索結果
    * @throws DataNotFoundException
    */
    public List<MAccountDto> execFindAll() throws DataNotFoundException;

    /**
    *
    * データ新規挿入
    *
    * @param account MUserDto
    * @return 登録したデータが格納されたDTO
    */
    public MAccountDto execInsert(MAccountDto account);

    /**
    *
    * データ更新
    *
    * @param account MUserDto
    * @return 更新したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public MAccountDto execUpdate(MAccountDto account) throws DataNotFoundException;

    /**
    *
    * データ削除(物理削除)
    *
    * @param accountId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public MAccountDto execDelete(Long accountId) throws DataNotFoundException;

    /**
    *
    * データ削除(論理削除)
    *
    * @param accountId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public MAccountDto execLogicalDelete(Long accountId) throws DataNotFoundException;

}
