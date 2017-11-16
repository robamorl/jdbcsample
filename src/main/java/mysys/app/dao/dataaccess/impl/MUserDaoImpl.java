package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.MUser;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.MUserDao;
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
public class MUserDaoImpl extends CommonDao implements MUserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.M_USER";

    /** SQL COLUMN */
    private SqlColumn USER_ID = new SqlColumn("USER_ID", "userId");
    private SqlColumn USER_CODE = new SqlColumn("USER_CODE", "userCode");
    private SqlColumn PASSWORD = new SqlColumn("PASSWORD", "password");
    private SqlColumn USER_NAME = new SqlColumn("USER_NAME", "userName");
    private SqlColumn MAIL_ADDRESS = new SqlColumn("MAIL_ADDRESS", "mailAddress");

    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            USER_ID,
            USER_CODE,
            PASSWORD,
            USER_NAME,
            MAIL_ADDRESS,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public MUser find(Long userId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
        SqlCondition condition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, userId);
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, condition), new MUserMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MUser> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new MUserMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(MUser user) {
        user.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(user));
    }

    /**
     * {@inheritDoc}
     */
    public void update(MUser user) throws EmptyResultDataAccessException {
        user.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, user.getUserId()),
                    new BeanPropertySqlParameterSource(user));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long userId) throws EmptyResultDataAccessException {
        try {
            namedParameterJdbcTemplate.update(super.getDeleteQuery(TABLE_NAME, COLUMNS, userId),
                    new BeanPropertySqlParameterSource(new MUser()));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long userId) throws EmptyResultDataAccessException {
        MUser user = this.find(userId);
        user.setDeleteFlg(Boolean.TRUE);
        this.update(user);
    }

    class MUserMapper implements RowMapper<MUser> {
        public MUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            MUser user = new MUser();
            user.setUserId(rs.getLong(USER_ID.getColumnName()));
            user.setUserCode(rs.getString(USER_CODE.getColumnName()));
            user.setPassword(rs.getString(PASSWORD.getColumnName()));
            user.setUserName(rs.getString(USER_NAME.getColumnName()));
            user.setMailAddress(rs.getString(MAIL_ADDRESS.getColumnName()));
            user.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            user.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            user.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            user.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            user.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return user;
        }
    }

}
