package by.ita.je.module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClassList {

// List<Integer> listik = new ArrayList<>();


    public  void uuu (String[] args) {
        List<Integer> listik = new ArrayList<>();
        listik.add(1);
        listik.add(3);
        listik.add(2);
        listik.add(1);
        System.out.println(listik);
        int i = 0;
       // while(listik.size()!=0){
         //   if(listik.get(i)>listik.get(i+1)){


           // }}
            Collections.sort(listik);
            System.out.println(listik);


    }
}
