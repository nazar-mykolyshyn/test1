/**
 * Created by Nazar on 01.08.2015.
 */
public class MonthOfYear {

    public int month;
    public int year;

    public MonthOfYear(int month, int year) {
        this.month = month;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MonthOfYear that = (MonthOfYear) o;

        if (month != that.month) return false;
        return year == that.year;

    }

    @Override
    public int hashCode() {
        int result = month;
        result = 31 * result + year;
        return result;
    }
}
