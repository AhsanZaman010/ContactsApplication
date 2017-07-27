package com.ahsanzaman.contactsapp.ui.module.contact.presenter.details;

import android.Manifest;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.View;

import com.ahsanzaman.contactsapp.R;
import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.service.IContactsService;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.base.BasePresenter;
import com.ahsanzaman.contactsapp.ui.module.base.BaseView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsActivity.CALL_PERMISSION_REQUEST;

/**
 * Created by Accolite- on 7/23/2017.
 */

public class ContactDetailsPresenter extends BasePresenter implements RemoteServiceCallback{

    private static final int CONTACT_DETAIL_REQUEST_CODE = 101;
    private final ContactDetailsView mContactDetailsView;
    private final Contact mContact;
    private final IContactsRepository mContactRepository;
    private ContactDetail mContactDetail;

    @Inject
    public ContactDetailsPresenter(Context context, Long contactID, IContactsRepository contactsRepository) {
        super((BaseView) context);
        mContactDetailsView = (ContactDetailsView) context;
        mContact = contactsRepository.getLocalRepository().getContactById(contactID);
        mContactRepository = contactsRepository;
    }

    public void getContactDetails(){
        mCompositeDisposable.add(mContactRepository.getContactDetail(this, mContact.getId(), CONTACT_DETAIL_REQUEST_CODE));
    }

    public boolean toggleFavourite() {
        mContact.setFavorite(!mContact.isFavorite());
        mContactRepository.getLocalRepository().updateContact(mContact);
        return mContact.isFavorite();
    }

    public boolean isFavourite() {
        if(mContact!=null)
        return mContact.isFavorite();
        return false;
    }

    public String getName() {
        if(mContact!=null){
            return mContact.getFirstName()+" "+ mContact.getLastName();
        }
        return "";
    }

    private void setResponse(final ContactDetail contactDetailResponse){
        mContactDetail = contactDetailResponse;
        if (contactDetailResponse == null) {
            List<com.ahsanzaman.contactsapp.model.ContactDetail> contactDetails = new ArrayList<>();
            if (!TextUtils.isEmpty(contactDetailResponse.getPhoneNumber())) {
                final com.ahsanzaman.contactsapp.model.ContactDetail contactDetail = new com.ahsanzaman.contactsapp.model.ContactDetail();
                contactDetail.setTitle(contactDetailResponse.getPhoneNumber());
                contactDetail.setDescription("Mobile");
                contactDetail.setEndImageResource(R.drawable.ic_message);
                contactDetail.setStartImageResource(R.drawable.ic_call_blue);
                contactDetail.setEndIconOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContactDetailsView.sendMessage(contactDetailResponse.getPhoneNumber());
                    }
                });
                contactDetail.setItemOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (ActivityCompat.checkSelfPermission((ContactDetailsActivity) mContactDetailsView, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                ((ContactDetailsActivity) mContactDetailsView).requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, CALL_PERMISSION_REQUEST);
                            }
                            return;
                        }
                        mContactDetailsView.callNumber(contactDetailResponse.getPhoneNumber());}
                });
                contactDetails.add(contactDetail);
            }
            if (!TextUtils.isEmpty(contactDetailResponse.getPhoneNumber())) {
                final com.ahsanzaman.contactsapp.model.ContactDetail contactDetail = new com.ahsanzaman.contactsapp.model.ContactDetail();
                contactDetail.setTitle(contactDetailResponse.getPhoneNumber());
                contactDetail.setDescription("Other");
                contactDetail.setStartImageResource(R.drawable.ic_email_blue);
                contactDetail.setItemOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContactDetailsView.sendMail(contactDetailResponse.getEmail());}
                });
                contactDetails.add(contactDetail);
                mContactDetailsView.bind(contactDetailResponse, contactDetails);
            }
        }
    }

    @Override
    public void onSuccess(Object responseObject, int requestCode) {
        ContactDetail contactDetail = (ContactDetail) responseObject;
        mContactDetailsView.hideLoading();
        setResponse(contactDetail);
    }

    @Override
    public void onError(NetworkErrorException networkError, int requestCode) {
        mContactDetailsView.hideLoading();
        mContactDetailsView.showError(networkError.getMessage());
    }
}
