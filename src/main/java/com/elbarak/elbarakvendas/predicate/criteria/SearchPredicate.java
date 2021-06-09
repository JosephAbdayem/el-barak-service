package com.elbarak.elbarakvendas.predicate.criteria;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.PathBuilder;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchPredicate<T> {

    public static final Integer PRIMEIRO = 1;

    public static final Integer SEGUNDO = 2;

    public static final Integer TERCEIRO = 3;

    public static final Integer QUARTO = 4;

    private final Class<T> classe;

    private final String search;

    private static final Map<Class, Expression> expressions = new HashMap<>();

    {
        expressions.put(Integer.class, new NumberExpression());
        expressions.put(Long.class, new NumberExpression());
        expressions.put(Enum.class, new EnumExpression());
        expressions.put(String.class, new StringExpression());
        expressions.put(Date.class, new DateExpression());
        expressions.put(Double.class, new NumberExpression());
        expressions.put(Float.class, new NumberExpression());
        expressions.put(Boolean.class, new BooleanTypeExpression());
    }

    private static final Set<Class> tiposNumericos = new HashSet<>();

    {
        tiposNumericos.add(Integer.class);
        tiposNumericos.add(Long.class);
    }

    public SearchPredicate(Class<T> classe, String search) {
        super();
        this.classe = classe;
        this.search = search;
    }

    public BooleanExpression getExpression() {
        Pattern pattern = Pattern.compile(
                "(\\sAND\\s|\\sOR\\s|START\\s|,)(\\w+?)(!|::|:|<|>|<:|>:|\\?)(((?:\\w*\\.)+\\w+)|(\\w*[\\u002F\\u0024a-zA-Z0-9éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄç]+))");
        Matcher matcher = pattern.matcher(search);
        BooleanExpression exp = null;
        while (matcher.find()) {
            String key = matcher.group(2);
            String valor = matcher.group(4);
            if (valor != null && valor.contains("$")) {
                valor = Arrays.stream(valor.split("\\u0024"))
                        .map(Object::toString)
                        .collect(Collectors.joining(" "));
            }

            if (!(isString(valor) && isTipoNumeric(getTypeField(key, classe)))) {
                String operacao = matcher.group(1).trim();
                SearchCriteria criteria = new SearchCriteria(key, matcher.group(3), valor);
                OperacaoLogica operacaoLogica = operacoes.get(operacao);
                exp = operacaoLogica.getOperacao(exp, criteria);
            }
        }
        return exp;
    }

    static Map<String, OperacaoLogica> operacoes = new HashMap<>();

    {
        operacoes.put("START", new OperacaoStart());
        operacoes.put("AND", new OperacaoAND());
        operacoes.put(",", new OperacaoAND());
        operacoes.put("OR", new OperacaoOR());
    }

    interface OperacaoLogica {
        BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria);
    }

    class OperacaoStart implements OperacaoLogica {

        @Override
        public BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria) {
            return getPredicate(criteria);
        }
    }

    class OperacaoAND implements OperacaoLogica {

        @Override
        public BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria) {
            return exp.and(getPredicate(criteria));
        }
    }

    class OperacaoOR implements OperacaoLogica {

        @Override
        public BooleanExpression getOperacao(BooleanExpression exp, SearchCriteria criteria) {
            return exp.or(getPredicate(criteria));
        }
    }

    private Class<?> getTypeField(String nomeCampo) {
        Class<T> classeTemp = this.classe;
        while (classeTemp != null) {
            for (Field field : classeTemp.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(nomeCampo)) {
                    return field.getType();
                } else {
                    for (Field fieldSecondLevel : field.getType().getDeclaredFields()) {
                        if (fieldSecondLevel.getName().equalsIgnoreCase(nomeCampo)) {
                            return fieldSecondLevel.getType();
                        } else {
                            for (Field fieldThirdLevel : fieldSecondLevel.getType()
                                    .getDeclaredFields()) {
                                if (fieldThirdLevel.getName().equalsIgnoreCase(nomeCampo)) {
                                    return fieldThirdLevel.getType();
                                }
                            }
                        }
                    }
                }
            }
            classeTemp = (Class<T>) classeTemp.getSuperclass();
        }
        return null;
    }

    private Class getTypeField(String nomeCampo, Class classe) {
        Class<T> classeTemp = classe;
        while (classeTemp != null) {
            for (Field field : classeTemp.getDeclaredFields()) {
                if (field.getName().equalsIgnoreCase(nomeCampo)) {
                    return field.getType();
                }
            }
            classeTemp = (Class<T>) classeTemp.getSuperclass();
        }
        return null;
    }

    private String getField(String key, Integer posicao) {
        return Arrays.asList(key.split("_")).get(posicao - 1);

    }

    private Boolean isFieldSecondLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 2;

    }

    private Boolean isFieldThirdLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 3;
    }

    private Boolean isFieldFourthLevel(String key) {
        return Arrays.asList(key.split("_")).size() == 4;
    }

    private BooleanExpression getPredicate(SearchCriteria criteria) {
        String nomeDaClasse = tornarPrimeiraLetraMinuscula(classe.getSimpleName());

        PathBuilder<T> entityPath = new PathBuilder<T>(classe, nomeDaClasse);

        String key = criteria.getKey();
        PathBuilder<?> entityPathGeneric = entityPath;
        Class<?> classe = this.classe;

        // Se for campo composto, substitui os valores key,entityPathGeneric e
        // classe para o atributo de negundo nível
        if (isFieldSecondLevel(key)) {
            String atributoComposto = getField(key, PRIMEIRO);
            Class tipoAtributoComposto = getTypeField(atributoComposto);
            PathBuilder<Class> path = entityPath.get(atributoComposto, tipoAtributoComposto);
            // substitui os valores
            entityPathGeneric = path;
            key = getField(key, SEGUNDO);
            classe = tipoAtributoComposto;
        }

        if (isFieldThirdLevel(key)) {
            String atributoComposto = getField(key, PRIMEIRO);
            Class tipoAtributoComposto = getTypeField(atributoComposto);
            PathBuilder<Class> path = entityPath.get(atributoComposto, tipoAtributoComposto);

            String attThirdLevel = getField(key, SEGUNDO);
            Class tipoThirdLevel = getTypeField(attThirdLevel, tipoAtributoComposto);
            PathBuilder<Class> pathThirdLevel = path.get(attThirdLevel, tipoThirdLevel);

            // substitui os valores
            entityPathGeneric = pathThirdLevel;
            key = getField(key, TERCEIRO);
            classe = tipoThirdLevel;
        }

        if (isFieldFourthLevel(key)) {
            String atributoComposto = getField(key, PRIMEIRO);
            Class tipoAtributoComposto = getTypeField(atributoComposto);
            PathBuilder<Class> path = entityPath.get(atributoComposto, tipoAtributoComposto);

            String attThirdLevel = getField(key, SEGUNDO);
            Class tipoThirdLevel = getTypeField(attThirdLevel, tipoAtributoComposto);
            PathBuilder<Class> pathThirdLevel = path.get(attThirdLevel, tipoThirdLevel);

            String attFourthLevel = getField(key, TERCEIRO);
            Class tipoFourthLevel = getTypeField(attFourthLevel, tipoThirdLevel);
            PathBuilder<Class> pathFourthLevel = pathThirdLevel.get(attFourthLevel, tipoFourthLevel);

            // substitui os valores
            entityPathGeneric = pathFourthLevel;
            key = getField(key, QUARTO);
            classe = tipoFourthLevel;
        }

        Class tipo = getTypeField(key, classe);
        Expression expression = tipo.isEnum() ? expressions.get(Enum.class) : expressions.get(tipo);

        String valor = criteria.getValue();
        return expression.getExpression(entityPathGeneric, tipo, key, criteria.getOperation(), valor);
    }

    private boolean isTipoNumeric(Class classe) {
        return tiposNumericos.contains(classe);
    }

    private boolean isString(String value) {
        try {
            Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            return true;
        }
        return false;
    }

    private String tornarPrimeiraLetraMinuscula(String nomeClasse) {
        String primeiraLetra = String.valueOf(nomeClasse.charAt(0));
        char primeiraLetraMinuscula = primeiraLetra.toLowerCase().charAt(0);
        StringBuffer nomeCorrigido = new StringBuffer(nomeClasse);
        nomeCorrigido.setCharAt(0, primeiraLetraMinuscula);
        return nomeCorrigido.toString();
    }

}
