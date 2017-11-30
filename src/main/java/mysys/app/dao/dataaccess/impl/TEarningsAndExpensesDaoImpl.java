package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.TEarningsAndExpensesDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.TEarningsAndExpensesDao;
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
public class TEarningsAndExpensesDaoImpl extends CommonDao implements TEarningsAndExpensesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.T_EARNINGS_AND_EXPENSES";

    /** SQL COLUMN */
    private SqlColumn EARNINGS_AND_EXPENSES_ID = new SqlColumn("EARNINGS_AND_EXPENSES_ID", "earningsAndExpensesId");
    private SqlColumn ACCOUNT_ID = new SqlColumn("ACCOUNT_ID", "earningsAndExpensesId");
    private SqlColumn EARNINGS_AND_EXPENSES_KUBUN = new SqlColumn("EARNINGS_AND_EXPENSES_KUBUN", "earningsAndExpensesKubun");
    private SqlColumn AMOUNT = new SqlColumn("AMOUNT", "amount");
    private SqlColumn TRANSACTION_DATE = new SqlColumn("TRANSACTION_DATE", "transactionDate");


    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            EARNINGS_AND_EXPENSES_ID,
            ACCOUNT_ID,
            EARNINGS_AND_EXPENSES_KUBUN,
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
    public TEarningsAndExpensesDto find(Long earningsAndExpensesId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, earningsAndExpensesId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new TEarningsAndExpensesMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public TEarningsAndExpensesDto findWithContainsDeleteRec(Long earningsAndExpensesId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, earningsAndExpensesId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByConditionWithContainsDeletedRecord(TABLE_NAME, condition),
                            new TEarningsAndExpensesMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TEarningsAndExpensesDto> findAllByAccountId(Long accountId) {
        SqlCondition condition = new SqlCondition(ACCOUNT_ID.getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate.query(super.getSelectQueryByCondition(TABLE_NAME, condition), new TEarningsAndExpensesMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TEarningsAndExpensesDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new TEarningsAndExpensesMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(TEarningsAndExpensesDto earningsAndExpenses) {
        earningsAndExpenses.setAccountId(this.getPkByNextVal());
        earningsAndExpenses.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(earningsAndExpenses));
    }

    /**
     * {@inheritDoc}
     */
    public void update(TEarningsAndExpensesDto earningsAndExpenses) throws EmptyResultDataAccessException {
        super.copyLogData(this.find(earningsAndExpenses.getAccountId()), earningsAndExpenses);
        earningsAndExpenses.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, earningsAndExpenses.getAccountId()),
                    new BeanPropertySqlParameterSource(earningsAndExpenses));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long earningsAndExpensesId) throws EmptyResultDataAccessException {
        try {
            namedParameterJdbcTemplate.update(super.getDeleteQuery(TABLE_NAME, COLUMNS, earningsAndExpensesId),
                    new BeanPropertySqlParameterSource(new TEarningsAndExpensesDto()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long earningsAndExpensesId) throws EmptyResultDataAccessException {
        TEarningsAndExpensesDto earningsAndExpenses = this.find(earningsAndExpensesId);
        earningsAndExpenses.setDeleteFlg(Boolean.TRUE);
        this.update(earningsAndExpenses);
    }

    /**
     * {@inheritDoc}
     */
    public Long getPkByNextVal() throws DataAccessException {
        return jdbcTemplate.queryForObject(super.getSequenceSelectQuery(ACCOUNT_ID), Long.class);
    }

    class TEarningsAndExpensesMapper implements RowMapper<TEarningsAndExpensesDto> {
        public TEarningsAndExpensesDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TEarningsAndExpensesDto earningsAndExpenses = new TEarningsAndExpensesDto();
            earningsAndExpenses.setEarningsAndExpensesId(rs.getLong(EARNINGS_AND_EXPENSES_ID.getColumnName()));
            earningsAndExpenses.setAccountId(rs.getLong(ACCOUNT_ID.getColumnName()));
            earningsAndExpenses.setEarningsAndExpensesKubun(rs.getString(EARNINGS_AND_EXPENSES_KUBUN.getColumnName()));
            earningsAndExpenses.setAmount(rs.getLong(AMOUNT.getColumnName()));
            earningsAndExpenses.setTransactionDate(rs.getString(TRANSACTION_DATE.getColumnName()));
            earningsAndExpenses.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            earningsAndExpenses.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            earningsAndExpenses.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            earningsAndExpenses.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            earningsAndExpenses.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return earningsAndExpenses;
        }
    }

}
