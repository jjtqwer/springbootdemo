package com.example.springbootdemo;

public enum Color {
    RED,BLUE,GREEN,BLACK,WHITE;

    public static void main(String[] args) {
        for (Color c:Color.values()){
            System.out.println(c+",索引:"+c.ordinal());
        }

        for (WeekDay w:WeekDay.values()){
            System.out.println(w+":"+w.getDay());
            WeekDay.printDay(w.ordinal()+1);
        }
    }
}

enum WeekDay{
    Mon("Monday"),Tue("Tuesday"),Wed("Wednesday"),Thu("Thursday"),Fri("Friday"),Sat("Saturday"),Sun("Sunday");

    private final String day;

    private WeekDay(String day){
        this.day=day;
    }

    public String getDay(){
        return day;
    }

    public static void printDay(int i){
        switch (i){
            case 1:
                System.out.println(WeekDay.Mon);
                break;
            case 2:
                System.out.println(WeekDay.Tue);
                break;
            case 3:
                System.out.println(WeekDay.Wed);
                break;
            case 4:
                System.out.println(WeekDay.Thu);
                break;
            case 5:
                System.out.println(WeekDay.Fri);
                break;
            case 6:
                System.out.println(WeekDay.Sat);
                break;
            case 7:
                System.out.println(WeekDay.Sun);
                break;
            default:
                System.out.println("wrong number");
        }
    }
}
