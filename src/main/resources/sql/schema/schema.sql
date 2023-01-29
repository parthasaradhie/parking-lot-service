-- Active: 1670331844557@@127.0.0.1@3306@parking_lot

-- Active: 1670331844557@@127.0.0.1@3306

CREATE SCHEMA `parking_lot`;

DROP TABLE if EXISTS parking_lot.parking_lot;
CREATE TABLE
    parking_lot.parking_lot(
        Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Mnemonic VARCHAR(100) NOT NULL,
        INDEX(Mnemonic)
    );

DROP TABLE if EXISTS parking_lot.floor;
CREATE TABLE
    parking_lot.floor(
        Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Mnemonic MEDIUMINT NOT NULL,
        ParkingLotId INT,
        FOREIGN KEY (ParkingLotId) REFERENCES parking_lot(Id),
        INDEX(Mnemonic, ParkingLotId)
    );

DROP TABLE if EXISTS parking_lot.slot;
CREATE TABLE
    parking_lot.slot(
        Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        Mnemonic VARCHAR(100) NOT NULL,
        slotType SMALLINT NOT NULL,
        ParkingLotId INT,
        FloorId INT,
        isAvailable BOOLEAN,
        FOREIGN KEY (ParkingLotId) REFERENCES parking_lot(Id),
        FOREIGN KEY (floorId) REFERENCES floor(Id),
        INDEX(
            Mnemonic,
            ParkingLotId,
            FloorId,
            slotType
        )
    );

DROP TABLE if EXISTS parking_lot.slot_occupancy;
CREATE TABLE
    parking_lot.slot_occupancy(
        Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
        VehicleId VARCHAR(50),
        ParkingLotId INT,
        FloorId INT,
        SlotId INT,
        vehicle_type SMALLINT NOT NULL,
        start_time DATETIME,
        end_time DATETIME,
        INDEX(
            VehicleId,
            ParkingLotId,
            FloorId,
            SlotId,
            start_time,
            end_time
        )
    )