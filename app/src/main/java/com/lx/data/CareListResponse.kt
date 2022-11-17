package com.lx.data


import com.google.gson.annotations.SerializedName

data class CareListResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("header")
    val header: Header,
    @SerializedName("message")
    val message: String
) {
    data class Data(
        @SerializedName("careAddress")
        val careAddress: String,
        @SerializedName("careApproval")
        val careApproval: Int,
        @SerializedName("careEducation")
        val careEducation: Int,
        @SerializedName("careExperience")
        val careExperience: String,
        @SerializedName("careId")
        val careId: String,
        @SerializedName("careImage")
        val careImage: String,
        @SerializedName("careName")
        val careName: String,
        @SerializedName("careNo")
        val careNo: Int,
        @SerializedName("carePw")
        val carePw: String,
        @SerializedName("careX")
        val careX: Double,
        @SerializedName("careY")
        val careY: Double
    )
    data class Header(
        @SerializedName("total")
        val total: Int
    )
}