package com.example.demo.spring.mvc;

import cn.hutool.core.date.DateUtil;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDateEditor extends PropertyEditorSupport {
    /**
     * Gets the property value as a string suitable for presentation
     * to a human to edit.
     *
     * @return The property value as a string suitable for presentation
     * to a human to edit.
     * <p>   Returns null if the value can't be expressed as a string.
     * <p>   If a non-null value is returned, then the PropertyEditor should
     * be prepared to parse that string back in setAsText().
     */
    @Override
    public String getAsText() {
        Date date = (Date) getValue();
        return DateUtil.formatDateTime(date);
    }

    /**
     * Sets the property value by parsing a given String.  May raise
     * java.lang.IllegalArgumentException if either the String is
     * badly formatted or if this kind of property can't be expressed
     * as text.
     *
     * @param text The string to be parsed.
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(DateUtil.parse(text));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}












