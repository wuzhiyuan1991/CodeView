package cn.com.shopec.core.common;

import java.io.Serializable;

/**
 * 数据对象基础操作类
 * @author yong
 * 
 */
public interface BaseDao<T,PK extends Serializable> {
	
	/**
	 * 增加对象
	 * @param obj
	 */
	public void add(T obj);

	/**
	 * 修改对象
	 * @param obj
	 */
	public int update(T obj);

	/**
	 * 删除对象
	 * @param pk
	 */
	public int delete(PK pk);

	/**
	 * 得到某个对象
	 * @param pk
	 */
	public T get(PK pk);

    /**
     * 得到一组对象
     */
    public List<T> getByIds(PK[] ids);

}
