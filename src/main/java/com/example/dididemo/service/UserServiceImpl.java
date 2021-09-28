package com.example.dididemo.service;



import com.example.dididemo.dao.UserDao;
import com.example.dididemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl{
    @Autowired
    private UserDao userDao;
    public int insertUser(String username,String password){
        return userDao.create(username,password);
    }
    public User findByName(String name){
        return userDao.findByName(name);
    }
    public User findByNameAndPassword(String username,String password){
        return userDao.findByNameAndPassword(username,password);
    }
  /*  @Override
    public String create(User u) {
        //jdbcTemplate.update("insert into user(username, password) values(?, ?)", username, password);
        if (userDao.save(u)!=null){
            return "login";
        }else {
            return "error";
        }
    }

    @Override
    public void deleteByName(String username) {
        jdbcTemplate.update("delete from user where username = ?", username);
    }

    @Override
    public Integer getAllUsers() {
        return jdbcTemplate.queryForObject("select count(1) from user",Integer.class);
    }
    @Override
    public void createName(String name){
        jdbcTemplate.execute("CREATE TABLE "+name+"(name varchar(30), id int, PRIMARY KEY(id))ENGINE=innodb DEFAULT CHARSET=utf8");
        //jdbcTemplate.update("alter table xxx rename to ?",name);
    }

    @Override
    public User findByName(String name) {
        return null;
    }

    @Override
    public User findByNameAndPassword(String name, String password) {
        return userDao.findByNameAndPassword(name,password);
    }*/


}
