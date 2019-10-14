package self.yang.mybatis.service;

import com.google.common.collect.Maps;
import self.yang.model.domain.ColumnDO;
import self.yang.mybatis.mapper.RootMapper;
import self.yang.util.constant.GeneralConstant;

import java.util.HashMap;
import java.util.List;

/**
 * self.yang.mybatis.service.RootService
 *
 * @author eleven
 * @date 2019/09/20
 */
public abstract class RootService<T extends RootMapper> {

    /**
     * 获取操作映射
     *
     * @return
     */
    protected abstract T getMapper();

    /**
     * 获取当前数据库的所有表名
     *
     * @return
     */
    public List<String> listAllTableNames() {
        return getMapper().listAllTableNames();
    }

    /**
     * 获取当前数据库的所有表和列的映射关系
     *
     * @return
     */
    public HashMap<String, String> mapAllTableAndColumn() {
        List<ColumnDO> columnDOS = getMapper().listAllTableColumns();

        HashMap<String, String> mapColumn = Maps.newHashMap();

        for (ColumnDO columnDO : columnDOS) {
            String tableName = columnDO.getTableName();
            String column = columnDO.getColumnName();

            if (mapColumn.containsKey(tableName)) {
                column = mapColumn.get(tableName) + GeneralConstant.COMMA + column;
            }

            mapColumn.put(tableName, column);
        }

        return mapColumn;
    }
}
