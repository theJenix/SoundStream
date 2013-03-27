package com.lastcrusade.soundstream.util;

import static org.junit.Assert.*;

import com.lastcrusade.soundstream.model.PlaylistEntry;
import com.lastcrusade.soundstream.model.SongMetadata;

public class CustomAssert {

    /**
     * Test to ensure SongMetadata objects are equal.
     * 
     * @param expected
     * @param actual
     */
    public static void assertSongMetaEquals(SongMetadata expected,
            SongMetadata actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getArtist(), actual.getArtist());
        assertEquals(expected.getAlbum(), actual.getAlbum());
        assertEquals(expected.getFileSize(), actual.getFileSize());
        assertEquals(expected.getMacAddress(), actual.getMacAddress());
    }
    
    public static void assertPlaylistEntry(PlaylistEntry expected,
            PlaylistEntry actual) {
        assertSongMetaEquals(expected, actual);
        assertEquals(expected.isLoaded(), actual.isLoaded());
        assertEquals(expected.isPlayed(), actual.isPlayed());
        assertEquals(expected.getFilePath(), actual.getFilePath());
    }
}
