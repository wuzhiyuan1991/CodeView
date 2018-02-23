<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${generatedMap[classInfo.firstUpperName+"Dao"]}">

    <resultMap type="${classInfo.firstUpperName}" id="${classInfo.firstUpperName}">
    <#if classInfo.elements??>
    <#list classInfo.elements as el>
    <#if el.isPrimaryKey==true>
        <id column="${el.columnName}" property="${el.javaName}" javaType="${el.javaTypeDetail}"/>
    <#else>
        <result column="${el.columnName}" property="${el.javaName}" javaType="${el.javaTypeDetail}"/>
    </#if>
    </#list>
    </#if>
    </resultMap>

    <#macro MPK><#list classInfo.primaryKey as pk><#if pk_index = 0>${pk.javaName}</#if></#list></#macro>
    <!-- 全部字段，一般用于明细查询 -->
    <sql id="AllColumnlist">
    <#if classInfo.elements??><#list classInfo.elements as el>${el.columnName}<#if el_has_next>, </#if><#if el_index !=0 && el_index%6==0>
    </#if></#list></#if>

    </sql>

    <!-- 常见字段，一般用于列表查询，可能不包含备注之类的字段 -->
    <sql id="CommonColumnlist">
    <#if classInfo.elements??><#list classInfo.elements as el><#if el.isNeededColumnOfList==true>${el.columnName}<#if el_has_next>, </#if><#if el_index !=0 && el_index%6==0>
    </#if></#if></#list></#if>

    </sql>

    <insert id="add" parameterType="${classInfo.firstUpperName}" useGeneratedKeys="true" keyProperty="<@MPK/>">
        insert into ${classInfo.tableName}(
    <#if classInfo.elements??>
        <#list classInfo.elements as el>
        <#if el.isPrimaryKey==true>
            <if test="${el.javaName}!=null">
                ${el.columnName}<#if el_has_next>,</#if>
            </if>
        <#else>
            ${el.columnName}<#if el_has_next>,</#if>
        </#if>
        </#list>
    </#if>
        ) values (
    <#if classInfo.elements??>
        <#list classInfo.elements as el>
        <#if el.isPrimaryKey==true>
            <if test="${el.javaName}!=null">
                #${"{"+el.javaName}}<#if el_has_next>,</#if>
            </if>
        <#else>
            #${"{"+el.javaName}}<#if el_has_next>,</#if>
        </#if>
        </#list>
    </#if>
        )
    </insert>

    <update id="update" parameterType="${classInfo.firstUpperName}">
        update ${classInfo.tableName}
    <#if classInfo.elements??>
        <set>
        <#list classInfo.elements as el>
        <#if el.isPrimaryKey != true>
            <if test="${el.javaName}!=null <#if el.javaType == 'String'>and ${el.javaName}!=''</#if>">
                ${el.columnName}=#${"{"+el.javaName}},
            </if>
        </#if>
        </#list>
        </set>
    </#if>
    <#if classInfo.primaryKey??>
        <where>
        <#list classInfo.primaryKey as el>
            AND ${el.columnName}=#${"{"+el.javaName+"}"}
        </#list>
        </where>
    </#if>
    </update>

    <delete id="delete">
        delete from ${classInfo.tableName}
    <#if classInfo.primaryKey??>
        <where>
            <#list classInfo.primaryKey as el>
            ${el.columnName}=#${"{"+el_index+"}"}
            </#list>
        </where>
    </#if>
    </delete>

    <delete id="deleteByIds">
        delete from ${classInfo.tableName}
        <where>
            <foreach item="item" index="index" collection="array" open="("
                     separator=" OR " close=")">${classInfo.primaryKeyElement.columnName} = ${"#"+"{"+"item"+"}"}</foreach>
        </where>
    </delete>

    <select id="get" resultMap="${classInfo.firstUpperName}">
        select
        <include refid="AllColumnlist"/>
        from ${classInfo.tableName}
    <#if classInfo.primaryKey??>
        <where>
            <#list classInfo.primaryKey as el>
            ${el.columnName}=#${"{"+el_index+"}"}
            </#list>
        </where>
    </#if>
    </select>

    <select id="getByIds" resultMap="${classInfo.firstUpperName}">
        select
        <include refid="AllColumnlist"/>
        from ${classInfo.tableName}
        <where>
            <foreach item="item" index="index" collection="array" open="(" separator=" OR "
             close=")">${classInfo.primaryKeyElement.columnName} = ${"#"+"{"+"item"+"}"}</foreach>
        </where>
    </select>

</mapper>