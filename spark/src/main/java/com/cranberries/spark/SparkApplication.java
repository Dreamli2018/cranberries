package com.cranberries.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;

/**
 * @author ：Dream Li
 * @version ：
 * @program ：cranberries
 * @date ：Created in 2021/11/19 14:32
 * @description ：
 */

//@SpringBootApplication
//@EnableDiscoveryClient
public class SparkApplication {

    private static final Pattern SPACE = Pattern.compile(" ");

    public static void main(String[] args) {
        // 将应用程序和elastic-apm连接在一起：Attaches the Elastic Apm agent to the current JVM.
       // ElasticApmAttacher.attach();

        SparkConf sc = new SparkConf().setAppName("spark-server").setMaster("local");
        JavaSparkContext sparkContext = new JavaSparkContext(sc);
        JavaRDD<String> lines = sparkContext.textFile("/usr/local/dev/data").cache();

        lines.map(new Function<String, Object>() {
            @Override
            public Object call(String s) throws Exception {
                return s;
            }
        });

        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public Iterator<String> call(String s) throws Exception {
                return Arrays.asList(SPACE.split(s)).iterator();
            }
        });

        JavaPairRDD<String, Integer> wordsOnes = words.mapToPair(new PairFunction<String, String, Integer>() {
            @Override
            public Tuple2<String, Integer> call(String s) throws Exception {
                return new Tuple2<String, Integer>(s, 1);
            }
        });

        JavaPairRDD<String, Integer> wordsCounts = wordsOnes.reduceByKey(new Function2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        wordsCounts.saveAsTextFile("/usr/local/dev/data1");
       // SpringApplication.run(SparkApplication.class, args);
    }
}
