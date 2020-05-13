package com.sb.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;;

@Component
public class PreTiempoFilter extends ZuulFilter{

	private static Logger log = org.slf4j.LoggerFactory.getLogger(PreTiempoFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true; // Indica si se debe ejecutar el filter 
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info(String.format("%s request entrando a %s", request.getMethod(), request.getRequestURI().toString()));
		
		Long tiempoIni = System.currentTimeMillis();
		request.setAttribute("tiempoIni", tiempoIni);
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
