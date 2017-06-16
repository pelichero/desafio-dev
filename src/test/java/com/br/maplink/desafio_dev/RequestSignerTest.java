package com.br.maplink.desafio_dev;

import java.net.URL;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.maplink.desafio_dev.autentication.RequestSigner;

@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestSignerTest {

	@Autowired	
	private RequestSigner requestSigner;
	
	private static final String APPLICATION_CODE = "desafiodev";
	

	@Test	
	public void testSignatureGenerator() {
		Throwable t = null;
		
		final String URL_EXEMPLO = "http://api.maplink.com.br/v0/search?q=rio de janeiro&applicationCode="+APPLICATION_CODE;
		final String ADHOC_GENERATED_SIGNATURE = "qWh2hMbXhmE2_2KxVb0lsGu8Uf0=";   
		String testSignature = null;
		
		try{
			// Convert the string to a URL so we can parse it
		    URL url = new URL(URL_EXEMPLO);

			testSignature = requestSigner.generateSignature(url.getPath(), url.getQuery(), null);
			System.out.println(testSignature);
		}catch (Exception e) {
			t = e;
		}
		
		
		Assert.assertEquals(ADHOC_GENERATED_SIGNATURE, testSignature);
		Assert.assertNull(t);
	}

}