package self.yang.redis.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * self.yang.redis.common.ExpireTimeEnum
 *
 * @author eleven
 * @date 2019/09/25
 */
@Getter
@AllArgsConstructor
public enum ExpireTimeEnum {

    ONE_MINUTE(1, TimeUnit.MINUTES),
    TEN_MINUTES(10, TimeUnit.MINUTES),
    HALF_AN_HOUR(30, TimeUnit.MINUTES),
    ONE_HOUR(1, TimeUnit.HOURS),
    ONE_DAY(1, TimeUnit.DAYS);

    private long time;

    private TimeUnit timeUnit;
}
