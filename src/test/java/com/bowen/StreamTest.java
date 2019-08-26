package com.bowen;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * java8 流API
 * @version v1.0.0
 * @since 2019年08月24日
 */
public class StreamTest {

    public static <T> void show(String title,Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title + ":");
        for(int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.println(",");
            if (i < SIZE) System.out.println(firstElements.get(i));
            else System.out.println("...");
        }
        System.out.println();
    }

    public static <T> void showMore(String title,Stream<T> stream) {
        List<T> firstElements = stream
                .collect(Collectors.toList());
        System.out.println(title + ":");
        for(int i = 0; i < firstElements.size(); i++) {
            System.out.println(firstElements.get(i));
        }
        System.out.println();
    }

    @Test
    public void testCountWord() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        List<String> words = Arrays.asList(content.split("\\PL+"));
        long count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);
    }
    /**
     * 创建流
     * @throws IOException
     */
    @Test
    public void testStreamOf() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(content.split("\\PL+"));
        words.forEach(w->{
            System.out.println(w);
        });
        System.out.println("-------------------------");
        Stream<String> jim = Stream.of("Jim", "Tom", "Dome");
        jim.forEach(name->{
            System.out.println(name);
        });
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("alice.txt");
        String contents = new String(Files.readAllBytes(path),StandardCharsets.UTF_8);
        Stream<Object> silence = Stream.empty();
        show("silence",silence);

        Stream<String> echos = Stream.generate(() -> "ECHO");
        show("echos",echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms",randoms);

        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integer",integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay",wordsAnotherWay);

        Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8);
        show("lines",lines);
        //map
        List<String> words = Arrays.asList(new String[]{"aaaA", "bbb", "ccc"});
        Stream<String> lowerCaseWords = words.stream().map(String::toLowerCase);
        show("lowercasewords",lowerCaseWords);
        //map
        Stream<String> firstletters= words.stream().map(s -> s.substring(0, 1));
        show("firstletters",firstletters);
    }
    /**
     * 创建随机的流，元素数量限制100
     */
    @Test
    public void test2() {
        Stream<Double> randoms = Stream.generate(Math::random).limit(100);
        showMore("randoms",randoms);
    }
    /**
     * 跳过元素 skip
     */
    public void test3() {

    }
    /**
     * 去重复
     */
    @Test
    public void test4() {
        Stream<String> distinct = Stream.of("a", "b", "c", "c", "d", "d").distinct();
        showMore("distinct",distinct);
    }
    /**
     * 排序
     */
    @Test
    public void test5() {
        Stream<String> words = Stream.of("a", "bb", "ccc", "cccc", "ddddd", "dddddd");
        Stream<String> longestFirst = words.sorted(Comparator.comparing(String::length).reversed());
        showMore("longestFirst",longestFirst);
    }







}
