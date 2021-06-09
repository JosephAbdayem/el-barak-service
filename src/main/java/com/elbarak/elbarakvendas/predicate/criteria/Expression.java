package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

public interface Expression {

    BooleanExpression getExpression(PathBuilder<?> pathBuilder, Class tipo, String key, String operation, String val);

}
