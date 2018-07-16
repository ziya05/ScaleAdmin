package com.ziya05.scaleadmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ziya05.scaleadmin.beans.test.InfoItem;
import com.ziya05.scaleadmin.beans.test.OptionSelected;
import com.ziya05.scaleadmin.beans.test.PersonalInfo;
import com.ziya05.scaleadmin.beans.test.Scale;
import com.ziya05.scaleadmin.beans.test.SelectedData;
import com.ziya05.scaleadmin.beans.test.TesteeData;

public class QuickTest extends HttpServlet {
	
	private String restService = "";
	
	private Gson gson = new Gson();
		
	public void service(ServletRequest request, 
			ServletResponse response) throws ServletException, IOException {
		
		HttpServletRequest req = ((HttpServletRequest)request);
		
		String api = this.getInitParameter("api");

		if (StringUtils.isAllBlank(api)) {
			PrintWriter pw = response.getWriter();
			pw.println("UnSupport!");
			pw.close();
			
		} else {
			restService = String.format("%s://%s:%d/%s/", 
					req.getScheme(),
					req.getServerName(),
					req.getServerPort(),
					api
					);
		}
		
		super.service(request, response);
	}
	
	public void doGet(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{  
		String strScaleId = request.getParameter("scale");
		
		List<Scale> scales = this.getScales();
		request.setAttribute("scales", scales);	
		
		int scaleId = 0;
		Scale scale = null;
		
		if (StringUtils.isAllBlank(strScaleId)
				&& scales.size() > 0) {
			scaleId = scales.get(0).getId();
			
			System.out.println("scaleId : " + scaleId);
			
		} else if(!StringUtils.isAllBlank(strScaleId)) {
			scaleId = Integer.parseInt(strScaleId);
			

		}
		
		if (scaleId != 0) {
			scale = this.getScale(scaleId);

			request.setAttribute("scale", scale);
			
			PersonalInfo info = this.getPersonalInfo(scaleId);
			request.setAttribute("info", info);
		}
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/quicktest.jsp");
		dispatcher.forward(request, response);
	}
	
	public void doPost(HttpServletRequest request,
            HttpServletResponse response)
    throws ServletException, IOException
	{
		String strScaleId = request.getParameter("scaleId");
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String age = request.getParameter("age");
		
		String[] attrs = request.getParameterValues("attr");
		String[] answers = request.getParameterValues("answer");
		
		System.out.println("name: " + name);
		System.out.println("gender: " + gender);
		System.out.println("age: " + age);
		
		int attrsSize = 0;
		String attrStr = null;
		if (attrs != null) {
			attrsSize = attrs.length;
			attrStr = String.join(",", attrs);
		}
		
		int anwsersSize = 0;
		String anwserStr = null;
		if (answers != null) {
			anwsersSize = answers.length;
			anwserStr = String.join(",", answers);
		}
		
		System.out.println("The count of attrs is : " + attrsSize);
		System.out.println("The count of answers is : " + anwsersSize);
		
		System.out.println("attr is: " + attrStr);
		System.out.println("anwser is: " + anwserStr);
		
		try {
			int scaleId = Integer.parseInt(strScaleId);
			PersonalInfo info = this.getPersonalInfo(scaleId);
	
			List<InfoItem> items = info.getItems();
			
			TesteeData testeeData = new TesteeData();
			PersonalInfo personalInfo = new PersonalInfo();
			personalInfo.setName(name);
			personalInfo.setGender(gender);
			personalInfo.setAge(Integer.parseInt(age));
			
			if (items != null) {
				personalInfo.setItems(items);
				
				for (int i = 0; i < attrsSize; i++) {
					InfoItem item = items.get(i);
					item.setContent(attrs[i]);
				}
			}
			
			testeeData.setInfo(personalInfo);
			
			SelectedData selectedData = new SelectedData();
			List<OptionSelected> optionSelectedLst = new ArrayList<OptionSelected>();
			selectedData.setItems(optionSelectedLst);
			for(int i = 0; i < anwsersSize; i++) {
				OptionSelected os = new OptionSelected();
				os.setQuestionId(i + 1);
				os.setOptionId(answers[i]);
				optionSelectedLst.add(os);
			}
			
			testeeData.setData(selectedData);
			
			String result = this.saveScale(scaleId, testeeData);
			
			request.setAttribute("result", "保存数据成功！" + result);
			
		} catch(Exception ex) {
			request.setAttribute("result", "保存数据失败！" + ex.toString());
		}

		this.doGet(request, response);
	}

	public List<Scale> getScales() throws ClientProtocolException, IOException {

		String json = this.getContent("ScaleService/scales");
		
		Type t = new TypeToken<List<Scale>>() {}.getType();
		List<Scale> scaleLst = gson.fromJson(json, t);

		return scaleLst;
	}
	
	public PersonalInfo getPersonalInfo(int scaleId) throws ClientProtocolException, IOException {
		String json = this.getContent("ScaleService/personalInfo/" + scaleId);
		
		return gson.fromJson(json, PersonalInfo.class);
	}
	
	public Scale getScale(int scaleId) throws ClientProtocolException, IOException {
		String json = this.getContent("ScaleService/scale/" + scaleId);
		
		return gson.fromJson(json, Scale.class);
	}
	
	public String saveScale(int scaleId, TesteeData data) throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		
		HttpPost request = new HttpPost(restService + "ScaleService/scale/result/" + scaleId );
		request.addHeader("Accept", "text/plain");
		request.addHeader("Content", "application/json");
		
		String jsonStr = gson.toJson(data);
		StringEntity se = new StringEntity(jsonStr, Consts.UTF_8);
		se.setContentEncoding("UTF-8");
		se.setContentType("application/json");
		
		request.setEntity(se);
		CloseableHttpResponse response = httpClient.execute(request);
		StatusLine statusLine = response.getStatusLine();
		String res = this.getContent(response);
		
		if (statusLine.getStatusCode() != 200) {
			throw new ClientProtocolException(res);
		}
		
		System.out.println("保存量表返回值： " + res);
		
		return res;
	}
	
	private String getContent(String url) throws ClientProtocolException, IOException {
		CloseableHttpResponse response = this.getResponse(url);
		return this.getContent(response);
	}
	
	private String getContent(HttpResponse response) throws UnsupportedOperationException, IOException {
		HttpEntity httpEntity = response.getEntity();
		
		String jsonString = IOUtils.toString(httpEntity.getContent(), "utf-8");
		return jsonString;
	}
	
	private CloseableHttpResponse getResponse(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();  
		
		HttpGet request = new HttpGet(restService + url);
		request.addHeader("Accept", "application/json");
		request.addHeader("Content", "application/json");
		
		CloseableHttpResponse response = httpClient.execute(request);
		return response;
	}
}
