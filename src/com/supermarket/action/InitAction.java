package com.supermarket.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.supermarket.service.impl.NewsServiceImpl;
import com.supermarket.service.impl.NoticeServiceImpl;
import com.supermarket.service.impl.ProductServiceImpl;
import com.supermarket.service.impl.ProductSmallTypeServiceImpl;
import com.supermarket.service.impl.ProductTypeServiceImpl;


/**
 * Application Lifecycle Listener implementation class InitAction
 *
 */
@WebListener
public class InitAction implements ServletContextListener{
	
	
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }
    
    
    public void contextInitialized(ServletContextEvent servletContextEvent)  { 
    	//获取 Spring 配置文件的名称
    	ServletContext application=servletContextEvent.getServletContext();
    	ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(application);
    	 //从 application 域对象中得到 IOC 容器的实例、从 IOC 容器中得到bean
    	ProductServiceImpl productServiceImpl = (ProductServiceImpl) ctx.getBean("productService");
    	List products=productServiceImpl.getAll();
    	//在应用过程中显示product属性
    	application.setAttribute("products",products);
    	
    	NewsServiceImpl newImpl=(NewsServiceImpl) ctx.getBean("newsService");
    	List news=newImpl.getAll();
    	application.setAttribute("news",news);
    	
    	NoticeServiceImpl noImpl=(NoticeServiceImpl) ctx.getBean("noticeService");
    	List notices=noImpl.getAll();
    	application.setAttribute("notices", notices);
    	
    	ProductTypeServiceImpl productTypeServiceImpl=(ProductTypeServiceImpl) ctx.getBean("productTypeService");
    	List proTypes=productTypeServiceImpl.getAll();
    	application.setAttribute("proTypes",proTypes);
    	
    }

}
