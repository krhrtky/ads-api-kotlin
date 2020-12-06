import libs.google.ads.adgroupad.AdGroupAdFindService
import libs.google.ads.base.GoogleAdsClient

fun main() {
    val client = GoogleAdsClient.instance
    val service = AdGroupAdFindService(client = client)

    service.find(
        customerId = "",
        adGroupId = "",
        adId = "",
    )
}
