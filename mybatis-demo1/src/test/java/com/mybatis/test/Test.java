package com.mybatis.test;
import java.io.Reader;
import java.util.List;

import com.mybatis.dao.StudentsMapper;
import com.mybatis.model.Student;
import com.mybatis.model.Students;
import com.mybatis.model.StudentsExample;
import com.mybatis.model.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;

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
public class Test {
    private  SqlSessionFactory sqlSessionFactory;
    private  Reader reader;

    public void init()
    {
        try{
            reader = Resources.getResourceAsReader("Configuration.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public SqlSessionFactory getSession(){
        return sqlSessionFactory;
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.init();
        test.testStudentAuto();
//        SqlSession session = test.getSession().openSession();
//        try {
//            User user = (User) session.selectOne("com.mybatis.models.UserMapper.selectUserByID", 1);
//            System.out.println(user.getAge());
//            System.out.println(user.getName());
//        } finally {
//            session.close();
//        }
    }

    @BeforeClass
    public void initMybatis()
    {
        init();
        System.out.println("开始....");
    }
    @org.junit.Test
    public void testStudent()
    {
        init();
        SqlSession session = getSession().openSession();
        try {
            Student user =  session.selectOne("com.mybatis.mappers.StudentMapper.selectStudentByID", 1);
            System.out.println(user.getEmail());
            System.out.println(user.getName());
        } finally {
            session.close();
        }
    }

    public void testStudentAuto()
    {
        init();
        SqlSession session = getSession().openSession();
        StudentsMapper mapper = session.getMapper(StudentsMapper.class);
        StudentsExample example =  new StudentsExample();
        example.createCriteria().andStudIdBetween(1,5);
        List<Students>  studentses = mapper.selectByExample(example);
        System.out.println(studentses.size());
    }
}
