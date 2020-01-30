package com.example.demo.app;

import java.io.InputStream;

public interface ArgumentResolver {
	
	Argument resolve(InputStream is);
}
