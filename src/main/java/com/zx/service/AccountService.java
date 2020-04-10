package com.zx.service;

import com.zx.domain.Account;

/**
 * service调用mapper里面的函数，完成数据库操作
 * service操作有：
 * 1. 校验转账账户是否正确
 * 2. 校验金额是否充足
 * 3. 校验收款账号的信息是否正确
 * 4. 修改转账账户金额
 * 5. 修改收款账户金额
 *
 * 其中 1，2，3都是查询操作
 * 4，5都是更新操作
 */
public interface AccountService {
    // 1. 查询登录的账号是否正确：是否查询得到输入的账号，将结果返回给servlet，然后servlet根据结果来判断应该返回什么结果给前端
    Account checkOut(String aid, String apwd);

    // 2. 查询输出的账号余额是否充足，将用户账号，密码和转出的钱作为查询条件，看数据库中是否有满足条件的账号，再根据输出的结果传输给前端
    Account checkMoney(String aid, String apwd, int money);

    // 3. 查询收款人的信息：输入收款人账号，看是否能够查询得到相应的账号并返回
    Account checkIn(String aid);

    // 4. 更新账号的金额，看返回条件是否为0， 如果为0则说明没有更新，转账失败
    int transfer(String out, String in, int money);


}
