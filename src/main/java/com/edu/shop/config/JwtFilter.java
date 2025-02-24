package com.edu.shop.config;

import com.edu.shop.serviceImpls.UserSecurityServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserSecurityServiceImpl userSecurityService;// la idea es obtener su imple
    public JwtFilter(JwtUtil jwtUtil, UserSecurityServiceImpl userSecurityService) {
        this.jwtUtil = jwtUtil;

        this.userSecurityService = userSecurityService;
    }

    /**
     * Same contract as for {@code doFilter}, but guaranteed to be
     * just invoked once per request within a single request thread.
     * See {@link #shouldNotFilterAsyncDispatch()} for details.
     * <p>Provides HttpServletRequest and HttpServletResponse arguments instead of the
     * default ServletRequest and ServletResponse ones.
     *
     * @param request
     * @param response
     * @param filterChain
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //validacion de header
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader==null || authHeader.isEmpty() || !authHeader.startsWith("Bearer"))
        {
            filterChain.doFilter(request,response);
            return;
        }
        //validar jwt
        String jwt = authHeader.split(" ")[1].trim();
        if(!this.jwtUtil.isValid(jwt)){
            filterChain.doFilter(request,response);
            return;
        }
        //cargar el usuario al userdeatilservice
        String username = this.jwtUtil.getUsername(jwt);
        User user = (User) this.userSecurityService.loadUserByUsername(username);
        //cargar el usuario al contexto de seguridad
        UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(
                user.getUsername(),user.getPassword(),user.getAuthorities()
        );
        //se cargan la autenticacion en el contexto de seguridad
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(authenticationToken);
        filterChain.doFilter(request,response);

    }
}
