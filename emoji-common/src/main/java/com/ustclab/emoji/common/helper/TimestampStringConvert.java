package com.ustclab.emoji.common.helper;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.alibaba.excel.util.DateUtils;

import java.sql.Timestamp;

/**
 * @author TZSXFJH
 * @date 2024/11/30
 */
public class TimestampStringConvert implements Converter<Timestamp> {
    @Override
    public Class<?> supportJavaTypeKey() {
        return Timestamp.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }


    @Override
    public WriteCellData<?> convertToExcelData(Timestamp value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData<String> cellData = new WriteCellData<String>();
        String cellValue;
        if (contentProperty == null || contentProperty.getDateTimeFormatProperty() == null) {
            cellValue = DateUtils.format(value.toLocalDateTime(), null, globalConfiguration.getLocale());
        } else {
            cellValue = DateUtils.format(value.toLocalDateTime(), contentProperty.getDateTimeFormatProperty().getFormat(),
                    globalConfiguration.getLocale());
        }
        cellData.setType(CellDataTypeEnum.STRING);
        cellData.setStringValue(cellValue);
        cellData.setData(cellValue);
        return cellData;
    }
}

