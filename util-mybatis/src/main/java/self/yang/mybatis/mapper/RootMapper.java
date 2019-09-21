package self.yang.mybatis.mapper;

import org.apache.ibatis.annotations.Select;
import self.yang.mybatis.domain.ColumnDO;

import java.util.List;

/**
 * self.yang.mybatis.mapper.RootMapper
 *
 * @author eleven
 * @date 2019/09/20
 */
public interface RootMapper {
    /**
     * 获取当前数据库的所有表
     *
     * @return
     */
    @Select("select TABLE_NAME from information_schema.TABLES where TABLE_SCHEMA=(select database())")
    List<String> listAllTableNames();

    /**
     * 查询当前数据库的所有列以及其归属的表
     *
     * @return
     */
    @Select("select TABLE_NAME,COLUMN_NAME from information_schema.COLUMNS where TABLE_SCHEMA=(select database()) order by 1")
    List<ColumnDO> listAllTableColumns();
}
