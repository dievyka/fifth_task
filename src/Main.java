import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        System.out.println("Введите дату в формате dd.MM.yyyy: ");
        String date = in.nextLine();
        SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy");
        Date parsingDate = ft.parse(date);
        Calendar date1 = Calendar.getInstance();
        date1.setTime(parsingDate);
        date1.add(Calendar.DATE, 45);
        System.out.println("Дата после увеличения на 45 дней: " + ft.format(date1.getTime()));
        date1.setTime(parsingDate);
        date1.set(Calendar.MONTH,0);
        date1.set(Calendar.DAY_OF_MONTH,1);
        System.out.println("Дата после сдвига на начало года: " + ft.format(date1.getTime()));

        date1.setTime(parsingDate);

        System.out.println("Дата после увеличения на 10 рабочих дней: " + ft.format(increaseDateWorkingDays(date1.getTime(), 10)));

        System.out.println("Введите вторую дату в формате dd.MM.yyyy: ");
        String newDate = in.nextLine();
        Date parsingNewDate = ft.parse(newDate);
        Calendar newDate1 = Calendar.getInstance();
        newDate1.setTime(parsingNewDate);
        System.out.println("Количество рабочих дней между введенными датами: " + getWorkingDaysBetweenTwoDates(date1.getTime(),newDate1.getTime()));
    }

    static Date increaseDateWorkingDays(Date startDate, int countWorkDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        int workDay = 0;

        while (workDay < countWorkDays){
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekDay >=Calendar.MONDAY && weekDay <= Calendar.FRIDAY){
                workDay++;
            }
        }
        return calendar.getTime();
    }

    public static int getWorkingDaysBetweenTwoDates(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int workDays = 0;

        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
            return 0;
        }
        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
            startCal.setTime(endDate);
            endCal.setTime(startDate);
        }
        do {
            startCal.add(Calendar.DAY_OF_MONTH, 1);
            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                ++workDays;
            }
        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());

        return workDays;
    }

}
