package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.PathBuilder;

public class BooleanTypeExpression implements Expression {

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key, String operation, String val) {
        Boolean value = Boolean.valueOf(val);

        BooleanPath path = pathBuilder.getBoolean(key);
        if (operation.equalsIgnoreCase(":")) {
            return path.eq(value);
        }

        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(value);
        }

        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        return null;
    }
}
