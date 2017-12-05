package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.TBalanceHistoryDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.TBalanceHistoryDao;
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
public class TBalanceHistoryDaoImpl extends CommonDao implements TBalanceHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.T_BALANCE_HISTORY";

    /** SQL COLUMN */
    private SqlColumn BALANCE_HISTORY_ID = new SqlColumn("BALANCE_HISTORY_ID", "balanceHistoryId");
    private SqlColumn ACCOUNT_ID = new SqlColumn("ACCOUNT_ID", "accountId");
    private SqlColumn BALANCE = new SqlColumn("BALANCE", "balance");
    private SqlColumn LATEST_BALANCE_HISTORY_ID = new SqlColumn("LATEST_BALANCE_HISTORY_ID", "latestBalanceHistoryId");
    private SqlColumn EARNINGS_AND_EXPENSES_ID = new SqlColumn("EARNINGS_AND_EXPENSES_ID", "earningsAndExpensesId");

    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            BALANCE_HISTORY_ID,
            ACCOUNT_ID,
            BALANCE,
            LATEST_BALANCE_HISTORY_ID,
            EARNINGS_AND_EXPENSES_ID,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public TBalanceHistoryDto find(Long balanceHistoryId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, balanceHistoryId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceHistoryMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceHistoryDto findWithContainsDeleteRec(Long balanceHistoryId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, balanceHistoryId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByConditionWithContainsDeletedRecord(TABLE_NAME, condition),
                            new TBalanceHistoryMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceHistoryDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new TBalanceHistoryMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(TBalanceHistoryDto balanceHistory) {
        balanceHistory.setBalanceHistoryId(this.getPkByNextVal());
        balanceHistory.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(balanceHistory));
    }

    /**
     * {@inheritDoc}
     */
    public void update(TBalanceHistoryDto balanceHistory) throws EmptyResultDataAccessException {
        super.copyLogData(this.find(balanceHistory.getAccountId()), balanceHistory);
        balanceHistory.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, balanceHistory.getAccountId()),
                    new BeanPropertySqlParameterSource(balanceHistory));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long balanceHistoryId) throws EmptyResultDataAccessException {
        try {
            namedParameterJdbcTemplate.update(super.getDeleteQuery(TABLE_NAME, COLUMNS, balanceHistoryId),
                    new BeanPropertySqlParameterSource(new TBalanceHistoryDto()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long balanceHistoryId) throws EmptyResultDataAccessException {
        TBalanceHistoryDto balanceHistory = this.find(balanceHistoryId);
        balanceHistory.setDeleteFlg(Boolean.TRUE);
        this.update(balanceHistory);
    }

    /**
     * {@inheritDoc}
     */
    public Long getPkByNextVal() throws DataAccessException {
        return jdbcTemplate.queryForObject(super.getSequenceSelectQuery(ACCOUNT_ID), Long.class);
    }

    class TBalanceHistoryMapper implements RowMapper<TBalanceHistoryDto> {
        public TBalanceHistoryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TBalanceHistoryDto balanceHistory = new TBalanceHistoryDto();
            balanceHistory.setBalanceHistoryId(rs.getLong(BALANCE_HISTORY_ID.getColumnName()));
            balanceHistory.setAccountId(rs.getLong(ACCOUNT_ID.getColumnName()));
            balanceHistory.setBalance(rs.getBigDecimal(BALANCE.getColumnName()));
            balanceHistory.setLatestBalanceHistoryId(rs.getLong(LATEST_BALANCE_HISTORY_ID.getColumnName()));
            balanceHistory.setEarningsAndExpensesId(rs.getLong(EARNINGS_AND_EXPENSES_ID.getColumnName()));
            balanceHistory.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            balanceHistory.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            balanceHistory.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            balanceHistory.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            balanceHistory.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return balanceHistory;
        }
    }

}
