package com.reba.api.person.enums;

public enum RelationType {
    PADRE("PADRE"),
    HERMANO("HERMAN@"),
    PRIMO("PRIM@”"),
    TIO("TI@");

    private String text;
    RelationType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static RelationType fromString(String text) {
        for (RelationType rel : RelationType.values()) {
            if (removeLastChar(rel.text).equalsIgnoreCase(removeLastChar(text))) {
                return rel;
            }
        }
        return null;
    }

    private static String removeLastChar(String str) {
        return str.toLowerCase().substring(0, str.length() - 1);
    }
}
