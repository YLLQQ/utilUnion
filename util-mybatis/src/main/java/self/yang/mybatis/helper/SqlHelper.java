package self.yang.mybatis.helper;

import self.yang.mybatis.domain.BaseDO;
import self.yang.mybatis.mapper.BaseMapper;
import self.yang.mybatis.sql.OrderCondition;
import self.yang.mybatis.sql.WhereCondition;
import self.yang.util.constant.GeneralConstant;
import self.yang.util.tool.ClassUtil;
import self.yang.util.tool.StringUtil;

import java.lang.reflect.Field;

/**
 * self.yang.mybatis.helper.SqlHelper
 *
 * @author eleven
 * @date 2019/09/21
 */
public class SqlHelper {

    private static final String WHERE_START = " where";
    private static final String GROUP_BY_START = " group by ";
    private static final String ORDER_BY_START = " order by ";

    /**
     * 获取投影列
     *
     * @param columns
     * @param convert 是否进行小写驼峰命名转驼峰下划线
     * @return
     */
    public static String getColumnString(String[] columns, boolean convert) {
        if (null == columns) {
            return null;
        }

        int length = columns.length;

        if (length == 1) {
            if (convert) {
                return StringUtil.lowerCamelToLowerUnderScore(columns[0]);
            }

            return columns[0];
        }

        StringBuilder columnString = new StringBuilder();

        for (String column : columns) {
            columnString.append(GeneralConstant.COMMA);
            columnString.append(GeneralConstant.RUMINATING);
            if (convert) {
                columnString.append(StringUtil.lowerCamelToLowerUnderScore(column));
            }
            columnString.append(GeneralConstant.RUMINATING);
        }

        return columnString.substring(1);
    }

    /**
     * 获取Order表达式
     *
     * @param orderConditions
     */
    public static String getOrderByString(OrderCondition[] orderConditions) {
        if (null == orderConditions) {
            return GeneralConstant.EMPTY;
        }

        int length = orderConditions.length;

        if (length == 1) {
            return ORDER_BY_START + orderConditions[0].toString();
        }

        StringBuilder stringBuilder = new StringBuilder(ORDER_BY_START);

        for (OrderCondition orderCondition : orderConditions) {
            stringBuilder.append(GeneralConstant.COMMA);
            stringBuilder.append(orderCondition);
        }

        stringBuilder.deleteCharAt(10);

        return stringBuilder.toString();
    }

    /**
     * 获取group by语句
     *
     * @param keys
     * @return
     */
    public static String getGroupByString(String[] keys) {
        if (null == keys) {
            return GeneralConstant.EMPTY;
        }

        int length = keys.length;

        if (length == 1) {
            return GROUP_BY_START + keys[0];
        }

        StringBuilder stringBuilder = new StringBuilder(GROUP_BY_START);

        for (String key : keys) {
            stringBuilder.append(GeneralConstant.COMMA);
            stringBuilder.append(GeneralConstant.RUMINATING);
            stringBuilder.append(key);
            stringBuilder.append(GeneralConstant.RUMINATING);
        }

        stringBuilder.deleteCharAt(10);

        return stringBuilder.toString();
    }

    /**
     * 获取Where表达式
     *
     * @param whereConditions
     */
    public static String getWhereString(WhereCondition[] whereConditions) {
        if (null == whereConditions) {
            return GeneralConstant.EMPTY;
        }

        int length = whereConditions.length;

        if (length == 1) {
            WhereCondition whereCondition = whereConditions[0];

            whereCondition.setWhereUnionNextEnum(null);

            return WHERE_START + whereCondition.toString();
        }

        StringBuilder stringBuilder = new StringBuilder(WHERE_START);

        for (int i = 0; i < length - 1; i++) {
            WhereCondition whereCondition = whereConditions[i];

            stringBuilder.append(whereCondition);

            if (whereCondition.getWhereUnionNextEnum() == null) {
                return stringBuilder.toString();
            }
        }

        WhereCondition whereCondition = whereConditions[length - 1];

        whereCondition.setWhereUnionNextEnum(null);

        stringBuilder.append(whereCondition);

        return stringBuilder.toString();
    }

    /**
     * 获取当前DO的set表达式
     * <p>
     * `account`='admin',`password`='admin'
     *
     * @param baseDO
     * @return
     */
    public static String getSetString(BaseDO baseDO) {
        Class<? extends BaseDO> aClass = baseDO.getClass();
        Field[] fields = aClass.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder();

        try {
            for (Field field : fields) {
                String attributeName = field.getName();

                if (BaseMapper.SQL_ID.equals(attributeName)) {
                    continue;
                }

                Object value = ClassUtil.valueByGetter(baseDO, field);

                if (null == value) {
                    continue;
                }

                stringBuilder.append(GeneralConstant.COMMA);
                stringBuilder.append(GeneralConstant.RUMINATING);
                stringBuilder.append(attributeName);
                stringBuilder.append(GeneralConstant.RUMINATING);
                stringBuilder.append(GeneralConstant.EQUALS);

                stringBuilder.append(StringUtil.appendSqlValue(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stringBuilder.substring(1);
    }

    /**
     * 获取当前DO的insert表达式
     * <p>
     * (`account`,`password`) values('1569052618293','admin')
     *
     * @param baseDO
     * @return
     */
    public static String getInsertString(BaseDO baseDO) {
        Class<? extends BaseDO> aClass = baseDO.getClass();
        Field[] fields = aClass.getDeclaredFields();

        StringBuilder keys = new StringBuilder("(");
        StringBuilder values = new StringBuilder();

        try {
            for (Field field : fields) {
                Object value = ClassUtil.valueByGetter(baseDO, field);

                if (null == value) {
                    continue;
                }

                String attributeName = field.getName();
                keys.append(GeneralConstant.COMMA);
                keys.append(GeneralConstant.RUMINATING);
                keys.append(attributeName);
                keys.append(GeneralConstant.RUMINATING);

                values.append(GeneralConstant.COMMA);
                values.append(StringUtil.appendSqlValue(value));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        keys.deleteCharAt(1);
        keys.append(") values(");
        keys.append(values.substring(1));
        keys.append(")");

        return keys.toString();
    }
}
