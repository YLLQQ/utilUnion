package self.yang.model.domain;

import lombok.Data;

import java.util.List;

/**
 * self.yang.model.domain.PageModel
 *
 * @author eleven
 * @date 2019/09/23
 */
@Data
public class PageModel<D> {

    /**
     * 当前页码
     */
    private int page;

    /**
     * 总页数
     */
    private int pageTotal;

    /**
     * 当前页码容量
     */
    private int pageSize;

    /**
     * 总条数
     */
    private long recordTotal;

    /**
     * 数据列表
     */
    private List<D> list;

}
