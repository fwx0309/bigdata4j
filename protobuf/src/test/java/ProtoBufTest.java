import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.study.myprotobuf.dto.CourseProto;
import com.study.myprotobuf.dto.StudentProto;

import java.util.Arrays;

/**
 * [ Comments ]
 *
 * @author : [ fwx ]
 * @version : [ v1.0 ]
 * @createTime : [ 2022/11/11 18:14 ]
 */
public class ProtoBufTest {
    public static void main(String[] args) {
        /**
         * 课程
         */
        CourseProto.course.Builder courseBuild = CourseProto.course.newBuilder();
        courseBuild.setName("语文");
        courseBuild.setScore(99);
        CourseProto.course course = courseBuild.build();

        /**
         * 学生
         */
        StudentProto.student.Builder studentBuild = StudentProto.student.newBuilder();
        studentBuild.setName("zhangsan");
        studentBuild.setAge(12);
        studentBuild.addCourse(0, course);
        StudentProto.student student = studentBuild.build();

        /**
         * 原始数据打印
         */
        System.out.println("********** 原始数据打印 **********");
        System.out.println(student);
        System.out.println();

        /**
         * 数据序列化
         */
        byte[] bytes = student.toByteArray();
        System.out.println("********** 数据序列化 **********");
        System.out.println(Arrays.toString(bytes));
        System.out.println();

        /**
         * 数据反序列化
         */
        try {
            StudentProto.student studentParse = StudentProto.student.parseFrom(bytes);

            System.out.println("********** 数据反序列化H **********");
            // 高版本 JsonFormat
            String studentJsonStrH = JsonFormat.printer().print(studentParse);
            System.out.println(studentJsonStrH);

            // 低版本 JsonFormat
            System.out.println("********** 数据反序列化L **********");
            com.googlecode.protobuf.format.JsonFormat jsonFormat = new com.googlecode.protobuf.format.JsonFormat();
            String studentJsonStrL = jsonFormat.printToString(studentParse);
            System.out.println(studentJsonStrL);

        } catch (InvalidProtocolBufferException e) {
            throw new RuntimeException(e);
        }
    }
}
