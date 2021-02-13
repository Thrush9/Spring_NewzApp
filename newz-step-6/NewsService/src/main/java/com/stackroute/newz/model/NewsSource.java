package com.stackroute.newz.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * Please note that this class is annotated with @Document annotation
 * @Document identifies a domain object to be persisted to MongoDB.
 *  
 */

@Document
public class NewsSource {

	/*
	 * This class should have five fields (newssourceId,newssourceName,
	 * newssourceDesc,newssourceCreatedBy,newssourceCreationDate). Out of these five
	 * fields, the field newssourceId should be annotated with @Id (This annotation
	 * explicitly specifies the document identifier). This class should also contain
	 * the getters and setters for the fields, along with the no-arg , parameterized
	 * constructor and toString method.The value of newssourceCreationDate should
	 * not be accepted from the user but should be always initialized with the
	 * system date.
	 */

	@Id
	private int newssourceId;

	private String newssourceName;

	private String newssourceDesc;

	private String newssourceCreatedBy;

	private LocalDateTime newssourceCreationDate;

	public NewsSource() {

	}

	public NewsSource(int sourceId, String sourceName, String sourceDesc, String sourceCreatedBy) {
		this.newssourceId = sourceId;
		this.newssourceName = sourceName;
		this.newssourceDesc = sourceDesc;
		this.newssourceCreatedBy = sourceCreatedBy;
		this.newssourceCreationDate = LocalDateTime.now();
	}

	public int getNewsSourceId() {
		return newssourceId;
	}

	public void setNewsSourceId(int newsSourceId) {
		this.newssourceId = newsSourceId;
	}

	public String getNewsSourceName() {
		return newssourceName;
	}

	public void setNewsSourceName(String newsSourceName) {
		this.newssourceName = newsSourceName;
	}

	public String getNewsSourceDesc() {
		return newssourceDesc;
	}

	public void setNewsSourceDesc(String newsSourceDesc) {
		this.newssourceDesc = newsSourceDesc;
	}

	public String getNewsSourceCreatedBy() {
		return newssourceCreatedBy;
	}

	public void setNewsSourceCreatedBy(String newsSourceCreatedBy) {
		this.newssourceCreatedBy = newsSourceCreatedBy;
	}

	public LocalDateTime getNewsSourceCreationDate() {
		return newssourceCreationDate;
	}

	public void setNewsSourceCreationDate() {
		this.newssourceCreationDate = LocalDateTime.now();
	}

	@Override
	public String toString() {
		return "NewsSource [newssourceId=" + newssourceId + ", newssourceName=" + newssourceName + ", newssourceDesc="
				+ newssourceDesc + ", newssourceCreatedBy=" + newssourceCreatedBy + ", newssourceCreationDate="
				+ newssourceCreationDate + "]";
	}

}
