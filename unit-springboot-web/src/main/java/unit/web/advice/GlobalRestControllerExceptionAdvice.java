package unit.web.advice;

import com.fasterxml.jackson.core.JsonParseException;
import define.unit.enums.ProjectResponseCodeEnum;
import define.unit.exception.BusinessException;
import define.unit.model.BusinessResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalRestControllerExceptionAdvice
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 10:02 上午
 */
@Slf4j
@RestControllerAdvice
public class GlobalRestControllerExceptionAdvice {


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
	 * json转换失败
	 *
	 * @return
	 */
	@ExceptionHandler(value = JsonParseException.class)
	public BusinessResponseModel jsonParseException() {
		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.JSON_PARSE_FAIL);
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

	/**
	 * 处理其他异常
	 *
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	public BusinessResponseModel exception(Exception e) {
		log.error("cannot deal the exception", e);

		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.CANNOT_CATCH_EXCEPTION);
	}
}
