package com.google.android.stardroid.test;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SplashScreenActivityTest {
    @Test
    public void useAppContext() {
	Assert.fail("I did this on purpose");
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.google.android.stardroid", appContext.getPackageName());
    }
}
