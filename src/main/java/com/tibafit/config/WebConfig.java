package com.tibafit.config;

import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Value("${file.upload-dir}")
	private String uploadDir;

	/** CORS 設定 (跨域) */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // 全站跨域
				.allowedOrigins("*") // 允許任意前端域名
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允許的 HTTP 方法
				.allowedHeaders("*") // 允許所有 header
				.allowCredentials(false); // 是否允許傳 cookie
	}

	/** 靜態資源映射 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 規則零：統一的上傳檔案入口
		String uploadRootUri = toDirUri(Paths.get(uploadDir));
		String uploadImgUri = toDirUri(Paths.get(uploadDir, "frontend-template", "assets", "img"));
		String classpathImg = "classpath:/static/frontend-template/assets/img/";

		registry.addResourceHandler("/uploads/**").addResourceLocations(uploadRootUri, uploadImgUri, classpathImg);

		// 規則一：使用者上傳頭像
		registry.addResourceHandler("/avatars/**").addResourceLocations("file:" + uploadDir + "/avatars/");

		// 規則二：專案內的靜態圖片
		registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");

		// 規則三：商品圖片 (外部優先)
		registry.addResourceHandler("/frontend-template/assets/img/**").addResourceLocations(
				"file:" + normalize(uploadDir) + "frontend-template/assets/img/",
				"classpath:/static/frontend-template/assets/img/");
		
		// 規則四：運動/自訂義運動/運動分類圖片 (外部優先)
		registry.addResourceHandler("/sportPics/img/**").addResourceLocations(
				"file:" + normalize(uploadDir) + "/",
				"classpath:/static/frontend-template/assets/img/sportPics/");

		// 規則五：其他前端靜態資源
		registry.addResourceHandler("/frontend-template/**")
				.addResourceLocations("classpath:/static/frontend-template/");
	}

	/** 確保結尾有斜線 */
	private String normalize(String path) {
		if (path == null || path.isEmpty())
			return "";
		return path.endsWith("/") || path.endsWith("\\") ? path : (path + "/");
	}

	private static String toDirUri(java.nio.file.Path dir) {
		String uri = dir.toAbsolutePath().toUri().toString();
		return uri.endsWith("/") ? uri : uri + "/";
	}
}
