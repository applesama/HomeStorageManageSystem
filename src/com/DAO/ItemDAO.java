package com.DAO;

import com.Model.ItemInfo;
import org.apache.commons.dbutils.QueryRunner;

public class ItemDAO extends BasicDAO<ItemInfo> {
    private QueryRunner qr = new QueryRunner();
}
