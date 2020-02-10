package timetable;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Train implements Serializable {
    public   int number;
    public String route;
    public Timestamp time;
    public Train(){
        Random rand=new Random();
        this.number=rand.nextInt(10000);
        this.time=new Timestamp(System.currentTimeMillis() + rand.nextInt(18000000) + 10800000);
        this.route=createroute();

    }
    @Override
    public String toString()
    {
        return "Train{" + "number='" + number  +", route='" + route  + ", time=" + time + '}';
    }
    public Train( int number,String route,Timestamp time){
        this.number=number;
        this.route=route;
        this.time=time;
    }


    String createroute(){
        Random rand=new Random();
        int n=rand.nextInt(4);
        HashMap<Integer, String> map = new HashMap<>();
        String s="-Москва";
        map.put(0,"Новосибирск");
        map.put(1,"Калиниград");
        map.put(2,"Санкт-Петербург");
        map.put(3,"Нижний Новгород");
        map.put(4,"Казань");
        System.out.println(n);
        s=map.get(n)+s;
        return s;
    }

}
