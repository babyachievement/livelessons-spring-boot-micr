curl -X POST -vu acme:acmesecret http://localhost:9999/uaa/oauth/token -H "Accept: application/json" -d "password=spring&username=jlong&scope=openid&client_secret=acmesecret&client_id=acme&grant_type=password"