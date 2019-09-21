package self.yang.mybatis.service;

import self.yang.mybatis.domain.BaseDO;
import self.yang.mybatis.helper.SqlHelper;
import self.yang.mybatis.mapper.BaseMapper;
import self.yang.mybatis.sql.WhereCondition;
import self.yang.util.constant.GeneralConstant;
import self.yang.util.tool.ClassUtil;
import self.yang.util.tool.StringUtil;

import java.lang.reflect.Type;
import java.util.List;
import java.util.StringJoiner;
import java.util.concurrent.ConcurrentHashMap;

/**
 * self.yang.mybatis.service.BaseService
 *
 * @author eleven
 * @date 2019/09/21
 */
public abstract class BaseService<M extends BaseMapper, D extends BaseDO> {

    private static final ConcurrentHashMap<Class, String> mapTableAndColumn = new ConcurrentHashMap();

    /**
     * 是否使用属性小写驼峰命名转小写下划线分割
     */
    private boolean lowerCamelToLowerUnderScore = true;

    public boolean isLowerCamelToLowerUnderScore() {
        return lowerCamelToLowerUnderScore;
    }

    /**
     * 获取操作映射
     *
     * @return
     */
    protected abstract M getMapper();

    /**
     * 基础映射操作的表名
     *
     * @return
     */
    protected abstract String getTableName();

    /**
     * 增加新值
     *
     * @param d
     * @return
     */
    public boolean add(D d) {
        return getMapper().add(getTableName(), SqlHelper.getInsertString(d));
    }

    /**
     * 通过主键自增编号修改数据
     *
     * @param d
     * @return
     */
    public boolean updateById(D d) {
        return getMapper().updateById(getTableName(), SqlHelper.getSetString(d), d.getId());
    }

    /**
     * 删除表中的数据
     *
     * @param id
     */
    public boolean deleteById(Number id) {
        return getMapper().deleteById(getTableName(), id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @param columns
     * @return
     */
    public D getWithOptionalColumnsById(Number id, String... columns) {
        String optionalColumn = columnsToSql(columns);

        return (D) getMapper().getWithColumnsById(getTableName(), optionalColumn, id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @return
     */
    public D getWithColumnsById(Number id) {
        String columns = getFullColumnsByClass(this.getClass());

        return (D) getMapper().getWithColumnsById(getTableName(), columns, id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @return
     */
    public D getById(Number id) {
        return (D) getMapper().getById(getTableName(), id);
    }

    /**
     * 获取表中的所有对象
     *
     * @param columns
     * @return
     */
    public List<D> listAllWithOptionalColumns(String... columns) {
        String optionalColumn = columnsToSql(columns);

        return getMapper().listAllWithColumns(getTableName(), optionalColumn);
    }

    /**
     * 获取表中的所有对象
     *
     * @param whereConditions
     * @return
     */
    public List<D> listAllWithColumnsAndWhereString(WhereCondition... whereConditions) {
        String columns = getFullColumnsByClass(this.getClass());
        String whereString = SqlHelper.getWhereString(whereConditions);

        return getMapper().listAllWithColumnsAndWhereString(getTableName(), columns, whereString, GeneralConstant.EMPTY,
                GeneralConstant.EMPTY
        );
    }

    /**
     * 获取表中的所有对象
     *
     * @return
     */
    public List<D> listAllWithColumns() {
        String columns = getFullColumnsByClass(this.getClass());

        return getMapper().listAllWithColumns(getTableName(), columns);
    }

    /**
     * 自定义where条件
     *
     * @param whereConditions
     * @return
     */
    public List<D> listAllWithWhereString(WhereCondition... whereConditions) {
        String whereString = SqlHelper.getWhereString(whereConditions);

        return getMapper().listAllWithWhereString(getTableName(), whereString, GeneralConstant.EMPTY,
                GeneralConstant.EMPTY
        );
    }

    /**
     * 获取表中的所有对象
     *
     * @return
     */
    public List<D> listAll() {
        return getMapper().listAll(getTableName());
    }

    private String columnsToSql(String... columns) {
        StringJoiner stringJoiner = new StringJoiner(",", "", "");

        for (String column : columns) {
            stringJoiner.add(GeneralConstant.RUMINATING + column + GeneralConstant.RUMINATING);
        }

        String optionalColumn = stringJoiner.toString();

        if (isLowerCamelToLowerUnderScore()) {
            optionalColumn = StringUtil.lowerCamelToLowerUnderScore(optionalColumn);
        }

        return optionalColumn;
    }

    private String getFullColumnsByClass(Class aClass) {
        String columns = mapTableAndColumn.get(aClass);

        if (null == columns) {
            Type[] paradigms = ClassUtil.getParadigms(aClass);
            Type type = paradigms[1];
            Class classByType = ClassUtil.getClassByType(type);
            columns = ClassUtil.getFullFields(classByType);

            if (isLowerCamelToLowerUnderScore()) {
                columns = StringUtil.lowerCamelToLowerUnderScore(columns);
            }

            mapTableAndColumn.put(aClass, columns);
        }

        return columns;
    }
}
