package com.example.imhashvapahversion1.version1.Entity.enums;

import java.util.*;

public class Address {


    private static final Map<String, List<String>> addresses = new HashMap<String, List<String>>() {{

        /**/
        put("Երեվան", Arrays.asList(" Նոր Նորկ " , "Արաբկիր", "Աջափնյակ" ,"Դավթաշեն","Էրեբունի","Ավան","Կենտրոն","Մալաթիա-Սեբաստիա","Նորք-Մարաշ","Նուբարաշեն","Շենգավիթ","Քանաքեռ-Զեյթուն"));
        put("Արագածոտն" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք","kghf,glhj"));
        put("Արարատ" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Արմավիր" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Գեղարքունիք" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Լոռի" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Կոտայք" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Շիրակ" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Սյունիք" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Վայոց Ձոր" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));
        put("Տավուշ" ,Arrays.asList("Ագարակ" , "Ագարակավան", "Ալագյազ" ,"Ակունք","Աղջք"));




    }};

    public static Map<String, List<String>> getAddresses() {
        return addresses;
    }
}


