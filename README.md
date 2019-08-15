# Oauthclient
Oauth Client with webview popup

For Kotlin:

    OauthSmart
            .newBuilder(this).baseUrl(Defs.Facebook)
            .clientId("clientId")
            .redirectUri("https://yourRedirectUri")
            .responseType(Defs.code) //Defs.code or Defs.token 
            // It is possible to add here scope method also
            .build { s-> System.out.println(s) }
        
    Oauth.Builder(this)
            .baseUrl(Defs.Facebook)
            .clientId("clientId")
            .redirectUri("https://yourRedirectUri")
            .responseType(Defs.code) //Defs.code or Defs.token
            .scope(FacebookScope.user_photos)
            .build { s->System.out.println(s)}
        

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
