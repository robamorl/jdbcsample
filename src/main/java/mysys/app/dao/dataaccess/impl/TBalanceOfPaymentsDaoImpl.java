package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.TBalanceOfPaymentsDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.TBalanceOfPaymentsDao;
import mysys.app.dao.dataaccess.common.CommonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TBalanceOfPaymentsDaoImpl extends CommonDao implements TBalanceOfPaymentsDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.T_BALANCE_OF_PAYMENTS";

    /** SQL COLUMN */
    private SqlColumn BALANCE_OF_PAYMENTS_ID = new SqlColumn("BALANCE_OF_PAYMENTS_ID", "balanceOfPaymentsId");
    private SqlColumn ACCOUNT_ID = new SqlColumn("ACCOUNT_ID", "accountId");
    private SqlColumn BALANCE_OF_PAYMENTS_KUBUN = new SqlColumn("BALANCE_OF_PAYMENTS_KUBUN", "balanceOfPaymentsKubun");
    private SqlColumn BALANCE_OF_PAYMENTS_SIGN = new SqlColumn("BALANCE_OF_PAYMENTS_SIGN", "balanceOfPaymentsSign");
    private SqlColumn EXPENSES_KUBUN = new SqlColumn("EXPENSES_KUBUN", "expensesKubun");
    private SqlColumn AMOUNT = new SqlColumn("AMOUNT", "amount");
    private SqlColumn TRANSACTION_DATE = new SqlColumn("TRANSACTION_DATE", "transactionDate");



    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            BALANCE_OF_PAYMENTS_ID,
            ACCOUNT_ID,
            BALANCE_OF_PAYMENTS_KUBUN,
            BALANCE_OF_PAYMENTS_SIGN,
            EXPENSES_KUBUN,
            AMOUNT,
            TRANSACTION_DATE,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto find(Long bopId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, bopId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceOfPaymentsMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> findAllByAccountIds(List<Long> accountIds) {
        SqlCondition condition = new SqlCondition(ACCOUNT_ID.getColumnName(), SqlCondition.IN, accountIds);
        try {
            return jdbcTemplate.query(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceOfPaymentsMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceOfPaymentsDto findWithContainsDeleteRec(Long bopId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, bopId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByConditionWithContainsDeletedRecord(TABLE_NAME, condition),
                            new TBalanceOfPaymentsMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> findAllByAccountId(Long accountId) {
        SqlCondition condition = new SqlCondition(ACCOUNT_ID.getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate.query(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceOfPaymentsMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceOfPaymentsDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new TBalanceOfPaymentsMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(TBalanceOfPaymentsDto bop) {
        bop.setBalanceOfPaymentsId(this.getPkByNextVal());
        bop.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(bop));
    }

    /**
     * {@inheritDoc}
     */
    public void update(TBalanceOfPaymentsDto bop) throws EmptyResultDataAccessException {
        super.copyLogData(this.find(bop.getBalanceOfPaymentsId()), bop);
        bop.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, bop.getBalanceOfPaymentsId()),
                    new BeanPropertySqlParameterSource(bop));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long bopId) throws EmptyResultDataAccessException {
        try {
            namedParameterJdbcTemplate.update(super.getDeleteQuery(TABLE_NAME, COLUMNS, bopId),
                    new BeanPropertySqlParameterSource(new TBalanceOfPaymentsDto()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long bopId) throws EmptyResultDataAccessException {
        TBalanceOfPaymentsDto bop = this.find(bopId);
        bop.setDeleteFlg(Boolean.TRUE);
        this.update(bop);
    }

    /**
     * {@inheritDoc}
     */
    public Long getPkByNextVal() throws DataAccessException {
        return jdbcTemplate.queryForObject(super.getSequenceSelectQuery(ACCOUNT_ID), Long.class);
    }

    class TBalanceOfPaymentsMapper implements RowMapper<TBalanceOfPaymentsDto> {
        public TBalanceOfPaymentsDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TBalanceOfPaymentsDto bop = new TBalanceOfPaymentsDto();
            bop.setBalanceOfPaymentsId(rs.getLong(BALANCE_OF_PAYMENTS_ID.getColumnName()));
            bop.setAccountId(rs.getLong(ACCOUNT_ID.getColumnName()));
            bop.setBalanceOfPaymentsKubun(rs.getString(BALANCE_OF_PAYMENTS_KUBUN.getColumnName()));
            bop.setBalanceOfPaymentsSign(rs.getLong(BALANCE_OF_PAYMENTS_SIGN.getColumnName()));
            bop.setExpensesKubun(rs.getString(EXPENSES_KUBUN.getColumnName()));
            bop.setAmount(rs.getBigDecimal(AMOUNT.getColumnName()));
            bop.setTransactionDate(rs.getDate(TRANSACTION_DATE.getColumnName()));
            bop.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            bop.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            bop.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            bop.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            bop.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return bop;
        }
    }

}
