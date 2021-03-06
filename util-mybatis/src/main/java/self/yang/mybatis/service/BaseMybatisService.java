package self.yang.mybatis.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import self.yang.model.domain.BaseDO;
import self.yang.model.domain.PageModel;
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
 * self.yang.mybatis.service.BaseMybatisService
 *
 * @author eleven
 * @date 2019/09/21
 */
public abstract class BaseMybatisService<D extends BaseDO> {

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
    protected abstract BaseMapper<D> getMapper();

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
    public D getWithOptionalColumnsById(Number id, String[] columns) {
        String optionalColumn = SqlHelper.getColumnString(columns, isLowerCamelToLowerUnderScore());

        return (D) getMapper().getWithColumnsById(getTableName(), optionalColumn, id);
    }

    /**
     * 获取表中的对象
     *
     * @param id
     * @return
     */
    public D getWithColumnsById(Number id) {

        String columns = getFullColumnsByClass();

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
     * @return
     */
    public List<D> listAll() {
        return this.list(null, null, null, null);
    }

    /**
     * 筛选语句
     *
     * @param orderConditions
     * @return
     */
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
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
    public List<D> listByCondition(
            String[] columns, WhereCondition[] whereConditions, String[] groupByConditions
    ) {
        return this.listByCondition(columns, whereConditions, groupByConditions, null);
    }

    /**
     * 分页筛选语句
     *
     * @param columns
     * @param whereConditions
     * @param groupByConditions
     * @param orderConditions
     * @param page
     * @param size
     * @return
     */
    public PageModel<D> listByConditionWithPage(
            String[] columns, WhereCondition[] whereConditions, String[] groupByConditions,
            OrderCondition[] orderConditions, int page, int size
    ) {
        String columnString = SqlHelper.getColumnString(columns, isLowerCamelToLowerUnderScore());
        String whereString = SqlHelper.getWhereString(whereConditions);
        String groupByString = SqlHelper.getGroupByString(groupByConditions);
        String orderByString = SqlHelper.getOrderByString(orderConditions);

        return this.listWithPage(columnString, whereString, groupByString, orderByString, page, size);
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
    public List<D> listByCondition(
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
     * 分页筛选语句
     *
     * @param columnString  投影列,默认为表的全部列
     * @param whereString   where条件语句
     * @param groupByString groupBy条件语句
     * @param orderByString orderBy条件语句
     * @return
     */
    private PageModel<D> listWithPage(
            String columnString, String whereString, String groupByString, String orderByString, int page, int size
    ) {
        if (StringUtil.isEmpty(columnString)) {
            columnString = getFullColumnsByClass();
        }

        PageHelper.startPage(page, size);

        Page<D> withPage = getMapper().listWithPage(
                getTableName(), columnString, whereString, groupByString, orderByString);

        List<D> list = Lists.newArrayList(withPage);

        PageModel<D> pageModel = new PageModel<>();

        pageModel.setPage(withPage.getPageNum());
        pageModel.setPageSize(list.size());
        pageModel.setPageTotal(withPage.getPages());
        pageModel.setRecordTotal(withPage.getTotal());
        pageModel.setList(list);

        return pageModel;
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
        Class<? extends BaseMybatisService> aClass = this.getClass();
        String columns = mapTableAndColumn.get(aClass);

        if (null == columns) {
            Type[] paradigms = ClassUtil.getParadigms(aClass);
            Type type = paradigms[0];
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
