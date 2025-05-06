package com.utc.rental.rental.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.utc.rental.rental.service.InitService;

@Component
public class DataInit implements CommandLineRunner {

	@Autowired
	InitService initService;
	@Override
	public void run(String... args) throws Exception {
//		initService.addAuthoritiesDefault();
//		initService.addRolesDefault();
//		initService.addUserRootDefault();
//		initService.updateAllPass();
	}
	
}
