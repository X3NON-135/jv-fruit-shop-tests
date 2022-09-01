package core.basesyntax.operations.impl;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.operations.TransactionHandle;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SupplyTransactionHandleImplTest {
    private static StorageDao storage;
    private static TransactionHandle transactionHandle;

    @BeforeClass
    public static void beforeClass() {
        storage = new StorageDaoImpl();
        transactionHandle = new SupplyTransactionHandleImpl(storage);
    }

    @Test
    public void supplyTransaction_equalsExecuteOperation() {
        Storage.storage.put("banana", 30);
        FruitTransaction actual =
                new FruitTransaction(FruitTransaction.Operation.SUPPLY, "banana", 10);
        transactionHandle.executeTransaction(actual);
        Integer excepted = 40;
        Assert.assertEquals(excepted, Storage.storage.get("banana"));
    }
}