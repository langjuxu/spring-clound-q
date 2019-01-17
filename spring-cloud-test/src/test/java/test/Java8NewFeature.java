package test;

import com.qian.SpringCloudTestApplication;
import com.qian.dto.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author qian
 * @date 2018/11/2
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringCloudTestApplication.class)
public class Java8NewFeature {

    /**
     * Stream(parallelStream) API:他可以对数组，集合等做一些操作，最终产生一个新的流，原数据是不会发生改变的。
     * Stream：串行流-有序
     * parallelStream：并行流-不保证顺序
     * <p>
     * “集合”讲的是数据，“流”讲的是计算！
     * <p>
     * 注意：
     * 1. Stream自己不会储存元素
     * 2. 不会改变源对象，相反，它会产生一个新的Stream
     * 3. 操作是延迟执行的，这意味着他们会等到需要结果的时候才执行
     * <p>
     * Stream 的中间操作:多个中间操作可以连接起来形成一个流水线，除非触发终止操作，否则中间操作不会执行任何处理！而在终止操作时一次性全部处理，称为惰性求值
     */

    public static List<Employee> employees = Arrays.asList(
            new Employee("张三", 19, 2000),
            new Employee("张四", 33, 3000),
            new Employee("张五", 38, 4000),
            new Employee("张六", 41, 2500),
            new Employee("张六", 41, 1500),
            new Employee("张七", 42, 5500),
            new Employee("张七", 42, 5500),
            new Employee("张七", 42, 5500)
    );


    /**
     * 筛选与切片:
     * filter-接收Lambda，从流中排除某些元素-过滤出指定条件下的元素
     * limit-截断流，使元素不超过给定数量
     * slip(n)-跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流，与limit互补
     * distinct-筛选，通过流所生成元素的hashCode(),equals()去除重复元素
     */

    @Test
    public void streamTest01() {
        // filter:过滤出指定条件下的元素
        employees.stream().filter(employee -> employee.getAge() == 19).forEach(System.out::println);
        employees.parallelStream().filter(employee -> employee.getSalary() == 4000).forEach(System.out::println);
        /**
         * Employee(name=张三, age=19, salary=2000)
         *
         * Employee(name=张五, age=38, salary=4000)
         */
    }

    @Test
    public void streamTest02() {
        // limit-截断流，使元素不超过给定数量;如果找到指定数量的数据后，循环迭代就自己结束了，不会一直遍历
        employees.parallelStream().filter(employee -> employee.getAge() < 42).limit(2).forEach(System.out::println);
        employees.stream().filter(employee -> employee.getSalary() > 3000).limit(2).forEach(System.out::println);
        /**
         * Employee(name=张四, age=33, salary=3000)
         * Employee(name=张三, age=19, salary=2000)
         *
         * Employee(name=张五, age=38, salary=4000)
         * Employee(name=张七, age=42, salary=5500)
         */
    }

    @Test
    public void streamTest03() {
        // skip:意思就是跳过前两个，输出满足条件的其他对象数据
        employees.stream().filter(employee -> employee.getAge() < 42).skip(3).forEach(System.out::println);
        employees.parallelStream().filter(employee -> employee.getSalary() > 3000).skip(3).forEach(System.out::println);
        /**
         * Employee(name=张六, age=41, salary=2500)
         *
         * Employee(name=张七, age=42, salary=5500)
         */
    }

    @Test
    public void streamTest04() {
        // distinct:去重操作！注意！！！！！必须重写去重目标对象中的hashCode与equals方法！
        employees.stream().distinct().forEach(System.out::println);
        employees.parallelStream().distinct().forEach(System.out::println);
        /**
         * Employee(name=张五, age=38, salary=4000)
         Employee(name=张七, age=42, salary=5500)
         Employee(name=张六, age=41, salary=2500)
         Employee(name=张四, age=33, salary=3000)
         Employee(name=张三, age=19, salary=2000)
         */
    }

    /**
     * 映射
     * map：接收Lambda，将元素转换成其他形式或提取信息。接受一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
     * flatMap：接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
     */

    @Test
    public void streamTest05() {
        // map:映射
        employees.stream().map(Employee::getName).distinct().forEach(System.out::println);
        employees.parallelStream().map(Employee::getName).distinct().forEach(System.out::println);
        /**
         * 张三 张四 张五 张六 张七
         */

        List<String> list = Arrays.asList("i", "love", "you");
        list.parallelStream().map(String::toUpperCase).forEach(System.out::println);
    }

    /**
     * 排序
     * sorted()-自然排序（Comparable）
     * sorted(Comparator com)-定制排序
     */
    @Test
    public void streamTest06() {
        // sorted:自然排序
        List<Integer> list = Arrays.asList(6, 9, 23, 1, 38, 22, 11, 33, 7, 23);
        list.stream().sorted().forEach(System.out::println);

        List<Integer> collect = list.parallelStream().sorted().collect(Collectors.toList());
        System.err.println(collect);// [1, 6, 7, 9, 11, 22, 23, 23, 33, 38]
        list.parallelStream().sorted().forEach(System.out::println);// 打印的顺序是无序的
    }

    @Test
    public void streamTest07() {
        // sorted(Comparator com)：定制排序
        // 按名字排序，名字相同按工资排序
        employees.stream().sorted((x, y) -> {
            if (x.getName().equals(y.getName())) {
                return Integer.compare(x.getSalary(), y.getSalary());
            } else {
                return x.getName().compareTo(y.getName());
            }
        }).forEach(System.out::println);
        /**
         * Employee(name=张七, age=42, salary=5500)
         Employee(name=张七, age=42, salary=5500)
         Employee(name=张七, age=42, salary=5500)
         Employee(name=张三, age=19, salary=2000)
         Employee(name=张五, age=38, salary=4000)
         Employee(name=张六, age=41, salary=1500)
         Employee(name=张六, age=41, salary=2500)
         Employee(name=张四, age=33, salary=3000)
         */
    }

    /**
     * 查找与匹配
     * allMatch-检查是否匹配所有元素
     * anyMatch-检查是否匹配至少一个元素
     * noneMatch-检查是否没有匹配所有元素
     * findFirst-返回第一个元素
     * findAny-返回当前流中的任意元素
     * count-返回流中总个数
     * max-返回流中最大值
     * min-返回流中最小值
     */

    @Test
    public void streamTest08() {
        // allMatch-检查是否匹配所有元素
        boolean allMatch = employees.stream().allMatch(employee -> employee.getSalary() == 300);
        System.err.println(allMatch);// false
        // anyMatch-检查是否匹配至少一个元素
        boolean anyMatch = employees.parallelStream().anyMatch(employee -> employee.getAge() == 33);// 存在
        System.err.println(anyMatch);// true
        // noneMatch-检查是否没有匹配所有元素
        boolean noneMatch = employees.parallelStream().noneMatch(employee -> employee.getSalary() == 1500);// 存在
        System.err.println(noneMatch);// false
    }

    @Test
    public void streamTest09() {
        // findFirst-返回第一个元素
        // Optional 是java 8 中新增的，为了防止空指针异常
        Optional<Employee> first = employees.parallelStream().findFirst();
        System.err.println(first.get());
        // findAny-返回当前流中的任意元素
        //Stream:一条线程去找，并行流parallelStream:几条线程同时去找，谁找到算谁的
        Optional<Employee> any = employees.stream().findAny();
        System.err.println(any.get());
        Optional<Employee> any1 = employees.parallelStream().findAny();
        System.err.println(any1.get());

        /**
         * Employee(name=张三, age=19, salary=2000)
         Employee(name=张三, age=19, salary=2000)
         Employee(name=张七, age=42, salary=5500)
         */
    }

    @Test
    public void streamTest10() {
        // count-返回流中总个数
        long count = employees.stream().count();
        System.err.println(count);
        long count1 = employees.parallelStream().count();
        System.err.println(count1);

    }


}
