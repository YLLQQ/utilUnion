package self.yang.util.constant;

import java.time.ZoneId;

/**
 * self.yang.util.constant.GeneralConstant
 * <p>
 * 通用常量类
 *
 * @author eleven
 * @date 2019/09/20
 */
public class GeneralConstant {

    /**
     * 百分号
     */
    public static final String PERCENT = "%";

    /**
     * 等号
     */
    public static final String EQUALS = "=";

    /**
     * 单引号
     */
    public static final String APOSTROPHE = "'";

    /**
     * 反撇
     */
    public static final String RUMINATING = "`";

    /**
     * 空字符串
     */
    public static final String EMPTY = "";

    /**
     * 逗号
     */
    public static final String COMMA = ",";

    /**
     * 编码格式，UTF-8
     */
    public static final String CHARSET = "UTF-8";

    /**
     * 东八区
     */
    public static final String ZONE = "GMT+08:00";

    /**
     * 时区标示
     */
    public static final ZoneId ZONE_ID = ZoneId.of(ZONE);

}
