package self.yang.mybatis.sql;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * self.yang.mybatis.sql.WhereValueOperatorEnum
 *
 * @author eleven
 * @date 2019/09/21
 */
@Getter
@AllArgsConstructor
public enum WhereValueOperatorEnum {
    equals(" %s=%s "),
    not_equals(" %s<>%s "),
    larger(" %s>%s "),
    larger_equals(" %s>=%s "),
    lower(" %s<%s "),
    lower_equals(" %s<=%s "),
    is_null(" %s is null "),
    like(" %s like '%s' ");

    private String desc;
}