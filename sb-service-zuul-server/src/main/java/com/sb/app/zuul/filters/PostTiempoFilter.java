package com.sb.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;;

@Component
public class PostTiempoFilter extends ZuulFilter{

	private static Logger log = org.slf4j.LoggerFactory.getLogger(PostTiempoFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true; // Indica si se debe ejecutar el filter 
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info("Entrando a Post");
		
		Long tiempoIni = (Long) request.getAttribute("tiempoIni");
		Long tiempoFin = System.currentTimeMillis();
		Long tiempoTotal = tiempoFin - tiempoIni;
		
		log.info(String.format("Tiempo Transcurrido en segundos %s seg.", tiempoTotal.doubleValue()/1000.00));
		log.info(String.format("Tiempo Transcurrido en milisegundos %s milseg.", tiempoTotal.doubleValue()));
		
		request.setAttribute("tiempoFin", tiempoFin);
		
		return null;
	}

	@Override
	public String filterType() {
		return "post";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
