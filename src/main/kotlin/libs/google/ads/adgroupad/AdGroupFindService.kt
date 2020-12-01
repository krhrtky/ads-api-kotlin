package libs.google.ads.adgroupad

import com.google.ads.googleads.lib.GoogleAdsClient
import com.google.ads.googleads.v6.services.SearchGoogleAdsRequest

class AdGroupFindService(private val client: GoogleAdsClient) {

    fun find(customerId: String, campaignId: String = "") {

        client.latestVersion.createGoogleAdsServiceClient().use { googleAdsServiceClient ->
            var searchQuery = "SELECT campaign.id, ad_group.id, ad_group.name FROM ad_group"
            if (campaignId.isBlank()) {
                searchQuery += java.lang.String.format(" WHERE campaign.id = %d", campaignId)
            }

            // Creates a request that will retrieve all ad groups using pages of the specified page size.
            val request = SearchGoogleAdsRequest.newBuilder()
                .setCustomerId(customerId.toString())
                .setPageSize(1_000)
                .setQuery(searchQuery)
                .build()
            // Issues the search request.
            val searchPagedResponse = googleAdsServiceClient.search(request)
            // Iterates over all rows in all pages and prints the requested field values for the ad group
            // in each row.
            for (googleAdsRow in searchPagedResponse.iterateAll()) {
                val adGroup = googleAdsRow.adGroup
                System.out.printf(
                    "Ad group with ID %d and name '%s' was found in campaign with ID %d.%n",
                    adGroup.id, adGroup.name, googleAdsRow.campaign.id
                )
            }
        }
    }
}
