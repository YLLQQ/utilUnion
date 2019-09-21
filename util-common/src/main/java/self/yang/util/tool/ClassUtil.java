package self.yang.util.tool;

import self.yang.util.constant.GeneralConstant;

import java.lang.reflect.*;

/**
 * self.yang.util.tool.ClassUtil
 *
 * @author eleven
 * @date 2019/09/21
 */
public class ClassUtil {

    /**
     * 通过getter获取有效值
     *
     * @param object
     * @param field
     * @return
     */
    public static Object valueByGetter(Object object, Field field) {
        if (!callGetter(field)) {
            return null;
        }

        return invokeGetter(object, field);
    }

    /**
     * 是否可以调用getter方法
     *
     * @param field
     * @return
     */
    public static boolean callGetter(Field field) {
        return fieldIsPrivate(field) && !fieldIsStatic(field);
    }

    /**
     * 跳用getter方法
     *
     * @param object
     * @param field
     * @return
     */
    public static Object invokeGetter(Object object, Field field) {
        String attributeName = field.getName();
        String firstLetter = String.valueOf(attributeName.charAt(0)).toUpperCase();
        String getter = "get" + firstLetter + attributeName.substring(1);

        return invokeModelNoParametersMethod(object, getter);
    }

    /**
     * 调用类的无参方法
     *
     * @param object
     * @param methodName
     * @return
     */
    public static Object invokeModelNoParametersMethod(
            Object object,
            String methodName
    ) {
        try {
            Method method = object.getClass().getMethod(methodName);

            return method.invoke(object);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 属性是否终态
     *
     * @param field
     * @return
     */
    public static boolean fieldIsFinal(Field field) {
        return Modifier.isFinal(field.getModifiers());
    }

    /**
     * 属性是否静态
     *
     * @param field
     * @return
     */
    public static boolean fieldIsStatic(Field field) {
        return Modifier.isStatic(field.getModifiers());
    }

    /**
     * 属性是否私有
     *
     * @param field
     * @return
     */
    public static boolean fieldIsPrivate(Field field) {
        return Modifier.isPrivate(field.getModifiers());
    }

    /**
     * 获取class下的所有属性
     *
     * @param aClass
     * @return
     */
    public static String getFullFields(Class aClass) {
        Field[] fields = aClass.getDeclaredFields();

        StringBuilder stringBuilder = new StringBuilder();

        for (Field field : fields) {
            if (fieldIsStatic(field) || !fieldIsPrivate(field)) {
                continue;
            }

            stringBuilder.append(GeneralConstant.COMMA);
            stringBuilder.append(field.getName());
        }

        return stringBuilder.substring(1);
    }

    /**
     * 通过type获取对应的class
     *
     * @param type
     * @return
     */
    public static Class getClassByType(Type type) {
        try {
            return Class.forName(type.getTypeName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 通过class获取具体范型对象
     *
     * @param aClass
     * @return
     */
    public static Type[] getParadigms(Class aClass) {
        return ((ParameterizedType) aClass.getGenericSuperclass()).getActualTypeArguments();
    }
}
