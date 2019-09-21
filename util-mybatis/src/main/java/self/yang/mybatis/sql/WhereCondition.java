package self.yang.mybatis.sql;

import self.yang.util.constant.GeneralConstant;
import self.yang.util.tool.StringUtil;

/**
 * self.yang.mybatis.sql.WhereCondition
 *
 * @author eleven
 * @date 2019/09/21
 */
public class WhereCondition {

    /**
     * where条件关键字
     */
    private String key;

    /**
     * where条件具体值
     */
    private Object value;

    /**
     * 值关联操作符
     */
    private WhereValueOperatorEnum whereValueOperatorEnum;

    /**
     * 链接下一条件
     */
    private WhereUnionNextEnum whereUnionNextEnum;

    public WhereCondition(
            String key,
            Object value,
            WhereValueOperatorEnum whereValueOperatorEnum,
            WhereUnionNextEnum whereUnionNextEnum
    ) {
        this.key = GeneralConstant.RUMINATING + key + GeneralConstant.RUMINATING;
        this.value = WhereValueOperatorEnum.like == whereValueOperatorEnum ? GeneralConstant.PERCENT + value + GeneralConstant.PERCENT : StringUtil.appendSqlValue(
                value);
        this.whereValueOperatorEnum = whereValueOperatorEnum;
        this.whereUnionNextEnum = whereUnionNextEnum;
    }

    public void setWhereUnionNextEnum(WhereUnionNextEnum whereUnionNextEnum) {
        this.whereUnionNextEnum = whereUnionNextEnum;
    }

    public WhereUnionNextEnum getWhereUnionNextEnum() {
        return whereUnionNextEnum;
    }

    @Override
    public String toString() {
        return String.format(
                whereValueOperatorEnum.getDesc(), key, value
        ) + (null == whereUnionNextEnum ? GeneralConstant.EMPTY : whereUnionNextEnum);
    }
}
