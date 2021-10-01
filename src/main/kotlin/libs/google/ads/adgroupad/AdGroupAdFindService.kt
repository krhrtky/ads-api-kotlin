package libs.google.ads.adgroupad

import com.google.ads.googleads.lib.GoogleAdsClient
import com.google.ads.googleads.v8.resources.AdGroupAdName
import org.slf4j.LoggerFactory

class AdGroupAdFindService(private val client: GoogleAdsClient) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun find(customerId: String, adGroupId: String, adId: String) {

        client.latestVersion.createAdGroupAdServiceClient().use { it ->

            val adGroupAdName = AdGroupAdName.of(
                customerId,
                adGroupId,
                adId,
            )

            val adGroupAd = it.getAdGroupAd(adGroupAdName)

            logger.info(adGroupAd.allFields.toString())
        }
    }
}
