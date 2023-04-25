package com.example.demo.Aop;

import com.example.demo.po.DogShow1;
import com.example.demo.po.TestAa;
import org.springframework.stereotype.Service;

@Service
public class Dog implements DogApi{


    @Override
    public TestAa show(TestAa a) {
        return new TestAa();
    }

    @Override
    public DogShow1 show1(String a) {
        return new DogShow1();
    }
}
