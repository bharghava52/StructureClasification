package com.cm.cbds.StructureClasification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StructureClasificationApplication {

	public static final String url = "http://localhost:9099";

	public static void main(String[] args) {
		SpringApplication.run(StructureClasificationApplication.class, args);
		startUI();
	}

	private static void startUI() {
		Runtime rt = Runtime.getRuntime();
		try {
			if (System.getProperty("os.name").toLowerCase().contains("windows")) {
				rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
			}
			if (System.getProperty("os.name").toLowerCase().contains("linux")) {
				rt.exec("x-www-browser " + url);
			}
			if (System.getProperty("os.name").toLowerCase().contains("mac")
					|| System.getProperty("os.name").toLowerCase().contains("debian")) {
				rt.exec("open " + url);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
