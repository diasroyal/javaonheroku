/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignments;
import java.util.*;

/**
 *
 * @author diasroyal
 */

   public class Main{
  public static void main(String[] args){
    Random r = new Random(System.currentTimeMillis());
    int n = r.nextInt(101) + 50;
    int[] a = new int[n];
    for(int i = 0; i < n; i++)
      a[i] = r.nextInt(100);
    n = r.nextInt(101) + 50;
    int[] b = new int[n];
    for(int i = 0; i < n; i++)
      b[i] = r.nextInt(100);
    SortThread s1=new SortThread(a);
    Thread t1=new Thread(s1);
    t1.start();
    SortThread s2 = new SortThread(b);
    Thread t2=new Thread(s2);
    t2.start(); 
    try{
    t1.join();
    t2.join();
    }catch(Exception e){}
    MergeThread m =new MergeThread(s1.get(),s2.get());
    Thread t3=new Thread(m);
    t3.start();
    try{
    t3.join();
    }catch(Exception e){}
    System.out.println(Arrays.toString(m.get()));
  }
}





    

