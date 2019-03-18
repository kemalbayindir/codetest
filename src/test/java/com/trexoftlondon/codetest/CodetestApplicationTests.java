package com.trexoftlondon.codetest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trexoftlondon.codetest.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CodetestApplicationTests {

	@Value("${product.service.by.category}")
	private String productServiceByCategory;


	InputStream swatchSample, fromToSample, priceSample, productSample;

	@Before
	public void loadJsons() {
		swatchSample = this.getClass().getClassLoader().getResourceAsStream("ColorSwatchSample.json");
		fromToSample = this.getClass().getClassLoader().getResourceAsStream("FromToSample.json");
		priceSample = this.getClass().getClassLoader().getResourceAsStream("PriceSample.json");
		productSample = this.getClass().getClassLoader().getResourceAsStream("ProductSample.json");

	}

	@Test
	public void testTargetCategoryUrlCall() {
		RestTemplate restTemplate = new RestTemplate();
		String categoryId = "600001506";
		String fullUrl = String.format(productServiceByCategory, categoryId);
		ServiceResult result = restTemplate.getForObject(fullUrl, ServiceResult.class);

		Assert.assertNotNull(result);
		Assert.assertEquals(result.getProducts().size(), 50);
	}


	// ColorSwatch conversion
	@Test
	public void testColorSwatchConversion() throws IOException {

		Assert.assertNotEquals(null, swatchSample);

		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<ColorSwatch> colorSwatches = objectMapper.readValue(swatchSample, new TypeReference<List<ColorSwatch>>(){});
		Assert.assertNotEquals(null, colorSwatches);
		Assert.assertEquals(7, colorSwatches.size());
		Assert.assertEquals("237169205", colorSwatches.get(3).getSkuId());
		Assert.assertEquals("#0000ff", colorSwatches.get(6).getRgbColor());
	}

	// FromTo conversion
	@Test
	public void testFromToConversion() throws IOException {
		Assert.assertNotEquals(null, fromToSample);
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<FromTo> fromToList = objectMapper.readValue(fromToSample, new TypeReference<List<FromTo>>(){});

		Assert.assertNotEquals(null, fromToList);
		Assert.assertEquals(4, fromToList.size());
		Assert.assertEquals("35.75", fromToList.get(1).getFrom());
		Assert.assertEquals("20", fromToList.get(1).getTo());
		Assert.assertEquals("10.00", fromToList.get(2).getTo());
	}

	// Price conversion
	@Test
	public void testPriceConversion() throws IOException {
		Assert.assertNotEquals(null, priceSample);
		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Price> prices = objectMapper.readValue(priceSample, new TypeReference<List<Price>>(){});

		Assert.assertNotEquals(null, prices);
		Assert.assertEquals(3, prices.size());
		Assert.assertEquals("59.00", prices.get(0).getNow());

		Assert.assertEquals("100.00", prices.get(1).getNow());
		Assert.assertEquals(false, prices.get(1).getIsThereReduceEvent().booleanValue());

		Assert.assertEquals("85.00", prices.get(2).getWas());
		Assert.assertEquals("then 68.00, ", prices.get(2).getFormattedThen());
		Assert.assertEquals(prices.get(2).getFormattedThen1(), prices.get(2).getFormattedThen());
		Assert.assertEquals(true, prices.get(2).getIsThereReduceEvent().booleanValue());
	}

	// Product conversation
	@Test
	public void testProductConversion() throws IOException {
		Assert.assertNotEquals(null, productSample);

		ObjectMapper objectMapper = new ObjectMapper();
		ArrayList<Product> products = objectMapper.readValue(productSample, new TypeReference<List<Product>>(){});

		Assert.assertNotEquals(null, products);
		Assert.assertEquals(3, products.size());

		Assert.assertEquals("Was £59.00, then 68.00, now £68.00", products.get(0).getPriceLabel());
	}

}
