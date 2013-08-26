package deezer.android;

import java.io.IOException;
import java.net.MalformedURLException;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.deezer.sdk.DeezerConnect;
import com.deezer.sdk.DeezerConnectImpl;
import com.deezer.sdk.DeezerError;
import com.deezer.sdk.DeezerRequest;
import com.deezer.sdk.DialogError;
import com.deezer.sdk.DialogListener;
import com.deezer.sdk.OAuthException;
import com.deezer.sdk.SessionStore;
public class DeezerInterface extends Activity {

private final String APP_ID = "118155";
private final static String[] PERMISSIONS = new String[] {"basic_access","offline_access","email_address"};
private static final String LOG_TAG = "BaseActvt";
DeezerConnect connection;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    StrictMode.enableDefaults();

    System.out.println("onCreate");

    connection = new DeezerConnectImpl(this, APP_ID);
}


@Override
public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.

    return true;
}
public String getAccessToken(){
	try{
		if(connection.getAccessToken()!=null && connection.getAccessToken()!="" ){
			this.login();
		}
		while(connection.getAccessToken()==null || connection.getAccessToken()==""){this.login();}

		return connection.getAccessToken();	
	}catch (Exception e) {
		// TODO: handle exception
	
	}
	return "";
}
public String getUserId(){
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
public void login(){
    System.out.println("Button clicked");
    connection.authorize(this, PERMISSIONS, new ReconnectDialogHandler());
}


public class ReconnectDialogHandler implements DialogListener {

    @Override
    public void onComplete(final Bundle values) {
        SessionStore sessionStore = new SessionStore();
        sessionStore.save( connection, DeezerInterface.this );

    }//met

    @Override
    public void onDeezerError(final DeezerError deezerError) {

        Log.e( LOG_TAG, "DialogError error during login" , deezerError );
    }//met

    @Override
    public void onError(final DialogError dialogError) {

        Log.e( LOG_TAG, "DialogError error during login", dialogError );
    }//met

    @Override
    public void onCancel() {

    }//met

    @Override
    public void onOAuthException(OAuthException oAuthException) {

    }//met


}//inner class

}
