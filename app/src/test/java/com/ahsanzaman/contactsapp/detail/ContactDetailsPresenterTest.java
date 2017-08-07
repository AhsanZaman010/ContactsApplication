package com.ahsanzaman.contactsapp.detail;

import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.ILocalRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.ContactDetailUIItem;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.model.response.ContactResponse;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.details.ContactDetailsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsView;
import com.ahsanzaman.contactsapp.ui.module.contact.view.details.ContactDetailsView;
import com.ahsanzaman.contactsapp.utils.ContactUtils;
import com.google.common.collect.Lists;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;
import java.util.List;

import static com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter.CONTACTS_REQUEST_CODE;
import static com.ahsanzaman.contactsapp.ui.module.contact.presenter.details.ContactDetailsPresenter.TOGGLE_FAVOURITE_API;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.refEq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Accolite- on 7/29/2017.
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class ContactDetailsPresenterTest {

        private static ContactDetail CONTACT_DETAIL = new ContactDetail(100001, "Test", "Name", "", "", "", true);

        private static long ID = 100001;

        private static Contact CONTACT = new Contact(100001, "Test", "Name", true);

        @Mock
        private IContactsRepository mContactsRepository;

        @Mock
        private ILocalRepository mLocalRepository;

        @Mock
        private ContactDetailsView mContactDetailsView;

        @Captor
        private ArgumentCaptor<RemoteServiceCallback> mLoadContactsCallbackCaptor;

        private ContactDetailsPresenter mContactDetailsPresenter;
        private List<ContactDetailUIItem> mContactDetailsList;

    @Before
        public void setupContactsPresenter() {
            // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
            // inject the mocks in the test the initMocks method needs to be called.
            MockitoAnnotations.initMocks(this);

            // Get a reference to the class under test
        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
            mContactDetailsPresenter = new ContactDetailsPresenter(mContactDetailsView, CONTACT.getId(),  mContactsRepository);
            mContactDetailsList = ContactUtils.getContactDetailsUIItems(CONTACT_DETAIL, mContactDetailsView);
        }

        @Test
        public void loadContactDetailsFromRepositoryAndLoadIntoViewTest() {
            // Given initialized ContactDetailsPresenter with initialized contact details
            // When loading of contact details are requested
            PowerMockito.mockStatic(Log.class);
            when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
            when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
            doNothing().when(mLocalRepository).clearContacts();
            doNothing().when(mLocalRepository).updateContact(CONTACT);
            when(mLocalRepository.getContactById(CONTACT.getId())).thenReturn(CONTACT);

            mContactDetailsPresenter.getContactDetails();

            verify(mContactsRepository).getContactDetail(mLoadContactsCallbackCaptor.capture(), eq(ID), eq(CONTACTS_REQUEST_CODE));
            mLoadContactsCallbackCaptor.getValue().onSuccess(CONTACT_DETAIL, CONTACTS_REQUEST_CODE);

            verify(mContactDetailsView).bind(CONTACT_DETAIL, mContactDetailsList);
            verify(mContactDetailsView).hideLoading();
        }

    @Test
    public void toggleFavouriteTest() {
        // Given initialized ContactDetailsPresenter with initialized contact details
        // When loading of contact details are requested
        PowerMockito.mockStatic(Log.class);
        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        doNothing().when(mLocalRepository).clearContacts();
        doNothing().when(mLocalRepository).updateContact(CONTACT);
        when(mLocalRepository.getContactById(CONTACT.getId())).thenReturn(CONTACT);

        mContactDetailsPresenter.toggleFavourite();

        CONTACT_DETAIL.setFavorite(false);
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setId(ID);
        contactDetail.setFavorite(false);
        verify(mContactsRepository).editContactDetail(mLoadContactsCallbackCaptor.capture(), eq(ID),  eq(TOGGLE_FAVOURITE_API), refEq(contactDetail));
        mLoadContactsCallbackCaptor.getValue().onSuccess(CONTACT_DETAIL, TOGGLE_FAVOURITE_API);

        verify(mContactDetailsView).showFavourite(false);
        verify(mContactDetailsView).hideLoading();
    }

        @Test
        public void clickOnSMS_SendsSMS() {
            mContactDetailsPresenter.setContactDetail(CONTACT_DETAIL);
            mContactDetailsPresenter.sendSMS();

            verify(mContactDetailsView).sendSMS(CONTACT_DETAIL.getPhoneNumber());
        }

    @Test
    public void clickOnEmail_SendsEmail() {
        mContactDetailsPresenter.setContactDetail(CONTACT_DETAIL);
        mContactDetailsPresenter.sendEmail();

        verify(mContactDetailsView).sendEmail(CONTACT_DETAIL.getEmail());
    }

    @Test
    public void clickOnEmail_Call() {
        mContactDetailsPresenter.setContactDetail(CONTACT_DETAIL);
        mContactDetailsPresenter.call();

        verify(mContactDetailsView).callNumber(CONTACT_DETAIL.getPhoneNumber());
    }

    @Test
    public void clickOnEmail_Dial() {
        mContactDetailsPresenter.setContactDetail(CONTACT_DETAIL);
        mContactDetailsPresenter.dial();

        verify(mContactDetailsView).dial(CONTACT_DETAIL.getPhoneNumber());
    }

}
