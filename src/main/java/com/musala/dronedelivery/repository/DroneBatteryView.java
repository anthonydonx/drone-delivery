/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery.repository;

/**
 * it has many properties, yet not all of them are helpful. we need battery percentage
 * select only specific column instead of fetching all the columns from the table
 */
public interface DroneBatteryView {
    Integer getBatteryCapacity();
}
