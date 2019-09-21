package self.yang.util.tool;

import org.junit.jupiter.api.Test;

/**
 * self.yang.util.tool.StringUtilTest
 *
 * @author eleven
 * @date 2019/09/20
 */
class StringUtilTest {

    @Test
    void splitString() {
        Iterable<String> strings = StringUtil.splitString("1,2,45,ab");

        strings.forEach(x -> System.out.println(x));
    }

    @Test
    void splitString1() {
        Iterable<String> strings = StringUtil.splitString("1,2,45@ab");

        strings.forEach(x -> System.out.println(x));
    }

    @Test
    void upperUnderScoreToLowerCamel() {
        System.out.println(StringUtil.upperUnderScoreToLowerCamel("USER_NAME"));
    }

    @Test
    void lowerCamelToUpperUnderScore() {
        System.out.println(StringUtil.lowerCamelToUpperUnderScore("userName"));
    }
}