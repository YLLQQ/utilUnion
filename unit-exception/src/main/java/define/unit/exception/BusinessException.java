package define.unit.exception;

import define.unit.interfaces.BusinessResponseInterface;
import define.unit.model.BusinessResponseModel;

/**
 * @ClassName BusinessException
 * @Description TODO
 * @Author yangguoqing
 * @Date 2019/11/20 9:30 上午
 */
public class BusinessException extends RuntimeException {

	private BusinessResponseInterface businessResponseInterface;

	public BusinessException(BusinessResponseInterface businessResponseInterface) {
		super(businessResponseInterface.getLogMessage());
		this.businessResponseInterface = businessResponseInterface;
	}

	public BusinessResponseModel toBusinessResponse() {
		return BusinessResponseModel.getInstance(businessResponseInterface);
	}
}
