package entities;

public class Date {
    int day, month, year;

    public Date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    @Override
    public String toString(){
        return day+ "/" + month + "/" + year;
    }
}
