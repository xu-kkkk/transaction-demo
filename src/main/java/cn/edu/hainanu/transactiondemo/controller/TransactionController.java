package cn.edu.hainanu.transactiondemo.controller;

import cn.edu.hainanu.transactiondemo.transaction.GetLatestBlockTransactionsAsJSON;
import cn.edu.hainanu.transactiondemo.transaction.TransactionProcess;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author: Tiankai Xu
 * @create: 2023-09-06 11:25
 */
@RestController
@RequestMapping("/")
public class TransactionController {
    @Deprecated
    @RequestMapping("/tx")
    public JSONObject transactionProcess(@RequestParam String transactionHash) {
        String nodeURL = "https://eth-mainnet.g.alchemy.com/v2/YYNvlN7f-3kl25Mn6Lw-0VDcx6GdgFaf";
//        transactionHash = "0x71906c318301a45f8fb98dce0146653c37927a42b8c73d234c2cbc1ed0253517";
//        transactionHash = "0x71906c318301a45f8fb98dce0146653c37927a42b8c73d234c2cbc1ed0253517";

        TransactionProcess transactionProcess = new TransactionProcess();
        JSONObject transactionJson = transactionProcess.getTransactionJsonOnEthereum(nodeURL, transactionHash);

        return transactionJson;
    }

    /**
     * 返回最新区块中的所有交易信息
     *
     * @return
     */
    @RequestMapping("/transaction")
    public String latestBlockTx() {
        GetLatestBlockTransactionsAsJSON getLatestBlockTransactionsAsJSON = new GetLatestBlockTransactionsAsJSON();
        try {
            return getLatestBlockTransactionsAsJSON.getTxInfo();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
