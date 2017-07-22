package com.ahsanzaman.contactsapp.utils;

import com.ahsanzaman.contactsapp.model.Contact;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Accolite- on 7/22/2017.
 */

public class ContactUtils {

    public static void sortContacts(List<Contact> contacts){
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact o1, Contact o2) {
                String name1 = o1.getFirstName() + " " + o1.getLastName();
                String name2 = o2.getFirstName() + " " + o2.getLastName();
                if(o1.isFavorite() && o2.isFavorite()){
                    return name1.compareTo(name2);
                } else if(o1.isFavorite()){
                    return -1;
                } else if(o2.isFavorite()){
                    return 1;
                } else {
                    return name1.compareTo(name2);
                }
            }
        });
    }

}
