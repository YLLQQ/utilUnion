package self.yang.util.tool;

import com.alibaba.fastjson.JSON;

/**
 * self.yang.util.tool.JsonUtil
 *
 * @author eleven
 * @date 2019/09/21
 */
public class JsonUtil {

    /**
     * 将对象转换为Json字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JSON.toJSONString(object);
    }
}
