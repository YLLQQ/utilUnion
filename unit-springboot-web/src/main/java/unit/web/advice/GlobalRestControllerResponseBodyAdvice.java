package unit.web.advice;

import com.alibaba.fastjson.JSON;
import define.unit.enums.ProjectResponseCodeEnum;
import define.unit.model.BusinessResponseModel;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @ClassName GlobalRestControllerResponseBodyAdvice
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 10:42 上午
 */
@RestControllerAdvice
public class GlobalRestControllerResponseBodyAdvice implements ResponseBodyAdvice {
	@Override
	public boolean supports(MethodParameter returnType, Class converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(
			Object body,
			MethodParameter returnType,
			MediaType selectedContentType,
			Class selectedConverterType,
			ServerHttpRequest request,
			ServerHttpResponse response
	) {
		if (null == body) {
			return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.GET_VALUE_IS_NULL);
		}

		if (body instanceof BusinessResponseModel) {
			return body;
		}

		if (body instanceof String) {
			BusinessResponseModel businessResponseModel =
					BusinessResponseModel.getInstance(ProjectResponseCodeEnum.SUCCESS, body);

			return JSON.toJSONString(businessResponseModel);
		}

		return BusinessResponseModel.getInstance(ProjectResponseCodeEnum.SUCCESS, body);
	}


}
