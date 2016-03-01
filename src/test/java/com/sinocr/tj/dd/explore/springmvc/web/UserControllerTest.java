package com.sinocr.tj.dd.explore.springmvc.web;

import java.io.IOException;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.sinocr.tj.dd.explore.springmvc.domain.User;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class UserControllerTest {

	@Test
	public void testhandle41() {
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
		form.add("userName", "tom");
		form.add("password", "123456");
		form.add("age", "45");
		restTemplate.postForLocation("http://localhost:8080/explore-springmvc/user/handle41.html", form);
	}

	@Test
	public void testhandle42() throws IOException {
		RestTemplate restTemplate = new RestTemplate();
		byte[] response = restTemplate.postForObject(
				"http://localhost:8080/explore-springmvc/user/handle42/{itemId}.html", null, byte[].class, "1233");
		Resource outFile = new FileSystemResource("d:/image_copy.jpg");
		FileCopyUtils.copy(response, outFile.getFile());
	}
	@Test
	public void testhandle51() throws IOException {
		RestTemplate restTemplate = buildRestTemplate();
		User user = new User();
		user.setUserName("tom");
		user.setPassword("1234");
		user.setRealName("Tom Cruise");
		HttpHeaders entityHeaders = new HttpHeaders();
		entityHeaders.setContentType(MediaType.valueOf("application/xml;UTF-8"));
		entityHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
		HttpEntity<User> requestEntity = new HttpEntity<User>(user, entityHeaders);
		ResponseEntity<User> responseEntity = restTemplate.exchange(
				"http://localhost:8080/explore-springmvc/user/handle51.html", HttpMethod.POST, requestEntity,
				User.class);
		User responseUser = responseEntity.getBody();
		Assert.assertNotNull(responseUser);
		Assert.assertEquals("1000", responseUser.getUserId());
		Assert.assertEquals("tom", responseUser.getUserName());
		Assert.assertEquals("Tom Curise", responseUser.getRealName());
	}

	private RestTemplate buildRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		XStreamMarshaller xmlMarshaller = new XStreamMarshaller();
		xmlMarshaller.setStreamDriver(new StaxDriver());
		xmlMarshaller.setAnnotatedClasses(new Class[] { User.class });
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
		xmlConverter.setMarshaller(xmlMarshaller);
		xmlConverter.setUnmarshaller(xmlMarshaller);
		restTemplate.getMessageConverters().add(xmlConverter);
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		restTemplate.getMessageConverters().add(jsonConverter);
		return restTemplate;
	}

}
