package co.netguru.android.github.data.model

import com.google.gson.annotations.SerializedName

data class User(
        var id: Long,
        var login: String,
        @SerializedName("avatar_url") var avatarUrl: String? = null,
        var score: Double? = null
)