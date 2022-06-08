package in.handyman.raven.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashSet;
import java.util.Set;

public class Table {

    @Data
    @AllArgsConstructor
    @Builder
    public static class Row {
        private Integer rowId;
        @Builder.Default
        private Set<ColumnInARow> columnSet = new LinkedHashSet<>();
    }

    @Data
    @AllArgsConstructor
    @Builder
    public static class ColumnInARow {
        private Integer columnType;
        private String columnTypeName;
        private String columnName;
        private String columnLabel;
        private Integer scale;
        private Object value;
        private String columnFunction;
        private Boolean isLastColumn;
    }

}
