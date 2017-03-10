package com.cothing.element.HALApi;

import java.net.URI;
import java.net.URL;

import javax.xml.bind.annotation.XmlAttribute;



public class HALLink {
	private String href;
	private Boolean templated;
	private String type;
	private URL deprecation;
	private String name;
	private URI profile;
	private String title;
	private String hreflang;

	public HALLink() {
		// Used by JAXB
	}

	protected HALLink(Builder builder) {
		this.href = builder.href;
		this.templated = builder.templated;
		this.type = builder.type;
		this.deprecation = builder.deprecation;
		this.name = builder.name;
		this.profile = builder.profile;
		this.title = builder.title;
		this.hreflang = builder.hreflang;
	}


 @XmlAttribute
	public String getHref() {
		return href;
	}
 @XmlAttribute
	public void setHref(String href) {
		this.href = href;
	}
 @XmlAttribute
	public Boolean getTemplated() {
		return templated;
	}
 @XmlAttribute
	public void setTemplated(Boolean templated) {
		this.templated = templated;
	}
 @XmlAttribute
	public String getType() {
		return type;
	}
 @XmlAttribute
	public void setType(String type) {
		this.type = type;
	}
 @XmlAttribute
	public URL getDeprecation() {
		return deprecation;
	}
 @XmlAttribute
	public void setDeprecation(URL deprecation) {
		this.deprecation = deprecation;
	}
 @XmlAttribute
	public String getName() {
		return name;
	}
 @XmlAttribute
	public void setName(String name) {
		this.name = name;
	}
 @XmlAttribute
	public URI getProfile() {
		return profile;
	}
 @XmlAttribute
	public void setProfile(URI profile) {
		this.profile = profile;
	}
 @XmlAttribute
	public String getTitle() {
		return title;
	}
 @XmlAttribute
	public void setTitle(String title) {
		this.title = title;
	}
 @XmlAttribute
	public String getHreflang() {
		return hreflang;
	}
 @XmlAttribute
	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}



	/**
	 * Builder to help build {@link HALLink} instances.
	 */
	public static class Builder {
		private final String href;
		private Boolean templated;
		private String type;
		private URL deprecation;
		private String name;
		private URI profile;
		private String title;
		private String hreflang;

		/**
		 * Should be used when constructing a templated link.
		 * 
		 * @param href
		 *            Template URI to construct link from.
		 */
		public Builder(String href) {
			this.href = href;
			templated = true;
		}

		/**
		 * Should be used when constructing a link which is not templated.
		 * 
		 * @param href
		 *            URI to construct link from.
		 */
		public Builder(URI href) {
			this.href = href.toString();
		}

		public Builder templated(boolean templated) {
			this.templated = templated;
			return this;
		}

		public Builder type(String type) {
			this.type = type;
			return this;
		}

		public Builder deprecation(URL deprecation) {
			this.deprecation = deprecation;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder profile(URI profile) {
			this.profile = profile;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder hreflang(String hreflang) {
			this.hreflang = hreflang;
			return this;
		}

		public HALLink build() {
			return new HALLink(this);
		}
	}
}