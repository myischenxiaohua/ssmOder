package cn.cxh.tool;


import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDateConverter implements Converter<String,Date> {
    private String datePattern;
    public StringToDateConverter(String datePattern){
        this.datePattern=datePattern;
    }

    @Override
    public Date convert(String s) {
//        s是传入的字符串时间
        Date date=null;
        try {
            date=  new SimpleDateFormat(datePattern).parse(s); //讲字符串时间转换成Date
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
