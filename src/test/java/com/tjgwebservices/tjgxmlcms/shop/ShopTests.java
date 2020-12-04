package com.tjgwebservices.tjgxmlcms.shop;

import com.tjgwebservices.tjgxmlcms.SpringWebConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.annotation.DirtiesContext;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@ExtendWith({SpringExtension.class})
@Import(SpringWebConfig.class)
public class ShopTests {

	@Autowired
	private TestRestTemplate restTemplate;

        @BeforeEach
        public void setUp() throws Exception {
        }        


        @Test
	void testAddCartStatusPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/addCartStatus");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}


        
        @Test
	void testAddShopOrderStatusPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/addShopOrderStatus");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
	void testAddShopPaymentTypePage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/addShopPaymentType");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
	void testAddShopProductPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/addShopProduct");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
        @Disabled
	void testShopMainPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/shop");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}

        @Test
        @Disabled
	void testShopProductPage() {
		HttpHeaders headers = restTemplate.headForHeaders("/shop/productList");
                Assertions.assertNotEquals(headers.getContentLength(),-1);
	}
        
}
