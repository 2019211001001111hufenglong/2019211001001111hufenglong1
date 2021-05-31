package com.dao;



import com.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDao implements IUserDao {
    @Override
    public boolean saveUser(Connection con, User user) throws SQLException {
        user = null;
        String sql = "insert into usertable(username,password,email,gender,birthdate) values(?,?,?,?,?)";//插入语句
        PreparedStatement ps=null;
        ps=con.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getGender());
        ps.setDate(5, (java.sql.Date) user.getBirthdate());
        int n= ps.executeUpdate();
        System.out.println("插入成功");
        if (n>0) return true;
        else return false;
    }

    @Override
    public int deleteUser(Connection con, User user) throws SQLException {
        PreparedStatement ps=null;
        String sql = "delete from usertable where id=?";//根据ID删除数据
        ps=con.prepareStatement(sql);
        ps.setInt(1,user.getId());
        ps.executeUpdate();
        return 0;
    }

    @Override
    public int updateUser(Connection con, User user) throws SQLException {
        PreparedStatement ps=null;
        String sql = "update usertable set username=?,password=?,email=?,gender=?,birthdate=? where id=?";//更新操作
        ps=con.prepareStatement(sql);
        ps.setString(1,user.getUsername());
        ps.setString(2,user.getPassword());
        ps.setString(3,user.getEmail());
        ps.setString(4,user.getGender());
        ps.setDate(5, (java.sql.Date) user.getBirthdate());
        ps.setInt(6,user.getId());
        return ps.executeUpdate();
    }


    @Override
    public User findById(Connection con, Integer id) throws SQLException {
        String search = "select * from usertable where id=?";//根据id查找
        PreparedStatement ps=null;
        ps=con.prepareStatement(search);
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        User user1 =new User();
        if(rs.next()){
            user1.setId(rs.getInt("id"));
            user1.setUsername(rs.getString("username"));
            user1.setPassword(rs.getString("password"));
            user1.setEmail(rs.getString("email"));
            user1.setGender(rs.getString("gender"));
            user1.setBirthdate(rs.getDate("birthdate"));
        }

        return user1;
    }

    @Override
    public User findByUsernamePassword(Connection con, String username, String password) throws SQLException {
        String sql = "select id,username,password,email,gender,birthdate from usertable where username=? and password=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);//为上面的sql语句的username 和password 赋值
        st.setString(2,password);
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
        }
        return user;
    }

    @Override
    public List<User> findByUsername(Connection con, String username) throws SQLException {
        String sql = "select id,username,password,email,gender,birthdate from usertable where username=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,username);//根据username查找数据
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;
    }

    @Override
    public List<User> findByPassword(Connection con, String password) throws SQLException {
        String sql = "select id,username,password,email,gender,birthdate from usertable where password=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,password);//根据password查找数据
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;


    }

    @Override
    public List<User> findByEmail(Connection con, String email) throws SQLException {

        String sql = "select id,username,password,email,gender,birthdate from usertable where email=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,email);//根据email查找数据
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;
    }

    @Override
    public List<User> findByGender(Connection con, String gender) throws SQLException {
        String sql = "select id,username,password,email,gender,birthdate from usertable where gender=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setString(1,gender);//根据gender查找数据
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;
    }

    @Override
    public List<User> findByBirthdate(Connection con, Date birthDate) throws SQLException {
        String sql = "select id,username,password,email,gender,birthdate from usertable where birthDate=?";
        PreparedStatement st = con.prepareStatement(sql);
        st.setDate(1, (java.sql.Date) birthDate);//根据出生日期查找
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = st.executeQuery();
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;
    }

    @Override
    public List<User> findAllUser(Connection con) throws SQLException {
        String sql = "select * from usertable";

        //查找数据库usertable表里所有数据
        ArrayList<User> list = new ArrayList<User>();
        ResultSet rs = con.createStatement().executeQuery(sql);
        User user =null;
        if (rs.next()){
            user=new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setEmail(rs.getString("email"));
            user.setGender(rs.getString("gender"));
            user.setBirthdate(rs.getDate("birthdate"));
            list.add(user);
        }

        return list;


    }
}
