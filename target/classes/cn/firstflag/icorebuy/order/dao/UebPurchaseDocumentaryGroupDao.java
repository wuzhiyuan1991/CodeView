package cn.firstflag.icorebuy.order.dao;

import java.util.List;

import cn.firstflag.icorebuy.common.model.UebPurchaseDocumentaryGroup;
import cn.firstflag.icorebuy.common.BaseDao;
import cn.firstflag.icorebuy.common.dto.Query;

/**<p>Title:采购跟单组别管理 </p>
 * <p>Description: </p>
 * @author zhiyuan.wu
 * @date 2018-02-06 10:27:53
 */
public interface UebPurchaseDocumentaryGroupDao extends BaseDao<UebPurchaseDocumentaryGroup,Integer> {

    List<UebPurchaseDocumentaryGroup> getUebPurchaseDocumentaryGroup (Query query);
}
