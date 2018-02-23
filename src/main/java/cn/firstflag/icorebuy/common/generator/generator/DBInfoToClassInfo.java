
package cn.firstflag.icorebuy.common.generator.generator;

import cn.firstflag.icorebuy.common.generator.classdata.ClassElement;
import cn.firstflag.icorebuy.common.generator.classdata.ElementInfo;
import cn.firstflag.icorebuy.common.generator.classdata.IndexInfo;
import cn.firstflag.icorebuy.common.generator.commons.MetaDataUtils;
import cn.firstflag.icorebuy.common.generator.commons.StringUtils;
import cn.firstflag.icorebuy.common.generator.database.JdbcMetaData;
import cn.firstflag.icorebuy.common.generator.database.TableInfo;
import cn.firstflag.icorebuy.common.generator.database.TcolumnInfo;
import cn.firstflag.icorebuy.common.generator.database.TindexInfo;

import java.util.*;
import java.util.Map.Entry;

/**
 * 数据库转换成类信息数据
 * @author yong
 *
 */
public class DBInfoToClassInfo {

	/**
	 * 得到所有类信息
	 * @param gparam
	 * @return
	 */
	public List<ClassElement> getAllClassInfo(GeneratorParam gparam, Map<String, String> dataType)
	{

		// 获得所有表
		List<TableInfo> tablesList = JdbcMetaData.getTablesList();
		// 整理出需要的表
		tablesList = neatenTalbe(tablesList, gparam);

		if (null == tablesList || tablesList.isEmpty())
		{
			return null;
		}

		List<ClassElement> classInfoList = new ArrayList<ClassElement>();

		for (TableInfo t : tablesList)
		{
			ClassElement classInfo = getClassInfo(t, gparam, dataType);
			if (null != classInfo)
			{
				classInfoList.add(classInfo);
			}
		}

		return classInfoList;

	}

	/**
	 * 得到表对应的类信息
	 * @param tableInfo
	 * @param gparam
	 * @param dataType
	 * @return
	 */
	public ClassElement getClassInfo(TableInfo tableInfo, GeneratorParam gparam, Map<String, String> dataType)
	{

		if (null == tableInfo || StringUtils.isEmpty(tableInfo.getTableName()))
		{
			return null;
		}

		// 得到类信息
		ClassElement ce = getClassElement(tableInfo, gparam, dataType);
		// 得到类属性
		List<ElementInfo> dbElements = getDBElements(tableInfo.getTableName(), gparam, dataType);
		// 设置主键信息
		List<IndexInfo> primaryKey = getPrimaryKeyInfo(tableInfo.getTableName(), dbElements);
		ce.setPrimaryKey(primaryKey);
		// 设置外键信息
		List<IndexInfo> foreignKey = getForeignKeyInfo(tableInfo.getTableName(), dbElements);
		ce.setImportedKeys(foreignKey);
		// 设置类属性
		dbElements = setElementIndexFlag(dbElements, primaryKey, foreignKey);
		ce.setElements(dbElements);
		return ce;

	}

	/**
	 * 设置索引标志
	 * @param dbElements
	 * @param pkeysInfo
	 * @param foreignKey
	 * @return
	 */
	private List<ElementInfo> setElementIndexFlag(List<ElementInfo> dbElements, List<IndexInfo> pkeysInfo, List<IndexInfo> foreignKey)
	{
		if (null != pkeysInfo && pkeysInfo.size() > 0)
		{
			for (int i = 0; i < pkeysInfo.size(); i++)
			{
				for (int z = 0; z < dbElements.size(); z++)
				{
					if (pkeysInfo.get(i).getColumnName().equals(dbElements.get(z).getColumnName()))
					{
						dbElements.get(z).setIsPrimaryKey(true);
						break;
					}
				}
			}
		}
		if (null != foreignKey && foreignKey.size() > 0)
		{
			for (int i = 0; i < foreignKey.size(); i++)
			{
				for (int z = 0; z < dbElements.size(); z++)
				{
					if (foreignKey.get(i).getColumnName().equals(dbElements.get(z).getColumnName()))
					{
						dbElements.get(z).setIsImportedKey(true);
						break;
					}
				}
			}
		}
		return dbElements;
	}

	/**
	 * 设置类信息
	 * @param tableInfo
	 * @param gparam
	 * @param dataType
	 * @return
	 */
	private ClassElement getClassElement(TableInfo tableInfo, GeneratorParam gparam, Map<String, String> dataType)
	{

		ClassElement ce = new ClassElement();

		// ce.setTableName(tableInfo.getTableName().toUpperCase());
		ce.setTableName(tableInfo.getTableName().toLowerCase());
		ce.setTableType(tableInfo.getTableType());
		ce.setRemarks(tableInfo.getRemarks());

		String ifx = gparam.getIgnoreTablePrefix();
		String dsym = gparam.getTableMetaDataSymb();
		String lr = gparam.getNotLetterReplace();
		// 设置类名
		String javaName = MetaDataUtils.getJavaName(tableInfo.getTableName(), ifx, dsym, lr);
		ce.setClassName(StringUtils.firstLowerStr(javaName));
		ce.setFirstUpperName(StringUtils.firstUpperStr(javaName));
		ce.setModuleGroup(tableInfo.getModuleGroup());

		return ce;
	}

	/**
	 * 得到主键信息
	 * @param tableName
	 * @return
	 */
	private List<IndexInfo> getPrimaryKeyInfo(String tableName, List<ElementInfo> dbElements)
	{

		List<IndexInfo> pIndex = new ArrayList<IndexInfo>();
		// 表主键
		List<TindexInfo> pkeysInfo = JdbcMetaData.getPrimaryKeysInfo(tableName);
		if (null == pkeysInfo || pkeysInfo.isEmpty())
		{
			return pIndex;
		}

		for (TindexInfo ti : pkeysInfo)
		{
			IndexInfo index = new IndexInfo();
			index.setColumnName(ti.getColumnName().toUpperCase());
			index.setIndexname(ti.getIndexname());
			index.setKeySeq(ti.getKeySeq());
			for (int i = 0; i < dbElements.size(); i++)
			{
				ElementInfo ei = dbElements.get(i);
				if (ti.getColumnName().toUpperCase().equals(ei.getColumnName().toUpperCase()))
				{
					index.setColumnType(getColumnType(ei.getColumnType()));
					index.setColumnLength(ei.getColumnLength());
					index.setIsNullAble(ei.getIsNullAble());
					index.setDefaultValue(ei.getDefaultValue());
					index.setRemark(ei.getRemark());
					index.setJavaName(ei.getJavaName());
					index.setFirstUpperName(ei.getFirstUpperName());
					index.setJavaTypeDetail(ei.getJavaTypeDetail());
					index.setJavaType(ei.getJavaType());
					index.setIsPrimaryKey(true);
					break;
				}
			}
			pIndex.add(index);
		}

		return pIndex;
	}

	/**
	 * 得到外键信息
	 * @param tableName
	 * @return
	 */
	private List<IndexInfo> getForeignKeyInfo(String tableName, List<ElementInfo> dbElements)
	{

		List<IndexInfo> pIndex = new ArrayList<IndexInfo>();
		// 表主键
		List<TindexInfo> pkeysInfo = JdbcMetaData.getImportedKeysInfo(tableName);
		if (null == pkeysInfo || pkeysInfo.isEmpty())
		{
			return pIndex;
		}

		for (TindexInfo ti : pkeysInfo)
		{
			IndexInfo index = new IndexInfo();
			index.setColumnName(ti.getColumnName().toUpperCase());
			index.setIndexname(ti.getIndexname());
			index.setKeySeq(ti.getKeySeq());
			for (int i = 0; i < dbElements.size(); i++)
			{
				ElementInfo ei = dbElements.get(i);
				if (ti.getColumnName().toUpperCase().equals(ei.getColumnName().toUpperCase()))
				{
					index.setColumnType(getColumnType(ei.getColumnType()));
					index.setColumnLength(ei.getColumnLength());
					index.setIsNullAble(ei.getIsNullAble());
					index.setDefaultValue(ei.getDefaultValue());
					index.setRemark(ei.getRemark());
					index.setJavaName(ei.getJavaName());
					index.setFirstUpperName(ei.getFirstUpperName());
					index.setJavaTypeDetail(ei.getJavaTypeDetail());
					index.setJavaType(ei.getJavaType());
					index.setIsImportedKey(true);
					break;
				}
			}
			pIndex.add(index);
		}

		return pIndex;
	}

	/**
	 * 得到列对应的java属性变量
	 * @param tableName
	 * @param gparam
	 * @param dataType
	 * @return
	 */
	private List<ElementInfo> getDBElements(String tableName, GeneratorParam gparam, Map<String, String> dataType)
	{
		List<ElementInfo> els = new ArrayList<ElementInfo>();
		// 设置字段
		List<TcolumnInfo> columnsInfos = JdbcMetaData.getColumnsInfo(tableName, null);
		if (null == columnsInfos || columnsInfos.isEmpty())
		{
			return els;
		}
		// String ifx = gparam.getIgnoreTablePrefix();
		String ifx = "";
		String dsym = gparam.getTableMetaDataSymb();
		String lr = gparam.getNotLetterReplace();

		for (int i = 0; i < columnsInfos.size(); i++)
		{
			ElementInfo el = new ElementInfo();
			TcolumnInfo tc = columnsInfos.get(i);
			el.setColumnName(tc.getColumnName().toUpperCase());
			el.setColumnType(getColumnType(tc.getColumnType().replaceAll("\\(.*?\\)", "").trim()));
			el.setColumnLength(tc.getColumnLength());
			el.setIsNullAble(tc.getIsNullAble());
			el.setDefaultValue(tc.getDefaultValue());
			el.setRemark(tc.getRemark());
			// 设置属性名
			String javaName = MetaDataUtils.getJavaName(el.getColumnName(), ifx, dsym, lr);
			el.setJavaName(StringUtils.firstLowerStr(javaName));
			el.setFirstUpperName(StringUtils.firstUpperStr(javaName));

			// 设置属性类型
			Integer d = tc.getDecimalDigits();
			String columnType = el.getColumnType();
			if (null != d && d > 0)
			{
				columnType = columnType + "_D";
			}
			el.setJavaTypeDetail(MetaDataUtils.getJavaType(dataType, columnType));
			el.setJavaType(MetaDataUtils.getSimpleType(el.getJavaTypeDetail()));
			els.add(el);
		}
		return els;
	}

	/**
	 * 整理可用表
	 */
	private List<TableInfo> neatenTalbe(List<TableInfo> tablesList, GeneratorParam gparam)
	{

		if (null == tablesList || tablesList.isEmpty())
		{
			return tablesList;
		}

		if (null == gparam || ((null == gparam.getIncludeTable() || gparam.getIncludeTable().isEmpty()) && StringUtils.isEmpty(gparam.getExcludeTable())))
		{
			return tablesList;
		}

		// 包含的表
		Map<String, String> includeMap = new HashMap<String, String>();
		if (null != gparam.getIncludeTable())
		{
			Iterator<Entry<String, String>> iterator = gparam.getIncludeTable().entrySet().iterator();
			while (iterator.hasNext())
			{
				Entry<String, String> next = iterator.next();
				String module = null == next.getValue() ? "" : next.getValue().toLowerCase().trim();
				includeMap.put(next.getKey().toLowerCase().trim(), module);
			}
		}

		// 排除的表
		String[] excludeTable = gparam.getExcludeTable().split(",");
		Map<String, String> excludeMap = new HashMap<String, String>();
		if (null != excludeTable)
		{
			for (int z = 0; z < excludeTable.length; z++)
			{
				if (StringUtils.isNotEmpty(excludeTable[z].trim()))
				{
					excludeMap.put(excludeTable[z].toLowerCase().trim(), "");
				}
			}
		}
		// 得到可用的表
		List<TableInfo> resultTalbe = new ArrayList<TableInfo>();
		for (TableInfo t : tablesList)
		{

			if (includeMap.size() > 0 && excludeMap.size() > 0)
			{

				if (null != includeMap.get(t.getTableName().toLowerCase().trim()) && null == excludeMap.get(t.getTableName().toLowerCase().trim()))
				{
					String module = includeMap.get(t.getTableName().toLowerCase().trim());
					t.setModuleGroup(module);
					resultTalbe.add(t);
				}

			}
			else if (includeMap.size() > 0)
			{

				if (null != includeMap.get(t.getTableName().toLowerCase().trim()))
				{
					String module = includeMap.get(t.getTableName().toLowerCase().trim());
					t.setModuleGroup(module);
					resultTalbe.add(t);
				}
			}
			else if (excludeMap.size() > 0)
			{

				if (null == excludeMap.get(t.getTableName().toLowerCase().trim()))
				{
					resultTalbe.add(t);
				}
			}
		}
		return resultTalbe;
	}

	private String getColumnType(String columnType)
	{
		if (jdbcType.size() < 1)
		{
			jdbcType.put("INT", "INTEGER");
			jdbcType.put("DATE", "TIMESTAMP");
			jdbcType.put("DATETIME", "TIMESTAMP");
			jdbcType.put("LONGTEXT", "VARCHAR");
			jdbcType.put("TEXT", "VARCHAR");
			jdbcType.put("MEDIUMTEXT", "VARCHAR");
			jdbcType.put("TINYTEXT", "VARCHAR");
		}
		String jdbctypeStr = jdbcType.get(columnType);
		if (null == jdbctypeStr)
		{
			jdbctypeStr = columnType;
		}
		return jdbctypeStr;
	}

	private static Map<String, String> jdbcType = new HashMap<String, String>();

}
