package libs.google.ads.base

import com.google.auth.oauth2.UserCredentials

class EnvironmentVariablesProvider {

    companion object {
        fun create(): UserCredentials {

            val clientId = System.getenv("GOOGLE_ADS_CLIENT_ID") ?: ""
            val clientSecret = System.getenv("GOOGLE_ADS_CLIENT_SECRET") ?: ""
            val refreshToken = System.getenv("GOOGLE_ADS_REFRESH_TOKEN") ?: ""

            if (
                clientId.isBlank() ||
                clientSecret.isBlank() ||
                refreshToken.isBlank()
            ) {
                throw IllegalPropertyException("Fail create user credentials. Some property is blank.")
            }

            return UserCredentials
                .newBuilder()
                .setClientId(clientId)
                .setClientSecret(clientSecret)
                .setRefreshToken(refreshToken)
                .build()
        }
    }
}
