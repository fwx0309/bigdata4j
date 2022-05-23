package org.fwx.solr.tokenizer;

import org.apache.lucene.analysis.util.CharTokenizer;

/**
 * 自定义加号分词器
 */
public class PlusTokenizer extends CharTokenizer {
    @Override
    protected boolean isTokenChar(int i) {
        return '+' != i;
    }
}
