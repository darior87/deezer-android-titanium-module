/**
 * This file was auto-generated by the Titanium Module SDK helper for Android
 * Appcelerator Titanium Mobile
 * Copyright (c) 2009-2013 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Apache Public License
 * Please see the LICENSE included with this distribution for details.
 *
 */
package deezer.android;

import java.io.IOException;
import java.net.MalformedURLException;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;

import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;

import android.os.Bundle;

import com.deezer.sdk.DeezerConnect;
import com.deezer.sdk.DeezerConnectImpl;
import com.deezer.sdk.DeezerError;
import com.deezer.sdk.DeezerRequest;
import com.deezer.sdk.DialogError;
import com.deezer.sdk.DialogListener;
import com.deezer.sdk.OAuthException;


@Kroll.module(name="DeezerAndroidModule", id="deezer.android")
public class DeezerAndroidModuleModule extends KrollModule
{

	// Standard Debugging variables
	private static final String TAG = "DeezerAndroidModuleModule";
private final String APP_ID = "xxx";
private final static String[] PERMISSIONS = new String[] {"basic_access","offline_access","email"};
	private DeezerInterface deezerInterface;
	// You can define constants with @Kroll.constant, for example:
	// @Kroll.constant public static final String EXTERNAL_NAME = value;
	private 	DeezerConnect connection;
	public DeezerAndroidModuleModule()
	{
		super();

	    connection = new DeezerConnectImpl(APP_ID);
	    deezerInterface = new DeezerInterface();
	}
	
	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		Log.d(TAG, "inside onAppCreate");
		// put module init code that needs to run when the application is created
	}

	// Methods
	@Kroll.method
	public void loginDeezer()
	{

	    connection.authorize(deezerInterface, PERMISSIONS, new ReconnectDialogHandler());

	}
	// Methods
	@Kroll.method @Kroll.getProperty
	public String userToken()
	{
		return connection.getAccessToken();
	}
	// Methods
	@Kroll.method @Kroll.getProperty
	public String userId()
	{

		DeezerRequest request = new DeezerRequest( "/user/me" );
	    String result = null;
	    try {
			result = connection.requestSync( request );
	    }//try
	    catch( MalformedURLException ex ) {

	    }//catch
	    catch( IOException ex ) {

	    }//catch
	    catch (OAuthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DeezerError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return result;
		
	}

	
	// Properties
	@Kroll.getProperty
	public String getExampleProp()
	{
		Log.d(TAG, "get example property");
		return "hello world";
	}
	
	
	@Kroll.setProperty
	public void setExampleProp(String value) {
		Log.d(TAG, "set example property: " + value);
	}
	
	class ReconnectDialogHandler implements DialogListener {

	    @Override
	    public void onComplete(final Bundle values) {


	    }//met

	    @Override
	    public void onDeezerError(final DeezerError deezerError) {

	    }//met

	    @Override
	    public void onError(final DialogError dialogError) {

	    }//met

	    @Override
	    public void onCancel() {

	    }//met

	    @Override
	    public void onOAuthException(OAuthException oAuthException) {

	    }//met


	}//inner class

}




