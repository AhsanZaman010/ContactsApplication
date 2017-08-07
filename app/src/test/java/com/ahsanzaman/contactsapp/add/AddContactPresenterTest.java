package com.ahsanzaman.contactsapp.add;

import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.ILocalRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.model.response.ContactDetail;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.add.AddContactPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit.EditContactPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.add.AddContactView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;

import static com.ahsanzaman.contactsapp.ui.module.contact.presenter.add.AddContactPresenter.ADD_CONTACT_REQUEST_CODE;
import static com.ahsanzaman.contactsapp.ui.module.contact.presenter.edit.EditContactPresenter.EDIT_CONTACT_REQUEST_CODE;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Accolite- on 8/6/2017.
 */

public class AddContactPresenterTest {

    private static ContactDetail CONTACT_DETAIL = new ContactDetail(100001, "Test", "Name", "", "", "", true);

    private static long ID = 100001;

    private static Contact CONTACT = new Contact(100001, "Test", "Name", true);

    @Mock
    private IContactsRepository mContactsRepository;

    @Mock
    private ILocalRepository mLocalRepository;

    @Mock
    private AddContactView mAddContactView;

    @Captor
    private ArgumentCaptor<RemoteServiceCallback> mLoadContactsCallbackCaptor;

    private AddContactPresenter mAddContactPresenter;

    @Before
    public void setupContactsPresenter() {
        MockitoAnnotations.initMocks(this);

        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
        mAddContactPresenter = new AddContactPresenter(mAddContactView,  mContactsRepository);
    }

    @Test
    public void makeAddContactDetailAndFinishOnSuccessTest() {
        PowerMockito.mockStatic(Log.class);
        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        when(mLocalRepository.getContactById(ID)).thenReturn(CONTACT);
        when(mLocalRepository.getContactById(CONTACT.getId())).thenReturn(CONTACT);
        when(mAddContactView.getContactDetail()).thenReturn(CONTACT_DETAIL);


        mAddContactPresenter.onSave();

        verify(mContactsRepository).addContactDetail(mLoadContactsCallbackCaptor.capture(), eq(ADD_CONTACT_REQUEST_CODE), eq(CONTACT_DETAIL));
        mLoadContactsCallbackCaptor.getValue().onSuccess(CONTACT_DETAIL, ADD_CONTACT_REQUEST_CODE);

        verify(mAddContactView).finishOnSuccess();
        verify(mLocalRepository).clearContacts();
    }

}
