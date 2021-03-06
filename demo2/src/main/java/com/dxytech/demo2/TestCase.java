package com.dxytech.demo2;

import android.os.Environment;
import android.test.AndroidTestCase;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/7/13.
 */
public class TestCase extends AndroidTestCase {
    public void test(){
       // writeXmlToLocal();

        List<Person> personList = parserXmlFormLocal();
        for (Person person : personList){
            Log.i("TestCase", person.toString());
        }
    }
    //写xml文件到本地
    private void writeXmlToLocal(){
        List<Person> personList = getPersonList();

        //获得序列化对象
        XmlSerializer serializer = Xml.newSerializer();

        try {
            File path = new File(Environment.getExternalStorageDirectory(),"persons.xml");
            FileOutputStream fos = new FileOutputStream(path);
            //指定序列化输出的位置和编码
            serializer.setOutput(fos, "utf-8");

            serializer.startDocument("utf-8", true); //写开始的文件头<? xml version="1.0" encoding="utf-8" standlone="yes"?>

            serializer.startTag(null, "persons"); //<persons>

            for (Person person : personList){
                //开始写人

                serializer.startTag(null, "person"); //<person>
                serializer.attribute(null, "id", String.valueOf(person.getId()));

                //写名字
                serializer.startTag(null,"name"); //<name>
                serializer.text(person.getName());
                serializer.endTag(null, "name"); //</name>

                //写年龄
                serializer.startTag(null,"age"); //<age>
                serializer.text(String.valueOf(person.getAge()));
                serializer.endTag(null,"age"); //</age>

                serializer.endTag(null,"person"); //</person>
            }

            serializer.endTag(null,"persons"); //</persons>

            serializer.endDocument(); //文件结束

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Person> getPersonList(){

        List<Person> personList = new ArrayList<Person>();

        for (int i = 0; i<30; i++){
            personList.add(new Person(i,"wang" + i,18 + i));
        }
        return personList;
    }

    //解析xml文件
    private List<Person> parserXmlFormLocal(){

        try {
            File path = new File(Environment.getExternalStorageDirectory(),"persons.xml");
            FileInputStream fis = new FileInputStream(path);

            //获得pull解析器对象
            XmlPullParser parser = Xml.newPullParser();
            //指定解析的文件和编码格式
            parser.setInput(fis, "utf-8");

            int eventType = parser.getEventType(); // 获得事件类型

            List<Person> personList = null;
            Person person = null;
            String id;
            while (eventType != XmlPullParser.END_DOCUMENT){

                String tagName = parser.getName(); //获得当前节点的名称

                switch (eventType){
                    case XmlPullParser.START_TAG: //当前等于开始节点
                        if ("persons".equals(tagName)){           //<persons>
                            personList = new ArrayList<Person>();
                        }else if ("person".equals(tagName)){      //<person>
                            person = new Person();
                            id = parser.getAttributeValue(null,"id");
                            person.setId(Integer.valueOf(id));
                        }else if ("name".equals(tagName)){          //<name>
                            person.setName(parser.nextText());
                        }else if ("age".equals(tagName)){           //<age>
                            person.setAge(Integer.valueOf(parser.nextText()));
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if ("person".equals(tagName)){
                            //把上面设置好的person对象添加到集合中
                            personList.add(person);
                        }
                        break;
                }

                eventType = parser.next(); //获取下一个事件类型
            }
            return personList;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
