package co.com.jetprogramming.interceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class TokenHandlerInterceptor implements HandlerInterceptor {

	private static Logger log = LoggerFactory.getLogger(TokenHandlerInterceptor.class);

	@Override
	public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
			final Object handler, final Exception ex) throws Exception {
		if (ex != null)
			log.error("afterCompletion", ex);
		log.info("[afterCompletion][{}][exception: {}]", request, ex);
	}

	@Override
	public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
			final ModelAndView modelAndView) throws Exception {
		log.info("[postHandle][{}]", request);
	}

	@Override
	public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler)
			throws Exception {
		log.info("[preHandle][{}]" + "[{}]{}{}", request, request.getMethod(), request.getRequestURI(),
				getParameters(request));
		return true;
	}

	private String getParameters(final HttpServletRequest request) {
		final StringBuilder posted = new StringBuilder();
		final Enumeration<?> e = request.getParameterNames();
		if (e != null)
			posted.append("?");
		while (e != null && e.hasMoreElements()) {
			if (posted.length() > 1)
				posted.append("&");
			final String curr = (String) e.nextElement();
			posted.append(curr).append("=");
			if (curr.contains("password") || curr.contains("answer") || curr.contains("pwd")) {
				posted.append("*****");
			} else {
				posted.append(request.getParameter(curr));
			}
		}

		final String ip = request.getHeader("X-FORWARDED-FOR");
		final String ipAddr = (ip == null) ? getRemoteAddr(request) : ip;
		if (ipAddr != null)
			posted.append("&_psip=" + ipAddr);
		return posted.toString();
	}

	private String getRemoteAddr(final HttpServletRequest request) {
		final String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
		if (ipFromHeader != null && ipFromHeader.length() > 0) {
			log.debug("ip from proxy - X-FORWARDED-FOR : {}", ipFromHeader);
			return ipFromHeader;
		}
		return request.getRemoteAddr();
	}

}
