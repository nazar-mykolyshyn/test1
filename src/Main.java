import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static List<Verification> verificationList = new ArrayList<>();
    public static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    static {
        try {
            verificationList.add(new Verification(new Employee("Employee1"), DATE_FORMAT.parse("17-04-2014")));
            verificationList.add(new Verification(new Employee("Employee2"), DATE_FORMAT.parse("03-05-2014")));
            verificationList.add(new Verification(new Employee("Employee3"), DATE_FORMAT.parse("03-05-2014")));
            verificationList.add(new Verification(new Employee("Employee3"), DATE_FORMAT.parse("04-06-2014")));
            verificationList.add(new Verification(new Employee("Employee3"), DATE_FORMAT.parse("28-08-2014")));
            verificationList.add(new Verification(new Employee("Employee4"), DATE_FORMAT.parse("17-09-2014")));
            verificationList.add(new Verification(new Employee("Employee5"), DATE_FORMAT.parse("18-11-2014")));
            verificationList.add(new Verification(new Employee("Employee6"), DATE_FORMAT.parse("01-12-2014")));
            verificationList.add(new Verification(new Employee("Employee7"), DATE_FORMAT.parse("01-02-2015")));
            verificationList.add(new Verification(new Employee("Employee8"), DATE_FORMAT.parse("02-03-2015")));
            verificationList.add(new Verification(new Employee("Employee8"), DATE_FORMAT.parse("02-03-2015")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ParseException {
        Date dateFrom = DATE_FORMAT.parse("15-04-2014");
        Date dateTo = DATE_FORMAT.parse("02-05-2015");

        Calendar start = Calendar.getInstance();
        start.setTime(dateFrom);
        rollDateToFirstDayOfMonth(start);

        Calendar end = Calendar.getInstance();
        end.setTime(dateTo);
        rollDateToFirstDayOfMonth(end);

        List<MonthOfYear> months = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        for (Date date = start.getTime(); start.before(end) || start.equals(end); start.add(Calendar.MONTH, 1), date = start.getTime()) {
            cal.setTime(date);
            MonthOfYear item = new MonthOfYear(cal.get(Calendar.MONTH), cal.get(Calendar.YEAR));
            months.add(item);
        }

        Map<String, ProviderEmployeeGraphic> employeeGraphicMap = new HashMap<>();
        Calendar initDateCal = Calendar.getInstance();
        for(Verification v : verificationList){
            ProviderEmployeeGraphic graphicItem;
            if(employeeGraphicMap.containsKey(v.employee.userName)){
                graphicItem = employeeGraphicMap.get(v.employee.userName);
            } else {
                graphicItem = new ProviderEmployeeGraphic();
                graphicItem.monthList = months;
                graphicItem.countOfWork = new int[months.size()];
                graphicItem.userName = v.employee.userName;
                employeeGraphicMap.put(v.employee.userName, graphicItem);
            }

            initDateCal.setTime(v.initialDate);
            MonthOfYear item = new MonthOfYear(initDateCal.get(Calendar.MONTH), initDateCal.get(Calendar.YEAR));
            int indexOfMonth = months.indexOf(item);
            graphicItem.countOfWork[indexOfMonth]++;

        }

        List<ProviderEmployeeGraphic> graphicItemsList = new ArrayList<>();
        for(Map.Entry<String, ProviderEmployeeGraphic> item : employeeGraphicMap.entrySet()){
            graphicItemsList.add(item.getValue());
        }

    }

    private static void rollDateToFirstDayOfMonth(Calendar calendar) {
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
