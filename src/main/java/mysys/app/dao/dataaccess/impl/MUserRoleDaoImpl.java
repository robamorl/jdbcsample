package mysys.app.dao.dataaccess.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import mysys.app.biz.domain.MUserRoleDto;
import mysys.app.dao.common.SqlColumn;
import mysys.app.dao.common.SqlCondition;
import mysys.app.dao.dataaccess.MUserRoleDao;
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
public class MUserRoleDaoImpl extends CommonDao implements MUserRoleDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    /** TABLE_NAME */
    private String TABLE_NAME = "SPRING_DEV.M_USER_ROLE";

    /** SQL COLUMN */
    private SqlColumn USER_ID = new SqlColumn("USER_ID", "userId");
    private SqlColumn ROLE_ID = new SqlColumn("ROLE_ID", "roleId");
    private SqlColumn DEFAULT_FLG = new SqlColumn("DEFAULT_FLG", "defaultFlg");

    private List<SqlColumn> COLUMNS = Arrays.asList(new SqlColumn[] {
            USER_ID,
            ROLE_ID,
            DEFAULT_FLG,
            ENTRY_DATE,
            ENTRY_USER,
            UPDATE_DATE,
            UPDATE_USER,
            DELETE_FLG
    });

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto find(Long userId, Long roleId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
        SqlCondition userCondition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, userId);
        SqlCondition roleCcondition = new SqlCondition(COLUMNS.get(1).getColumnName(), SqlCondition.EQ, roleId);
        SqlCondition[] conditions = new SqlCondition[]{userCondition, roleCcondition};
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByCondition(TABLE_NAME, conditions), new MUserMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public MUserRoleDto findWithContainsDeleteRec(Long userId, Long roleId) throws EmptyResultDataAccessException, IncorrectResultSizeDataAccessException {
        SqlCondition userCondition = new SqlCondition(COLUMNS.get(0).getColumnName(), SqlCondition.EQ, userId);
        SqlCondition roleCcondition = new SqlCondition(COLUMNS.get(1).getColumnName(), SqlCondition.EQ, roleId);
        SqlCondition[] conditions = new SqlCondition[]{userCondition, roleCcondition};
        try {
            return jdbcTemplate
                    .queryForObject(super.getSelectQueryByConditionWithContainsDeletedRecord(TABLE_NAME, conditions), new MUserMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        } catch (IncorrectResultSizeDataAccessException e2) {
            throw e2;
        }
    }

    /**
     * {@inheritDoc}
     */
    public List<MUserRoleDto> findAll() {
        try {
            return jdbcTemplate.query(super.getSelectQueryByAll(TABLE_NAME), new MUserMapper());
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void insert(MUserRoleDto userRole) {
        userRole.setEntryData();
        namedParameterJdbcTemplate.update(super.getInsertQuery(TABLE_NAME, COLUMNS),
                new BeanPropertySqlParameterSource(userRole));
    }

    /**
     * {@inheritDoc}
     */
    public void update(MUserRoleDto userRole) throws EmptyResultDataAccessException {
        super.copyLogData(this.find(userRole.getUserId(), userRole.getRoleId()), userRole);
        userRole.setUpdateData();
        try {
            namedParameterJdbcTemplate.update(super.getUpdateQuery(TABLE_NAME, COLUMNS, new Object[]{userRole.getUserId(), userRole.getRoleId()}),
                    new BeanPropertySqlParameterSource(userRole));
        } catch (EmptyResultDataAccessException e1) {
            throw e1;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void logicalDelete(Long userId, Long roleId) throws EmptyResultDataAccessException {
        MUserRoleDto userRole = this.find(userId, roleId);
        userRole.setDeleteFlg(Boolean.TRUE);
        this.update(userRole);
    }

    /**
     * {@inheritDoc}
     */
    public Long getPkByNextVal()  throws DataAccessException {
        return jdbcTemplate.queryForObject(super.getSequenceSelectQuery(USER_ID), Long.class);
    }

    class MUserMapper implements RowMapper<MUserRoleDto> {
        public MUserRoleDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            MUserRoleDto userRole = new MUserRoleDto();
            userRole.setUserId(rs.getLong(USER_ID.getColumnName()));
            userRole.setRoleId(rs.getLong(ROLE_ID.getColumnName()));
            userRole.setDefaultFlg(rs.getBoolean(DEFAULT_FLG.getColumnName()));
            userRole.setEntryDate(rs.getTimestamp(CommonDao.ENTRY_DATE.getColumnName()));
            userRole.setEntryUser(rs.getString(CommonDao.ENTRY_USER.getColumnName()));
            userRole.setUpdateDate(rs.getTimestamp(CommonDao.UPDATE_DATE.getColumnName()));
            userRole.setUpdateUser(rs.getString(CommonDao.UPDATE_USER.getColumnName()));
            userRole.setDeleteFlg(rs.getBoolean(CommonDao.DELETE_FLG.getColumnName()));
            return userRole;
        }
    }

}
