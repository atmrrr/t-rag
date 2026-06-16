package com.rag.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

@Slf4j
@Intercepts({
        @Signature(
                type = ParameterHandler.class,
                method = "setParameters",
                args = {java.sql.PreparedStatement.class}
        )
})
public class MybatisSqlLogInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        if (target instanceof ParameterHandler parameterHandler) {
            MetaObject metaObject = org.apache.ibatis.reflection.SystemMetaObject.forObject(parameterHandler);

            BoundSql boundSql = (BoundSql) metaObject.getValue("boundSql");
            Configuration configuration = (Configuration) metaObject.getValue("configuration");

            String sql = buildSql(configuration, boundSql);

            log.info(sql);
        }

        return invocation.proceed();
    }

    private String buildSql(Configuration configuration, BoundSql boundSql) {
        String sql = boundSql.getSql().replaceAll("\\s+", " ");
        Object parameterObject = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        if (parameterMappings == null || parameterMappings.isEmpty() || parameterObject == null) {
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
        MetaObject metaObject = configuration.newMetaObject(parameterObject);

        for (ParameterMapping parameterMapping : parameterMappings) {
            String propertyName = parameterMapping.getProperty();

            Object value;

            if (boundSql.hasAdditionalParameter(propertyName)) {
                value = boundSql.getAdditionalParameter(propertyName);
            } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                value = parameterObject;
            } else if (metaObject.hasGetter(propertyName)) {
                value = metaObject.getValue(propertyName);
            } else {
                value = null;
            }

            sql = sql.replaceFirst("\\?", formatValue(value));
        }

        return sql;
    }

    private String formatValue(Object value) {
        if (value == null) {
            return "NULL";
        }

        if (value instanceof Number) {
            return value.toString();
        }

        if (value instanceof Boolean) {
            return value.toString();
        }

        if (value instanceof Date date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(
                    DateFormat.DEFAULT,
                    DateFormat.DEFAULT,
                    Locale.CHINA
            );
            return "'" + formatter.format(date) + "'";
        }

        return "'" + value.toString().replace("'", "''") + "'";
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}