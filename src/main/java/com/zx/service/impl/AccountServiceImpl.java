package com.zx.service.impl;

import com.zx.domain.Account;
import com.zx.mapper.AccountMapper;
import com.zx.service.AccountService;

public class AccountServiceImpl implements AccountService {
    //注入mapper对象
    private AccountMapper accountMapper;

    @Override
    public Account checkOut(String aid, String apwd) {
        return accountMapper.selectOne(aid,apwd, 0);
    }

    @Override
    public Account checkMoney(String aid, String apwd, int money) {
        return accountMapper.selectOne(aid, apwd, money);
    }

    // 查询转入的账号是否存在
    @Override
    public Account checkIn(String aid) {
        return accountMapper.selectOne(aid,null,0);
    }

    @Override
    public int transfer(String out, String in, int money) {
        int i = accountMapper.updateByPrimaryKeySelective(in, money);
        int j = accountMapper.updateByPrimaryKeySelective(out, -1*money);
        if(i>0 && j>0) return 1;
        else return 0;
    }

    public AccountMapper getAccountMapper() {
        return accountMapper;
    }

    public void setAccountMapper(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
}
