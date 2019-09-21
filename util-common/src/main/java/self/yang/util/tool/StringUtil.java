package self.yang.util.tool;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import self.yang.util.constant.GeneralConstant;

/**
 * self.yang.util.tool.StringUtil
 * <p>
 * 字符串操作工具类
 *
 * @author eleven
 * @date 2019/09/20
 */
public class StringUtil {

    /**
     * 拼接SQL值
     *
     * @param value
     * @return
     */
    public static Object appendSqlValue(Object value) {
        if (value instanceof Number || value instanceof Boolean) {
            return value;
        } else {
            return GeneralConstant.APOSTROPHE + value + GeneralConstant.APOSTROPHE;
        }
    }

    /**
     * 首字母小写的驼峰形式转换为下划线分割的小写字符串
     *
     * @param string
     * @return
     */
    public static String lowerCamelToLowerUnderScore(String string) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, string);
    }

    /**
     * 首字母小写的驼峰形式转换为下划线分割的大写字符串
     *
     * @param string
     * @return
     */
    public static String lowerCamelToUpperUnderScore(String string) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, string);
    }

    /**
     * 下划线分割的大写字符串转换为首字母小写的驼峰形式
     *
     * @param string
     * @return
     */
    public static String upperUnderScoreToLowerCamel(String string) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, string);
    }

    /**
     * 下划线分割的小写字符串转换为首字母小写的驼峰形式
     *
     * @param string
     * @return
     */
    public static String lowerUnderScoreToLowerCamel(String string) {
        return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, string);
    }

    /**
     * 使用逗号拆分字符串
     *
     * @param string
     * @return
     */
    public static Iterable<String> splitString(String string) {
        return splitString(string, GeneralConstant.COMMA);
    }

    /**
     * 通过标识符拆分字符串
     *
     * @param string
     * @param flag
     * @return
     */
    public static Iterable<String> splitString(String string, String flag) {
        return Splitter.on(flag).trimResults().omitEmptyStrings().split(string);
    }
}
