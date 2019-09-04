package com.yqh.dubbo.common;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yqh.dubbo.common.anotation.ExcelHeadMap;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.annotation.AnnotationUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.text.MessageFormat.format;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * @author yangq
 * Create time in 2018-07-24 09:12
 */


public class ExcelUtil {
    static String EXCEL_TEMPLATES_FOLDER = "excelTemplate";

    /**
     * excel模板解析
     *
     * @param index excel的第几列来做判断，从0开始
     */
    public static <T> List<T> buildObjectFromExcel(InputStream resetableInputStream, Class<T> t, int index) {
        Workbook workbook = getWorkbook(resetableInputStream);
        Sheet sheet = workbook.getSheetAt(0);
        List<T> dtos = Lists.newArrayList();
        int rowNum = sheet.getLastRowNum();
        ExcelUtil.ExcelHeadHelper excelHeadHelper = new ExcelUtil.ExcelHeadHelper();
        try {
            for (int i = 0; i <= rowNum; i++) {
                Row row = sheet.getRow(i);
                if (i == 0) {
                    excelHeadHelper.build(t).index(row);
                } else {
                    if (nonNull(row)) {
                        if (isNotEmpty(ExcelUtil.getStringValue(row, index))) {
                            T dto = t.newInstance();
                            excelHeadHelper.fill(row, dto);
                            dtos.add(dto);
                        }
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException();
        }
        return dtos;
    }

    private static Workbook getWorkbook(InputStream fileInputStream) {
        Workbook workbook;//NOPMD
        try {
            workbook = WorkbookFactory.create(fileInputStream);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        return workbook;
    }

    public static Sheet getSheet(int index, HSSFWorkbook workbook) {
        return workbook.getSheetAt(index);
    }

    @Deprecated
    public static int getRowNum(Sheet sheet) {
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null) {
                return i;
            } else if (isCellEmpty(row.getCell(0)) && isCellEmpty(row.getCell(1)) && isCellEmpty(row.getCell(2))
                    && isCellEmpty(row.getCell(3))) {
                return i;
            }
        }
        return sheet.getPhysicalNumberOfRows();
    }

    public static boolean isEmpty(Row r) {
        return r == null || r.getPhysicalNumberOfCells() == 0;
    }

    public static boolean isCellEmpty(Cell cell) {
        return cell == null || cell.getCellType() == Cell.CELL_TYPE_BLANK;
    }

    public static String formatFileName(String fileName) {
        try {
            fileName = new String(fileName.getBytes("iso-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        if (StringUtils.indexOf(fileName, ":") >= 0) {
            fileName = StringUtils.substringAfterLast(fileName, ":");
        }

        if (StringUtils.indexOf(fileName, "\\") >= 0) {
            fileName = StringUtils.substringAfterLast(fileName, "\\");
        }
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_" + fileName;
    }

    public static String getStringValueOfCell(Cell cell) {
        if (!isCellEmpty(cell)) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String cellValue = cell.getStringCellValue();
                if (isNotEmpty(cellValue)) {
                    return cellValue.trim();
                }
            }
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        }
        return null;
    }

    public static String getStringValue(Row row, int index) {
        return getStringValueOfCell(row.getCell(index));
    }

    public static Double getDecimalValueOfCell(Cell cell) {
        if (!isCellEmpty(cell)) {
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String value = cell.getStringCellValue();
                if (isNotEmpty(value)
                        && isNotEmpty(value.trim())) {
                    return Double.valueOf(value);
                }
            }
            if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

                DecimalFormat df = new DecimalFormat("0.00");

                String value = df.format(cell.getNumericCellValue());
                if (isNotEmpty(value)) {
                    return Double.valueOf(value);
                }
            }
        }
        return null;
    }

    public static String getDateString(Cell cell) {
        String date = StringUtils.EMPTY;
        if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
            String cellString = cell.getStringCellValue();
            Pattern patternHyphen = Pattern
                    .compile("[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}");
            Pattern patternSlash = Pattern
                    .compile("[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}");
            if (patternHyphen.matcher(cellString).matches()) {
                date = LocalDate.parse(cellString,
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString();
            } else if (patternSlash.matcher(cellString).matches()) {
                date = LocalDate.parse(cellString,
                        DateTimeFormatter.ofPattern("yyyy/MM/dd"))
                        .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } else {
                return null;
            }
        } else {
            date = new SimpleDateFormat("yyyy-MM-dd")
                    .format(cell.getDateCellValue());
        }
        return date;
    }

    public static BigDecimal getBigDecimalString(Cell cell) {
        BigDecimal value = BigDecimal.ZERO;
        if (!ExcelUtil.isCellEmpty(cell)) {
            if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                if (!cell.getStringCellValue().equals("")) {
                    value = new BigDecimal(Double.parseDouble(cell.getStringCellValue()));
                }
            } else if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                value = new BigDecimal(cell.getNumericCellValue());
            }
        }
        return value;
    }

    public static BigDecimal getBigDecimalString(Row row, int idex) {
        return getBigDecimalString(row.getCell(idex));
    }

    public static Date getStringDate(Cell cell) {
        Date cellDate = null;
        if (!isCellEmpty(cell)) {
            if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) {
                cellDate = cell.getDateCellValue();
            } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {
                try {
                    cellDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .parse(cell.getStringCellValue());
                } catch (Exception e) {
                    cellDate = null;
                }
            }
        }
        return cellDate;
    }

    public static String getAnsteelDateCellStringValue(Row row, Integer index) {
        Cell cell = row.getCell(index);
        if (nonNull(cell)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                String cellStringValue = getStringValueOfCell(cell);
                if (isNotEmpty(cellStringValue)) {
                    try {
                        if (cellStringValue.contains(":")) {
                            Date date = simpleDateFormat.parse(cellStringValue.replace("/", "-"));
                            return simpleDateFormat.format(date);
                        } else if (cellStringValue.contains(".")) {
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cellStringValue.replace(".", "-"));
                            return simpleDateFormat.format(date);
                        } else {
                            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cellStringValue.replace("/", "-"));
                            return simpleDateFormat.format(date);
                        }
                    } catch (ParseException e) {
                        throw new RuntimeException(format(
                                "无效的日期格式{0}", cellStringValue));
                    }
                } else {
                    return "";
                }
            } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                return simpleDateFormat.format(cell.getDateCellValue());
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    private void offsetRow(Row row, int i) {
        short lastCellNum = row.getLastCellNum();
        for (int j = lastCellNum - 1; j >= 0; j--) {
            Cell oldCell = row.getCell(j);
            Cell newCell = row.createCell(j + i, oldCell.getCellType());
            newCell.setCellStyle(oldCell.getCellStyle());
            // Set the cell data value
            switch (oldCell.getCellType()) {
                case Cell.CELL_TYPE_BLANK:
                    break;
                case Cell.CELL_TYPE_BOOLEAN:
                    newCell.setCellValue(oldCell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_ERROR:
                    newCell.setCellErrorValue(oldCell.getErrorCellValue());
                    break;
                case Cell.CELL_TYPE_FORMULA:
                    newCell.setCellFormula(oldCell.getCellFormula());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    newCell.setCellValue(oldCell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    newCell.setCellValue(oldCell.getRichStringCellValue());
                    break;
                default:
                    break;
            }
        }
    }

    public static class ExcelHeadHelper {
        private Map<String, Integer> indexMap = Maps.newHashMap();
        private Map<String, ExcelCellType> typeMap = Maps.newHashMap();
        private Map<String, Field> fieldMap = Maps.newHashMap();

        public ExcelHeadHelper build(Class clazz) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                Annotation[] annos = f.getAnnotations();
                for (Annotation a : annos) {
                    if (a instanceof ExcelHeadMap) {
//                        //get all attributes
//                        Map map = AnnotationUtils.getAnnotationAttributes(a);
//                        //get value
                        String key = (String) AnnotationUtils.getValue(a, "name");
                        indexMap.put(key.trim(), null);
                        fieldMap.put(key.trim(), f);
                        ExcelCellType type = (ExcelCellType) AnnotationUtils.getValue(a, "type");
                        typeMap.put(key.trim(), type);
                    }
                }
            }
            return this;
        }

        public void index(Row row) {
            short lastCellNum = row.getLastCellNum();
            for (int j = 0; j < lastCellNum; j++) {
                Cell cell = row.getCell(j);
                String cellStringValue = getStringValueOfCell(cell);
                if (isNotEmpty(cellStringValue)) {
                    indexMap.putIfAbsent(cellStringValue, j);
                }
            }
            String missingColumns = indexMap.entrySet().stream().filter(e -> isNull(e.getValue())).map(Map.Entry::getKey).collect(Collectors.joining(","));
            if (isNotEmpty(missingColumns)) {
                throw new RuntimeException(format("找不到对应的列 {0}", missingColumns));
            }
        }

        public Integer headIndex(String key) {
            return indexMap.get(key);
        }

        public void fill(Row row, Object dto) {
            for (Map.Entry<String, Field> stringFieldEntry : fieldMap.entrySet()) {
                String key = stringFieldEntry.getKey();
                Integer integer = indexMap.get(key);
                try {
                    Field field = stringFieldEntry.getValue();
                    ExcelCellType type = typeMap.get(key);
                    Object setValue;
                    if (nonNull(type) && type.equals(ExcelCellType.anDate)) {
                        setValue = getAnsteelDateCellStringValue(row, integer);
                    } else {
                        if (field.getType() == String.class) {
                            setValue = getStringValue(row, integer);
                        } else if (field.getType() == BigDecimal.class) {
                            setValue = getBigDecimalString(row, integer);
                        } else if (field.getType() == Integer.class) {
                            setValue = getBigDecimalString(row, integer).intValue();
                        } else if (field.getType() == Boolean.class) {
                            setValue = getBooleanString(row, integer);
                        } else {
                            throw new RuntimeException(format("不识别的数据类型{0}", field.getType().getName()));
                        }

                    }
                    BeanUtils.setProperty(dto, field.getName(), setValue);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        private Object getBooleanString(Row row, Integer index) {
            Cell cell = row.getCell(index);
            if (!ExcelUtil.isCellEmpty(cell)) {
                if ("true".equalsIgnoreCase(cell.getStringCellValue())) {
                    return Boolean.TRUE;
                } else if ("false".equalsIgnoreCase(cell.getStringCellValue())) {
                    return Boolean.FALSE;
                }
            }
            return null;
        }


        public void index(Row[] rows) {
            for (Row row : rows) {
                short lastCellNum = row.getLastCellNum();
                for (int j = 0; j < lastCellNum; j++) {
                    Cell cell = row.getCell(j);
                    String cellStringValue = getStringValueOfCell(cell);
                    if (isNotEmpty(cellStringValue)) {
                        indexMap.putIfAbsent(cellStringValue, j);
                    }
                }
            }
            String missingColumns = indexMap.entrySet().stream().filter(e -> isNull(e.getValue())).map(Map.Entry::getKey).collect(Collectors.joining(","));
            if (isNotEmpty(missingColumns)) {
                throw new RuntimeException(format("找不到对应的列 {0}", missingColumns));
            }
        }
    }
}
