/*
 *  (C) 2022 Asanka Anthony. - All Rights Reserved
 *
 *  Unauthorized copying or redistribution of this file in source and binary forms via any medium  is strictly prohibited.
 */

package com.musala.dronedelivery;

import com.musala.dronedelivery.common.StateType;
import com.musala.dronedelivery.entity.Drone;
import com.musala.dronedelivery.repository.DroneRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class DroneDeliveryApplicationTests {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private DroneRepository droneRepository;
	@Test
	void contextLoads() {
	}



	@Test
	public void getDronesByStatusTest() throws Exception {
			mockMvc.perform(get("/api/v1/drones/state/" + StateType.IDLE)).andExpect(status().isOk());
			mockMvc.perform(get("/api/v1/drones/state/" + StateType.DELIVERED)).andExpect(status().isOk());

	}

	@Test
	public void getBatteryLevelByDroneIdTest() throws Exception {
		List<Drone> droneList = droneRepository.findAll();
		String id = null;
		if(!droneList.isEmpty()){
			id=droneList.get(0).getId();
		}
			mockMvc.perform(get("/api/v1/drones/battery/" + id)).andExpect(status().isOk());
	}
}

