package kr.co.itcen.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



public class ContextLoadListener implements ServletContextListener {

	//
	public void contextInitialized(ServletContextEvent servletContextEvent)  {
		String contextConfigContext = servletContextEvent.getServletContext().getInitParameter("contextConfigLocation");
		System.out.println("mysite2 Application Starts with.." + contextConfigContext);
	}
	//<context-param>
	//<param-name>contextConfigLocation</param-name>
	//<param-value>/WEB-INF/applicationContext.xml</param-value>
	//</context-param>
	
	//톰캣이 내려갈때
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	System.out.println("mysite2 Application contextDestroyed.....");
    }

	
}
