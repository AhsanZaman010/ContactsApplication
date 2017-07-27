package com.ahsanzaman.contactsapp.utils;

import java.util.List;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */

public class CollectionUtils {

    public static synchronized boolean isEmpty(List list){
        if(null == list || list.size()==0){
            return true;
        }
        return false;
    }

}
