package com.syt.cellphone.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.syt.cellphone.pojo.PhoneBase;
import com.syt.cellphone.pojo.PhoneRecommend;
import com.syt.cellphone.pojo.PhoneTrademark;
import com.syt.cellphone.pojo.SearchHistory;
import com.syt.cellphone.pojo.Soc;

import com.syt.cellphone.greendao.PhoneBaseDao;
import com.syt.cellphone.greendao.PhoneRecommendDao;
import com.syt.cellphone.greendao.PhoneTrademarkDao;
import com.syt.cellphone.greendao.SearchHistoryDao;
import com.syt.cellphone.greendao.SocDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig phoneBaseDaoConfig;
    private final DaoConfig phoneRecommendDaoConfig;
    private final DaoConfig phoneTrademarkDaoConfig;
    private final DaoConfig searchHistoryDaoConfig;
    private final DaoConfig socDaoConfig;

    private final PhoneBaseDao phoneBaseDao;
    private final PhoneRecommendDao phoneRecommendDao;
    private final PhoneTrademarkDao phoneTrademarkDao;
    private final SearchHistoryDao searchHistoryDao;
    private final SocDao socDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        phoneBaseDaoConfig = daoConfigMap.get(PhoneBaseDao.class).clone();
        phoneBaseDaoConfig.initIdentityScope(type);

        phoneRecommendDaoConfig = daoConfigMap.get(PhoneRecommendDao.class).clone();
        phoneRecommendDaoConfig.initIdentityScope(type);

        phoneTrademarkDaoConfig = daoConfigMap.get(PhoneTrademarkDao.class).clone();
        phoneTrademarkDaoConfig.initIdentityScope(type);

        searchHistoryDaoConfig = daoConfigMap.get(SearchHistoryDao.class).clone();
        searchHistoryDaoConfig.initIdentityScope(type);

        socDaoConfig = daoConfigMap.get(SocDao.class).clone();
        socDaoConfig.initIdentityScope(type);

        phoneBaseDao = new PhoneBaseDao(phoneBaseDaoConfig, this);
        phoneRecommendDao = new PhoneRecommendDao(phoneRecommendDaoConfig, this);
        phoneTrademarkDao = new PhoneTrademarkDao(phoneTrademarkDaoConfig, this);
        searchHistoryDao = new SearchHistoryDao(searchHistoryDaoConfig, this);
        socDao = new SocDao(socDaoConfig, this);

        registerDao(PhoneBase.class, phoneBaseDao);
        registerDao(PhoneRecommend.class, phoneRecommendDao);
        registerDao(PhoneTrademark.class, phoneTrademarkDao);
        registerDao(SearchHistory.class, searchHistoryDao);
        registerDao(Soc.class, socDao);
    }
    
    public void clear() {
        phoneBaseDaoConfig.clearIdentityScope();
        phoneRecommendDaoConfig.clearIdentityScope();
        phoneTrademarkDaoConfig.clearIdentityScope();
        searchHistoryDaoConfig.clearIdentityScope();
        socDaoConfig.clearIdentityScope();
    }

    public PhoneBaseDao getPhoneBaseDao() {
        return phoneBaseDao;
    }

    public PhoneRecommendDao getPhoneRecommendDao() {
        return phoneRecommendDao;
    }

    public PhoneTrademarkDao getPhoneTrademarkDao() {
        return phoneTrademarkDao;
    }

    public SearchHistoryDao getSearchHistoryDao() {
        return searchHistoryDao;
    }

    public SocDao getSocDao() {
        return socDao;
    }

}
