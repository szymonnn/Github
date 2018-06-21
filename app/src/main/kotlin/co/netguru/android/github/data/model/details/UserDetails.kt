package co.netguru.android.github.data.model.details

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDetails(
        var id: Long,
        var login: String,
        @SerializedName("avatar_url") var avatarUrl: String? = null,
        var type: String? = null,
        var name: String? = null,
        var company: String? = null,
        var blog: String? = null,
        var location: String? = null,
        var createdAt: Date? = null
)