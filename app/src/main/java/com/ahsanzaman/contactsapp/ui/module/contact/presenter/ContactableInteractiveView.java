package com.ahsanzaman.contactsapp.ui.module.contact.presenter;

import com.ahsanzaman.contactsapp.ui.module.base.BaseView;

/**
 * Created by Accolite- on 7/28/2017.
 */

public interface ContactableInteractiveView extends BaseView {

    void onCallClicked();

    void callNumber(String number);

    void onSMSClicked();

    void sendSMS(String number);

    void onEmailClicked();

    void sendEmail(String email);

    void dial(String phoneNumber);

}
