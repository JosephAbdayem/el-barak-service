package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.util.HashMap;
import java.util.Map;

public class NumberExpression implements Expression {

    static Map<String, NumberOperation> operacoes = new HashMap<>();

    {
        operacoes.put(":", new NumberOperationIgual());
        operacoes.put(">:", new NumberOperationMaiorOuIgual());
        operacoes.put("<:", new NumberOperationMenorOuIgual());
        operacoes.put(">", new NumberOperationMaior());
        operacoes.put("<", new NumberOperationMenor());
        operacoes.put("::", new NumberOperationISNull());
        operacoes.put("!", new NumberOperationDiferente());
    }

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key,
                                           String operation, String val) {
        NumberPath path = pathBuilder.getNumber(key, tipo);
        NumberOperation numberOperation = operacoes.get(operation);
        return numberOperation.getNumberExpression(path, val);
    }

    interface NumberOperation {
        BooleanExpression getNumberExpression(NumberPath path, String valor);
    }

    static class NumberOperationISNull implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            return path.isNull();
        }
    }

    class NumberOperationMaiorOuIgual implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.goe(value);
            } else {
                int value = Integer.parseInt(val);
                return path.goe(value);
            }
        }
    }

    class NumberOperationMenorOuIgual implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.loe(value);
            } else {
                int value = Integer.parseInt(val);
                return path.loe(value);
            }
        }
    }

    class NumberOperationIgual implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (val.equals("null")) {
                return path.isNull();
            }

            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.eq(value);
            } else {
                Integer value = Integer.parseInt(val);
                return path.eq(value);
            }

        }
    }

    class NumberOperationMaior implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.gt(value);
            } else {
                int value = Integer.parseInt(val);
                return path.gt(value);
            }
        }
    }

    class NumberOperationMenor implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.lt(value);
            } else {
                int value = Integer.parseInt(val);
                return path.lt(value);
            }
        }
    }

    class NumberOperationDiferente implements NumberOperation {
        @Override
        public BooleanExpression getNumberExpression(NumberPath path, String val) {
            if (valorIsDecimal(val)) {
                double value = Double.parseDouble(val);
                return path.notIn(value);
            } else {
                int value = Integer.parseInt(val);
                return path.notIn(value);
            }
        }
    }

    private boolean valorIsDecimal(String val) {
        boolean contains = val.contains(".") || val.contains(",");
        return contains;
    }
}