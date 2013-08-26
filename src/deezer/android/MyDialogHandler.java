package deezer.android;


import android.os.Bundle;

import com.deezer.sdk.DeezerConnect;
import com.deezer.sdk.DeezerError;
import com.deezer.sdk.DialogError;
import com.deezer.sdk.DialogListener;
import com.deezer.sdk.OAuthException;

 class MyDialogHandler implements DialogListener {
	 
	 private DeezerConnect deezerConnect;
	public MyDialogHandler(DeezerConnect deezerConnect){
		this.deezerConnect = deezerConnect;
	}
	public void onComplete(final Bundle values) {
		deezerConnect.getAccessToken();
	}
	
	public void onDeezerError(final DeezerError deezerError) {
	 
	}
	
	public void onError(final DialogError dialogError) {
	 
	}
	
	public void onCancel() {
	 
	}
	
	public void onOAuthException(OAuthException oAuthException) {
	 
	}//met
}//inner class