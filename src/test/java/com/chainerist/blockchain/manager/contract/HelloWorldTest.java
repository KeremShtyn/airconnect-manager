package com.chainerist.blockchain.manager.contract;
/*
import com.chainerist.blockchain.smart.contracts.HelloWorld;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.web3j.EVMTest;
import org.web3j.protocol.Web3j;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import static org.junit.jupiter.api.Assertions.*;

@EVMTest
@Tag("HelloWorldTest")
public class HelloWorldTest {

    private static HelloWorld contract =null;

    @Test
    @Order(1)
    public void deploy_helloWord_OK(Web3j web3j, TransactionManager transactionManager, ContractGasProvider gasProvider) throws Exception {
        try {
            contract = HelloWorld.deploy(web3j, transactionManager, gasProvider).send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertTrue(contract.getContractAddress().startsWith("0x"));
        assertEquals(contract.getContractBinary(),HelloWorld.BINARY);

    }

    @Test
    @Order(2)
    public void get_helloWord_OK() {
        String result = null;
        try {
            result = contract.sayHelloWorld().send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(result, "Hello World");
    }


    @Test
    @Order(3)
    public void get_helloWord_BAD() {
        String result = null;
        try {
            result = contract.sayHelloWorld().send();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertNotEquals(result, "Hello World!");
    }
}

 */
