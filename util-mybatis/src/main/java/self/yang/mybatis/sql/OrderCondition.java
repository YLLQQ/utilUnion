package self.yang.mybatis.sql;

import self.yang.util.constant.GeneralConstant;

/**
 * self.yang.mybatis.sql.OrderCondition
 *
 * @author eleven
 * @date 2019/09/21
 */
public class OrderCondition {

    private String key;

    private Boolean desc;

    public OrderCondition(String key) {
        this(key, false);
    }

    public OrderCondition(String key, boolean desc) {
        this.key = key;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return GeneralConstant.RUMINATING + key + GeneralConstant.RUMINATING + (desc ? " desc" : " asc");
    }
}
