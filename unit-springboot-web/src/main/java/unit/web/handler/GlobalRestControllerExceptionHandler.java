package unit.web.handler;

import define.unit.enums.ProjectResponseCodeEnum;
import define.unit.exception.BusinessException;
import define.unit.model.BusinessResponseModel;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalRestControllerExceptionHandler
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 10:02 上午
 */
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {


	/**
	 * 业务逻辑异常处理
	 *
	 * @param e 业务逻辑异常
	 * @return
	 */
	@ExceptionHandler(value = BusinessException.class)
	public BusinessResponseModel serviceExceptionHandler(BusinessException e) {
		return e.toBusinessResponse();
	}

	/**
	 * 请求方法不支持异常处理
	 *
	 * @return
	 */
	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public BusinessResponseModel httpRequestMethodNotSupportedExceptionHandler() {
		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.REQUEST_METHOD_NOT_SUPPORT);
	}
}
