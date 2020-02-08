package com.ensat.property;

import org.springframework.boot.context.properties.ConfigurationProperties;


//@ConfigurationProperties Annotation에 prefix="file"로 선언된 부분은 
// application.properties에 선언한 file.upload-dir=/User/genie/Documents/UPLOAD_FILES file 필드에 접근한다는 말이다
// upload-dir 값에 자동으로 설정값이 연결된다.
// 필드명은 camelCase로 연결되기때문에 이름작성시 주의
// 위의 설정값들을 작성했다고 바로 적용 아니고, springBoot 메인클래스에 추가해야 적용된다. - 메인클래스로 이동

@ConfigurationProperties(prefix="file")
public class FileUploadProperties {
	private String uploadDir;

	public String getUploadDir() {
		return uploadDir;
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}
	
	
}
