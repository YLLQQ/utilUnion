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

	REQUEST_METHOD_NOT_SUPPORT(1000, "目标接口请求方式错误", "request method not support"),

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
