package com.ahsanzaman.contactsapp.contacts;

import android.util.Log;

import com.ahsanzaman.contactsapp.data.repository.ContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.IContactsRepository;
import com.ahsanzaman.contactsapp.data.repository.ILocalRepository;
import com.ahsanzaman.contactsapp.model.Contact;
import com.ahsanzaman.contactsapp.network.callback.RemoteServiceCallback;
import com.ahsanzaman.contactsapp.ui.module.contact.presenter.ContactsPresenter;
import com.ahsanzaman.contactsapp.ui.module.contact.view.ContactsView;
import com.google.common.collect.Lists;

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
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Ahsan Zaman on 27-07-2017.
 */


@RunWith(PowerMockRunner.class)
@PrepareForTest({Log.class})
public class ContactsPresenterTest{

    private static List<Contact> CONTACTS = Lists.newArrayList(new Contact(100001, "Test", "Name", true),
            new Contact(100002, "Test1", "Name", false));

    private static List<Contact> EMPTY_CONTACTS = new ArrayList<>(0);

    @Mock
    private IContactsRepository mContactsRepository;

    @Mock
    private ILocalRepository mLocalRepository;

    @Mock
    private ContactsView mContactsView;

    @Captor
    private ArgumentCaptor<RemoteServiceCallback> mLoadContactsCallbackCaptor;

    private ContactsPresenter mContactsPresenter;

    @Before
    public void setupContactsPresenter() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this);

        // Get a reference to the class under test
        mContactsPresenter = new ContactsPresenter(mContactsView, mContactsRepository);
    }

    @Test
    public void loadContactsFromRepositoryAndLoadIntoView() {
        // Given an initialized ContactsPresenter with initialized contacts
        // When loading of contacts is requested
        PowerMockito.mockStatic(Log.class);
        when(mContactsRepository.getLocalRepository()).thenReturn(mLocalRepository);
        doNothing().when(mLocalRepository).clearContacts();
        doNothing().when(mLocalRepository).setAllContacts(CONTACTS);

        mContactsPresenter.loadContacts(true);

        mContactsPresenter.loadContacts(true);

        verify(mContactsRepository, times(2)).getContacts(mLoadContactsCallbackCaptor.capture(), eq(CONTACTS_REQUEST_CODE));
        mLoadContactsCallbackCaptor.getValue().onSuccess(CONTACTS, CONTACTS_REQUEST_CODE);
        mLoadContactsCallbackCaptor.getValue().onSuccess(new ArrayList<Contact>(), CONTACTS_REQUEST_CODE);

        verify(mContactsView).updateContacts(CONTACTS);
        verify(mContactsView, times(2)).hideLoading();
        verify(mContactsView).showNoDataFound();
        verify(mContactsView, times(2)).showAddContactButton();
    }

    @Test
    public void clickOnFab_ShowsAddsContactUi() {
        // When adding a new contact
        mContactsPresenter.addContact();

        // Then add contact UI is shown
        verify(mContactsView).openAddContactScreen();
    }

    @Test
    public void clickOnContact_ShowsDetailUi() {
        // Given a stubbed contact
        Contact requestedContact = new Contact(100001, "Test", "Name", true);

        // When open contact details is requested
        mContactsPresenter.onItemClick(requestedContact);

        // Then contact detail UI is shown
        verify(mContactsView).showContactDetails(requestedContact);
    }
}