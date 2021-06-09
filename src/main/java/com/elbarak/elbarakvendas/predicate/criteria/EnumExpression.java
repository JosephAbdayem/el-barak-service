package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.PathBuilder;

public class EnumExpression implements Expression {

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key,
                                           String operation, String val) {
        Enum enumObject = Enum.valueOf(tipo, val.toUpperCase());
        EnumPath path = pathBuilder.getEnum(key, tipo);

        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(val);
        }

        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }

        if (operation.equalsIgnoreCase(":")) {
            return path.eq(enumObject);
        }
        return null;
    }
}
