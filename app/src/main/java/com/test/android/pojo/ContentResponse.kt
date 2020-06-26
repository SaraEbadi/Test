package com.test.android.pojo

import com.squareup.moshi.JsonClass

/**
 * Used to main response model that receive from server and includes all details of contents.
 *
 * @property elements The list of [DetailContent] item received from the server.
 * @property hasNext Represents whether the next page exists.
 */
@JsonClass(generateAdapter = true)
data class ContentResponse(val elements: List<DetailContent>, val hasNext: Boolean)

/**
 * Used to get element detail of Content that detected by type name of content.
 *
 * @property id The id of each item of DetailContent.
 * @property title The title of each item of DetailContent.
 * @property publishDate The date of publish for News or Article.
 * @property thumbnailPath The thumbnails of image which is smaller image.
 * @property contentType The type of Content element that detected by type name of content.
 * @property contentBody The description of each item of DetailContent.
 * @property imagePath The path of image that which is original image.
 */
@JsonClass(generateAdapter = true)
data class DetailContent(
    val id: Long,
    val title: String,
    val publishDate: String,
    val thumbnailPath: String,
    val contentType: ContentType? = null,
    val contentBody: String? = null,
    val imagePath: String? = null
)

/**
 * Used to detect of content type for News or Article.
 *
 * @property id The id of type content that included news or articles.
 */
@JsonClass(generateAdapter = true)
data class ContentType(val id: Int)
