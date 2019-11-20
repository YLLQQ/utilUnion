package define.unit.model;

import define.unit.interfaces.BusinessResponseInterface;
import lombok.Getter;

/**
 * @ClassName BusinessResponseModel
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 9:33 上午
 */
@Getter
public class BusinessResponseModel {

	/**
	 * 响应码
	 */
	private int code;

	/**
	 * 响应信息
	 */
	private String message;

	/**
	 * 响应结果
	 */
	private Object result;

	private BusinessResponseModel(int code, String message, Object result) {
		this.code = code;
		this.message = message;

		if (null != result) {
			this.result = result;
		}
	}

	public static BusinessResponseModel getInstance(BusinessResponseInterface businessResponseInterface, Object o) {
		int code = businessResponseInterface.getCode();
		String message = businessResponseInterface.getMessage();

		return new BusinessResponseModel(code, message, o);
	}

	public static BusinessResponseModel getInstance(BusinessResponseInterface businessResponseInterface) {
		int code = businessResponseInterface.getCode();
		String message = businessResponseInterface.getMessage();

		return new BusinessResponseModel(code, message, null);
	}
}
