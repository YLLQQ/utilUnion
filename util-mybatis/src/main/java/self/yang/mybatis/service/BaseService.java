package self.yang.mybatis.service;

import self.yang.mybatis.domain.BaseDO;
import self.yang.mybatis.helper.SqlHelper;
import self.yang.mybatis.mapper.BaseMapper;
import self.yang.mybatis.sql.OrderCondition;
import self.yang.mybatis.sql.WhereCondition;
import self.yang.util.tool.ClassUtil;
import self.yang.util.tool.StringUtil;

import java.lang.reflect.Type;
import java.util.List;
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
    protected boolean add(D d) {
        return getMapper().add(getTableName(), SqlHelper.getInsertString(d));
    }

    /**
     * 通过主键自增编号修改数据
     *
     * @param d
     * @return
     */
    protected boolean updateById(D d) {
        return getMapper().updateById(getTableName(), SqlHelper.getSetString(d), d.getId());
    }

    /**
     * 删除表中的数据
     *
     * @param id
     */
    protected boolean deleteById(Number id) {
        return getMapper().deleteById(getTableName(), id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @param columns
     * @return
     */
    protected D getWithOptionalColumnsById(Number id, String[] columns) {
        String optionalColumn = SqlHelper.getColumnString(columns, isLowerCamelToLowerUnderScore());

        return (D) getMapper().getWithColumnsById(getTableName(), optionalColumn, id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @return
     */
    protected D getWithColumnsById(Number id) {
        String columns = getFullColumnsByClass();

        return (D) getMapper().getWithColumnsById(getTableName(), columns, id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @return
     */
    protected D getById(Number id) {
        return (D) getMapper().getById(getTableName(), id);
    }


    /**
     * 获取表中的所有对象
     *
     * @return
     */
    protected List<D> listAll() {
        return this.list(null, null, null, null);
    }

    /**
     * 筛选语句
     *
     * @param orderConditions
     * @return
     */
    protected List<D> listByCondition(
            OrderCondition[] orderConditions
    ) {
        return this.listByCondition(null, null, null, orderConditions);
    }

    /**
     * 筛选语句
     *
     * @param orderConditions
     * @return
     * @Param columns
     */
    protected List<D> listByCondition(
            String[] columns,
            OrderCondition[] orderConditions
    ) {
        return this.listByCondition(columns, null, null, orderConditions);
    }

    /**
     * 筛选语句
     *
     * @param whereConditions
     * @param orderConditions
     * @return
     */
    protected List<D> listByCondition(
            WhereCondition[] whereConditions, OrderCondition[] orderConditions
    ) {
        return this.listByCondition(null, whereConditions, null, orderConditions);
    }

    /**
     * 筛选语句
     *
     * @param whereConditions
     * @param groupByConditions
     * @return
     */
    protected List<D> listByCondition(
            WhereCondition[] whereConditions, String[] groupByConditions
    ) {
        return this.listByCondition(null, whereConditions, groupByConditions, null);
    }

    /**
     * 筛选语句
     *
     * @param whereConditions
     * @return
     */
    protected List<D> listByCondition(
            WhereCondition[] whereConditions
    ) {
        return this.listByCondition(null, whereConditions);
    }

    /**
     * 筛选语句
     *
     * @param columns
     * @return
     */
    protected List<D> listByCondition(
            String[] columns
    ) {
        return this.listByCondition(columns, null, null, null);
    }

    /**
     * 筛选语句
     *
     * @param columns
     * @param whereConditions
     * @return
     */
    protected List<D> listByCondition(
            String[] columns, WhereCondition[] whereConditions
    ) {
        return this.listByCondition(columns, whereConditions, null);
    }

    /**
     * 筛选语句
     *
     * @param columns
     * @param whereConditions
     * @param groupByConditions
     * @return
     */
    protected List<D> listByCondition(
            String[] columns, WhereCondition[] whereConditions, String[] groupByConditions
    ) {
        return this.listByCondition(columns, whereConditions, groupByConditions, null);
    }

    /**
     * 筛选语句
     *
     * @param columns
     * @param whereConditions
     * @param groupByConditions
     * @param orderConditions
     * @return
     */
    protected List<D> listByCondition(
            String[] columns, WhereCondition[] whereConditions, String[] groupByConditions,
            OrderCondition[] orderConditions
    ) {
        String columnString = SqlHelper.getColumnString(columns, isLowerCamelToLowerUnderScore());
        String whereString = SqlHelper.getWhereString(whereConditions);
        String groupByString = SqlHelper.getGroupByString(groupByConditions);
        String orderByString = SqlHelper.getOrderByString(orderConditions);

        return this.list(columnString, whereString, groupByString, orderByString);
    }

    /**
     * 筛选语句
     *
     * @param columnString  投影列,默认为表的全部列
     * @param whereString   where条件语句
     * @param groupByString groupBy条件语句
     * @param orderByString orderBy条件语句
     * @return
     */
    private List<D> list(
            String columnString, String whereString, String groupByString, String orderByString
    ) {
        if (StringUtil.isEmpty(columnString)) {
            columnString = getFullColumnsByClass();
        }

        return getMapper().list(getTableName(), columnString, whereString, groupByString, orderByString);
    }

    private String getFullColumnsByClass() {
        Class<? extends BaseService> aClass = this.getClass();
        String columns = mapTableAndColumn.get(aClass);

        if (null == columns) {
            Type[] paradigms = ClassUtil.getParadigms(aClass);
            Type type = paradigms[1];
            Class classByType = ClassUtil.getClassByType(type);
            columns = ClassUtil.getFullFields(classByType, true);

            if (isLowerCamelToLowerUnderScore()) {
                columns = StringUtil.lowerCamelToLowerUnderScore(columns);
            }

            mapTableAndColumn.put(aClass, columns);
        }

        return columns;
    }
}
