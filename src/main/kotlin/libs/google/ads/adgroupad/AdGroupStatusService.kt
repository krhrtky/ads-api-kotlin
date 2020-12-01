package libs.google.ads.adgroupad

import com.google.ads.googleads.lib.GoogleAdsClient
import com.google.ads.googleads.lib.utils.FieldMasks
import com.google.ads.googleads.v6.enums.AdGroupAdStatusEnum
import com.google.ads.googleads.v6.resources.AdGroupAd
import com.google.ads.googleads.v6.services.AdGroupAdOperation
import com.google.ads.googleads.v6.services.MutateAdGroupAdsResponse
import com.google.ads.googleads.v6.utils.ResourceNames
import com.google.common.collect.ImmutableList

class AdGroupAdStatusService(private val client: GoogleAdsClient) {

    fun activate(customerId: Long, adGroupId: Long): Unit {

        val adGroupAd = AdGroupAd
            .newBuilder()
            .setResourceName(ResourceNames.adGroup(customerId, adGroupId))
            .setStatus(AdGroupAdStatusEnum.AdGroupAdStatus.ENABLED)
            .build()

        val result = update(
            customerId = customerId.toString(),
            adGroupAd = adGroupAd,
        )
    }

    fun stop(customerId: Long, adGroupId: Long): Unit {

        val adGroup = AdGroupAd
            .newBuilder()
            .setResourceName(ResourceNames.adGroup(customerId, adGroupId))
            .setStatus(AdGroupAdStatusEnum.AdGroupAdStatus.PAUSED)
            .build()

        val result = update(
            customerId = customerId.toString(),
            adGroupAd = adGroup
        )
    }

    private fun update(customerId: String, adGroupAd: AdGroupAd): MutateAdGroupAdsResponse? {

        val operation = AdGroupAdOperation.newBuilder()
                .setUpdate(adGroupAd)
                .setUpdateMask(FieldMasks.allSetFieldsOf(adGroupAd))
                .build()

        return try {
            client
                .latestVersion
                .createAdGroupAdServiceClient()
                .use {
                    it.mutateAdGroupAds(
                        customerId,
                        ImmutableList.of(operation)
                    )
                }
        } catch (e: Throwable) {
            println(e)
            null
        }
    }
}
