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
@Mapper
public interface BaseMapper<D extends BaseDO> {

    String SQL_TABLE_NAME = "tableName";

    String SQL_COLUMNS = "columns";

    String SQL_ID = "id";

    String SQL_SET_STRING = "setString";

    String SQL_INSERT_STRING = "insertString";

    String SQL_WHERE_STRING = "whereString";

    String SQL_GROUP_BY_STRING = "groupByString";

    String SQL_ORDER_BY_STRING = "orderByString";

    /**
     * 增加新值
     *
     * @param tableName
     * @param insertString
     * @return
     */
    @Insert("insert into ${tableName}${insertString}")
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
    @Update("update ${tableName} set ${setString} where id=${id}")
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
    @Delete("delete from ${tableName} where id=${id}")
    boolean deleteById(@Param(SQL_TABLE_NAME) String tableName, @Param(SQL_ID) Number id);

    /**
     * 获取表中的对象
     *
     * @param tableName
     * @param columns
     * @param id
     * @return
     */
    @Select("select ${columns} from ${tableName} where id=${id}")
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
    @Select("select * from ${tableName} where id=${id}")
    D getById(@Param(SQL_TABLE_NAME) String tableName, @Param(SQL_ID) Number id);

    /**
     * 获取筛选查询语句
     *
     * @param tableName
     * @param columns
     * @param whereString
     * @param groupByString
     * @param orderByString
     * @return
     */
    @Select("select ${columns} from ${tableName} ${whereString} ${groupByString} ${orderByString}")
    List<D> list(
            @Param(SQL_TABLE_NAME) String tableName,
            @Param(SQL_COLUMNS) String columns,
            @Param(SQL_WHERE_STRING) String whereString,
            @Param(SQL_GROUP_BY_STRING) String groupByString,
            @Param(SQL_ORDER_BY_STRING) String orderByString
    );
}
