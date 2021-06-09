package com.elbarak.elbarakvendas.predicate.criteria;

public class SearchCriteria {

    private String key;

    private String operation;

    private String value;

    public SearchCriteria() {
        super();
    }

    public SearchCriteria(String key, String operation, String value) {
        super();
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    /* Getters and Setters */

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /* To string */
    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", operation='" + operation + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
