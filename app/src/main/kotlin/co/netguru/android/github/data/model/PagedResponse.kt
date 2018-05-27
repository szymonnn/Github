package co.netguru.android.github.data.model

import com.google.gson.annotations.SerializedName

data class PagedResponse<T>(
        @SerializedName("total_count") var totalCount: Int,
        @SerializedName("incomplete_results") var incompleteResults: Boolean,
        @SerializedName("items") var items: List<T>
)