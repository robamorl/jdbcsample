package mysys.app.biz.service;

import java.math.BigDecimal;
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
    public TBalanceDto execFindByAccountId(Long accountId) throws DataNotFoundException;

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
     * 引数の口座IDに紐づく残高を引数の金額によって更新します。
     * 残高の金額は
     *  現在残高 + (金額 * サイン値(1or-1)
     * にて求めます。
     *
     * @param accoundId 口座ID
     * @param amount 金額
     * @param sign 金額に乗算するサイン
     * @return 更新した残高Dto
     * @throws DataNotFoundException
     */
    public TBalanceDto execUpdateByAmount(Long accoundId, BigDecimal amount, Long sign) throws DataNotFoundException;

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
