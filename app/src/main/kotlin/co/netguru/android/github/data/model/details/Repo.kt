package co.netguru.android.github.data.model.details

import com.google.gson.annotations.SerializedName
import java.util.*

data class Repo(
        var name: String,
        var language: String? = null,
        var watchers: Int? = null,
        @SerializedName("updated_at") var lastUpdated: Date? = null
)