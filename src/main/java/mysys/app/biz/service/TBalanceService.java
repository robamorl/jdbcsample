package mysys.app.biz.service;

import java.util.List;

import mysys.app.biz.domain.TBalanceDto;
import mysys.app.biz.service.exception.DataNotFoundException;

import org.springframework.stereotype.Service;

@Service
public interface TBalanceService {

    /**
     * PKによる検索
     *
     * @param balanceId PK
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public TBalanceDto execFind(Long balanceId) throws DataNotFoundException;

    /**
     *
     * ユーザIDに紐づくデータの検索
     *
     * @param accountId 口座ID
     * @return 検索結果
     * @throws DataNotFoundException
     */
    public List<TBalanceDto> execFindAllByAccountId(Long accountId) throws DataNotFoundException;

    /**
    *
    * 全件検索
    *
    * @return 検索結果
    * @throws DataNotFoundException
    */
    public List<TBalanceDto> execFindAll() throws DataNotFoundException;

    /**
    *
    * データ新規挿入
    *
    * @param accountId 口座ID
    * @return 登録したデータが格納されたDTO
    */
    public TBalanceDto execInsert(Long accountId);

    /**
    *
    * データ更新
    *
    * @param balance TBalanceDto
    * @return 更新したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceDto execUpdate(TBalanceDto balance) throws DataNotFoundException;

    /**
    *
    * データ削除(物理削除)
    *
    * @param accountId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceDto execDelete(Long accountId) throws DataNotFoundException;

    /**
    *
    * データ削除(論理削除)
    *
    * @param balanceId PK
    * @return 削除したデータが格納されたDTO
    * @throws DataNotFoundException
    */
    public TBalanceDto execLogicalDelete(Long balanceId) throws DataNotFoundException;

}
