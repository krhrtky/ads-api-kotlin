package libs.google.ads.base

import com.google.ads.googleads.lib.GoogleAdsClient

class GoogleAdsClient {

    companion object {
        private val developerToken = System.getenv("GOOGLE_ADS_DEVELOPER_TOKEN") ?: throw
        throw IllegalPropertyException("Fail create Google Ads Client. Developer token is blank.")

        private val loginCustomerId = System.getenv("GOOGLE_ADS_LOGIN_CUSTOMER_ID") ?: throw
        throw IllegalPropertyException("Fail create Google Ads Client. Login Customer ID is blank.")

        val instance = GoogleAdsClient
            .newBuilder()
            .setDeveloperToken(developerToken)
            .setLoginCustomerId(loginCustomerId.toLong())
            .setCredentials(EnvironmentVariablesProvider.create())
            .build() ?: throw Exception("Fail create Google Ads Client. Occur error.")
    }
}
