package com.karan.todo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

	private static final String ROOT_API_PATH = "/api/*";
	private static final String DISPATCHER_SERVLET = "DispatcherServlet";

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appContext = createApplicationContext(servletContext);
		setupDispatcherServlet(servletContext, appContext);
	}

	private void setupDispatcherServlet(ServletContext servletContext,
			AnnotationConfigWebApplicationContext appContext) {
		Dynamic dispatcherServlet = servletContext.addServlet(DISPATCHER_SERVLET, new DispatcherServlet(appContext));
		dispatcherServlet.setLoadOnStartup(1);
		dispatcherServlet.addMapping(ROOT_API_PATH);
	}

	private AnnotationConfigWebApplicationContext createApplicationContext(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
		appContext.setServletContext(servletContext);
		appContext.register(AppConfig.class);
		return appContext;
	}

}
