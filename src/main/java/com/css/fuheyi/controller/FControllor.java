package com.css.fuheyi.controller;

import com.css.fuheyi.Wapper.UserWapper;
import com.css.fuheyi.vo.User;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by fhy51 on 2018/8/10.e
 */
@RestController
@RequestMapping("/hii")
public class FControllor {
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @RequestMapping("/mysql/{id}")
    public List mysql(@PathVariable String id) {
        String sql="select * from user where id=?";
        List userlist=jdbcTemplate.queryForList(sql,id);
        System.out.println(userlist);
        User user=jdbcTemplate.queryForObject(sql,new UserWapper(),id);
        return userlist;
    }


    @RequestMapping("/jackson")
    public User jackson() throws IOException {
        String userJson="{\"name\":\"fuhey\",\"age\":\"17\",\"id\":\"110111198806030310\"}";
        String jsonInput ="[{\"id\": 2 , \"name\": \"xiandafu\"} ,{\"id\":3,\"name\":\"lucy\"}]";
        JavaType javaType= mapper.getTypeFactory().constructParametricType(List.class,User.class);

        User user=mapper.readValue(userJson, User.class);
        List userlist=mapper.readValue(jsonInput, javaType);
        System.out.println(userlist);
        return user;
    }

    @RequestMapping("/testAdd")
    public String test(){
        for(int i=0;i<=100; i++){
            stringRedisTemplate.opsForValue().set("name"+i,"fuhey"+i);
        }
        System.out.println("添加成功");
        return "添加成功";
    }


    @RequestMapping("/testDel")
    public String del(){
        StringBuffer stringBuffer=new StringBuffer();

        for(int i=0;i<=100; i++){
            String value=stringRedisTemplate.opsForValue().get("name"+i);
            stringBuffer.append("name"+i+":"+value+";   ");
        }
        System.out.println("删除成功");
        return stringBuffer.toString();
    }


    @RequestMapping("/testListAdd")
    public String list(){
        for(int i=0;i<=100; i++){
            stringRedisTemplate.opsForList().leftPush("list",i+"");
        }
        System.out.println("添加成功");
        return "添加列表成功";
    }


    @RequestMapping("/testView")
    public String view(){
        List rs= stringRedisTemplate.opsForList().range("list",0,-1);
        Set res= stringRedisTemplate.opsForZSet().range("score",0,-1);
        System.out.println("添加成功");
        return res.toString();
    }
}
