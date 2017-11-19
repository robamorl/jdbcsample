package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.MAccountDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.MAccountDao;
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
public class MAccountDaoImpl extends CommonDao implements MAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.M_ACCOUNT";

    /** SQL COLUMN */
    private SqlColumn ACCOUNT_ID = new SqlColumn("ACCOUNT_ID", "accountId");
    private SqlColumn ACCOUNT_NUMBER = new SqlColumn("ACCOUNT_NUMBER", "accountNumber");
    private SqlColumn USER_ID = new SqlColumn("USER_ID", "userId");
    private SqlColumn ACCOUNT_KUBUN = new SqlColumn("ACCOUNT_KUBUN", "accountKubun");

    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            ACCOUNT_ID,
            ACCOUNT_NUMBER,
            USER_ID,
            ACCOUNT_KUBUN,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public MAccountDto find(Long accountId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, accountId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new MAccountMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MAccountDto> findAllByUserId(Long userId) {
        SqlCondition condition = new SqlCondition(USER_ID.getColumnName(), SqlCondition.EQ, userId);
        try {
            return jdbcTemplate.query(super.getSelectQueryByCondition(TABLE_NAME, condition), new MAccountMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MAccountDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new MAccountMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(MAccountDto account) {
        account.setAccountId(this.getPkByNextVal());
        account.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(account));
    }

    /**
     * {@inheritDoc}
     */
    public void update(MAccountDto account) throws EmptyResultDataAccessException {
        account.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, account.getAccountId()),
                    new BeanPropertySqlParameterSource(account));
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
                    new BeanPropertySqlParameterSource(new MAccountDto()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long accountId) throws EmptyResultDataAccessException {
        MAccountDto account = this.find(accountId);
        account.setDeleteFlg(Boolean.TRUE);
        this.update(account);
    }

    /**
     * {@inheritDoc}
     */
    public Long getPkByNextVal()  throws DataAccessException {
        return jdbcTemplate.queryForObject(super.getSequenceSelectQuery(ACCOUNT_ID), Long.class);
    }

    class MAccountMapper implements RowMapper<MAccountDto> {
        public MAccountDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            MAccountDto account = new MAccountDto();
            account.setAccountId(rs.getLong(ACCOUNT_ID.getColumnName()));
            account.setAccountNumber(rs.getLong(ACCOUNT_NUMBER.getColumnName()));
            account.setUserId(rs.getLong(USER_ID.getColumnName()));
            account.setAccountKubun(rs.getString(ACCOUNT_KUBUN.getColumnName()));
            account.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            account.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            account.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            account.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            account.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return account;
        }
    }

}
