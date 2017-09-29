package com.vir.service;

import org.springframework.core.io.Resource;

public interface  ResourceService {
	
	public Resource get() throws Exception;
	public void save();
}
