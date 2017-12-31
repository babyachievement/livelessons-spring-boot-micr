使用 curl -X POST -vu acme:acmesecret http://localhost:9999/uaa/oauth/token -H "Accept: application/json" -d "password=spring&username=jlong&scope=openid&client_secret=acmesecret&client_id=acme&grant_type=password"

中获取到的access_token
{"access_token":"2ac8a4c4-d308-468a-b2d7-ce8f07177fe4","token_type":"bearer","refresh_token":"f6515001-8b5f-4a0a-b7d2-a17e15c282c0","expires_in":43199,"scope":"openid"}

curl http://localhost:9000/hi -H "Authorization: Bearer 2ac8a4c4-d308-468a-b2d7-ce8f07177fe4"