package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.TBalanceDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.TBalanceDao;
import mysys.app.dao.dataaccess.common.CommonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TBalanceDaoImpl extends CommonDao implements TBalanceDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.T_BALANCE";

    /** SQL COLUMN */
    private SqlColumn ACCOUNT_ID = new SqlColumn("ACCOUNT_ID", "accountId");
    private SqlColumn BALANCE = new SqlColumn("BALANCE", "balance");
    private SqlColumn LATEST_BALANCE_HISTORY_ID = new SqlColumn("LATEST_BALANCE_HISTORY_ID", "latestBalanceHistoryId");

    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            ACCOUNT_ID,
            BALANCE,
            LATEST_BALANCE_HISTORY_ID,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public TBalanceDto find(Long accountId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public TBalanceDto findWithContainsDeleteRec(Long accountId) throws EmptyResultDataAccessException,
            IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByConditionWithContainsDeletedRecord(TABLE_NAME, condition),
                            new TBalanceMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceDto> findAllByAccountId(Long accountId) {
        SqlCondition condition = new SqlCondition(ACCOUNT_ID.getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate.query(super.getSelectQueryByCondition(TABLE_NAME, condition), new TBalanceMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<TBalanceDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new TBalanceMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(TBalanceDto balance) {
        balance.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(balance));
    }

    /**
     * {@inheritDoc}
     */
    public void update(TBalanceDto balance) throws EmptyResultDataAccessException {
        super.copyLogData(this.find(balance.getAccountId()), balance);
        balance.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, balance.getAccountId()),
                    new BeanPropertySqlParameterSource(balance));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long accountId) throws EmptyResultDataAccessException {
        try {
            namedParameterJdbcTemplate.update(super.getDeleteQuery(TABLE_NAME, COLUMNS, accountId),
                    new BeanPropertySqlParameterSource(new TBalanceDto()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long accountId) throws EmptyResultDataAccessException {
        TBalanceDto balance = this.find(accountId);
        balance.setDeleteFlg(Boolean.TRUE);
        this.update(balance);
    }

    class TBalanceMapper implements RowMapper<TBalanceDto> {
        public TBalanceDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            TBalanceDto balance = new TBalanceDto();
            balance.setAccountId(rs.getLong(ACCOUNT_ID.getColumnName()));
            balance.setBalance(rs.getBigDecimal(BALANCE.getColumnName()));
            balance.setLatestBalanceHistoryId(rs.getLong(LATEST_BALANCE_HISTORY_ID.getColumnName()));
            balance.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            balance.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            balance.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            balance.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            balance.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return balance;
        }
    }

}
