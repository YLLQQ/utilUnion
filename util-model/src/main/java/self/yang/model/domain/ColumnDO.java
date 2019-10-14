package self.yang.model.domain;

import lombok.Data;

/**
 * self.yang.model.domain.ColumnDO
 * <p>
 * 表和列映射关系
 *
 * @author eleven
 * @date 2019/09/20
 */
@Data
public class ColumnDO {

    private String tableName;

    private String columnName;
}
