/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TaxiFleet;
import taxiservice.*;
/**
 *
 * @author rkjain2001
 */
public class Location {
    private int x;
    private int y;
    public Location(int x,int y){
        x=0;
        y=0;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int a){
        x=a;
    }
    public void setY(int a){
        y=a;
    }
}
