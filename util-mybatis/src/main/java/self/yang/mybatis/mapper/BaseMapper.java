package self.yang.mybatis.mapper;

import org.apache.ibatis.annotations.*;
import self.yang.mybatis.domain.BaseDO;

import java.util.List;

/**
 * self.yang.mybatis.mapper.BaseMapper
 *
 * @author eleven
 * @date 2019/09/21
 */
public interface BaseMapper<D extends BaseDO> {

    String SQL_TABLE_NAME = "tableName";

    String SQL_COLUMNS = "columns";

    String SQL_ID = "id";

    String SQL_SET_STRING = "setString";

    String SQL_INSERT_STRING = "insertString";

    String SQL_WHERE_STRING = "whereString";

    String SQL_GROUP_BY_STRING = "groupByString";

    String SQL_ORDER_BY_STRING = "orderByString";

    String BASE_SELECT_SQL = "select * from ${tableName}";

    String BASE_SELECT_DETAIL_SQL = "select ${columns} from ${tableName}";

    String BASE_DELETE_SQL = "delete from ${tableName}";

    String BASE_UPDATE_SQL = "update ${tableName} set ${setString}";

    String BASE_INSERT_SQL = "insert into ${tableName}${insertString}";

    String WHERE_ID = " where id=${id}";

    String WHERE_OPTIONAL = " ${whereString}";

    String GROUP_BY_OPTIONAL = " ${groupByString}";

    String ORDER_BY_OPTIONAL = " ${orderByString}";

    /**
     * 增加新值
     *
     * @param tableName
     * @param insertString
     * @return
     */
    @Insert(BASE_INSERT_SQL)
    boolean add(
            @Param(SQL_TABLE_NAME) String tableName, @Param(SQL_INSERT_STRING) String insertString
    );

    /**
     * 通过主键自增编号修改数据
     *
     * @param tableName
     * @param setString
     * @param id
     * @return
     */
    @Update(BASE_UPDATE_SQL + WHERE_ID)
    boolean updateById(
            @Param(SQL_TABLE_NAME) String tableName,
            @Param(SQL_SET_STRING) String setString,
            @Param(SQL_ID) Number id
    );

    /**
     * 删除表中的数据
     *
     * @param tableName
     * @param id
     * @return
     */
    @Delete(BASE_DELETE_SQL + WHERE_ID)
    boolean deleteById(@Param(SQL_TABLE_NAME) String tableName, @Param(SQL_ID) Number id);

    /**
     * 获取表中的对象
     *
     * @param tableName
     * @param columns
     * @param id
     * @return
     */
    @Select(BASE_SELECT_DETAIL_SQL + WHERE_ID)
    D getWithColumnsById(
            @Param(SQL_TABLE_NAME) String tableName,
            @Param(SQL_COLUMNS) String columns,
            @Param(SQL_ID) Number id
    );

    /**
     * 获取表中的对象
     *
     * @param tableName
     * @param id
     * @return
     */
    @Select(BASE_SELECT_SQL + WHERE_ID)
    D getById(@Param(SQL_TABLE_NAME) String tableName, @Param(SQL_ID) Number id);

    /**
     * 获取表中的所有对象
     *
     * @param tableName
     * @param columns
     * @param whereString
     * @param groupByString
     * @param orderByString
     * @return
     */
    @Select(BASE_SELECT_DETAIL_SQL + WHERE_OPTIONAL + GROUP_BY_OPTIONAL + ORDER_BY_OPTIONAL)
    List<D> listAllWithColumnsAndWhereString(
            @Param(SQL_TABLE_NAME) String tableName,
            @Param(SQL_COLUMNS) String columns,
            @Param(SQL_WHERE_STRING) String whereString,
            @Param(SQL_GROUP_BY_STRING) String groupByString,
            @Param(SQL_ORDER_BY_STRING) String orderByString
    );

    /**
     * 获取表中的所有对象
     *
     * @param tableName
     * @param whereString
     * @param groupByString
     * @param orderByString
     * @return
     */
    @Select(BASE_SELECT_SQL + WHERE_OPTIONAL + GROUP_BY_OPTIONAL + ORDER_BY_OPTIONAL)
    List<D> listAllWithWhereString(
            @Param(SQL_TABLE_NAME) String tableName,
            @Param(SQL_WHERE_STRING) String whereString,
            @Param(SQL_GROUP_BY_STRING) String groupByString,
            @Param(SQL_ORDER_BY_STRING) String orderByString
    );

    /**
     * 获取表中的所有对象
     *
     * @param tableName
     * @param columns
     * @return
     */
    @Select(BASE_SELECT_DETAIL_SQL)
    List<D> listAllWithColumns(@Param(SQL_TABLE_NAME) String tableName, @Param(SQL_COLUMNS) String columns);

    /**
     * 获取表中的所有对象
     *
     * @param tableName
     * @return
     */
    @Select(BASE_SELECT_SQL)
    List<D> listAll(@Param(SQL_TABLE_NAME) String tableName);
}
