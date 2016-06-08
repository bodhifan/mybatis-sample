package com.test;

import com.data.model.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;
import java.util.List;

/**
 * DESCRIPTION
 * TODO
 * <p>
 * NOTES
 * <other useful comments, qualifications, etc.>
 * <p>
 * MODIFIED    (MM/DD/YY)
 * bofan     2016/5/19 - Creation
 */
public class TestSample {
    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static{
        try{
            reader = Resources.getResourceAsReader("configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            Student students = session.selectOne("com.mybatis.mappers.StudentMapper.selectStudentByID",1);
            System.out.println(students);
        } finally {
            session.close();
        }
    }

    //@Test
//    public void insertTest()
//    {
//        SqlSession session = sqlSessionFactory.openSession();
//        try {
//            Student student = new Student();
//            student.setId(5);
//            student.setName("hello");
//            student.setBod("yayya");
//            student.setEmail("hello@gmail.com");
//            int i = session.insert("com.mybatis.mappers.StudentMapper.insertStudent",student);
//            System.out.println(i);
//
//        } finally {
//            session.close();
//        }
//    }
}
