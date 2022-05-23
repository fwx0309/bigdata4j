package org.fwx.solr.tokenizer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.util.TokenizerFactory;
import org.apache.lucene.util.AttributeFactory;

import java.util.Map;

/**
 * 自定义加号分词器工厂类
 */
public class PlusTokenizerFactory extends TokenizerFactory {

    // 显示调用父类的有参构造方法

    /**
     * 在schema配置文件中配置分词器的时候，指定参数args配置分词器时候的指定的参数
     * 注意：方法需要用 public 修饰
     *  <analyzer>
     *      <tokenizer class="org.fwx.tokenizer.PlusTokenizerFactory" mode="complex"/>
     *  </analyzer>
     * @param args
     */
    public PlusTokenizerFactory(Map<String, String> args) {
        super(args);
    }

    @Override
    public Tokenizer create(AttributeFactory attributeFactory) {
        return new PlusTokenizer();
    }
}
