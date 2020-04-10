package com.zx.mapper;

import com.zx.domain.Account;

/*
1. 校验转账账户是否正确
2. 校验金额是否充足
3. 校验收款账号的信息是否正确
4. 修改转账账户金额
5. 修改收款账户金额
 */
public interface AccountMapper {
    /*
        primaryKey 即为 account id 如果返回的值为null说明查询的账号不存在
        要查询转账的金额，以及余额都可以通过返回的Account来查询其余额（,getMoney()）
     */
    Account selectOne(String aid, String apwd, int money);

    /**
     *
     * @param record: 使用constructor创建除了money有值的Account对象，money 的值为更新后的值
     * @return
     */
    int updateByPrimaryKeySelective(String uid, int money);
}