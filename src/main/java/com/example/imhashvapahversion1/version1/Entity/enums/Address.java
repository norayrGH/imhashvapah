package com.example.imhashvapahversion1.version1.Entity.enums;

import java.util.*;

public class Address {


    private static final Map<String, List<String>> addresses = new HashMap<String, List<String>>() {{


        put("Երեվան", Arrays.asList(" Նոր Նորկ " , "Արաբկիր", "Աջափնյակ" ,"Դավթաշեն","Էրեբունի"));
        put("Արագածոտն" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));




    }};

    public static Map<String, List<String>> getAddresses() {
        return addresses;
    }
}


