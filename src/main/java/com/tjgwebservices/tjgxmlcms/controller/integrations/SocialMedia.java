package com.tjgwebservices.tjgxmlcms.controller.integrations;


import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class SocialMedia {

	protected RestTemplate restTemplate;
	private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com/v2.12";
        
	public SocialMedia(String accessToken) {
		this.restTemplate = new RestTemplate();
		if (accessToken != null) {
			this.restTemplate.getInterceptors().add(getBearerTokenInterceptor(accessToken));
		} else {
			this.restTemplate.getInterceptors().add(getNoTokenInterceptor());
		}
	}
	
	private ClientHttpRequestInterceptor getBearerTokenInterceptor(String accessToken) {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
				request.getHeaders().add("Authorization", "Bearer " + accessToken);
				return execution.execute(request, bytes);
			}
		};
	}
	
	private ClientHttpRequestInterceptor getNoTokenInterceptor() {
		return new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution) throws IOException {
				throw new IllegalStateException("Can't access the Facebook API without an access token");
			}
		};
	}  
        
	public Profile getProfile() {
		return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me", Profile.class);
	}
	
	public List<Post> getFeed() {
		return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me/feed", Feed.class).getData();
	}        
}
