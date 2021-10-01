package libs.google.ads.adgroupad

import com.google.ads.googleads.lib.GoogleAdsClient
import com.google.ads.googleads.lib.utils.FieldMasks
import com.google.ads.googleads.v8.enums.AdGroupAdStatusEnum
import com.google.ads.googleads.v8.resources.AdGroupAd
import com.google.ads.googleads.v8.services.AdGroupAdOperation
import com.google.ads.googleads.v8.services.MutateAdGroupAdsResponse
import com.google.ads.googleads.v8.utils.ResourceNames
import com.google.common.collect.ImmutableList
import org.slf4j.LoggerFactory

class AdGroupAdStatusService(private val client: GoogleAdsClient) {
    private val logger = LoggerFactory.getLogger(javaClass)

    fun activate(customerId: Long, adGroupId: Long) {

        val adGroupAd = AdGroupAd
            .newBuilder()
            .setResourceName(ResourceNames.adGroup(customerId, adGroupId))
            .setStatus(AdGroupAdStatusEnum.AdGroupAdStatus.ENABLED)
            .build()

        val result = update(
            customerId = customerId.toString(),
            adGroupAd = adGroupAd,
        )

        logger.info(result?.allFields.toString())
    }

    fun stop(customerId: Long, adGroupId: Long) {

        val adGroup = AdGroupAd
            .newBuilder()
            .setResourceName(ResourceNames.adGroup(customerId, adGroupId))
            .setStatus(AdGroupAdStatusEnum.AdGroupAdStatus.PAUSED)
            .build()

        val result = update(
            customerId = customerId.toString(),
            adGroupAd = adGroup
        )

        logger.info(result?.allFields.toString())
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
            logger.error("Occur error on update status AdGroupAd.", e)
            null
        }
    }
}
