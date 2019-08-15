# Oauthclient
Oauth Client with webview popup

For Kotlin:

         new Oauth.Builder(this)
                .baseUrl(Defs.Facebook)
                .clientId("clientId")
                .redirectUri("https://uri")
                .responseType(Defs.code) //Defs.code or Defs.token
                //   .scope(FacebookScope.user_photos)
                .build(authCode ->{
                    Toast.makeText(this,authCode,Toast.LENGTH_LONG).show();
                })
		
		
		
        OauthSmart.newBuilder(this).baseUrl(Defs.Facebook).clientId("clientId").redirectUri("https://uri").responseType(Defs.code).build(authCode->{
            Toast.makeText(this,authCode,Toast.LENGTH_LONG).show();
        });
        

# Dependencies 
<pre>
	allprojects {
		repositories {
 			maven { url 'https://jitpack.io' }
		}
	}
  
  	dependencies {
	        implementation 'com.github.hasan5151:Oauthclient:2.5'
	}
</pre>
