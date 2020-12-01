import libs.google.ads.adgroupad.AdGroupFindService
import libs.google.ads.base.GoogleAdsClient

fun main(args: Array<String>) {
    val client = GoogleAdsClient.instance
    val service = AdGroupFindService(client = client)
}
