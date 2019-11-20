package define.unit.enums;

import define.unit.interfaces.BusinessResponseInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ProjectResponseCodeEnum
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 9:44 上午
 */
@Getter
@AllArgsConstructor
public enum ProjectResponseCodeEnum implements BusinessResponseInterface {

	SUCCESS(200, "响应成功", "response success"),

	CANNOT_CATCH_EXCEPTION(999, "异常暂未捕捉", "cannot catch exception"),
	REQUEST_METHOD_NOT_SUPPORT(1000, "目标接口请求方式错误", "request method not support"),
	GET_VALUE_IS_NULL(1001, "获取有效值为null", "get value is null"),
	JSON_PARSE_FAIL(1002, "json转换失败", "json parse fail"),
	REQUEST_READ_FAIL(1003, "请求体读入失败", "request read fail"),

	;

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应信息
	 */
	private String message;

	/**
	 * 日志信息
	 */
	private String logMessage;
}
