package com.bkopec.getmyip.util; // Assuming you keep it in a 'util' package

import jakarta.servlet.http.HttpServletRequest; // For Spring Boot 3.x+ / Jakarta EE
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

public class IpAddressUtil {

    // Prioritized list of IP headers
    private static final String[] IP_HEADER_NAMES = {
            "X-Forwarded-For",         // Standard for proxies, often contains a comma-separated list
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA"                 // Less reliable for actual client IP, usually proxy
            // Note: REMOTE_ADDR is handled by request.getRemoteAddr() fallback
    };

    /**
     * Retrieves the client's IP address from an HttpServletRequest.
     * It checks various common headers set by proxies and load balancers,
     * falling back to HttpServletRequest.getRemoteAddr() if no suitable header is found.
     *
     * @param request The HttpServletRequest object.
     * @return The client's IP address as a String, or "0.0.0.0" if request is null.
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        if (request == null) {
            return "0.0.0.0";
        }

        for (String headerName : IP_HEADER_NAMES) {
            String ipList = request.getHeader(headerName);
            // Check if header exists, is not empty, and not "unknown"
            if (ipList != null && !ipList.isEmpty() && !"unknown".equalsIgnoreCase(ipList)) {
                // For X-Forwarded-For, the first IP is usually the client's original IP
                String ip = ipList.split(",")[0].trim();
                return ip; // Return the first valid IP found
            }
        }

        // Fallback to getRemoteAddr() if no proxy header was found
        return request.getRemoteAddr();
    }

    /**
     * Overloaded method to get client IP from Spring's RequestAttributes.
     *
     * @param requestAttributes Spring's RequestAttributes.
     * @return The client's IP address as a String, or "0.0.0.0" if requestAttributes is null.
     */
    public static String getClientIpAddress(RequestAttributes requestAttributes) {
        if (requestAttributes == null) {
            return "0.0.0.0";
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return getClientIpAddress(request); // Delegate to the main method
    }
}