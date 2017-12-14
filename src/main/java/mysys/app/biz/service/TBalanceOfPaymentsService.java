package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface TBalanceOfPaymentsService {

    /**
     * PKによる検索
     *
     * @param eaeId PK
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public TBalanceOfPaymentsDto execFind(Long eaeId) throws DataNotFoundException;

    /**
     *
     * 口座IDに紐づくデータの検索
     *
     * @param accountId 口座ID
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public List<TBalanceOfPaymentsDto> execFindAllByAccountId(Long accountId) throws DataNotFoundException;

    /**
    *
    * 口座ID群に紐づくデータの検索
    *
    * @param accountIds 口座ID群
    * @return 検索結果
    * @throws DataNotFoundException
    */
    public List<TBalanceOfPaymentsDto> execFindByAccountIdList(List<Long> accountIds) throws DataNotFoundException;

    /**
    *
    * 全件検索
    *
    * @return 検索結果
    * @throws DataNotFoundException
    */
    public List<TBalanceOfPaymentsDto> execFindAll() throws DataNotFoundException;

    /**
    *
    * データ新規挿入
    *
    * @param account MUserDto
    * @return 登録したデータが格納されたDTO
    */
    public TBalanceOfPaymentsDto execInsert(TBalanceOfPaymentsDto account);

    /**
    *
    * データ更新
    *
    * @param account MUserDto
    * @return 更新したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceOfPaymentsDto execUpdate(TBalanceOfPaymentsDto account) throws DataNotFoundException;

    /**
    *
    * データ削除(物理削除)
    *
    * @param eaeId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceOfPaymentsDto execDelete(Long eaeId) throws DataNotFoundException;

    /**
    *
    * データ削除(論理削除)
    *
    * @param eaeId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceOfPaymentsDto execLogicalDelete(Long eaeId) throws DataNotFoundException;

}
