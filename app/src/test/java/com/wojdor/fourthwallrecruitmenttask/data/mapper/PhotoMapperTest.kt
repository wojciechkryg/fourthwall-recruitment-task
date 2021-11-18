package com.wojdor.fourthwallrecruitmenttask.data.mapper

import com.wojdor.fourthwallrecruitmenttask.data.entity.PhotoEntity
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PhotoMapperTest {

    @Test
    fun `Check that PhotoEntity can be converted to Photo`() {
        val photoEntity = PhotoEntity("1", "Anonymous", "https://photo.com")

        val photo = photoEntity.toPhoto()

        assertEquals(photoEntity.id, photo.id)
        assertEquals(photoEntity.author, photo.author)
        assertEquals(photoEntity.imageUrl, photo.imageUrl)
    }

    @Test
    fun `Check that PhotoEntity with null values can be converted to Photo with empty values`() {
        val photoEntity = PhotoEntity(null, null, null)

        val photo = photoEntity.toPhoto()

        assertEquals("", photo.id)
        assertEquals("", photo.author)
        assertEquals("", photo.imageUrl)
    }

    @Test
    fun `Check that PhotoEntity list can be converted to Photo list`() {
        val photoEntities = List(2) {
            PhotoEntity("$it", "Anonymous", "https://photo.com")
        }

        val photos = photoEntities.toPhotos()

        assertEquals(photoEntities[0].id, photos[0].id)
        assertEquals(photoEntities[0].author, photos[0].author)
        assertEquals(photoEntities[0].imageUrl, photos[0].imageUrl)
        assertEquals(photoEntities[1].id, photos[1].id)
        assertEquals(photoEntities[1].author, photos[1].author)
        assertEquals(photoEntities[1].imageUrl, photos[1].imageUrl)
    }
}