package com.huzzy.oauth_client.Function;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.huzzy.oauth_client.Model.Getcode;
import com.huzzy.oauth_client.R;

import java.util.Map;

public class Authorization {

    private ProgressDialog pd;
    private Dialog authpopup;
    WebView webView;
    String url;
    WebSettings webSettings;
    Boolean isToken=false;

    /**
     * Get authorization code from given url
     * @param authCode Authorization Code
     * @param oauthMap Url Parameters
     * @param baseUrl Base Url
     */
    @SuppressLint("SetJavaScriptEnabled")
    public void get (Getcode authCode, Map<String,Object> oauthMap, String baseUrl, Context context) {
        String params = MapQuery.urlEncodeUTF8(oauthMap);
        authpopup = dialogSettings(R.layout.popup_auth,context);
        webView =  authpopup.findViewById(R.id.webView);
        url =String.format("%s?%s",baseUrl,params);
        webView.requestFocus(View.FOCUS_DOWN);
        webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //Show a progress dialog to the user
        pd = ProgressDialog.show(context, "", context.getString(R.string.loading),true);
        authpopup.show();

        //Set a custom web view client
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                //This method will be executed each time a page finished loading.
                //The only we do is dismiss the progressDialog, in case we are showing any.
                if(pd!=null && pd.isShowing()){
                    pd.dismiss();
                    webView.setVisibility(View.VISIBLE);
                }
            }

            public void onReceivedError(WebView view, int errorCode,
                                        String description, String failingUrl) {
                authCode.code("");
                authpopup.dismiss();
                Toast.makeText(context,"Oauth Error:"+ errorCode,Toast.LENGTH_LONG).show();
                /*try{
                    throw new OauthException();
                }catch (OauthException e) {
                    OauthException ex = new OauthException("State code didnt match",new Throwable("State code didnt match"));
                    ex.printStackTrace();
                }*/
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String authorizationUrl) {
                //This method will be called when the Auth proccess redirect to our RedirectUri.
                //We will check the url looking for our RedirectUri.
                if (!oauthMap.containsKey("redirect_uri")){
                    //authCode.code("Redirect Uri Missing");
                    authpopup.dismiss();
                    /*try{
                        throw new OauthException();
                    }catch (OauthException e) {
                        OauthException ex = new OauthException("Redirect Uri Missing",new Throwable("Redirect Uri Missing"));
                        ex.printStackTrace();
                    }*/
                    authCode.code("");
                    Toast.makeText(context,R.string.redirect,Toast.LENGTH_LONG).show();
                    return true;
                 }

                else if(authorizationUrl.startsWith(oauthMap.get("redirect_uri").toString())){
                    if (oauthMap.containsKey("response_type") && oauthMap.get("response_type").toString().contentEquals("token")) {
                        authorizationUrl = authorizationUrl.replace("#", "?");
                        isToken=true;
                    }
                    Uri uri = Uri.parse(authorizationUrl);

                    //We take from the url the authorizationToken and the state token. We have to check that the state token returned by the Service is the same we sent.
                    //If not, that means the request may be a result of CSRF and must be rejected.
                    if (oauthMap.containsKey("state")) {
                        String stateToken = uri.getQueryParameter("state");
                        //   if(stateToken==null){
                        if (stateToken == null || !stateToken.equals(oauthMap.get("state").toString())) {
                            authpopup.dismiss();
                            authCode.code("");
                            Toast.makeText(context,context.getString(R.string.state),Toast.LENGTH_LONG).show();
                            return true;
                            /*try{
                                throw new OauthException();
                            }catch (OauthException e) {
                                OauthException ex = new OauthException("State code didnt match",new Throwable("State code didnt match"));
                                ex.printStackTrace();
                            }*/
                        }
                    }
                    if (isToken)
                        authCode.code(uri.getQueryParameter("access_token"));
                    else
                        authCode.code(uri.getQueryParameter("code"));
                    authpopup.dismiss();
                }else{
                    webView.loadUrl(authorizationUrl);
                }
                return true;
            }
        });
        webView.loadUrl(url);
    }

    /**
     * Set popup window
     * @param LayoutId Layout Id
     * @param context Context
     * */
    private Dialog dialogSettings(int LayoutId,Context context) {
        Dialog dialog = new Dialog(context);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getAttributes().windowAnimations = R.style.DialogAnimation;
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
        dialog.setContentView(LayoutId);
        dialog.setCancelable(true);
        return dialog;
    }
}
