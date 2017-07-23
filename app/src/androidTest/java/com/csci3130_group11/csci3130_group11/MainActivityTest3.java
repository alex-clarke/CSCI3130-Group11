package com.csci3130_group11.csci3130_group11;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest3 {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest3() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.chat_button), withText("CHAT"), isDisplayed()));
        appCompatButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3595438);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.messageTextView),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView2 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView2.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView3 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView3.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView4.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView5.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\nTom: Hello\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction editText = onView(
                allOf(withClassName(is("android.widget.EditText")),
                        withParent(allOf(withId(R.id.custom),
                                withParent(withId(R.id.customPanel)))),
                        isDisplayed()));
        editText.perform(replaceText("Tom"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("Enter")));
        appCompatButton2.perform(scrollTo(), click());

        pressBack();

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3580678);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.chat_button), withText("CHAT"), isDisplayed()));
        appCompatButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(3596930);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.messageTextView),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView6.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView7 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView7.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView8 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView8.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView9 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView9.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\n"), closeSoftKeyboard());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView10 = onView(
                allOf(withId(R.id.messageTextView), withText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\n"),
                        withParent(withId(R.id.scrollview))));
        appCompatTextView10.perform(scrollTo(), replaceText("Karen: Someone please check on my tomatoes.\nTom: They're dead!\nTom: They're all dead!\nKaren: Oh, the tomanity!\nTom: Hello\n"), closeSoftKeyboard());

    }

}
