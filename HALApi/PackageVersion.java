package com.cothing.element.HALApi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;

public class PackageVersion {
	
	public static final Version VERSION;
	private static final String MAVEN_PROPERTIES = "/META-INF/pom.properties";
	static {
		Version version = Version.unknownVersion();
		InputStream is = PackageVersion.class
				.getResourceAsStream(MAVEN_PROPERTIES);
		if (is != null) {
			try {
				Properties p = new Properties();
				p.load(is);
				version = VersionUtil.parseVersion(p.getProperty("version"),
						p.getProperty("groupId"), p.getProperty("artifactId"));
			} catch (IOException e) {
				// Silently ignore
			} finally {
				try {
					is.close();
				} catch (IOException ex) {
					// Ignore
				}
			}
		}
		VERSION = version;
	}


	public Version version() {
		return VERSION;
	}

}
