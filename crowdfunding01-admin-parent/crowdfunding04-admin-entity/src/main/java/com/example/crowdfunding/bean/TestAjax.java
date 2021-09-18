package com.example.crowdfunding.bean;

import java.util.List;
import java.util.Map;

/**
 * @author wanghu
 * @discrption：
 * @create 2021-09-16 15:57
 */
public class TestAjax {

    private Integer id;
    private String name;
    private Address address;
    private List<School> schools;
    private Map<String,String> map;

    public TestAjax() {
    }

    public TestAjax(Integer id, String name, Address address, List<School> schools, Map<String, String> map) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schools = schools;
        this.map = map;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<School> getSchools() {
        return schools;
    }

    public void setSchools(List<School> schools) {
        this.schools = schools;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "testAjax{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", schools=" + schools +
                ", map=" + map +
                '}';
    }
}
