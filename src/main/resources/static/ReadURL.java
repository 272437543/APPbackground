package com.drake.APPbackground.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ReadURL {

	List<Comment> getAllComments(String u) throws Exception {
		String json = getURLSource(new URL(u));
		System.out.println(json);
		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray = jsonParser.parse(json).getAsJsonArray();

		Gson gson = new Gson();
		ArrayList<Comment> commentList = new ArrayList<>();
		for (JsonElement e : jsonArray) {
			Comment comment = gson.fromJson(e, Comment.class);
			commentList.add(comment);
			System.out.println("INFO: " + comment);
		}
		return commentList;
	}

	public String getURLSource(URL url) throws Exception {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setConnectTimeout(5 * 1000);
		InputStream in = conn.getInputStream();

		String source = new String(readInputStream(in));
		return source;

	}

	public byte[] readInputStream(InputStream in) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			out.write(buffer, 0, len);
		}
		in.close();
		return out.toByteArray();
	}
}
