package self.yang.util.tool;

import self.yang.util.constant.GeneralConstant;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * self.yang.util.tool.TimeUtil
 * <p>
 * 时间操作类
 *
 * @author eleven
 * @date 2019/09/20
 */
public class TimeUtil {

    /**
     * yyyyMMdd
     */
    public static final DateTimeFormatter DATE_FORMAT1 = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * yyyy/MM/dd
     */
    public static final DateTimeFormatter DATE_FORMAT2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    /**
     * yyMMdd
     */
    public static final DateTimeFormatter DATE_FORMAT3 = DateTimeFormatter.ofPattern("yyMMdd");
    /**
     * yy/MM/dd
     */
    public static final DateTimeFormatter DATE_FORMAT4 = DateTimeFormatter.ofPattern("yy/MM/dd");
    /**
     * yyyyMMddHHmmss
     */
    public static final DateTimeFormatter DATETIME_FORMAT5 = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    /**
     * yyyy-MM-dd HH:mm:ss
     */
    public static final DateTimeFormatter DATETIME_FORMAT6 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * yyMMddHHmmss
     */
    public static final DateTimeFormatter DATETIME_FORMAT7 = DateTimeFormatter.ofPattern("yyMMddHHmmss");

    /**
     * 毫秒形式格式化
     *
     * @param time
     * @param dateTimeFormatter
     * @return
     */
    public static String longToDateTime(long time, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime datetime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), GeneralConstant.ZONE_ID);
        return datetime.format(dateTimeFormatter);
    }

    /**
     * 返回当前时间毫秒形式
     *
     * @return
     */
    public static long getCurrentMillions() {
        return System.nanoTime();
    }

    /**
     * 格式化当前时间戳
     *
     * @param dateTimeFormatter
     * @return
     */
    public static String getCurrentDateTime(DateTimeFormatter dateTimeFormatter) {
        return currentDateTime().format(dateTimeFormatter);
    }

    /**
     * 获取当前时间戳
     * <p>
     * 2019-09-20T14:17:30.007
     *
     * @return
     */
    public static String getCurrentDateTime() {
        return currentDateTime().toString();
    }

    /**
     * 格式化当前日期
     *
     * @param dateTimeFormatter
     * @return
     */
    public static String getCurrentDate(DateTimeFormatter dateTimeFormatter) {
        return currentDate().format(dateTimeFormatter);
    }

    /**
     * 获取当前日期
     * <p>
     * 2019-09-20
     *
     * @return
     */
    public static String getCurrentDate() {
        return currentDate().toString();
    }


    /**
     * 2019-09-20T14:14:36.810
     *
     * @return
     */
    private static LocalDateTime currentDateTime() {
        return LocalDateTime.now(GeneralConstant.ZONE_ID);
    }

    /**
     * 2019-09-20
     *
     * @return
     */
    private static LocalDate currentDate() {
        return LocalDate.now(GeneralConstant.ZONE_ID);
    }
}
