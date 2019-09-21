package self.yang.util.tool;

import org.junit.jupiter.api.Test;

/**
 * self.yang.util.tool.TimeUtilTest
 *
 * @author eleven
 * @date 2019/09/20
 */
class TimeUtilTest {

    @Test
    void longToDateTime() {
        System.out.println(TimeUtil.longToDateTime(1568963729757l, TimeUtil.DATETIME_FORMAT6));
    }

    @Test
    void getCurrentMillions() {
        System.out.println(TimeUtil.getCurrentMillions());
    }

    @Test
    void getCurrentDateTime() {
        System.out.println(TimeUtil.getCurrentDateTime());
    }

    @Test
    void getCurrentDateTime1() {
        System.out.println(TimeUtil.getCurrentDateTime(TimeUtil.DATETIME_FORMAT5));
    }

    @Test
    void getCurrentDate() {
        System.out.println(TimeUtil.getCurrentDate());
    }

    @Test
    void getCurrentDate1() {
        System.out.println(TimeUtil.getCurrentDate(TimeUtil.DATE_FORMAT1));
    }
}