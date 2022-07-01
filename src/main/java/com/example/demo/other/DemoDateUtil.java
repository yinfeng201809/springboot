package com.example.demo.other;

import cn.hutool.core.date.DateUtil;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;


public class DemoDateUtil {

    public static void main(String[] args) {


        LocalDate localDate = LocalDate.now();
        localDate.atStartOfDay();
        LocalDate mondayDay = localDate.with(firstDayOfMonth());
        System.out.println(localDate.atStartOfDay());

        Instant instant = Instant.now();
        instant.atZone(ZoneId.of("Asia/Shanghai"));
        Instant instant1= Instant.ofEpochMilli(System.currentTimeMillis());
        System.out.println(instant);
        System.out.println(instant.plus(10, ChronoUnit.HOURS));
        System.out.println(instant.plus(Duration.ofHours(1)));
        System.out.println(instant.atZone(ZoneId.systemDefault()));


        LocalDate localDate1 = LocalDate.now();
        System.out.println(DateUtil.formatDateTime(toDate(localDate1)));
        System.out.println(DateUtil.formatDateTime(toDate(LocalDateTime.now())));
        System.out.println(toLocalDate(new Date()));
        System.out.println(toLocalDateTime(new Date()));






    }

    private static void dateIntervalTest() {
        Date startDay = DateUtil.parseDate("2022-04-27");
        Date endDay = DateUtil.parseDate("2022-05-23");
        List<String> allIntervalList = getIntervalList(startDay, endDay);
        for (String s : allIntervalList) {
            System.out.println(s);
        }
    }

    private static List<String> getIntervalList(Date startDay, Date endDay) {
        List<String> allIntervalList = new ArrayList<>();
        String interval;
        boolean flag = true;
        Date curDate = startDay;
        while (flag) {
            if (curDate.after(endDay)) {
                curDate = endDay;
                flag=false;
            }
            interval = getInterval(curDate, startDay, endDay);
            if (interval != null) {
                allIntervalList.add(interval);
                curDate = DateUtil.offsetWeek(curDate, 1);
            }
        }
        return allIntervalList;
    }

    public static String getInterval(Date date, Date startTime, Date endTime) {
        if (date.before(startTime)||date.after(endTime)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);
        Date intervalStartDay = intervalStartDay(startTime, calendar);
        Date intervalEndDay = intervalEndDay(endTime, calendar);
        DataSourceProperties d;
        return getFomatInterval(intervalStartDay, intervalEndDay);

    }

    private static Date intervalEndDay(Date endTime, Calendar calendar) {
        Date sunday=getDayOfWeek(calendar,Calendar.SUNDAY);
        Date intervalEndDay = sunday;
        if (sunday.after(endTime)) {
            intervalEndDay = endTime;
        }
        return intervalEndDay;
    }

    private static Date intervalStartDay(Date startTime, Calendar calendar) {
        Date mondayDay = getDayOfWeek(calendar,Calendar.MONDAY);
        Date intervalStartDay = mondayDay;
        if (mondayDay.before(startTime)) {
            intervalStartDay= startTime;
        }
        return intervalStartDay;
    }

    private static Date getDayOfWeek(Calendar calendar, int dayOfWeek) {
        calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
        Date mondayDay = calendar.getTime();
        return mondayDay;
    }

    private static String getFomatInterval(Date mondayDay, Date sunday) {
        return DateUtil.formatDate(mondayDay) + "~" + DateUtil.formatDate(sunday);
    }


    public static LocalDateTime toLocalDateTime(Date date) {

        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return zonedDateTime.toLocalDateTime();
    }

    public static LocalDate toLocalDate(Date date) {
        Instant instant = date.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        return zonedDateTime.toLocalDate();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        ZonedDateTime zdt = localDateTime.atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }

    public static Date toDate(LocalDate localDateTime) {
        ZonedDateTime zdt = localDateTime.atStartOfDay().atZone(ZoneId.systemDefault());
        return Date.from(zdt.toInstant());
    }



}
