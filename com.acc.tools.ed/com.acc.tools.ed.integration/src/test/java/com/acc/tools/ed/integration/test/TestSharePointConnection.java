package com.acc.tools.ed.integration.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.auth.NTLMScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import com.acc.tools.ed.database.http.JCIFSEngine;

public class TestSharePointConnection {
	
	public static void main(String ... arg){
		try {

	        HttpParams params = new BasicHttpParams();
	        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	        DefaultHttpClient httpclient = new DefaultHttpClient(params);

	        //Register JCIF NTLMv2 to manage ntlm auth.
	        httpclient.getAuthSchemes().register("ntlm", new AuthSchemeFactory() {
	            public AuthScheme newInstance(HttpParams hp) {
	                return new NTLMScheme(new JCIFSEngine());
	            }
	        });

	        //Provide login/password
	        httpclient.getCredentialsProvider().setCredentials(
	                AuthScope.ANY,
	                new NTCredentials("murali.k.gavarasana", "Amma123!@#", "", "dir"));
	        //Create HTTP PUT Request       
	        HttpGet request = new HttpGet("https://ts.accenture.com/sites/HCSC%20Engagement%20WEbsite/CCSP%202014%20Offshore/_layouts/WopiFrame.aspx?sourcedoc=/sites/HCSC%20Engagement%20WEbsite/CCSP%202014%20Offshore/Shared%20Documents/BusinessAnalyst_KT_Docs/SampleResources.xlsx&action=default&Source=https%3A%2F%2Fts%2Eaccenture%2Ecom%2Fsites%2FHCSC%2520Engagement%2520WEbsite%2FCCSP%25202014%2520Offshore%2FShared%2520Documents%2FForms%2FAllItems%2Easpx%3FRootFolder%3D%252Fsites%252FHCSC%2520Engagement%2520WEbsite%252FCCSP%25202014%2520Offshore%252FShared%2520Documents%252FBusinessAnalyst%255FKT%255FDocs%26FolderCTID%3D0x012000B209F51D1C6B3B4C9D300B3B7B39BC72%26View%3D%7B8F55D57C%2D76EA%2D4D10%2D9F34%2D3BDFF68EFD0D%7D&DefaultItemOpen=1");
	      
	       // request.setEntity(new FileEntity(new File("C:\\EngagementDashboard\\SampleResources.xlsx")));  
	     //   request.get

	        HttpResponse response=httpclient.execute(request);

	    } catch (Exception ex) {
	     System.out.println(ex);
	    }
		
	}

}
