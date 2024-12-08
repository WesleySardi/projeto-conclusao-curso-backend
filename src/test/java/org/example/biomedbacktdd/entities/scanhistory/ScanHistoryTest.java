package org.example.biomedbacktdd.entities.scanhistory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ScanHistoryTest {

    private ScanHistory scanHistory;

    @BeforeEach
    void setUp() {
        scanHistory = new ScanHistory();
    }

    @Test
    void testGettersAndSetters() {
        scanHistory.setScanId(1L);
        assertEquals(1L, scanHistory.getScanId());

        scanHistory.setScanName("John Doe");
        assertEquals("John Doe", scanHistory.getScanName());

        scanHistory.setScanEmail("johndoe@example.com");
        assertEquals("johndoe@example.com", scanHistory.getScanEmail());

        scanHistory.setScanPhone("123456789");
        assertEquals("123456789", scanHistory.getScanPhone());

        scanHistory.setDepCpf("12345678900");
        assertEquals("12345678900", scanHistory.getDepCpf());

        OffsetDateTime now = OffsetDateTime.now();
        scanHistory.setScanDateTime(now);
        assertEquals(now, scanHistory.getScanDateTime());

        scanHistory.setLatitude(12.345678);
        assertEquals(12.345678, scanHistory.getLatitude());

        scanHistory.setLongitude(98.765432);
        assertEquals(98.765432, scanHistory.getLongitude());
    }

    @Test
    void testEmptyConstructor() {
        assertNull(scanHistory.getScanId());
        assertNull(scanHistory.getScanName());
        assertNull(scanHistory.getScanEmail());
        assertNull(scanHistory.getScanPhone());
        assertNull(scanHistory.getDepCpf());
        assertNull(scanHistory.getScanDateTime());
        assertNull(scanHistory.getLatitude());
        assertNull(scanHistory.getLongitude());
    }

    @Test
    void testMandatoryFields() {
        scanHistory.setScanName("John Doe");
        scanHistory.setScanEmail("johndoe@example.com");
        scanHistory.setScanPhone("123456789");
        scanHistory.setScanDateTime(OffsetDateTime.now());

        assertNotNull(scanHistory.getScanName(), "Scan name should not be null");
        assertNotNull(scanHistory.getScanEmail(), "Scan email should not be null");
        assertNotNull(scanHistory.getScanPhone(), "Scan phone should not be null");
        assertNotNull(scanHistory.getScanDateTime(), "Scan date time should not be null");
    }

    @Test
    void testOptionalFields() {
        scanHistory.setDepCpf("12345678900");
        scanHistory.setLatitude(12.345678);
        scanHistory.setLongitude(98.765432);

        assertEquals("12345678900", scanHistory.getDepCpf());
        assertEquals(12.345678, scanHistory.getLatitude());
        assertEquals(98.765432, scanHistory.getLongitude());
    }

    @Test
    void testEqualsAndHashCode() {
        ScanHistory sh1 = new ScanHistory();
        sh1.setScanId(1L);
        sh1.setScanName("John Doe");
        sh1.setScanEmail("johndoe@example.com");
        sh1.setScanPhone("123456789");
        sh1.setScanDateTime(OffsetDateTime.now());

        ScanHistory sh2 = new ScanHistory();
        sh2.setScanId(1L);
        sh2.setScanName("John Doe");
        sh2.setScanEmail("johndoe@example.com");
        sh2.setScanPhone("123456789");
        sh2.setScanDateTime(sh1.getScanDateTime());

        assertEquals(sh1, sh2);
        assertEquals(sh1.hashCode(), sh2.hashCode());

        sh2.setScanId(2L);
        assertNotEquals(sh1, sh2);
        assertNotEquals(sh1.hashCode(), sh2.hashCode());
    }
}
