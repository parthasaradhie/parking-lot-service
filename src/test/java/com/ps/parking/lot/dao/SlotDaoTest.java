package com.ps.parking.lot.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;

import com.ps.parking.lot.dao.repositories.SlotRepository;
import com.ps.parking.lot.models.domain.enums.SlotSize;
import com.ps.parking.lot.models.entities.ParkingLot;
import com.ps.parking.lot.models.entities.Slot;

@ExtendWith(MockitoExtension.class)
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = SlotDao.class)
public class SlotDaoTest {
    private static final Pageable SINGLE_OBJECT_PAGESIZE = Pageable.ofSize(1);
    private static final SlotSize LARGE_SLOT_SIZE = SlotSize.LARGE;
    private static final String PARKING_LOT_ID = "PARKING_LOT_ID1";
    private static final String SLOT_MNEMONIC = "11";
    private static final Long SLOT_DB_ID = 21L;
    private static final Long PARKING_LOT_DB_COLUMN_ID = 2L;
    private static final ParkingLot.Builder PARKING_LOT_BUILDER = ParkingLot.builder().id(PARKING_LOT_DB_COLUMN_ID)
            .mnemonic(PARKING_LOT_ID);

    private Slot slot;
    @MockBean
    private SlotRepository slotRepository;
    @InjectMocks
    private SlotDao slotDao;

    @BeforeEach
    void beforeEach() {
        slot = Slot.builder().id(SLOT_DB_ID).isAvailable(true).mnemonic(SLOT_MNEMONIC)
                .parkingLot(PARKING_LOT_BUILDER.build()).slotSize(LARGE_SLOT_SIZE).build();
    }

    @Test
    void testFindByMnemonicAndParkingLotId() {
		when(slotRepository.findByMnemonicAndParkingLot_Id(SLOT_MNEMONIC, PARKING_LOT_DB_COLUMN_ID))
					.thenReturn(Optional.of(slot));
        Optional<Slot> actual = slotDao.findByMnemonicAndParkingLotId(SLOT_MNEMONIC, PARKING_LOT_DB_COLUMN_ID);
        assertTrue(actual.isPresent());
        assertEquals(Optional.of(slot), actual);
    }

    @Test
    void testLockAndGetValidSlot() {
        when(slotRepository.lockAndGetValidSlotsInternal(SINGLE_OBJECT_PAGESIZE, LARGE_SLOT_SIZE, PARKING_LOT_DB_COLUMN_ID))
					.thenReturn(List.of(slot));
        when(slotRepository.save(any())).thenReturn(slot);
        Optional<Slot> actual = slotDao.lockAndGetValidSlot(LARGE_SLOT_SIZE, PARKING_LOT_DB_COLUMN_ID);
        assertTrue(actual.isPresent());
        assertEquals(Optional.of(slot), actual);
    }

    @Test
    void testSave() {
        when(slotRepository.save(any())).thenReturn(slot);
        Slot actual = slotDao.save(slot);
        assertEquals(slot, actual);
    }

    @Test
    void testSaveAll() {
        when(slotRepository.saveAll(any())).thenReturn(List.of(slot));
         slotDao.saveAll(List.of(slot));
    }

    @Test
    void testSetSlotAsAvailable() {
        when(slotRepository.save(any())).thenReturn(slot);
        slotDao.setSlotAsAvailable(slot, PARKING_LOT_DB_COLUMN_ID);
    }
}
