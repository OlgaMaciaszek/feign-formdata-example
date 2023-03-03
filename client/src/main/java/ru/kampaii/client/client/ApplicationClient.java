package ru.kampaii.client.client;

import feign.form.FormData;
import ru.kampaii.example.model.TestDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.AbstractFormWriter;
import org.springframework.cloud.openfeign.support.JsonFormWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "application-client", url = "localhost:8080"
		, configuration = ApplicationClient.Configuration.class
)
public interface ApplicationClient {

	@PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	void testFileUpload(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") MultipartFile file
	);


	@PostMapping(path = "/stream", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	void testStreamUpload(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") FormData file
	);

	class Configuration {

		@Bean
		public AbstractFormWriter jsonFormWriter() {
			return new JsonFormWriter();
		}
	}
}
