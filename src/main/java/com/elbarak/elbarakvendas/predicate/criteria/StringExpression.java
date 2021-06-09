package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.StringPath;

public class StringExpression implements Expression {

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key,
                                           String operation, String val) {
        StringPath path = pathBuilder.getString(key);

        if (operation.equalsIgnoreCase("!")) {
            return path.notEqualsIgnoreCase(val);
        }
        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        if (operation.equalsIgnoreCase(":")) {
            return path.containsIgnoreCase(val);
        }
        if (operation.equalsIgnoreCase("?")) {
            String v = "%".concat(val).concat("%");
            return path.likeIgnoreCase(v);
        }
        return null;
    }

}
