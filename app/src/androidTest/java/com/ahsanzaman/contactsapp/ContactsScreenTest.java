package com.ahsanzaman.contactsapp;

import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.runner.RunWith;

/**
 * Created by Accolite- on 8/9/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ContactsScreenTest {
    /*@Rule
    public ActivityTestRule<ContactsActivity> mContactsActivityTestRule =
            new ActivityTestRule<>(ContactsActivity.class);

    @Mock
    NetworkModule mNetworkModule;
    @Mock
    AppModule mAppModule;


    @Before
    public void setUp() throws Exception {
        mMockPresenter = mock(MainPresenter.class);

        when(m.provideMainView()).thenReturn(mock(MainView.class)); // this is needed to fool dagger
        when(m.provideMainPresenter(any(MainView.class), any(KeyValueStorage.class)))
                .thenReturn(mMockPresenter);

        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        DepComponent depComponent = DaggerDepComponent.builder()
                .networkModule(mNetworkModule)
                .appModule(mAppModule)
                .build();
        ContactsApplication app
                = (ContactsApplication) instrumentation.getTargetContext().getApplicationContext();

        // forced to the application object
        app.setDepComponent(depComponent);
    }
    private App getApp() {
        return (App) InstrumentationRegistry.getInstrumentation()
                .getTargetContext().getApplicationContext();
    }

    @Test
    public void clickAddContactButton_opensAddContactUi() throws Exception {
        // Click on the add contact button
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        ContactsApplication app
                = (ContactsApplication) instrumentation.getTargetContext().getApplicationContext();

        // forced to the application object
        app.setMainModule(m);
        onView(withId(R.id.add_contact_fab)).perform(click());

        // Check if the add contact screen is displayed
        onView(withId(R.id.name_et)).check(matches(isDisplayed()));
    }

    *//*@Test
    public void addContactToContactsList() throws Exception {
        String newContactTitle = "Espresso";
        String newContactDescription = "UI testing for Android";

        // Click on the add contact button
        onView(withId(R.id.fab_add_contacts)).perform(click());

        // Add contact title and description
        // Type new contact title
        onView(withId(R.id.add_contact_title)).perform(typeText(newContactTitle), closeSoftKeyboard());
        onView(withId(R.id.add_contact_description)).perform(typeText(newContactDescription),
                closeSoftKeyboard()); // Type new contact description and close the keyboard

        // Save the contact
        onView(withId(R.id.fab_add_contacts)).perform(click());

        // Scroll contacts list to added contact, by finding its description
        onView(withId(R.id.contacts_list)).perform(
                scrollTo(hasDescendant(withText(newContactDescription))));

        // Verify contact is displayed on screen
        onView(withItemText(newContactDescription)).check(matches(isDisplayed()));
    }*//*
*/
}
