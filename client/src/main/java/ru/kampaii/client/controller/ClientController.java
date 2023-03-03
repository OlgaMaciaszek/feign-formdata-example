package ru.kampaii.client.controller;

import feign.form.FormData;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.kampaii.client.client.ApplicationClient;
import ru.kampaii.example.model.TestDto;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ClientController {

	private final ApplicationClient applicationClient;

	@SneakyThrows
	@PostMapping(path = "/test", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void testPost(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") MultipartFile file
	) {
		log.info("sending {} bytes {}", testDto, file.getName());
		applicationClient.testFileUpload(testDto, file);
	}

	@SneakyThrows
	@PostMapping(path = "/teststream")
	public void testStreamUpload(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") MultipartFile file
	) {
		log.info("formData sending {} bytes {}", testDto, file.getSize());
		applicationClient.testStreamUpload(testDto, new FormData(file.getContentType(), file.getName(), file.getBytes()));
	}

	@PostMapping(path = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	void testFileUpload(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") MultipartFile file
	) {
		log.info("testDto: {}, file size: {}", testDto, file.getSize());
	}

	@PostMapping(path = "/stream", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	void testStreamUpload(
			@RequestPart("dto") TestDto testDto,
			@RequestPart("file") FormData file
	) {
		log.info("testDto: {}, file name: {}", testDto, file.getFileName());
	}

}
