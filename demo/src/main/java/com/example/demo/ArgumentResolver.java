package com.example.demo;

import java.io.InputStream;

public interface ArgumentResolver {
	
	Argument resolve(InputStream is);
}
