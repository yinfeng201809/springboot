package com.example.demo.study.enumstudy;

import java.util.EnumMap;

import static com.example.demo.study.enumstudy.Input.*;

public enum Category {

    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORD_TRANSACTION),
    SHUT_DOWN(STOP);
    private Input[] values;

    Category(Input... types) {
        this.values = types;
    }

    private static EnumMap<Input, Category> inputCategoryEnumMap = new EnumMap<>(Input.class);

    static {
        for (Category category : Category.class.getEnumConstants()) {
            for (Input type : category.values) {
                inputCategoryEnumMap.put(type, category);
            }
        }
    }

    public static Category categorize(Input input) {
        return inputCategoryEnumMap.get(input);
    }
}
