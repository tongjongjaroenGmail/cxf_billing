/**
 * 
 */
package com.metasoft.claim.authentication;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.metasoft.claim.dao.ConClaimTypeDao;
import com.metasoft.claim.dao.ConJobStatusDao;
import com.metasoft.claim.dao.StdInsuranceDao;
import com.metasoft.claim.dao.StdPositionDao;
import com.metasoft.claim.dao.StdReceiveTypeDao;
import com.metasoft.claim.model.SecUser;
import com.metasoft.claim.service.SecUserService;

/**
 * @author 
 * 
 */
@Component
public class CustomSimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
//    protected Log logger = LogFactory.getLog(this.getClass());
 
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    private ConClaimTypeDao conClaimTypeDao;
    
    @Autowired
    private ConJobStatusDao conJobStatusDao;
    
    @Autowired
    private StdInsuranceDao stdInsuranceDao;
    
    @Autowired
    private StdPositionDao stdPositionDao;
    
    @Autowired
    private StdReceiveTypeDao stdReceiveTypeDao;
    
    @Autowired
	 private SecUserService secUserService;
 
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        handle(request, response, authentication);
        clearAuthenticationAttributes(request);
    }
 
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
//            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }
        
        // TODO if has standard screen. You must search every time for redirect  to that page
        // create comboBox
        HttpSession session = request.getSession();
        session.setAttribute("claimTypes", conClaimTypeDao.findAll());
        session.setAttribute("jobStatuses", conJobStatusDao.findAll());
        session.setAttribute("insurances", stdInsuranceDao.findAll());
        session.setAttribute("positions", stdPositionDao.findAll());
        session.setAttribute("receiveTypes", stdReceiveTypeDao.findAll());
        
    	SecUser secUser = secUserService.findByUserName(authentication.getName());
    	session.setAttribute("loginUser", secUser);
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
 
    /** Builds the target URL according to the logic defined in the main class Javadoc. */
    protected String determineTargetUrl(Authentication authentication) {
//        boolean isUser = false;
//        boolean isAdmin = false;
//        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
//                isUser = true;
//                break;
//            } else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
//                isAdmin = true;
//                break;
//            }
//        }
// 
//        if (isUser) {
//            return "/homepage.html";
//        } else if (isAdmin) {
//            return "/console.html";
//        } else {
//            throw new IllegalStateException();
//        }
    	return "/mainPage";
    }
 
    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
 
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
}

