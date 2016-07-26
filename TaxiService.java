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
public class TaxiService {

    /**
     * @param args the command line arguments
     */
    static ArrayList<User> usrs;
    TaxiFleet tf;
    
    public TaxiService(){
        usrs=new ArrayList<>();
        usrs.add(new User(138));
        tf=new TaxiFleet();
    }
        
    public int request(int userId,Location loc){
        if(userId>160)
            return -3;//-3 for invalid id
        if(usrs.indexOf(userId)==-1)
            usrs.add(new User(userId));
        int bal=usrs.get(usrs.indexOf(userId)).getBal();
        if(bal<=5)
            return -2;//-2 for user cannot book due to insufficient balance
        else{
            int status=tf.request(userId, loc);
            if(status==-1){
                usrs.get(usrs.indexOf(userId)).setBal(bal-10);
                return -1;//no more taxis left
            }
        }
        return 0;
    }
    
    public int release(int userId,Location loc){
        int time=tf.release(userId, loc);
        int cost=6*time;
        int i=usrs.indexOf(userId);
        if(i==-1)
            return -1;
        usrs.get(i).setCabId(-1);
        usrs.get(i).setBal(usrs.get(i).getBal()-cost);
        usrs.get(i).setRides(usrs.get(i).getRides()+1);
        return 0;
    }
    
    public void freqUser(){
        for(int i=0;i<usrs.size();i++){
            if(usrs.get(i).getRides()==5){
                usrs.get(i).setBal(usrs.get(i).getBal()+50);
                usrs.get(i).setRides(0);
            }
        }
    }
    
}
