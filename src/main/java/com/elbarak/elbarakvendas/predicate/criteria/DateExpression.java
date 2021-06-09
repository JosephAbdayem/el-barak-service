package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.PathBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DateExpression implements Expression {

    private Boolean isValorComposto(String valor) {
        return Arrays.asList(valor.split("_")).size() == 2;
    }

    @Override
    public BooleanExpression getExpression(PathBuilder pathBuilder, Class tipo, String key,
                                           String operation, String val) {
        DatePath path = pathBuilder.getDate(key, Date.class);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
        if (operation.equalsIgnoreCase("!")) {
            return path.notIn(val);
        }
        if (operation.equalsIgnoreCase("::")) {
            return path.isNull();
        }
        if (operation.equalsIgnoreCase(":")) {
            if (isValorComposto(val)) {
                String[] datas = val.split("_");
                try {
                    Date dataInicial = sdf.parse(datas[0]);
                    Date dataFinal = sdf.parse(datas[1]);
                    dataFinal.setHours(23);
                    dataFinal.setMinutes(59);
                    dataFinal.setSeconds(59);

                    return path.between(dataInicial, dataFinal);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            } else {
                try {
                    Date data = sdf.parse(val);
                    return path.eq(data);

                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        } else if (operation.equals("<")) {
            try {
                Date data = sdf.parse(val);
                data.setHours(23);
                data.setMinutes(59);
                data.setSeconds(59);

                return path.loe(data);

            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        } else if (operation.equals(">")) {
            try {
                Date data = sdf.parse(val);
                data.setHours(0);
                data.setMinutes(0);
                data.setSeconds(0);

                return path.goe(data);

            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return null;
    }
}
