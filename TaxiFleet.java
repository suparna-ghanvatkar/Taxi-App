/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxiFleet;

import java.util.*;

/**
 *
 * @author rkjain2001
 */
public class TaxiFleet {
    private int num_taxis;
    ArrayList<Taxi> taxi;
    ArrayList<Long> start_time;

    public TaxiFleet() {
        num_taxis=0;
        taxi=new ArrayList<>();
        start_time=new ArrayList<>();
    }

    public ArrayList<Taxi> getTaxis() {
        return taxi;
    }

    public int getNum_taxis() {
        return num_taxis;
    }

    public void setNum_taxis(int num_taxis) {
        this.num_taxis = num_taxis;
    }
    private int distance(Location l1,Location l2){
        int X=l1.getX()-l2.getX();
        int Y=l1.getY()-l2.getY();
        int xsq=X*X;
        int ysq=Y*Y;
        return (int)Math.sqrt(xsq+ysq);
    }
    
    public void start(int num){
        for(int i=0;i<num;i++){
            taxi.add(new Taxi(i));
            start_time.add(-1l);
        }
        num_taxis=num;
    }
    public int request(int userId,Location loc){
        int nearest=100000;
        int nearest_t=-1;
        for(int i=0;i<num_taxis;i++){
            int x=distance(taxi.get(i).getLoc(),loc);
            if (x<nearest && !(taxi.get(i).isBusy())){
                nearest=x;
                nearest_t=i;
            }
        }
        taxi.get(nearest_t).setStatus(1);
        taxi.get(nearest_t).setRider_id(userId);
        start_time.set(nearest_t, System.currentTimeMillis());
        int u=TaxiService.usrs.indexOf(userId);
        if(u!=-1)
            TaxiService.usrs.get(u).setCabId(nearest_t);
        else {
            TaxiService.usrs.add(new User(userId));
            u=TaxiService.usrs.indexOf(userId);
            TaxiService.usrs.get(u).setCabId(nearest_t);
        }
        return nearest_t;
    }
    
    public int release(int userId, Location loc){
        int cabId=TaxiService.usrs.get(TaxiService.usrs.indexOf(userId)).getCabId();
        long start=start_time.get(cabId);
        long end=System.currentTimeMillis();
        taxi.get(cabId).setLoc(loc);
        taxi.get(cabId).setRider_id(-1);
        taxi.get(cabId).setStatus(0);
        return (int)(end-start);
    }
}
