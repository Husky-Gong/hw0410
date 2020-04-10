package com.zx.servlet;

import com.zx.domain.Account;
import com.zx.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet("/zx/AccountServlet")
public class AccountServlet extends HttpServlet {
    private AccountService accountService;

    @Override
    public void init() throws ServletException {
        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        accountService = applicationContext.getBean("service", AccountService.class);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String service = req.getParameter("service");
        try {
            Method method = this.getClass().getDeclaredMethod(service, HttpServletRequest.class, HttpServletResponse.class);

            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 1. 检查输入账号是否合法
     * @return
     */
    public void checkInAcc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String inAcc = req.getParameter("inAcc");
        Account account = accountService.checkIn(inAcc);
        if(account!=null){
            resp.getWriter().print(true);
        }else{
            resp.getWriter().print(false);
        }
    }

    /**
     * 2. 检验输出账号密码是否合法
     * @return
     */
    public void checkOutAcc(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String outAcc = req.getParameter("outAcc");
        String pwd = req.getParameter("pwd");
        Account account = accountService.checkOut(outAcc, pwd);

        if(account != null){
            resp.getWriter().print(true);
        }else{
            resp.getWriter().print(false);
        }
    }

    /**
     * 3. 检验卡中金额是否充足
     * @return
     */
    public void checkMoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String outAcc = req.getParameter("outAcc");
        String pwd = req.getParameter("pwd");
        int money = Integer.parseInt(req.getParameter("money"));

        Account account = accountService.checkMoney(outAcc,pwd,money);

        if(account != null){
            resp.getWriter().print(true);
        }else{
            resp.getWriter().print(false);
        }
    }

    /**
     * 4. 进行转账
     */
    public void transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String inAcc = req.getParameter("inAcc");
        String outAcc = req.getParameter("outAcc");
        int money = Integer.parseInt(req.getParameter("money"));

        int i = accountService.transfer(outAcc, inAcc, money);

        if(i>0){
            resp.sendRedirect(req.getContextPath()+"/success.jsp");
        }else{
            req.setAttribute("msg", "Failed");
            req.getRequestDispatcher("/account.jsp").forward(req,resp);
        }
    }

    public AccountService getAccountService() {
        return accountService;
    }

    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
}
