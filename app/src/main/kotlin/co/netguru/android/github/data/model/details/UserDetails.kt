package co.netguru.android.github.data.model.details

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserDetails(
        var id: Long,
        var login: String,
        @SerializedName("avatar_url") var avatarUrl: String? = null,
        var type: String,
        var name: String,
        var company: String,
        var blog: String,
        var location: String,
        var createdAt: Date
)