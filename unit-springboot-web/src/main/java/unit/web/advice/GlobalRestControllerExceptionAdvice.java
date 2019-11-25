package unit.web.advice;

import com.fasterxml.jackson.core.JsonParseException;
import define.unit.enums.ProjectResponseCodeEnum;
import define.unit.exception.BusinessException;
import define.unit.model.BusinessResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
	 * SQL表达式错误
	 *
	 * @return
	 */
	@ExceptionHandler(value = BadSqlGrammarException.class)
	public BusinessResponseModel badSqlGrammarExceptionHandler() {
		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.BAD_SQL_GRAMMAR);
	}

	/**
	 * 方法必要参数未匹配
	 *
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public BusinessResponseModel methodArgumentNotValidExceptionHandler() {
		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.METHOD_ARGUMENT_NOT_VALID);
	}

	/**
	 * json转换失败
	 *
	 * @return
	 */
	@ExceptionHandler(value = HttpMessageNotReadableException.class)
	public BusinessResponseModel httpMessageNotReadableException(HttpMessageNotReadableException e) {
		log.error("request body convert fail", e);

		Throwable cause = e.getCause();

		if (null != cause) {
			if (cause instanceof JsonParseException) {
				return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.JSON_PARSE_FAIL);
			}
		}

		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.REQUEST_READ_FAIL);
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
