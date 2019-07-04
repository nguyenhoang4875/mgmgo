package com.mgmtp.internship.experiences.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

public class StringReplaceWhitespaceEditor extends PropertyEditorSupport {
    @Nullable
    private final String charsToDelete;
    private final boolean emptyAsNull;

    public StringReplaceWhitespaceEditor(boolean emptyAsNull) {
        this.charsToDelete = null;
        this.emptyAsNull = emptyAsNull;
    }

    public StringReplaceWhitespaceEditor(String charsToDelete, boolean emptyAsNull) {
        this.charsToDelete = charsToDelete;
        this.emptyAsNull = emptyAsNull;
    }

    public void setAsText(@Nullable String text) {
        if (text == null) {
            this.setValue((Object) null);
        } else {
            String value = text.trim().replaceAll("\\s{2,}", " ");
            if (this.charsToDelete != null) {
                value = StringUtils.deleteAny(value, this.charsToDelete);
            }
            if (this.emptyAsNull && "".equals(value)) {
                this.setValue((Object) null);
            } else {
                this.setValue(value);
            }
        }
    }
}
